package com.haha.rpg.events;

import com.haha.rpg.gui.Slot;
import com.haha.rpg.main.Events;
import com.haha.rpg.main.basics.Basics;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@Events
public class EventItemInfo extends MouseAdapter implements Basics {
    @Override
    public void mouseMoved(MouseEvent e) {
        super.mouseMoved(e);
        int mouseX = e.getX();
        int mouseY = e.getY();
        for (Slot slot : gui.playerInventoryGui.getAllSlots()) {
            if (slot.getArea().contains(mouseX, mouseY) && slot.getContent() != null && gui.playerInventoryGui.isVisible()) {
                gui.itemInfoGui.loadItem(slot.getContent());
                gui.itemInfoGui.setVisible(true);
                break;
            } else {
                gui.itemInfoGui.setVisible(false);
            }
        }
    }
}
