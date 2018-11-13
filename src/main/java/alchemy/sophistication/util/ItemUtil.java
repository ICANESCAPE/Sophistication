package alchemy.sophistication.util;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SCT_Alchemy
 * @date 2018/11/13 6:30 PM
 */

public class ItemUtil {
    public static ItemStack removeAll(ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        List<String> lore = meta.getLore();
        lore.clear();
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack del(int index, ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        List<String> lore = meta.getLore();
        try{
            index = index - 1;
            lore.remove(index);
        } catch (Exception e) {
            e.printStackTrace();
        }
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
}
