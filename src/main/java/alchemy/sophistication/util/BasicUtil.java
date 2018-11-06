package alchemy.sophistication.util;

import alchemy.sophistication.dto.Data;
import alchemy.sophistication.file.Config;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class BasicUtil {

    public static String getString(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static int getRandom(int max, int min) {
        return min + (int) (Math.random() * (max - min) + 1);
    }


   public static Type getType(ItemStack item) {
        switch (item.getType()) {
            case LEATHER_HELMET:
            case GOLD_HELMET:
            case CHAINMAIL_HELMET:
            case IRON_HELMET:
            case DIAMOND_HELMET:

            case LEATHER_LEGGINGS:
            case GOLD_LEGGINGS:
            case CHAINMAIL_LEGGINGS:
            case IRON_LEGGINGS:
            case DIAMOND_LEGGINGS:

            case LEATHER_BOOTS:
            case GOLD_BOOTS:
            case CHAINMAIL_BOOTS:
            case IRON_BOOTS:
            case DIAMOND_BOOTS:

            case LEATHER_CHESTPLATE:
            case GOLD_CHESTPLATE:
            case CHAINMAIL_CHESTPLATE:
            case IRON_CHESTPLATE:
            case DIAMOND_CHESTPLATE:
            case SHIELD:
                return Type.EQUIP;
            case BOW:
            case STONE_SWORD:
            case DIAMOND_SWORD:
            case WOOD_SWORD:
            case IRON_SWORD:
            case GOLD_SWORD:
                return Type.WEAPON;
            default:
                return null;
        }
   }

   public static String info(String msg) {
        return getString(msg);
   }

   public static boolean isAllow(ItemStack item) {
        for(int i : Config.getAllowList()) {
            if(item.getTypeId() == i) {
                return true;
            }
        }
        return false;
   }

    public static String luck(Map<String, Integer> map) {
        int sum = 0;
        for (String key : map.keySet()) {
            sum += map.get(key);
        }

        int random = BasicUtil.getRandom(sum, 1);
        sum = 0;

        for (String key : map.keySet()) {
            sum += map.get(key);
            if (sum >= random) return key;
        }
        return null;
    }



    public static Data getData(ItemStack item) {
        Data data;
        Type type = BasicUtil.getType(item);
        switch (type) {
            case EQUIP:
                data = Config.getData(BasicUtil.luck(Config.getEquipMap()));
                break;
            case WEAPON:
                data = Config.getData(BasicUtil.luck(Config.getWeaponMap()));
                break;
            default:
                data = Config.getData(BasicUtil.luck(Config.getWeaponMap()));
        }
        return data;
    }

}
