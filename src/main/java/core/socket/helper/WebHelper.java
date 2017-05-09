package core.socket.helper;

import org.jooq.tools.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ibnujakaria on 09/05/17.
 */
public class WebHelper {
    public static boolean isPostMethod (List<String> inputs) {
        return inputs.get(0).toUpperCase().startsWith("POST");
    }

    public static String getPostData (BufferedReader in) throws IOException {
        char[] data = new char[1000]; // dinah, anggep 1000 karakter maksimal
        int chars = in.read(data);

        return new String(data, 0, chars);
    }

    public static BufferedReader getReader (Socket socket) throws IOException {
        return new BufferedReader(
                new InputStreamReader(socket.getInputStream())
        );
    }

    public static PrintWriter getWriter (Socket socket) throws IOException {
        return new PrintWriter(socket.getOutputStream(), true);
    }


    public static void printJSON(PrintWriter writer, String serverName, String body) {
        writer.println(
                "HTTP/1.1 200 OK\r\n" +
                "Server: " + serverName + "\r\n" +
                "Content-Type: application/json\r\n\r\n" +
                body
        );
    }

    public static List<String> getHeaders(Socket socket) {
        try {
            BufferedReader reader = getReader(socket);
            return getHeaders(socket, reader);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<String> getHeaders(Socket socket, BufferedReader reader) {
        List<String> headers = new ArrayList<String>();
        String line;
        try {
            while ((line = reader.readLine()) != null && !line.isEmpty()) {
                headers.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return headers;
    }

    public static List<String> getHeaders(List<String> output) {
        List<String> headers = new ArrayList<String>();

        for (String header: output) {
            if (!header.isEmpty()) {
                headers.add(header);
            } else {
                break;
            }
        }

        return headers;
    }

    public static List<String> getPaths (List<String> headers) {
        List<String> paths = new ArrayList<String>();

        for (String path : headers.get(0).split(" ")[1].split("/")) {
            paths.add(path);
        }

        return paths;
    }

    public static String getPath (List<String> headers) {
        return headers.get(0).split(" ")[1];
    }

    public static void print404(PrintWriter writer, String serverName) {
        JSONObject body = new JSONObject();
        body.put("message", "Page not found.");
        writer.println(
                "HTTP/1.1 404 Not Found\r\n" +
                        "Server: " + serverName + "\r\n" +
                        "Content-Type: application/json\r\n\r\n" +
                        body.toString()
        );
    }

    public static void printRequestHeaders(PrintWriter writer, String host, int port) {
        writer.println(
                "GET / HTTP/1.1\r\n" +
                        "Host: " + host + ":" + port + "\r\n"
        );
    }

    public static String getBody(Socket socket) {
        try {
            System.out.println("getbody");
            BufferedReader reader = getReader(socket);
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "tes";
    }

    public static List<String> getOutput (BufferedReader reader) {
        List<String> result = new ArrayList<String>();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                result.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static List<String> getBody (List<String> output) {
        List<String> body = new ArrayList<String>();
        boolean isBalaghoIlaNull = false;

        for (String line: output) {
            if (line.isEmpty() && !isBalaghoIlaNull) {
                isBalaghoIlaNull = true;
            } else if (isBalaghoIlaNull) {
                body.add(line);
            }
        }

        return body;
    }
}
