package org.example;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class HttpClientDemo {
    public static byte[] getAllBytesForImage(String host, String path) throws URISyntaxException, IOException {
            URI uri = new URI("https", host, path, null);
            URL url = uri.toURL();

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();


            int responseCode = connection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Status code = " + responseCode);
            }

            BufferedInputStream bufferedInputStream = new BufferedInputStream(connection.getInputStream());
            return bufferedInputStream.readAllBytes();


    }

}
