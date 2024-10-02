package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class DemoClient {
    private static final String END_OF_MESSAGE_MARK = "\n";

    public static void main(String[] args) {
        final int PORT = 9999;

        try (
                Socket socket = new Socket("localhost", PORT);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                OutputStream out = socket.getOutputStream()
        ) {
            System.out.println("Підключено до сервера за адресою: http://localhost:" + PORT);

            String messageToSend = "Java" + END_OF_MESSAGE_MARK;
            out.write(messageToSend.getBytes());
            out.flush();

            String response = in.readLine();
            System.out.println("Відповідь від сервера: " + response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
