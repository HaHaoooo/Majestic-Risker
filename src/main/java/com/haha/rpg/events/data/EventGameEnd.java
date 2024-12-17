package com.haha.rpg.events.data;

import com.haha.rpg.items.Item;
import com.haha.rpg.main.basics.Basics;
import com.haha.rpg.main.Events;
import com.haha.rpg.util.JsonHelper;
import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@Events
public class EventGameEnd extends WindowAdapter implements Basics {
    @Override
    public void windowClosing(WindowEvent e) {
        saveGameState();
        super.windowClosing(e);
    }

    private void saveGameState() {
        JSONObject gameState = new JSONObject();

        JSONObject playerData = new JSONObject();
        playerData.put("x", world.getPlayer().getX());
        playerData.put("y", world.getPlayer().getY());
        playerData.put("direction", world.getPlayer().getDirection());

        JSONArray itemsData = new JSONArray();
        for (Item item : items) {
            JSONObject itemData = new JSONObject();
            itemData.put("x", item.getX());
            itemData.put("y", item.getY());
            itemData.put("filename", item.getFilename());
            itemsData.put(itemData);
        }

        JSONObject cameraData = new JSONObject();
        cameraData.put("x", world.getCamera().x);
        cameraData.put("y", world.getCamera().y);
        gameState.put("player", playerData);
        gameState.put("items", itemsData);
        gameState.put("camera", cameraData);
        JsonHelper.write(gameState, "run/state.json");
    }
}
