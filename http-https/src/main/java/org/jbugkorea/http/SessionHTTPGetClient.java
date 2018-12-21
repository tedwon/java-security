package org.jbugkorea.http;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class SessionHTTPGetClient {

    public static void sendRequest(int port) throws Exception {

        String host = "127.0.0.1";
        InetAddress addr = InetAddress.getByName(host);
        Socket socket = new Socket(addr, port);
        boolean autoflush = true;
        PrintWriter out = new PrintWriter(socket.getOutputStream(), autoflush);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));

        // send an HTTP request "HEADER" to the web server
        out.println("GET /session/?stateful HTTP/1.1");
        out.println("Content-Type: text/html;charset=UTF-8");
        out.println("Accept: text/html");
        out.println("Accept-Language: ko-KR");
        out.println("Host: localhost:8080");
        out.println("User-Agent: PostmanRuntime/7.1.1");
        out.println("Connection: Close");
        out.println("cache-control: no-cache");
        out.println("Cookie: JSESSIONID=Nq9oF95YclkMmRzC2goAVqTOS0yXvPtrzgfyXa-C.myservera");
        out.println();
        out.flush();

        // read the response
        int c;
        while ((c = in.read()) != -1) {
            System.out.write(c);
        }
        System.out.flush();

        in.close();
        out.close();
        socket.close();
    }

    public static void main(String[] args) throws Exception {
        SessionHTTPGetClient.sendRequest(8080);
    }
}
