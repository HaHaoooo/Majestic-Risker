package com.haha.rpg.gui.renderer;

import com.haha.rpg.gui.RenderScreen;
import com.haha.rpg.gui.Slot;
import com.haha.rpg.items.Item;
import com.haha.rpg.util.manager.DragManager;
import com.haha.rpg.world.World;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class PlayerInventoryGui {
    public RenderScreen screen;
    private final World world;
    private final DragManager dragManager = new DragManager();
    private boolean visible = false;

    public final Slot[] itemSlots = new Slot[16];
    public final Slot[] equipmentSlots = new Slot[4];
    public final Slot[] decorationSlots = new Slot[4];
    public Slot mainSlot;

    public PlayerInventoryGui(World world) {
        this.world = world;
        int image = 83 * 3;
        SwingUtilities.invokeLater(() -> {
            int x = (world.getPanel().getWidth() - image) / 2;
            int y = (world.getPanel().getHeight() - image) / 2 + 14;

            // initialize mainSlot
            this.mainSlot = new Slot(x + 12, y + image - Slot.side - 26, null);
            // initialize equipmentSlots
            for (int i = 0; i < equipmentSlots.length; i++)
                equipmentSlots[i] = new Slot(x + 12, y + i * (Slot.side + 2) - 5, null);
            // initialize decorationSlots
            for (int i = 0; i < decorationSlots.length; i++)
                decorationSlots[i] = new Slot(x + (i % 4) * (Slot.side + 2) + 78, y + image - Slot.side - 26, null);
            // initialize itemSlots
            int gridSize = 4;
            int slotSpacing = Slot.side + 2;
            for (int i = 0; i < itemSlots.length; i++) {
                int row = i / gridSize;
                int col = i % gridSize;
                itemSlots[i] = new Slot(x + col * slotSpacing + 78, y + row * slotSpacing - 5, null);
            }
        });
    }

    public void render(Graphics g) {
        if (!visible) return;

        // render transparent black background
        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, world.getPanel().getWidth(), world.getPanel().getHeight());
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));

        this.screen = new RenderScreen(g);
        int image = 83 * 3;
        int x = (world.getPanel().getWidth() - image) / 2;
        int y = (world.getPanel().getHeight() - image) / 2;
        screen.drawImage("player_inventory.png", x, y, image, image, 0, 0, 83, 83);

        // render slots
        mainSlot.render(g);
        for (Slot slot : equipmentSlots) slot.render(g);
        for (Slot slot : itemSlots) slot.render(g);
        for (Slot slot : decorationSlots) slot.render(g);

        if (dragManager.isDragging()) {
            Item draggedItem = dragManager.getDraggedItem();
            if (draggedItem != null) {
                screen.drawImage(draggedItem.getFilename(), dragManager.getDragX() - draggedItem.getWidth() / 2, dragManager.getDragY() - draggedItem.getHeight() / 2, draggedItem.getWidth(), draggedItem.getHeight(), 0, 0, draggedItem.getWidth(), draggedItem.getHeight());
            }
        }
    }

    public DragManager getDragManager() {
        return dragManager;
    }

    public boolean isVisible() {
        return visible;
    }

    public void openGui() {
        visible = true;
    }

    public void closeGui() {
        visible = false;
    }

    public List<Slot> getAllSlots() {
        List<Slot> slotList = new ArrayList<>();
        slotList.add(mainSlot);
        slotList.addAll(List.of(equipmentSlots));
        slotList.addAll(List.of(itemSlots));
        slotList.addAll(List.of(decorationSlots));
        return slotList;
    }
}
