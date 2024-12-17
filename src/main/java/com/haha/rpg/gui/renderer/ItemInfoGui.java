package com.haha.rpg.gui.renderer;

import com.haha.rpg.font.CustomFont;
import com.haha.rpg.gui.RenderScreen;
import com.haha.rpg.items.Item;
import com.haha.rpg.util.ItemHelper;
import com.haha.rpg.util.StringHelper;
import com.haha.rpg.world.World;

import java.awt.*;
import java.util.List;

public class ItemInfoGui {
    public RenderScreen screen;
    private final World world;
    private boolean visible = false;
    private Item item;

    public ItemInfoGui(World world) {
        this.world = world;
    }

    public void render(Graphics g) {
        if (!visible) return;
        screen = new RenderScreen(g);
        // draw slot
        int image = 83;
        int x = (world.getPanel().getWidth() - image) / 2 + 190;
        int y = (world.getPanel().getHeight() - image) / 2 - image + 2;
        screen.drawImage("player_inventory.png", x, y, image, image, 1, 64, 18, 18);

        // draw item
        int i = image - 30;
        int xi = (world.getPanel().getWidth() - i) / 2 + 190;
        int yi = (world.getPanel().getHeight() - i) / 2 - image;
        screen.drawImage(item.getFilename(), xi, yi, i, i, 0, 0, 32, 32);

        // draw item info
        screen.drawText(item.getName(), xi + 80, yi + 2, CustomFont.Jacquard12.deriveFont(32f), ItemHelper.getColorByRank(item));
        screen.drawText(item.getRank(), xi + 80, yi + 30, CustomFont.Jacquard12, ItemHelper.getColorByRank(item));
        List<String> list = StringHelper.splitString(item.getDescription(), 15);
        screen.drawTextLine(list, xi + 83, yi + 50, CustomFont.Dancing.deriveFont(20f), Color.BLACK);

        // draw item sword specific info
        try {
            String damage = "Damage: " + item.damage;
            String mana = "Mana: " + item.mana;
            String stamina = "Stamina: " + item.stamina;
            String durability = "Durability: " + item.durability;
            screen.drawText(damage, xi, yi + image + 5, new Font("Arial", Font.PLAIN, 10), Color.WHITE);
            screen.drawText(mana, xi, yi + image + 20, new Font("Arial", Font.PLAIN, 10), Color.WHITE);
            screen.drawText(stamina, xi, yi + image + 35, new Font("Arial", Font.PLAIN, 10), Color.WHITE);
            screen.drawText(durability, xi, yi + image + 50, new Font("Arial", Font.PLAIN, 10), Color.WHITE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setVisible(boolean visible){
        this.visible = visible;
    }

    public void loadItem(Item item){
        this.item = item;
    }
}
