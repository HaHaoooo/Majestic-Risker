package com.haha.rpg.events;

import com.haha.rpg.main.Basics;
import com.haha.rpg.main.Events;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@Events
public class TogglePlayerInventory extends KeyAdapter implements Basics {
    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
            gui.playerInventoryGui.closeGui();
        }
        if (e.getKeyCode() == KeyEvent.VK_E){
            gui.playerInventoryGui.openGui();
        }
    }
}
