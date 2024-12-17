package com.haha.rpg.util;

import com.haha.rpg.items.Item;

import java.awt.*;

public class ItemHelper {
    public static Color getColorByRank(Item item){
        String rank = item.getRank();
        return switch (rank) {
            case "Basic" -> Color.GRAY;
            case "Rare" -> Color.GREEN.brighter();
            case "Super Rare" -> Color.CYAN;
            case "Epic" -> Color.MAGENTA;
            case "Legendary" -> Color.ORANGE;
            case "Ultimate" -> Color.RED;
            default -> Color.BLACK;
        };
    }
}
