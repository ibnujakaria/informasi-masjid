package core.socket.client;

import core.socket.helper.WebHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

/**
 * Created by ibnujakaria on 09/05/17.
 */
public class CoreClient {
    private String host;
    private int port;
    private Socket socket;
    private ClientOnSuccess onSuccess;
    private List<String> output;
    private BufferedReader reader;
    private PrintWriter writer;

    public CoreClient(String host, int port, ClientOnSuccess onSuccess) {
        this.host = host;
        this.port = port;
        this.onSuccess = onSuccess;
    }

    public void connect () {
        System.out.println("function connect() called");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    socket = new Socket(host, port);
                    writer = WebHelper.getWriter(socket);
                    WebHelper.printRequestHeaders(writer,host, port);

                    reader = WebHelper.getReader(socket);
                    output = WebHelper.getOutput(reader);

                    onSuccess.doWhatYouWant(socket, WebHelper.getHeaders(output), WebHelper.getBody(output));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public Socket getSocket() {
        return socket;
    }

    public String getBody() {
        return WebHelper.getBody(socket);
    }

}
