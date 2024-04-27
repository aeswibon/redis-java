package com.aeswibon.client.request;

import com.aeswibon.client.response.EchoResponse;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.List;

public class PingRequest implements Request {
    @Override
    public String getName() {
        return "PING";
    }

    @Override
    public boolean canHandle(List<String> commands) {
        return commands.size() == 1 && commands.getFirst().equalsIgnoreCase(getName());
    }

    @Override
    public void handle(List<String> commands, OutputStream outputStream) throws IOException {
        if (!commands.getFirst().equalsIgnoreCase(getName())) {
            throw new IOException("Invalid command");
        }
        String response = new EchoResponse().handle("PONG");
        System.out.println("PING response: " + response);
        outputStream.write(response.getBytes(Charset.defaultCharset()));
    }
}
