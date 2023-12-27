package org.group05.police.chat;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@ServerEndpoint(value = "/Room/{username}")
public class WsBytomcate {
    private static List<Session> sessions = new ArrayList<>();

    @OnOpen
    public void onOpen(Session session, @PathParam(value = "username") String username) {
        sessions.add(session);
        sendTextMsg("好友 [" + username + "] 加入群聊");
        System.out.println("ok");
    }

    @OnMessage
    public void onMsg(String msg, @PathParam(value = "username") String username) {
        sendTextMsg(username + ":" + msg);
    }

    @OnClose
    public void onClose(Session session, @PathParam("username") String username) throws IOException {
        sessions.remove(session);
        sendTextMsg("好友 [" + username + "] 退出群聊");
    }

    @OnError
    public void onError(Throwable e) {
        e.printStackTrace();
    }

    private void sendTextMsg(String msg) {
        for (Session session : sessions) {
            session.getAsyncRemote().sendText(msg);
        }
    }
}
