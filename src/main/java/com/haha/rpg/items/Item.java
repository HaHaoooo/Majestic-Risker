package com.haha.rpg.items;

import com.haha.rpg.gui.Camera;
import com.haha.rpg.util.JsonHelper;
import com.haha.rpg.util.ResourceLocation;
import org.json.JSONObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Item {
    private BufferedImage image;
    private String fileLocation;
    private int x, y;
    private final String name;
    private final String description;
    private final JSONObject jsonObject;
    private boolean pickedUp = false;

    public Item(String fileLocation, int x, int y) {
        try {
            this.image = ImageIO.read(new File(fileLocation));
            this.fileLocation = fileLocation;
            this.x = x;
            this.y = y;
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.jsonObject = JsonHelper.read(ResourceLocation.getLocation("infos/" + getFilename().replace("png", "json")));
        assert jsonObject != null;
        this.name = jsonObject.getString("name");
        this.description = jsonObject.getString("description");
    }

    public void render(Graphics g, Camera camera) {
        if (pickedUp) return;
        int screenX = x - camera.x;
        int screenY = y - camera.y;
        long currentTime = System.currentTimeMillis();
        int hoverOffset = (int) (Math.sin(currentTime / 300.0) * 5);
        g.drawImage(image, screenX, screenY + hoverOffset, null);
    }

    public boolean isClicked(int mouseX, int mouseY, Camera camera) {
        if (pickedUp) return false;
        int screenX = x - camera.x;
        int screenY = y - camera.y;
        int width = 32;
        int height = 32;
        return mouseX >= screenX && mouseX <= screenX + width &&
                mouseY >= screenY && mouseY <= screenY + height;
    }

    public void pickup() {
        pickedUp = true;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getFilename() {
        return fileLocation.split("/")[fileLocation.split("/").length - 1];
    }

    public String getName() {
        return name;
    }

    public int getWidth() {
        return image.getWidth();
    }

    public int getHeight() {
        return image.getHeight();
    }

    public String getDescription() {
        return description;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }
}