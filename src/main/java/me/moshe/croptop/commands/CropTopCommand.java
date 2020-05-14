package me.moshe.croptop.commands;

import com.google.gson.annotations.Until;
import me.moshe.croptop.CropTop;
import me.moshe.croptop.file.CropData;
import me.moshe.croptop.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

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
        if (!Utils.permCheck(sender, "croptop.croptop")) return true;
        if (!Utils.argsCheck(sender, 0, args)) return true;
        Map<UUID, Integer> keys = getCropMap().entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                LinkedHashMap::new));
        Utils.sendRawMsg(sender, "&6---------------&f=&cCropTop&f=&6---------------");
        int i = 0;
        for (Map.Entry<UUID, Integer> entry : keys.entrySet()) {
            if(i == 10) break;
            String pName = Bukkit.getOfflinePlayer(entry.getKey()).getName();
            int num = entry.getValue();
            int place = i + 1;
            sender.sendMessage(Utils.colorize("&c" + place + ". &b" + pName + "'s &aCrops &c" + num));
            i++;
        }
        Utils.sendRawMsg(sender, "&6---------------------------------------");



        return false;
    }

    public HashMap<UUID, Integer> getCropMap(){
        return CropData.cropMap;
    }
}
