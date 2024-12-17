package com.haha.rpg.events;

import com.haha.rpg.data.PlayerData;
import com.haha.rpg.gui.Camera;
import com.haha.rpg.gui.Slot;
import com.haha.rpg.items.Item;
import com.haha.rpg.main.Events;
import com.haha.rpg.main.basics.Basics;
import com.haha.rpg.main.basics.Direction;
import com.haha.rpg.player.Player;
import com.haha.rpg.util.JsonHelper;
import com.haha.rpg.util.ResourceLocation;
import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@Events
public class EventGameStart extends WindowAdapter implements Basics {
    @Override
    public void windowOpened(WindowEvent e) {
        loadGameState();
        loadPlayerInventory();
        super.windowOpened(e);
    }

    private void loadGameState() {
        JSONObject state = JsonHelper.read("run/state.json");
        if (state == null) return;

        JSONObject playerState = state.getJSONObject("player");
        JSONObject cameraState = state.getJSONObject("camera");
        JSONArray itemsState = state.getJSONArray("items");
        Player player = world.getPlayer();
        Camera camera = world.getCamera();

        player.setX(playerState.getInt("x"));
        player.setY(playerState.getInt("y"));
        player.setDirection(playerState.getEnum(Direction.class, "direction"));

        camera.x = cameraState.getInt("x");
        camera.y = cameraState.getInt("y");

        for (int i = 0; i < itemsState.length(); i++) {
            JSONObject data = itemsState.getJSONObject(i);
            String filename = data.getString("filename");
            Item sword = new Item(ResourceLocation.getLocation(filename));
            sword.setX(data.getInt("x"));
            sword.setY(data.getInt("y"));
            items.add(sword);
        }
    }

    private void loadPlayerInventory() {
        PlayerData data = new PlayerData();
        for (int i = 0; i < data.getInventory().length; i++) {
            Slot slot = gui.playerInventoryGui.itemSlots[i];
            slot.setContent(data.getInventory()[i]);
        }
    }
}
