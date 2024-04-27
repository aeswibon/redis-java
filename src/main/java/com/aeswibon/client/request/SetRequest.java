package com.aeswibon.client.request;

import com.aeswibon.client.cache.Cache;
import com.aeswibon.client.response.EchoResponse;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.List;

public class SetRequest implements Request {
    @Override
    public String getName() {
        return "SET";
    }

    @Override
    public boolean canHandle(List<String> commands) {
        return commands.size() >= 3 && commands.getFirst().equalsIgnoreCase(getName());
    }

    @Override
    public void handle(List<String> commands, OutputStream outputStream) throws IOException {
        if (!commands.getFirst().equalsIgnoreCase(getName())) {
            throw new IOException("Invalid command");
        }
        if (commands.size() == 3) {
            Cache.set(commands.get(1), commands.get(2));
        } else {
            Cache.set(commands.get(1), commands.get(2), Long.parseLong(commands.get(4)));
        }
        String response = new EchoResponse().handle("OK");
        System.out.println("SET response: " + response);
        outputStream.write(response.getBytes(Charset.defaultCharset()));
    }
}
