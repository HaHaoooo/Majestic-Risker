package com.haha.rpg.font;

import com.haha.rpg.util.ResourceLocation;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class CustomFont {
    static File jacquard_12 = new File(ResourceLocation.getLocation("font/jacquard12/jacquard12_regular.ttf"));
    static File dancing = new File(ResourceLocation.getLocation("font/dancing/dancing_wght.ttf"));
    public static Font Jacquard12;
    public static Font Dancing;

    static {
        try {
            Jacquard12 = Font.createFont(Font.TRUETYPE_FONT, jacquard_12).deriveFont(24f);
            Dancing = Font.createFont(Font.TRUETYPE_FONT, dancing).deriveFont(24f);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }
}
