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
