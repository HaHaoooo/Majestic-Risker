package com.haha.rpg.events;

import com.haha.rpg.Direction;
import com.haha.rpg.gui.Camera;
import com.haha.rpg.items.ItemSword;
import com.haha.rpg.main.Basics;
import com.haha.rpg.main.Events;
import com.haha.rpg.player.Player;
import com.haha.rpg.util.JsonHelper;
import com.haha.rpg.util.ResourceLocation;
import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@Events
public class GameStart extends WindowAdapter implements Basics {
    @Override
    public void windowOpened(WindowEvent e) {
        loadGameState();
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
            int x = data.getInt("x");
            int y = data.getInt("y");
            String filename = data.getString("filename");
            items.add(new ItemSword(ResourceLocation.getLocation(filename), x, y));
        }
    }
}
