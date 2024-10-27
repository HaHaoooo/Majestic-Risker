package com.haha.rpg.util;

import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JsonHelper {
    public static void write(JSONObject jsonObject, String filepath) {
        try {
            Path path = Paths.get(filepath);
            Files.createDirectories(path.getParent());
            try (FileWriter writer = new FileWriter(path.toFile())) {
                writer.write(jsonObject.toString(2));
            }
        } catch (IOException e) {
            System.err.println("Error writing JSON to " + filepath + ": " + e.getMessage());
        }
    }


    public static JSONObject read(String filepath) {
        Path path = Paths.get(filepath);
        if (Files.exists(path)) {
            try {
                String content = Files.readString(path);
                return new JSONObject(content);
            } catch (IOException e) {
                System.err.println("Error reading JSON from " + filepath + ": " + e.getMessage());
            }
        } else {
            System.err.println("File not found: " + filepath);
        }
        return null;
    }
}