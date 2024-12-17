package com.haha.rpg.events.player;

import com.haha.rpg.main.basics.Basics;
import com.haha.rpg.main.Events;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@Events
public class EventTogglePlayerInventory extends KeyAdapter implements Basics {
    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
            gui.playerInventoryGui.closeGui();
            gui.itemInfoGui.setVisible(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_E){
            gui.playerInventoryGui.openGui();
        }
    }
}
