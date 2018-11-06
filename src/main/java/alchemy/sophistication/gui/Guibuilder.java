package alchemy.sophistication.gui;

import alchemy.sophistication.file.Config;
import alchemy.sophistication.util.BasicUtil;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.sct.core.file.FileTool;

public class Guibuilder {

    private static Inventory inventory;

    public static void open(Player player, boolean isSoph) {
        if(isSoph) {
            inventory = Bukkit.createInventory(null, 36, BasicUtil.getString("&d&l装备洗练"));
            add();
            player.openInventory(inventory);
        } else {
            inventory = Bukkit.createInventory(null, 36, BasicUtil.getString("我也不知道叫啥名字"));
            add();
            player.openInventory(inventory);
        }
    }

    private static void add() {
        for(int i = 0; i < 36; i++) {
            if(i == 15 || i == 11 || i == 31) {
            } else {
                inventory.setItem(i, FileTool.getItem(Config.getItem()));
            }
        }
        inventory.setItem(31, FileTool.getItem(Config.getConfirm()));
    }

}
