package ru.mixvbrc.finderway;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Mine extends JavaPlugin {

    @Override
    public void onEnable()
    {
        Bukkit.getPluginManager().registerEvents(new Handler(), this);
    }
}
