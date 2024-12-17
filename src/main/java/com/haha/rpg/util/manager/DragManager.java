package com.haha.rpg.util.manager;

import com.haha.rpg.gui.Slot;
import com.haha.rpg.items.Item;

public class DragManager {
    private Item draggedItem;
    private Slot fromSlot;
    private int dragX;
    private int dragY;
    private boolean dragging = false;

    public void startDrag(Slot slot, int mouseX, int mouseY) {
        dragging = true;
        draggedItem = slot.getContent();
        fromSlot = slot;
        slot.setContent(null);
        updateDragPosition(mouseX, mouseY);
    }

    public void updateDragPosition(int mouseX, int mouseY) {
        if (dragging) {
            dragX = mouseX;
            dragY = mouseY;
        }
    }

    public void endDrag(Slot targetSlot) {
        if (dragging) {
            if (targetSlot != null && targetSlot != fromSlot) {
                targetSlot.setContent(draggedItem);
            } else if (fromSlot != null) {
                fromSlot.setContent(draggedItem);
            }
            reset();
        }
    }

    private void reset() {
        draggedItem = null;
        fromSlot = null;
        dragging = false;
    }

    public boolean isDragging() {
        return dragging;
    }

    public Item getDraggedItem() {
        return draggedItem;
    }

    public int getDragX() {
        return dragX;
    }

    public int getDragY() {
        return dragY;
    }
}