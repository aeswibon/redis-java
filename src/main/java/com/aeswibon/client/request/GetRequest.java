package com.aeswibon.client.request;

import com.aeswibon.client.cache.Cache;
import com.aeswibon.client.response.EchoResponse;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.List;

public class GetRequest implements Request {
    @Override
    public String getName() {
        return "GET";
    }

    @Override
    public boolean canHandle(List<String> commands) {
        return commands.size() == 2 && commands.getFirst().equalsIgnoreCase(getName());
    }

    @Override
    public void handle(List<String> commands, OutputStream outputStream) throws IOException {
        if (!commands.getFirst().equalsIgnoreCase(getName())) {
            throw new IOException("Invalid command");
        }
        String key = commands.get(1);
        String value = Cache.get(key);
        String response = new EchoResponse().handle(value);
        System.out.println("GET response: " + response);
        outputStream.write(response.getBytes(Charset.defaultCharset()));
    }
}
