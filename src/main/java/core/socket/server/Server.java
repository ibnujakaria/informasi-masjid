package core.socket.server;

import app.Main;
import core.json.generator.QuestionJSONGenerator;
import core.socket.helper.WebHelper;
import database.DB;
import database.models.jooq.Question;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

/**
 * Created by ibnujakaria on 09/05/17.
 */
public class Server extends CoreServer {

    public Server () {
        super (1234);
        bootApp();
    }

    private void bootApp() {
        Main.readProperties();
        connectDatabase();
    }

    private void connectDatabase() {
        DB.start();
    }

    @Override
    protected void handleConnection(Socket socket) throws IOException {
        List<String> headers = WebHelper.getHeaders(socket);

        System.out.println(WebHelper.getPath(headers));

        PrintWriter writer = WebHelper.getWriter(socket);
        String body = QuestionJSONGenerator.generateArray(Question.getAnsweredQuestion()).toString();
        WebHelper.printJSON(writer, "ibnujakaria", body);

//        if (WebHelper.getPath(headers).equals("/questions/answered")) {
//
//        } else {
//            WebHelper.print404(writer, "ibnujakaria");
//        }

        socket.close();
    }
}
