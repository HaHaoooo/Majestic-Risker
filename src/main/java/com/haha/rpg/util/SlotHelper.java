package com.haha.rpg.util;

import com.haha.rpg.gui.Slot;
import com.haha.rpg.items.Item;

public class SlotHelper {
    public static void slotsFiller(Slot[] slots, Item item) {
        if (slots == null || slots.length == 0 || item == null) {
            System.out.println("Not valid slots");
            return;
        }
        for (Slot slot : slots) {
            if (slot.getContent() == null) {
                slot.setContent(item);
                break;
            }
        }
    }

    public static boolean slotsAreFull(Slot[] slots) {
        boolean full = true;
        for (Slot slot : slots) {
            if (slot.getContent() == null) {
                full = false;
                break;
            }
        }
        return full;
    }
}
