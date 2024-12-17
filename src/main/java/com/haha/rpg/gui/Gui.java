package com.haha.rpg.gui;

import com.haha.rpg.gui.renderer.ChatGui;
import com.haha.rpg.gui.renderer.ItemInfoGui;
import com.haha.rpg.gui.renderer.PlayerInventoryGui;
import com.haha.rpg.gui.renderer.PlayerStatusGui;
import com.haha.rpg.world.World;

import java.awt.*;

public class Gui {

    public PlayerStatusGui playerStatusGui;
    public ChatGui chatGui;
    public PlayerInventoryGui playerInventoryGui;
    public ItemInfoGui itemInfoGui;

    public Gui(World world){
        this.playerStatusGui = new PlayerStatusGui(world);
        this.chatGui = new ChatGui(world);
        this.playerInventoryGui = new PlayerInventoryGui(world);
        this.itemInfoGui = new ItemInfoGui(world);
    }

    public void render(Graphics g){
        playerStatusGui.render(g);
        chatGui.render(g);
        playerInventoryGui.render(g);
        itemInfoGui.render(g);
    }
}
