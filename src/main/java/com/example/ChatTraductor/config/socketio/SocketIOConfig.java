package com.example.ChatTraductor.config.socketio;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.example.ChatTraductor.enums.MessageType;
import com.example.ChatTraductor.model.service.MessageDTO;
import com.example.ChatTraductor.model.service.UserDTO;
import com.example.ChatTraductor.model.socket.MessageFromClient;
import com.example.ChatTraductor.model.socket.MessageFromServer;
import com.example.ChatTraductor.security.configuration.JwtTokenUtil;
import com.example.ChatTraductor.security.service.IUserService;
import com.example.ChatTraductor.service.IMessageService;

import io.netty.handler.codec.http.HttpHeaders;
import jakarta.annotation.PreDestroy;

@Configuration
public class SocketIOConfig {

	@Autowired
	JwtTokenUtil jwtUtil;

	@Autowired
	IUserService userService;

	@Autowired
	IMessageService messageService;

	@Value("${socket-server.host}")
	private String host;

	@Value("${socket-server.port}")
	private Integer socketServerPort;
	@Value("${server.port}")
	private Integer webServerPort;
	@Value("${localazy.token}")
	private String apiToken;

	private SocketIOServer server;

	public final static String CLIENT_USER_NAME_PARAM = "sendername";
	public final static String CLIENT_USER_ID_PARAM = "senderid";
	public final static String AUTHORIZATION_HEADER = "Authorization";

	@Bean
	public SocketIOServer socketIOServer() {
		com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
		config.setHostname(host);
		config.setPort(socketServerPort);

		// vamos a permitir a una web que no este en el mismo host y port conectarse. Si
		// no da error de Cross Domain
		config.setAllowHeaders("Authorization");
		config.setOrigin("http://localhost:" + webServerPort);
		config.setMaxFramePayloadLength(1024 * 1024); // 1 MB in bytes

		server = new SocketIOServer(config);

		server.addConnectListener(new MyConnectListener(server));
		server.addDisconnectListener(new MyDisconnectListener());
		server.addEventListener(SocketEvents.ON_MESSAGE_RECEIVED.value, MessageFromClient.class, onSendMessage());
		server.start();
		return server;
	}

	// SINO QUITO EL STATIC ME DICE QUE JWT ES NULO
	private class MyConnectListener implements ConnectListener {

		private SocketIOServer server;

		MyConnectListener(SocketIOServer server) {
			this.server = server;
		}

		@Override
		public void onConnect(SocketIOClient client) {

			if (!isAllowedToConnect(client)) {
				// FUERA
				System.out.println("Nuevo cliente no permitida la conexion: " + client.getSessionId());
				client.disconnect();
			} else {
				HttpHeaders headers = client.getHandshakeData().getHttpHeaders();
				loadClientData(headers, client);
				System.out.printf("Nuevo cliente conectado: %s . Clientes conectados ahora mismo: %d \n",
						client.getSessionId(), this.server.getAllClients().size());
				// aqui incluso se podria notificar a todos o a salas de que se ha conectado...
				// server.getBroadcastOperations().sendEvent("chat message", messageFromServer);
			}
		}

		// COMPROBAMOS SI EL CLIENTE CUMPLE LOS REQUISITOS PARA CONECTARSE
		private boolean isAllowedToConnect(SocketIOClient client) {

			HttpHeaders headers = client.getHandshakeData().getHttpHeaders();

			String senderization = headers.get(AUTHORIZATION_HEADER);
			String token = senderization.split(" ")[1].trim();

			boolean hassenderizationHeader = headers.get(AUTHORIZATION_HEADER) != null;

			boolean isTokenValid = jwtUtil.validateAccessToken(token);

			return hassenderizationHeader && isTokenValid;
		}

		private void loadClientData(HttpHeaders headers, SocketIOClient client) {

			try {
				String senderization = headers.get(AUTHORIZATION_HEADER);
				String token = senderization.split(" ")[1].trim();

				Integer userId = jwtUtil.getUserId(token);

				UserDTO userDTO = userService.findById(userId);
				String senderId = userDTO.getId().toString();
				String senderName = userDTO.getName();

				client.set(CLIENT_USER_ID_PARAM, senderId);
				client.set(CLIENT_USER_NAME_PARAM, senderName);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private class MyDisconnectListener implements DisconnectListener {
		@Override
		public void onDisconnect(SocketIOClient client) {
			client.getNamespace().getAllClients().stream().forEach(data -> {
				System.out.println("user disconnected " + data.getSessionId().toString());
				// FIXME
				// notificateDisconnectToUsers(client);
			});
			System.out.printf("Cliente restantes: %s . Clientes conectados ahora mismo: %d \n", client.getSessionId(),
					server.getAllClients().size());
		}

		// podemos notificar a los demas usuarios que ha salido. Ojo por que el
		// broadcast envia a todos
		private void notificateDisconnectToUsers(SocketIOClient client) {

			String message = "el usuario se ha desconectado salido";
			String senderIdS = client.get(CLIENT_USER_ID_PARAM);
			Integer senderId = Integer.valueOf(senderIdS);
			String senderName = client.get(CLIENT_USER_NAME_PARAM);

			MessageFromServer messageFromServer = new MessageFromServer(MessageType.SERVER, null, message, senderName,
					senderId);
			client.getNamespace().getBroadcastOperations().sendEvent(SocketEvents.ON_SEND_MESSAGE.value,
					messageFromServer);
		}
	}

	private DataListener<MessageFromClient> onSendMessage() {
		return (senderClient, data, acknowledge) -> {

			String senderIdS = senderClient.get(CLIENT_USER_ID_PARAM);
			Integer senderId = Integer.valueOf(senderIdS);
			String senderName = senderClient.get(CLIENT_USER_NAME_PARAM);

			System.out.printf("Mensaje recibido de (%d) %s. El mensaje es el siguiente: %s \n", senderId, senderName,
					data.toString());

			if (data.getMessage() == null || data.getMessage().trim().isEmpty()) {
				MessageFromServer errorMessage = new MessageFromServer(MessageType.SERVER, null,
						"No puedes enviar un mensaje vacio", "Server", 0);

				System.out.printf("Mensaje reenviado al usuario" + errorMessage);
				senderClient.sendEvent(SocketEvents.ON_MESSAGE_NOT_SENT.value, errorMessage);
				return;
			}

			MessageFromServer message = new MessageFromServer(MessageType.CLIENT, null, data.getMessage(), senderName,
					senderId);

			MessageDTO createdMessage;

			MessageDTO messageDTO = new MessageDTO(null, data.getMessage(), senderId, data.getReceiverId());

			createdMessage = messageService.createMessage(messageDTO);

			// enviamos a la room correspondiente:
			System.out.printf("Mensaje enviado a la room" + message);
			System.out.println(message.getMessage());
			SocketIOClient receiverClient = findClientByUserId(data.getReceiverId());

			String translatedMessage = messageService.translateMessage(createdMessage.getText(), senderId,
					data.getReceiverId());
			createdMessage.setText(translatedMessage);
			if (createdMessage != null) {
				message.setId(createdMessage.getId());
			}
			if (receiverClient != null) {
				receiverClient.sendEvent(SocketEvents.ON_SEND_MESSAGE.value, message);
			}

			// TODO esto es para mandar a todos los clientes. No para mandar a los de una
			// Room
			// senderClient.getNamespace().getBroadcastOperations().sendEvent("chat
			// message", message);

			// esto puede que veamos mas adelante
			// acknowledge.sendAckData("El mensaje se envio al destinatario
			// satisfactoriamente");
		};
	}

	private boolean checkIfSendCanSendToRoom(SocketIOClient senderClient, String room) {
		if (senderClient.getAllRooms().contains(room)) {
			System.out.println("SI tiene permiso para enviar mensaje en la room");
			return true;
		} else {
			System.out.println("NO tiene permiso para enviar mensaje en la room");
			return false;
		}
	}

	@PreDestroy
	public void stopSocketIOServer() {
		this.server.stop();
	}

	private SocketIOClient findClientByUserId(Integer idUser) {
		SocketIOClient response = null;

		Collection<SocketIOClient> clients = server.getAllClients();
		for (SocketIOClient client : clients) {
			Integer currentClientId = Integer.valueOf(client.get(SocketIOConfig.CLIENT_USER_ID_PARAM));
			if (currentClientId == idUser) {
				response = client;
				break;
			}
		}
		return response;
	}
}