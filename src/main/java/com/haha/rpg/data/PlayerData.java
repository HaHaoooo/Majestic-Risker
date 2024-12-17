package com.haha.rpg.data;

import com.haha.rpg.items.Item;
import com.haha.rpg.util.ResourceLocation;
import com.haha.rpg.util.manager.DataManager;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class PlayerData {

    private DataManager manager;

    public PlayerData(){
        try {
            manager = new DataManager("run/player.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public int getHealth() {
        Object value = manager.getData("stat", "health");
        return value instanceof Number ? ((Number) value).intValue() : 0;
    }

    public void setHealth(int health) {
        manager.setData(health, "stat", "health");
    }

    public int getMaxHealth() {
        Object value = manager.getData("stat", "maxHealth");
        return value instanceof Number ? ((Number) value).intValue() : 0;
    }

    public void setMaxHealth(int maxHealth) {
        manager.setData(maxHealth, "stat", "maxHealth");
    }

    public int getMana() {
        Object value = manager.getData("stat", "mana");
        return value instanceof Number ? ((Number) value).intValue() : 0;
    }

    public void setMana(int mana) {
        manager.setData(mana, "stat", "mana");
    }

    public int getMaxMana() {
        Object value = manager.getData("stat", "maxMana");
        return value instanceof Number ? ((Number) value).intValue() : 0;
    }

    public void setMaxMana(int maxMana) {
        manager.setData(maxMana, "stat", "maxMana");
    }

    public int getStamina() {
        Object value = manager.getData("stat", "stamina");
        return value instanceof Number ? ((Number) value).intValue() : 0;
    }

    public void setStamina(int stamina) {
        manager.setData(stamina, "stat", "stamina");
    }

    public int getMaxStamina() {
        Object value = manager.getData("stat", "maxStamina");
        return value instanceof Number ? ((Number) value).intValue() : 0;
    }

    public void setMaxStamina(int maxStamina) {
        manager.setData(maxStamina, "stat", "maxStamina");
    }

    public void addItem(Item item) {
        JSONArray inventory = (JSONArray) manager.getData("inventory");
        if (inventory == null) {
            inventory = new JSONArray();
            manager.setData(inventory, "inventory");
        }
        int newIndex = inventory.length();

        JSONObject itemJson = new JSONObject();
        itemJson.put("filename", item.getFilename());
        itemJson.put("slot", "items");
        inventory.put(newIndex, itemJson);

        manager.setData(inventory, "inventory");
    }

    public Item[] getInventory() {
        JSONArray inventoryArray = (JSONArray) manager.getData("inventory");
        if (inventoryArray == null) {
            return new Item[0];
        }
        Item[] items = new Item[inventoryArray.length()];
        for (int i = 0; i < inventoryArray.length(); i++) {
            JSONObject itemJson = inventoryArray.getJSONObject(i);
            String itemFilename = itemJson.getString("filename");
            Item item = new Item(ResourceLocation.getLocation(itemFilename));
            items[i] = item;
        }
        return items;
    }

    public void saveData(){
        try {
            manager.saveData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
