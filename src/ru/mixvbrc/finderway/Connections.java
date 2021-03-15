package ru.mixvbrc.finderway;

import org.bukkit.Location;

import java.util.HashMap;
import java.util.Map;

public class Connections {

    public static Map<Location, Location> connectionList = new HashMap<>();

    public static void setConnection(Location child, Location parent)
    {
        if (!connectionList.containsKey(child))
            connectionList.put(child, parent);
    }

    public static Location getConnection(Location child)
    {
        return connectionList.get(child);
    }
}
