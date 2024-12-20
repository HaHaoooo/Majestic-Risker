package com.haha.rpg;

import com.haha.rpg.main.basics.Basics;
import com.haha.rpg.main.basics.Direction;
import com.haha.rpg.util.EventHelper;
import com.haha.rpg.util.JsonHelper;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.io.File;
import java.util.Random;

public class Main extends JFrame implements Basics {

    public static int WIDTH = 1000;
    public static int HEIGHT = 800;
    private static final Main instance = new Main();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(instance::run);
        EventHelper.registerAllEvents();
    }

    public void run(){
        File runFile = new File("run/player.json");
        if (!runFile.exists()) {
            initGameState();
        }
        this.add(panel);
        this.setTitle("Game Test");
        this.setSize(WIDTH, HEIGHT);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static Main getInstance() {
        return instance;
    }

    private void initGameState() {
        JSONObject content = new JSONObject();
        JSONObject playerInfo = new JSONObject();
        playerInfo.put("speed", 1);
        playerInfo.put("maxHealth", 20);
        playerInfo.put("health", 20);
        playerInfo.put("maxMana", 50);
        playerInfo.put("mana", 50);
        playerInfo.put("maxStamina", 50);
        playerInfo.put("stamina", 50);
        content.put("init", true);
        content.put("stat", playerInfo);
        JsonHelper.write(content, "run/player.json");

        JSONObject gameState = new JSONObject();
        JSONObject camera = new JSONObject();
        JSONObject player = new JSONObject();
        JSONArray items = new JSONArray();
        Random random = new Random();
        int x = random.nextInt(400) + 300;
        int y = random.nextInt(400) + 300;
        camera.put("x", x);
        camera.put("y", y);
        player.put("x", x + 200);
        player.put("y", y + 200);
        player.put("direction", Direction.RIGHT);
        JSONObject ironSword = new JSONObject();
        ironSword.put("filename", "iron_sword.png");
        ironSword.put("x", x + 200 + random.nextInt(200));
        ironSword.put("y", y + 200 + random.nextInt(200));
        items.put(ironSword);

        gameState.put("player", player);
        gameState.put("camera", camera);
        gameState.put("items", items);
        JsonHelper.write(gameState, "run/state.json");
    }
}