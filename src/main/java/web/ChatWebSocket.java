package web;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
@ClientEndpoint
public class ChatWebSocket extends Endpoint {
    private static Set<Session> sessions = new HashSet<>();

    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
        sessions.add(session);
    }

    @OnMessage
    public void onMessage(Session session, String message) {
        // Traitez le message reçu (si nécessaire)
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        sessions.remove(session);
    }

    public static void sendMessage(String message, Long conversationId, Long userId) {
        String targetEndpointURL = "ws://localhost:8080/TPCHAT/websocket/" + conversationId + "/" + userId;
        sessions.stream()
                .filter(s -> s.getRequestURI().toString().equals(targetEndpointURL))
                .forEach(s -> {
                    try {
                        s.getBasicRemote().sendText(message);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }
}
