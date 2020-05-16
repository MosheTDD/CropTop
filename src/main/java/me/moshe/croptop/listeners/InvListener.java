package me.moshe.croptop.listeners;

import me.moshe.croptop.CropTop;
import me.moshe.croptop.utils.Utils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;

public class InvListener implements Listener {
    private CropTop plugin;
    public InvListener(CropTop plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onClick(InventoryClickEvent e){
        if(e.getView().getTitle().equalsIgnoreCase(Utils.colorize("&6&lCropTop Leaderboard"))){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDrag(InventoryDragEvent e){
        if(e.getView().getTitle().equalsIgnoreCase(Utils.colorize("&6&lCropTop Leaderboard"))){
            e.setCancelled(true);
        }
    }
}
