package alchemy.sophistication.listener;

import alchemy.sophistication.dto.Data;
import alchemy.sophistication.file.Config;
import alchemy.sophistication.util.BasicUtil;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import org.bukkit.inventory.meta.ItemMeta;
import org.sct.core.Core;
import org.sct.core.file.FileTool;
import org.sct.core.util.ItemStackUtil;

import java.util.List;

public class InventroyClickListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if(e.getWhoClicked() instanceof Player) {
            Player player = (Player) e.getWhoClicked();
            Inventory inventory = e.getInventory();
            int slot = e.getSlot();

            if(inventory.getName().contains("装备洗练") || inventory.getName().equals("我也不知道叫啥名字")) {
                if(e.getCurrentItem().isSimilar(FileTool.getItem(Config.getItem()))) {
                    e.setCancelled(true);
                } else { Core.info("测试信息"); }
            }

            if(inventory.getName().equals(BasicUtil.getString("&d&l装备洗练"))) {
                if(slot == 31) {
                    e.setCancelled(true);
                    ItemStack rune = inventory.getItem(15);
                    ItemStack item = inventory.getItem(11);
                    if(item == null || item.getItemMeta().equals(Material.AIR) ) {
                        player.sendMessage("曹尼玛瞎几把点什么呢");
                    } else if(rune == null || rune.getItemMeta().equals(Material.AIR)) {
                        player.sendMessage("你的符文不足");
                    }
                    if(rune != null || !rune.getItemMeta().equals(Material.AIR)) {
                        if (rune.isSimilar(FileTool.getItem(Config.getRune()))) {
                            if (BasicUtil.isAllow(item)) {
                                Data data = BasicUtil.getData(item);
                                if (data == null) {
                                    player.sendMessage("未知错误，联系腐竹");
                                    return;
                                }


                                // 首选删除两行
                                if(item.hasItemMeta() && item.getItemMeta().hasLore()){

                                    // 首先判断是否存在
                                    int startIndex = ItemStackUtil.getLoreIndex(item.getItemMeta().getLore(),Config.getList().get(0));
                                    int endIndex = startIndex + 1;
                                    if(startIndex == -1){  // 没有进行过洗练
                                        // 把Config的两行添加
                                        ItemStackUtil.addLore(item,Config.getList().get(0));
                                        ItemStackUtil.addLore(item,Config.getList().get(2));
                                        startIndex = ItemStackUtil.getLoreIndex(item.getItemMeta().getLore(),Config.getList().get(0));
                                    }

                                    // 获取到最后一行
                                    endIndex = ItemStackUtil.getLoreIndex(item.getItemMeta().getLore(),Config.getList().get(2));

                                    ItemMeta itemMeta = item.getItemMeta();
                                 //   List<String> loreList = itemMeta.getLore();

                                    // 删除startIndex 到 endIndex 之间的lore
                                    if(endIndex - startIndex == 1){
                                        // DO NOTHING
                                    }else{
                                        for(int i = startIndex + 1; i < endIndex; i++){
                                            ItemStackUtil.removeLore(item, i);
                                           // loreList.remove(i);
                                        }
                                    }

                                    // 在startIndex 到 endIndex 之间把洗练得到的lore添加
                                    startIndex = startIndex + 1;
                                    for(int i = 0; i < data.getLore().split("#").length; i ++) {
                                        String lore = data.getLore().split("#")[i] + BasicUtil.getRandom(
                                                Integer.parseInt(data.getRange().split("-")[1]),
                                                Integer.parseInt(data.getRange().split("-")[0]));
                                        ItemStackUtil.setLore(item, startIndex + 1, lore);
                                        startIndex ++;
                                    }
                                }else{
                                    // 把Config的两行添加
                                    ItemStackUtil.addLore(item, Config.getList().get(0));
                                    ItemStackUtil.addLore(item, Config.getList().get(2));
                                    int startIndex = 1;
                                    Core.info("获取到的Lore信息: " + data.getLore());
                                  //  List<String> list = item.getItemMeta().getLore();
                                    // 在startIndex 到 endIndex 之间把洗练得到的lore添加
                                    for(int i = 0; i < data.getLore().split("#").length; i ++) {
                                        String lore = data.getLore().split("#")[i] + BasicUtil.getRandom(
                                                Integer.parseInt(data.getRange().split("-")[1]),
                                                Integer.parseInt(data.getRange().split("-")[0]));
                                        Core.info("添加Lore: " + lore);
                                        ItemStackUtil.setLore(item, startIndex, lore);
                                        //list.add(startIndex,lore);
                                        startIndex ++;
                                    }
                                    //ItemMeta itemMeta = item.getItemMeta();
                                    //itemMeta.setLore(list);
                                  //  item.setItemMeta(itemMeta);
                                }

                                inventory.setItem(11,item);
                                rune.setAmount(rune.getAmount() - 1);
                                inventory.setItem(15, rune);
                                if(data.isInfo()) {
                                    Bukkit.broadcastMessage(BasicUtil.info(Config.getMessage().replace("%quality%", BasicUtil.info(data.getQuality()))));
                                } else {
                                    player.sendMessage(BasicUtil.getString("&a洗练成功"));
                                }
                            } else {
                                player.sendMessage(BasicUtil.getString("&c这不是一个可打造的武器"));
                            }
                        } else {
                            player.sendMessage(BasicUtil.getString("&c物品放置位置错误"));
                        }
                    } else if(rune == null || !rune.getItemMeta().equals(Material.AIR)){
                        player.sendMessage("未知错误");
                    }
                }
            }
        }
    }
}
