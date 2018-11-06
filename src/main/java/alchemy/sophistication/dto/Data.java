package alchemy.sophistication.dto;

public class Data {
    String lore;
    String range;
    String quality;
    int chance;
    boolean info;
    boolean equip;

    public Data(String lore, String range, String quality, int chance, boolean info, boolean equip) {
        this.lore = lore;
        this.range = range;
        this.quality = quality;
        this.chance = chance;
        this.info = info;
        this.equip = equip;
    }

    public String getLore() {
        return lore;
    }

    public String getRange() {
        return range;
    }

    public String getQuality() {
        return quality;
    }

    public int getChance() {
        return chance;
    }

    public boolean isInfo() {
        return info;
    }

    public boolean isEquip() {
        return equip;
    }
}
