package com.aeswibon.client.handlers;

import com.aeswibon.client.request.*;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class RequestHandler {
    private final List<Request> handlers = new ArrayList<>();

    public RequestHandler() {
        this.handlers.add(new PingRequest());
        this.handlers.add(new EchoRequest());
        this.handlers.add(new GetRequest());
        this.handlers.add(new SetRequest());
    }

    public void generateResponse(List<String> commands, OutputStream outputStream) throws IOException {
        for (Request handler : handlers) {
            if (handler.canHandle(commands)) {
                System.out.println("Calling handler: " + handler.getName());
                handler.handle(commands, outputStream);
            }
        }
    }
}
