package me.moshe.croptop.listeners;

import me.moshe.croptop.CropTop;
import me.moshe.croptop.file.CropData;
import me.moshe.croptop.utils.Utils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.omg.CORBA.INTERNAL;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class CropListener implements Listener {
    private CropTop plugin;
    public CropListener(CropTop plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onCropBreak(BlockBreakEvent e){
        if(e.getBlock() == null) return;
        Material mat = e.getBlock().getType();
        UUID uuid = e.getPlayer().getUniqueId();
        List<String> crops = Utils.csl("cropBlocks");
        if(crops.contains(mat.toString())){
            incrementStat(uuid);
        }
    }


    public void incrementStat(UUID uuid){
        if(!getCropMap().containsKey(uuid)){
            getCropMap().put(uuid, 1);
            return;
        }
        int old = getCropMap().get(uuid);
        getCropMap().replace(uuid, old + 1);
    }

    public HashMap<UUID, Integer> getCropMap(){
        return CropData.cropMap;
    }
}
