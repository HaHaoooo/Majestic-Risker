package com.haha.rpg.gui;

import com.haha.rpg.items.Item;
import com.haha.rpg.util.ResourceLocation;

import java.awt.*;

public class Slot {
    public static int side = 39;
    private boolean hovered;
    private final int x, y;
    private final Rectangle area;
    private Item content;


    public Slot(int x, int y, Item content){
        this.x = x;
        this.y = y;
        this.area = new Rectangle(x, y, side, side);
        this.content = content;
    }

    public void render(Graphics g){
        if (content != null) {
            RenderScreen screen = new RenderScreen(g);
            int x = (side - content.getWidth()) / 2 + this.x;
            int y = (side - content.getHeight()) / 2 + this.y;
            screen.drawImage(content.getFilename(), x, y, content.getWidth(), content.getHeight(), 0, 0, content.getWidth(), content.getHeight());
        }
        if (hovered) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
            g2d.setColor(Color.WHITE);
            g2d.fillRect(x, y, side, side);
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        }
    }

    public void setContent(Item content){
        this.content = content;
    }

    public Item getContent() {
        return content;
    }

    public void setHovered(boolean hovered) {
        this.hovered = hovered;
    }

    public boolean isHovered() {
        return hovered;
    }

    public Rectangle getArea() {
        return area;
    }
}
