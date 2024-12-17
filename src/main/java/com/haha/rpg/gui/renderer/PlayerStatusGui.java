package com.haha.rpg.gui.renderer;

import com.haha.rpg.gui.RenderScreen;
import com.haha.rpg.player.Player;
import com.haha.rpg.world.World;

import java.awt.*;

public class PlayerStatusGui {

    public RenderScreen screen;
    private final World world;

    public PlayerStatusGui(World world) {
        this.world = world;
    }

    public void render(Graphics g) {
        screen = new RenderScreen(g);
        int xOffset = 5;
        int yOffset = 5;
        String filename = "ui.png";

        Player player = world.getPlayer();
        screen.drawImage(filename, xOffset, yOffset, 200, 62, 0, 0, 200, 62);
        screen.drawImage(filename, xOffset + 72, yOffset + 6, (int) (104 * (player.health / player.maxHealth)), 12, 224, 8, 102, 8);
        screen.drawImage(filename, xOffset + 72, yOffset + 26, (int) (104 * (player.stamina / player.maxStamina)), 12, 224, 28, 102, 8);
        screen.drawImage(filename, xOffset + 72, yOffset + 46, (int) (104 * (player.mana / player.maxMana)), 12, 224, 48, 102, 8);

        int textX = (xOffset + 72 + 104) / 2;
        Font font = new Font("Arial", Font.BOLD, 10);
        screen.drawText("HP: " + player.health + "/" + player.maxHealth, textX, yOffset + 15, font, null);
        screen.drawText("SP: " + player.stamina + "/" + player.maxStamina, textX, yOffset + 35, font, null);
        screen.drawText("MP: " + player.mana + "/" + player.maxMana, textX, yOffset + 55, font, null);
    }
}
