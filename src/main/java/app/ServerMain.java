package app;

import core.socket.server.Server;

/**
 * Created by ibnujakaria on 09/05/17.
 */
public class ServerMain {

    public static void main(String[] args) {
        Server server = new Server();
        server.listen();
    }
}
