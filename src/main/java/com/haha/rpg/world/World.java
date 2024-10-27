package com.haha.rpg.world;

import com.haha.rpg.gui.Camera;
import com.haha.rpg.player.Player;
import com.haha.rpg.util.ResourceLocation;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class World {
    private BufferedImage grassTile;
    private final Player player = new Player();
    private final Camera camera = new Camera();
    private final JPanel panel;

    public World(JPanel panel){
        this.panel = panel;
        try {
            grassTile = ImageIO.read(new File(ResourceLocation.getLocation("grass.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void render(Graphics g) {
        int tileSize = 32;
        int startX = (camera.x / tileSize) - 1;
        int startY = (camera.y / tileSize) - 1;
        int endX = ((camera.x + panel.getWidth()) / tileSize) + 1;
        int endY = ((camera.y + panel.getHeight()) / tileSize) + 1;

        for (int x = startX; x <= endX; x++) {
            for (int y = startY; y <= endY; y++) {
                int screenX = x * tileSize - camera.x;
                int screenY = y * tileSize - camera.y;
                g.drawImage(grassTile, screenX, screenY, tileSize, tileSize, null);
            }
        }
        player.render(g, camera.x, camera.y);
    }

    public Player getPlayer() {
        return player;
    }

    public Camera getCamera() {
        return camera;
    }

    public JPanel getPanel() {
        return panel;
    }
}
