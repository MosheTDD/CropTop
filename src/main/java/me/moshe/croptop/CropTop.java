package me.moshe.croptop;

import me.moshe.croptop.commands.CropTopCommand;
import me.moshe.croptop.file.CropData;
import me.moshe.croptop.listeners.CropListener;
import me.moshe.croptop.utils.Utils;
import org.bukkit.plugin.java.JavaPlugin;

public final class CropTop extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        getConfig().options().copyDefaults(true);
        CropData.setup();
        getServer().getPluginManager().registerEvents(new CropListener(this), this);
        new CropTopCommand(this);
        new Utils(this);
    }

    @Override
    public void onDisable() {
        CropData.save();
    }
}
