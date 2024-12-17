package com.haha.rpg.data;

import com.haha.rpg.util.manager.DataManager;

import java.io.IOException;

public class StateData {
    private DataManager manager;

    public StateData() {
        try {
            manager = new DataManager("run/state.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void saveData(){
        try {
            manager.saveData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
