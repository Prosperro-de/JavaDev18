package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DemoServer {
    private static ExecutorService pool;
    private static final String END_OF_MESSAGE_MARK = System.lineSeparator();

    public static void main(String[] args) {
        final int MAX_CONNECTIONS = 100;
        final int PORT = 9999;

        startServer(MAX_CONNECTIONS, PORT);
    }

    private static void startServer(int maxConnections, int port) {
        pool = Executors.newFixedThreadPool(maxConnections);

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started on port: " + port);

            while (true) {
                listenForClients(serverSocket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void listenForClients(ServerSocket serverSocket) {
        try {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected");

            pool.submit(() -> handleClient(clientSocket));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleClient(Socket clientSocket) {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                OutputStream out = clientSocket.getOutputStream()
        ) {
            String receivedMessage = in.readLine();
            if (receivedMessage != null) {
                System.out.println("Client request message: " + receivedMessage);

                //TODO: parse path code from receivedMessage
//                String htmlResponse = getHTMLResponse();

                byte[] imageByteArray = HttpClientDemo.getAllBytesForImage("http.cat", "/418");

                String serverResponse = "HTTP/1.1 200 OK" + END_OF_MESSAGE_MARK +
                                        "Content-Type: image/jpeg" + END_OF_MESSAGE_MARK +
//                                        "Content-Length: " + htmlResponse.length() + END_OF_MESSAGE_MARK +
                                        "Content-Length: " + imageByteArray.length + END_OF_MESSAGE_MARK +
                                        END_OF_MESSAGE_MARK;
                out.write(serverResponse.getBytes());
                out.write(imageByteArray);
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static String getHTMLResponse() {
        return """
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <title>Title</title>
                </head>
                <body>
                <h1>Hello from my first web server</h1>
                </body>
                </html>
                """;
    }
}
