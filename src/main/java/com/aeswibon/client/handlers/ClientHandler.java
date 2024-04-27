package com.aeswibon.client.handlers;

import com.aeswibon.client.CommandParser;

import java.io.IOException;
import java.net.Socket;

public class ClientHandler extends Thread {
    private final Socket client;
    private final CommandParser parser;
    private final RequestHandler requestHandler;

    public ClientHandler(Socket client) {
        this.client = client;
        this.parser = new CommandParser();
        this.requestHandler = new RequestHandler();
    }

    @Override
    public void run() {
        while (true) {
            try {
                requestHandler.generateResponse(parser.parse(client.getInputStream()), client.getOutputStream());
            } catch (IOException e) {
                System.err.println("Error: " + e.getMessage());
                break;
            }
        }
    }
}
