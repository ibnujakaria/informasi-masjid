package core.socket.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by ibnujakaria on 09/05/17.
 */
public abstract class CoreServer {
    private int port;

    public CoreServer (int port) {
        this.port = port;
    }

    public void listen () {
        System.out.println("Server listening to port: " + port);
        try (ServerSocket listener = new ServerSocket(port)) {

            Socket socket = null;
            while (true) {
                socket = listener.accept();
                new Thread(new ConnectionHandler(socket)).start();
            }

        } catch (Exception e) {
            System.err.println("IOException: " + e);
            e.printStackTrace();
        }
    }

    protected abstract void handleConnection (Socket socket) throws IOException;

    public int getPort () {
        return port;
    }

    private class ConnectionHandler implements Runnable {
        private Socket socket;

        public ConnectionHandler (Socket socket) {
            this.socket = socket;
            System.out.println("ConnectionHandler() instance created.");
        }

        public void run () {
            try {
                System.out.println("Handle connection thread started");
                handleConnection(socket);
                System.out.println("Handle connection thread finished");
            } catch (Exception e) {
                System.err.println("IOException: " + e);
                e.printStackTrace();
            }
        }
    }
}
