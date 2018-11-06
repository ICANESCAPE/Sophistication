package alchemy.sophistication.listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

public class InventroyCloseListener implements Listener {

    @EventHandler
    public void onClose(InventoryCloseEvent e ){
        Player player = (Player) e.getPlayer();

        ItemStack weapon, tool;

        if (e.getInventory().getName().contains("装备洗练")) {
            weapon = e.getInventory().getItem(11);
            tool = e.getInventory().getItem(15);
            if ((weapon != null) && (!weapon.getType().equals(Material.AIR))) {
                player.getInventory().addItem(new ItemStack[] { weapon } );
            }
            if ((tool != null) && (!tool.getType().equals(Material.AIR))) {
                player.getInventory().addItem(new ItemStack[] { tool });
            }
        }

        if (e.getInventory().getName().equals("我也不知道叫啥名字")) {
            weapon = e.getInventory().getItem(11);
            tool = e.getInventory().getItem(15);
            if ((weapon != null) && (!weapon.getType().equals(Material.AIR))) {
               player.getInventory().addItem(new ItemStack[] { weapon } );
            }
            if ((tool != null) && (!tool.getType().equals(Material.AIR))) {
               player.getInventory().addItem(new ItemStack[] { tool });
            }

        }


    }
}
