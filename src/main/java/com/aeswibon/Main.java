package com.aeswibon;

import com.aeswibon.client.Constant;
import com.aeswibon.client.handlers.ClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private static final ExecutorService executor = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(Constant.port)) {
            server.setReuseAddress(true);
            System.out.println("Server started on port " + Constant.port);
            while (true) {
                try {
                    Socket client = server.accept();
                    System.out.println("New client connected: " + client.getInetAddress().getHostAddress());
                    executor.submit(new ClientHandler(client));
                } catch (IOException e) {
                    System.out.println("Error accepting connection: " + e.getMessage());
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }
}
