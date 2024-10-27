package com.haha.rpg.gui.renderer;

import com.haha.rpg.gui.RenderScreen;
import com.haha.rpg.world.World;

import javax.swing.*;
import java.awt.*;

public class ChatGui {

    public RenderScreen screen;
    private final World world;
    private boolean visible = false;
    private String currentText = "";
    private Color currentColor = Color.BLACK;
    private float currentAlpha = 1.0f;

    public ChatGui(World world) {
        this.world = world;
    }

    public void render(Graphics g) {
        if (visible) {
            Graphics2D g2d = (Graphics2D) g;
            screen = new RenderScreen(g);

            // Set transparency
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, currentAlpha));

            // Calculate dynamic chat width based on text length
            FontMetrics metrics = g2d.getFontMetrics();
            int chatWidth = metrics.stringWidth(currentText) + 20; // Add padding
            int chatHeight = 20;
            int chatX = 10;
            int chatY = world.getPanel().getHeight() - chatHeight - 20;

            // Draw background rectangle
            screen.drawRect(chatX, chatY, chatWidth, chatHeight, 10, Color.GRAY);
            screen.setRectFill(Color.GRAY.brighter());

            // Draw text
            screen.drawText(currentText, chatX + 10, chatY + 13, null, currentColor);

            // Reset composite to opaque
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        }
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void print(String text, Color color) {
        // Set initial values for the new message
        this.currentText = text;
        this.currentColor = color;
        this.currentAlpha = 1.0f;

        // Use Swing Timer for smooth fade-out effect
        Timer timer = new Timer(50, e -> {
            if (currentAlpha > 0) {
                currentAlpha -= 0.01f;
                if (currentAlpha < 0) {
                    currentAlpha = 0;
                }
                setVisible(true);
            } else {
                setVisible(false);
                ((Timer) e.getSource()).stop();
            }
            world.getPanel().repaint(); // Request a repaint to update transparency
        });
        timer.setInitialDelay(0);
        timer.start();
    }
}