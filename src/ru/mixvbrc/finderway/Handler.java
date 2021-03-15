package ru.mixvbrc.finderway;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;
import java.util.List;

public class Handler implements Listener {

    private Location startLocation;
    private Location endLocation;

    List<Location> lb = new ArrayList<>();
    public static List<Location> la = new ArrayList<>();

    @EventHandler
    public void onBlockPlaceEvent(BlockPlaceEvent event)
    {
        if (event.getBlock().getType().equals(Material.GOLD_BLOCK))
        {
            startLocation = event.getBlock().getLocation();
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent event)
    {
        Player player = event.getPlayer();

        endLocation = player.getLocation();

        if (lb != null)
        {
            for (Location l : lb)
                l.getBlock().setType(Material.AIR);
            lb.clear();
        }

        if (la != null)
        {
            for (Location l : la)
                l.getBlock().setType(Material.AIR);
            la.clear();
        }


        if (player.getInventory().getItemInMainHand().getType().equals(Material.PAPER))
        {
            if (startLocation != null)
            {
                for (Location l : Way.getWay(startLocation, endLocation))
                {
                    lb.add(l);
                    l.getBlock().setType(Material.RED_WOOL);
                }

                for (Location l : la)
                {
                    l.getBlock().setType(Material.GLASS);
                }
            }
        }
    }
}
