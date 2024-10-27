package com.haha.rpg.gui;

import com.haha.rpg.gui.renderer.ChatGui;
import com.haha.rpg.gui.renderer.PlayerInventoryGui;
import com.haha.rpg.gui.renderer.PlayerStatusGui;
import com.haha.rpg.world.World;

import java.awt.*;

public class Gui {

    public PlayerStatusGui playerStatusGui;
    public ChatGui chatGui;
    public PlayerInventoryGui playerInventoryGui;

    public Gui(World world){
        this.playerStatusGui = new PlayerStatusGui(world);
        this.chatGui = new ChatGui(world);
        this.playerInventoryGui = new PlayerInventoryGui(world);
    }

    public void render(Graphics g){
        playerStatusGui.render(g);
        chatGui.render(g);
        playerInventoryGui.render(g);
    }
}
