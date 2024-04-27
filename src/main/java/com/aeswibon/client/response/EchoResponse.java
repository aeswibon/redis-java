package com.aeswibon.client.response;

public class EchoResponse implements Response {
    @Override
    public String handle(String command) {
        if (command == null) {
            return "$-1\r\n";
        }
        return "$" + command.length() + "\r\n" + command + "\r\n";
    }
}
