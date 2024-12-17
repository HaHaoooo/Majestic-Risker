package com.haha.rpg.util;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class StringHelper {
    public static List<String> splitString(String str, int rowWidth) {
        List<String> list = new ArrayList<>();
        int length = str.length();
        int start = 0;
        while (start < length) {
            int end = Math.min(length, start + rowWidth);
            if (end < length && str.charAt(end) != ' ') {
                int lastSpace = str.lastIndexOf(' ', end);
                if (lastSpace > start) {
                    end = lastSpace;
                }
            }
            list.add(str.substring(start, end).trim());
            start = end + 1;
        }

        return list;
    }

    public static int listHeight(List<String> list, Font font, Graphics g) {
        FontMetrics metrics = g.getFontMetrics(font);
        int lineHeight = metrics.getHeight();
        return list.size() * lineHeight;
    }
}
