package com.haha.rpg.gui;

import com.haha.rpg.util.ResourceLocation;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class RenderScreen {

    private final Graphics g;
    private int rectX;
    private int rectY;
    private int rectWidth;
    private int rectHeight;
    private int rectArc;
    private boolean isRectArc;

    public RenderScreen(Graphics g) {
        this.g = g;
    }

    /**
     *
     * @param filename resources/rpg下的资源坐标
     * @param x 在屏幕上的x坐标
     * @param y 在屏幕上的y坐标
     * @param width 在屏幕上的宽
     * @param height 在屏幕上的高
     * @param sx 在图片上的x坐标
     * @param sy 在图片上的y坐标
     * @param sWidth 在图片上的宽
     * @param sHeight 在图片上的高
     */
    public void drawImage(String filename, int x, int y, int width, int height, int sx, int sy, int sWidth, int sHeight) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(ResourceLocation.getLocation(filename)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        int destinationX = x + width;
        int destinationY = y + height;
        int sx2 = sx + sWidth;
        int sy2 = sy + sHeight;
        g.drawImage(image, x, y, destinationX, destinationY, sx, sy, sx2, sy2, null);
    }

    public void drawText(String text, int x, int y, Font font, Color color) {
        g.setColor(color);
        g.setFont(font);
        g.drawString(text, x, y);
    }

    public void drawRect(int x, int y, int width, int height, Color color) {
        g.setColor(color);
        g.drawRect(x, y, width, height);
        this.rectX = x;
        this.rectY = y;
        this.rectWidth = width;
        this.rectHeight = height;
        this.isRectArc = false;
    }

    public void drawRect(int x, int y, int width, int height, int arc, Color color) {
        g.setColor(color);
        g.drawRoundRect(x, y, width, height, arc, arc);
        this.rectX = x;
        this.rectY = y;
        this.rectWidth = width;
        this.rectHeight = height;
        this.rectArc = arc;
        this.isRectArc = true;
    }

    public void setRectFill(Color color) {
        g.setColor(color);
        if (isRectArc) {
            g.fillRoundRect(rectX, rectY, rectWidth, rectHeight, rectArc, rectArc);
        } else {
            g.fillRect(rectX, rectY, rectWidth, rectHeight);
        }
    }
}
