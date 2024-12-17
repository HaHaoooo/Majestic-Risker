package com.haha.rpg.events.gui.inventory;

import com.haha.rpg.gui.Slot;
import com.haha.rpg.main.basics.Basics;
import com.haha.rpg.main.Events;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@Events
public class EventHoverOnSlot extends MouseAdapter implements Basics {
    @Override
    public void mouseMoved(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();
        for (Slot slot : gui.playerInventoryGui.getAllSlots()) {
            updateHoverState(slot, mouseX, mouseY);
        }
    }

    private void updateHoverState(Slot slot, int mouseX, int mouseY) {
        boolean isHovered = slot.getArea().contains(mouseX, mouseY);
        if (slot.isHovered() != isHovered) {
            slot.setHovered(isHovered);
        }
    }
}
