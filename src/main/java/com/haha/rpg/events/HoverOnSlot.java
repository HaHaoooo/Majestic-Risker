package com.haha.rpg.events;

import com.haha.rpg.gui.Slot;
import com.haha.rpg.main.Basics;
import com.haha.rpg.main.Events;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@Events
public class HoverOnSlot extends MouseAdapter implements Basics {
    @Override
    public void mouseMoved(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();

        Slot mainSlot = gui.playerInventoryGui.getMainSlot();
        updateHoverState(mainSlot, mouseX, mouseY);

        for (Slot slot : gui.playerInventoryGui.getEquipmentSlots()) {
            updateHoverState(slot, mouseX, mouseY);
        }

        for (Slot slot : gui.playerInventoryGui.getItemSlots()) {
            updateHoverState(slot, mouseX, mouseY);
        }

        for (Slot slot : gui.playerInventoryGui.getDecorationSlots()) {
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
