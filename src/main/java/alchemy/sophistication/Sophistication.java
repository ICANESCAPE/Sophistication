package alchemy.sophistication;

import alchemy.sophistication.command.OnCommand;
import alchemy.sophistication.file.Config;
import alchemy.sophistication.listener.InventroyCloseListener;
import alchemy.sophistication.listener.onClick;
import alchemy.sophistication.util.BasicUtil;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("ALL")
public final class Sophistication extends JavaPlugin {

    private static Sophistication instance;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        Config.reload();
        Bukkit.getPluginCommand("so").setExecutor(new OnCommand());
        Bukkit.getPluginManager().registerEvents(new onClick(), this);
        Bukkit.getPluginManager().registerEvents(new InventroyCloseListener(), this);
    }

    @Override
    public void onDisable() {
        BasicUtil.info("&c卸载完成");
    }

    public static Sophistication getInstance() {
        return instance;
    }
}
