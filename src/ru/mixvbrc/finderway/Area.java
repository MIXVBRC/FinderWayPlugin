package ru.mixvbrc.finderway;

import org.bukkit.Location;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public class Area {

    public static List<Location> locationList = new ArrayList<>();

    public static List<Location> open = new ArrayList<>();
    public static List<Location> close = new ArrayList<>();

    public static List<Location> getLocations(Location l, int radiusX, int radiusY, int radiusZ) {

        List<Location> result = new ArrayList<>();

        Integer[] startXYZ = {
                (int)l.getX() - radiusX,
                (int)l.getY() - radiusY,
                (int)l.getZ() - radiusZ,
        };

        Integer[] endXYZ = {
                (int)l.getX() + radiusX,
                (int)l.getY() + radiusY,
                (int)l.getZ() + radiusZ,
        };

        for (int x = startXYZ[0]; x<= endXYZ[0]; x++)
        {
            for (int y=startXYZ[1]; y <= endXYZ[1]; y++)
            {
                for (int z = startXYZ[2]; z<=endXYZ[2]; z++)
                {
                    Location nl = new Location(l.getWorld(), x, y, z);

                    if (Calculate.getDistance(l,nl) > 1) continue;
                    if (locationList.contains(nl)) continue;

                    locationList.add(nl);
                    Connections.setConnection(nl, l);

                    if (!checkAir(nl) || !checkAir(getLocation(nl,0,1,0))) continue;

                    result.add(nl);
                    Weight.setWeight(nl);
                }
            }
        }

        return result;
    }

    public static boolean compareLocations(Location al, Location bl)
    {
        return al.getX() == bl.getX()
                && al.getY() == bl.getY()
                && al.getZ() == bl.getZ();
    }

    public static Location getLocation(Location location, double x, double y, double z)
    {
        return new Location(location.getWorld(),location.getX()+x,location.getY()+y,location.getZ()+z);
    }

    public static Location standardizeLocation(Location l)
    {
        return new Location(l.getWorld(), Math.floor(l.getX()),Math.floor(l.getY()),Math.floor(l.getZ()));
    }

    public static void addLocation(Location nl, Location l)
    {
        if (!locationList.contains(nl))
        {
            locationList.add(nl);
            Connections.setConnection(nl, l);
            Weight.setWeight(nl);
            open.add(nl);
        }
    }

    public static boolean checkAir(Location l)
    {
        if (l.getBlock().isPassable()) return true;
        return false;
    }
}
