package core.socket.client;

import java.net.Socket;
import java.util.List;

/**
 * Created by ibnujakaria on 09/05/17.
 */
public abstract class ClientOnSuccess {
    public abstract void doWhatYouWant(Socket socket, List<String> headers, List<String> body);
}
