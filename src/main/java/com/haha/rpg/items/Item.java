package com.haha.rpg.items;

import com.haha.rpg.data.PlayerData;
import com.haha.rpg.gui.Camera;
import com.haha.rpg.util.JsonHelper;
import com.haha.rpg.util.ResourceLocation;
import org.json.JSONObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static com.haha.rpg.main.basics.Basics.items;

public class Item {
    private BufferedImage image;
    private String filepath;
    private int x, y;
    private final String name;
    private final String description;
    private final String rank;
    private final JSONObject jsonObject;
    private boolean pickedUp = false;

    public final int damage, durability, mana, stamina;

    public Item(String filepath) {
        try {
            this.image = ImageIO.read(new File(filepath));
            this.filepath = filepath;
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.jsonObject = JsonHelper.read(ResourceLocation.getLocation("infos/" + getFilename().replace("png", "json")));
        assert jsonObject != null;
        this.name = jsonObject.getString("name");
        this.description = jsonObject.getString("description");
        this.rank = jsonObject.getString("rank");
        this.damage = getJsonObject().getInt("damage");
        this.durability = getJsonObject().getInt("durability");
        this.mana = getJsonObject().getInt("mana");
        this.stamina = getJsonObject().getInt("stamina");
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
        PlayerData data = new PlayerData();
        data.addItem(this);
        data.saveData();
        items.remove(this);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public String getFilename() {
        return filepath.split("/")[filepath.split("/").length - 1];
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

    public String getRank() {
        return rank;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }
}