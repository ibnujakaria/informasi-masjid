package core.socket.client.api;

import core.json.reader.QuestionJSONReader;
import core.socket.client.Client;
import core.socket.client.ClientOnSuccess;
import core.socket.client.CoreClient;
import org.jooq.Record;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ibnujakaria on 09/05/17.
 */
public class QuestionSocketApi {

    public static void getAnsweredQuestion (ClientOnSuccess onSuccess) {
        CoreClient client = new CoreClient("localhost", 1234, onSuccess);
        client.connect();
    }
}
