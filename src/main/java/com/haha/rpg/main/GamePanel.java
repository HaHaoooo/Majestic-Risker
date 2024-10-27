package com.haha.rpg.main;

import com.haha.rpg.items.Item;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Basics {

    public GamePanel() {
        setFocusable(true);
        requestFocusInWindow();

        Timer timer = new Timer(16, e -> {
            world.getPlayer().updateMovement(getWidth(), getHeight(), world.getCamera());
            repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        world.render(g);
        for (Item item : items){
            item.render(g, world.getCamera());
        }
        gui.render(g);
    }
}