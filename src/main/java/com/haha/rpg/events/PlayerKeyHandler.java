package com.haha.rpg.events;

import com.haha.rpg.main.Basics;
import com.haha.rpg.main.Events;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@Events
public class PlayerKeyHandler extends KeyAdapter implements Basics {
    @Override
    public void keyPressed(KeyEvent e) {
        world.getPlayer().handleKeyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        world.getPlayer().handleKeyReleased(e);
    }
}
