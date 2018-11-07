package alchemy.sophistication.command;

import alchemy.sophistication.file.Config;
import alchemy.sophistication.gui.Guibuilder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.sct.core.util.ItemStackUtil;

public class OnCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if(args[0].equalsIgnoreCase("open")) {
                player.closeInventory();
                Guibuilder.open(player, true);
            }
            if(args[0].equalsIgnoreCase("open2")) {
                player.closeInventory();
                Guibuilder.open(player, false);
            }
            if(args[0].equalsIgnoreCase("reload")) {
                Config.reload();
                sender.sendMessage("重载好了");
            }
            if(args[0].equalsIgnoreCase("print")) {
                Config.print();
            }
            if(args[0].equalsIgnoreCase("t")) {
                player.getInventory().setItemInMainHand(ItemStackUtil.removeLore(player.getInventory().getItemInMainHand(), 0));
            }
        }
        return false;
    }
}
