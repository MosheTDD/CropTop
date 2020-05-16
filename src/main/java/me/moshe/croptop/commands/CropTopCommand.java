package me.moshe.croptop.commands;

import com.google.gson.annotations.Until;
import me.moshe.croptop.CropTop;
import me.moshe.croptop.file.CropData;
import me.moshe.croptop.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class CropTopCommand implements CommandExecutor {
    private CropTop plugin;
    public CropTopCommand(CropTop plugin){
        this.plugin = plugin;
        plugin.getCommand("croptop").setExecutor(this);
    }

    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if(!Utils.playerCheck(sender)) return true;
        if (!Utils.permCheck(sender, "croptop.croptop")) return true;
        if (!Utils.argsCheck(sender, 0, args)) return true;
        Map<UUID, Integer> keys = getCropMap().entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                LinkedHashMap::new));
        Player p = (Player) sender;
        openCropTopGUI(p, keys);



        return false;
    }

    public void openCropTopGUI(Player p, Map<UUID, Integer> map){
        Inventory inv = Bukkit.createInventory(null, 2*9, Utils.colorize("&6&lCropTop Leaderboard"));
        int i = 0;
        for(Map.Entry<UUID, Integer> entry : map.entrySet()){
            if(i == 10)break;
            OfflinePlayer player = Bukkit.getOfflinePlayer(entry.getKey());
            int cropBroke = entry.getValue();
            int place = i + 1;
            List<String> configLore = Utils.csl("lore");
            ArrayList<String> lore = new ArrayList<>();
            for(String s : configLore){
                lore.add(s.replaceAll("%crops%", String.valueOf(cropBroke)));
            }
            ItemStack head = Utils.createItem(Material.SKULL_ITEM, "&6" + place + ". " + "&b" + player.getName(), lore, (short) 3);
            SkullMeta meta = (SkullMeta) head.getItemMeta();
            meta.setOwningPlayer(player);
            head.setItemMeta(meta);
            inv.setItem(i, head);
            i++;
        }
        p.openInventory(inv);
    }

    public HashMap<UUID, Integer> getCropMap(){
        return CropData.cropMap;
    }
}
