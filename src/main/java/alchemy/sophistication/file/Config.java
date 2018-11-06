package alchemy.sophistication.file;

import alchemy.sophistication.Sophistication;
import alchemy.sophistication.dto.Data;

import org.bukkit.configuration.ConfigurationSection;
import org.inventivetalent.itembuilder.util.FileUtil;
import org.sct.core.Core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Config extends FileUtil {

    private static Config config;

    public Config() { super(Sophistication.getInstance(), "config.yml"); }

    static List<Data> dataList = new ArrayList<>();
    static Map<String, Integer> equipMap = new HashMap<>();
    static Map<String, Integer> weaponMap = new HashMap<>();

    public static void reload() { config = new Config(); }

    @Override
    public void check() {
        ConfigurationSection cs = this.getConfigurationSection("Lottery");
        for (String key : cs.getKeys(false)) {
            dataList.add(new Data(
                    cs.getString(key + ".lore"),
                    cs.getString(key + ".range"),
                    cs.getString(key + ".quality"),
                    cs.getInt(key + ".chance"),
                    cs.getBoolean(key + ".info"),
                    cs.getBoolean(key + ".equip"))
            );

            for(Data data : getDataList()) {
                if(data.isEquip()) {
                    equipMap.put(cs.getString(key +".lore"), cs.getInt(key + ".chance"));
                } else {
                    weaponMap.put(cs.getString(key +".lore"), cs.getInt(key + ".chance"));
                }
            }
        }

    }

    public static void print() { Core.info("Equip:"+equipMap); Core.info("Weapon:" + weaponMap); }

    public static List<Data> getDataList() { return dataList; }

    public static List<Integer> getAllowList() { return config.getIntegerList("AllowList"); }

    public static Map<String, Integer> getEquipMap() { return equipMap; }

    public static Map<String, Integer> getWeaponMap() { return weaponMap; }

    public static String getRune() { return config.getString("rune"); }

    public static String getItem() { return config.getString("kong"); }

    public static String getMessage() { return config.getString("message"); }

    public static String getConfirm() { return config.getString("confirm"); }

    public static List<String> getList() { return config.getStringList("Lore"); }

    public static String getTool() { return config.getString("tool"); }

    public static Data getData(String lore) {
        if(lore == null) return null;
        for(Data data : getDataList()) {
            if(data.getLore().equals(lore)) {
                return data;
            }
        }
        return null;
    }

}
