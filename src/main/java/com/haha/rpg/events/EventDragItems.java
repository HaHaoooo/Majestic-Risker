package com.haha.rpg.events;

import com.haha.rpg.gui.Slot;
import com.haha.rpg.main.basics.Basics;
import com.haha.rpg.main.Events;
import com.haha.rpg.util.manager.DragManager;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@Events
public class EventDragItems extends MouseAdapter implements Basics {

    private final DragManager dragManager = gui.playerInventoryGui.getDragManager();

    @Override
    public void mousePressed(MouseEvent e) {
        Slot slot = getSlotUnderMouse(e.getX(), e.getY());
        if (slot != null && slot.getContent() != null) {
           dragManager.startDrag(slot, e.getX(), e.getY());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (!dragManager.isDragging()) return;
        Slot targetSlot = getSlotUnderMouse(e.getX(), e.getY());
        dragManager.endDrag(targetSlot);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        dragManager.updateDragPosition(e.getX(), e.getY());
    }

    private Slot getSlotUnderMouse(int x, int y) {
        for (Slot slot : gui.playerInventoryGui.getAllSlots()) {
            if (slot.getArea().contains(x, y)) {
                return slot;
            }
        }
        return null;
    }
}