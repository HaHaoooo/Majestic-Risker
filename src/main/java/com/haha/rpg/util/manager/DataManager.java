package com.haha.rpg.util.manager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class DataManager {
    private JSONObject data;
    private final File dataFile;

    public DataManager(String filePath) throws IOException {
        this.dataFile = new File(filePath);
        loadData();
    }

    private void loadData() throws IOException {
        if (dataFile.exists()) {
            try (InputStream is = new FileInputStream(dataFile);
                 BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                data = new JSONObject(sb.toString());
            }
        } else {
            data = new JSONObject();
        }
    }

    public void saveData() throws IOException {
        try (OutputStream os = new FileOutputStream(dataFile); BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8))) {
            writer.write(data.toString(4)); // Indent with 4 spaces for readability
        }
    }

    // Method to remove data using a path
    public void removeData(String... keys) {
        JSONObject current = data;
        for (int i = 0; i < keys.length - 1; i++) {
            Object obj = current.opt(keys[i]);
            if (obj instanceof JSONObject) {
                current = (JSONObject) obj;
            } else {
                // Key path does not exist
                return;
            }
        }
        current.remove(keys[keys.length - 1]);
    }

    // Additional utility methods
    public void incrementData(Number amount, String... keys) {
        Object value = getData(keys);
        if (value instanceof Number number) {
            setData(number.doubleValue() + amount.doubleValue(), keys);
        } else {
            throw new IllegalArgumentException("Value at the specified path is not a Number");
        }
    }

    public JSONObject getDataObject() {
        return data;
    }

    public Object getData(String... keys) {
        Object current = data;
        for (String key : keys) {
            if (current instanceof JSONObject) {
                current = ((JSONObject) current).opt(key);
            } else if (current instanceof JSONArray) {
                try {
                    int index = Integer.parseInt(key);
                    current = ((JSONArray) current).opt(index);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Expected an integer index for JSONArray");
                }
            } else {
                return null;
            }
            if (current == null) {
                return null;
            }
        }
        return current;
    }

    public void setData(Object value, String... keys) {
        Object current = data;
        for (int i = 0; i < keys.length - 1; i++) {
            String key = keys[i];
            Object next;
            if (current instanceof JSONObject) {
                next = ((JSONObject) current).opt(key);
                if (next == null) {
                    // Decide whether to create a JSONObject or JSONArray
                    if (isNextKeyAnInteger(keys[i + 1])) {
                        next = new JSONArray();
                    } else {
                        next = new JSONObject();
                    }
                    ((JSONObject) current).put(key, next);
                }
            } else if (current instanceof JSONArray array) {
                int index = Integer.parseInt(key);
                while (array.length() <= index) {
                    array.put(new JSONObject());
                }
                next = array.get(index);
            } else {
                throw new IllegalArgumentException("Invalid path");
            }
            current = next;
        }
        String lastKey = keys[keys.length - 1];
        if (current instanceof JSONObject) {
            ((JSONObject) current).put(lastKey, value);
        } else if (current instanceof JSONArray array) {
            int index = Integer.parseInt(lastKey);
            while (array.length() <= index) {
                array.put(JSONObject.NULL);
            }
            array.put(index, value);
        } else {
            throw new IllegalArgumentException("Invalid path");
        }
    }

    private boolean isNextKeyAnInteger(String key) {
        try {
            Integer.parseInt(key);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}