package com.haha.rpg.events;

import com.haha.rpg.gui.Slot;
import com.haha.rpg.main.Basics;
import com.haha.rpg.main.Events;
import com.haha.rpg.util.manager.DragManager;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@Events
public class DragItemToSlot extends MouseAdapter implements Basics {

    @Override
    public void mousePressed(MouseEvent e) {
        for (Slot slot : gui.playerInventoryGui.getAllSlots()) {
            if (slot.getContent() != null && slot.getArea().contains(e.getX(), e.getY())) {
                gui.playerInventoryGui.getDragManager().startDrag(slot, e.getX(), e.getY());
                break;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        DragManager dragManager = gui.playerInventoryGui.getDragManager();
        if (!dragManager.isDragging()) return;

        Slot targetSlot = null;
        for (Slot slot : gui.playerInventoryGui.getAllSlots()) {
            if (slot.getArea().contains(e.getX(), e.getY())) {
                targetSlot = slot;
                break;
            }
        }
        dragManager.endDrag(targetSlot);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        gui.playerInventoryGui.getDragManager().updateDragPosition(e.getX(), e.getY());
    }
}