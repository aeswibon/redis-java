package com.aeswibon.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CommandParser {
    private int readInteger(BufferedReader reader) throws IOException {
        StringBuilder builder = new StringBuilder();
        char current = (char) reader.read();
        while (current != '\r') {
            builder.append(current);
            current = (char) reader.read();
        }
        reader.read();
        return Integer.parseInt(builder.toString());
    }

    private String readString(BufferedReader reader) throws IOException {
        StringBuilder builder = new StringBuilder();
        char current = (char) reader.read();
        while (current != '\r') {
            builder.append(current);
            current = (char) reader.read();
        }
        reader.read();
        return builder.toString();
    }

    public List<String> parse(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        List<String> redisCommand = new ArrayList<>();
        reader.read();
        int totalRows = readInteger(reader);
        for (int i = 0; i < totalRows; i++) {
            reader.read();
            readInteger(reader);
            String row = readString(reader);
            redisCommand.add(row);
        }
        System.out.println("Redis command: " + String.join(" ", redisCommand));
        return redisCommand;
    }
}
