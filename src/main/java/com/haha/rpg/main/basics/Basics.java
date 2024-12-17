package com.haha.rpg.main.basics;

import com.haha.rpg.gui.Gui;
import com.haha.rpg.items.Item;
import com.haha.rpg.main.GamePanel;
import com.haha.rpg.world.World;

import java.util.ArrayList;

public interface Basics {
    GamePanel panel = new GamePanel();
    World world = new World(panel);
    Gui gui = new Gui(world);
    ArrayList<Item> items = new ArrayList<>();
}
