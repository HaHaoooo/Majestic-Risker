package com.haha.rpg.events;

import com.haha.rpg.items.Item;
import com.haha.rpg.main.Basics;
import com.haha.rpg.main.Events;
import com.haha.rpg.util.SlotHelper;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@Events
public class PickItems extends MouseAdapter implements Basics {
    @Override
    public void mouseClicked(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();
        for (Item item : items) {
            if (item.isClicked(mouseX, mouseY, world.getCamera()) && world.getPlayer().isCloseEnough(item.getX(), item.getY(), 80)) {
                if (!SlotHelper.slotsAreFull(gui.playerInventoryGui.getItemSlots())) {
                    item.pickup();
                    gui.chatGui.print("Pick up an item [" + item.getName() + "]", Color.BLACK);
                    SlotHelper.slotsFiller(gui.playerInventoryGui.getItemSlots(), item);
                } else {
                    gui.chatGui.print("Your inventory is full", Color.BLACK);
                }
            }
        }
    }
}
