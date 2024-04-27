package com.aeswibon.client.request;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public interface Request {
    boolean canHandle(List<String> commands);

    String getName();

    void handle(List<String> commands, OutputStream outputStream) throws IOException;
}
