package com.haha.rpg.items;

public class ItemSword extends Item{
    public final int damage, durability, mana, stamina;
    public ItemSword(String filename, int x, int y) {
        super(filename, x, y);
        this.damage = getJsonObject().getInt("damage");
        this.durability = getJsonObject().getInt("durability");
        this.mana = getJsonObject().getInt("mana");
        this.stamina = getJsonObject().getInt("stamina");
    }

    public int getDamage() {
        return damage;
    }

    public int getDurability() {
        return durability;
    }

    public int getMana() {
        return mana;
    }

    public int getStamina() {
        return stamina;
    }
}
