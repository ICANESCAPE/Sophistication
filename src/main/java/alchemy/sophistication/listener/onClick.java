package alchemy.sophistication.listener;

import alchemy.sophistication.dto.Data;
import alchemy.sophistication.file.Config;
import alchemy.sophistication.util.BasicUtil;
import alchemy.sophistication.util.ItemUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.sct.core.file.FileTool;
import org.sct.core.util.ItemStackUtil;

public class onClick implements Listener {

    @EventHandler
    public void onInvClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        Inventory inventory = e.getInventory();
        int slot = e.getSlot();
        String invname = "装备洗练";


        if (inventory.getName().contains(invname)) {
            if (e.getCurrentItem().isSimilar(FileTool.getItem(Config.getItem()))) {
                e.setCancelled(true);
            } else {
            }
        }

        if (inventory.getName().contains(invname)) {
            ItemStack rune = e.getInventory().getItem(15);
            ItemStack item = e.getInventory().getItem(11);
            if (slot == 31) {
                e.setCancelled(true);

                if (item == null || item.getItemMeta().equals(Material.AIR)) {
                    player.sendMessage("曹尼玛瞎几把点什么呢");
                } else if (rune == null || rune.getItemMeta().equals(Material.AIR)) {
                    player.sendMessage("你的符文不足");
                }

                if (rune != null && !rune.getItemMeta().equals(Material.AIR)) {
                    if (rune.isSimilar(FileTool.getItem(Config.getRune()))) {
                        if (BasicUtil.isAllow(item)) {
                            Data data = BasicUtil.getData(item);
                            if (data == null) {
                                player.sendMessage("未知错误，联系腐竹");
                                return;
                            }
                            /* 如果物品含有Lore，替换中间行数的所有Lore */
                            if (item.getItemMeta().hasLore()) {
                                int start = ItemStackUtil.getLoreIndex(item.getItemMeta().getLore(), Config.getList().get(0));
                                int end = ItemStackUtil.getLoreIndex(item.getItemMeta().getLore(), Config.getList().get(2));

                                if (end - start > 1) {
                                  //  for (int i = start + 1; i < end; i++) {
                                       // player.sendMessage("测试i " + i);
                                        //player.sendMessage("测试start " + start);
                                        //player.sendMessage("测试end " + end);
                                        //player.sendMessage("测试Lore" + item.getItemMeta().getLore());

                                        item = ItemUtil.removeAll(item);
                                    //}
                                } else { }

                                int s =  1;
                                item = ItemStackUtil.addLore(item, Config.getList().get(0));
                                item = ItemStackUtil.addLore(item, Config.getList().get(2));
                                for (int i = 0; i < data.getLore().split("#").length; i++) {
                                    String lore = data.getLore().split("#")[i] + BasicUtil.getRandom(
                                            Integer.parseInt(data.getRange().split("-")[1]),
                                            Integer.parseInt(data.getRange().split("-")[0]));
                                    inventory.setItem(11, ItemStackUtil.setLore(item, s, BasicUtil.getString(lore)));
                                    if (s < ItemStackUtil.getLoreIndex(item.getItemMeta().getLore(), Config.getList().get(2))) {
                                        s++;
                                    } else { }
                                    inventory.setItem(11, item);
                                    rune.setAmount(rune.getAmount() - 1);
                                    inventory.setItem(15, rune);
                                }
                            } else {
                                /* 开始考虑一开始没有lore的情况 */
                                int start = 1;
                                item = ItemStackUtil.addLore(item, Config.getList().get(0));
                                item = ItemStackUtil.addLore(item, Config.getList().get(2));
                                for (int i = 0; i < data.getLore().split("#").length; i++) {
                                    String lore = data.getLore().split("#")[i] + BasicUtil.getRandom(
                                            Integer.parseInt(data.getRange().split("-")[1]),
                                            Integer.parseInt(data.getRange().split("-")[0]));
                                   inventory.setItem(11, ItemStackUtil.setLore(item, start, BasicUtil.getString(lore)));
                                   if(start < ItemStackUtil.getLoreIndex(item.getItemMeta().getLore(), Config.getList().get(2))) {
                                       start++;
                                   }
                                }
                                inventory.setItem(11, item);
                                rune.setAmount(rune.getAmount() - 1);
                                inventory.setItem(15, rune);
                                if (data.isInfo()) {
                                    player.sendMessage(BasicUtil.getString("&a洗练成功"));
                                    Bukkit.broadcastMessage(BasicUtil.info(Config.getMessage().replace("%quality%", BasicUtil.info(data.getQuality()))));
                                } else {
                                    player.sendMessage(BasicUtil.getString("&a洗练成功"));
                                }
                            }
                        } else {
                            player.sendMessage(BasicUtil.getString("&c这个物品不在可洗练范围内"));
                        }
                    } else {
                        player.sendMessage(BasicUtil.getString("&c你放入的不是符文"));
                    }
                } else {
                    player.sendMessage(BasicUtil.getString("&c你瞎几把放的什么狗屁"));
                }
            }

        }
    }
}
