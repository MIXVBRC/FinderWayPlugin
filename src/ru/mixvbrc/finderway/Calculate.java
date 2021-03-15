package ru.mixvbrc.finderway;

import org.bukkit.Location;

public class Calculate {

    public static double getDistance(Location aLocation, Location bLocation) {
        return Math.sqrt( Math.pow(aLocation.getX() - bLocation.getX(),2) + Math.pow(aLocation.getY() - bLocation.getY(),2) + Math.pow(aLocation.getZ() - bLocation.getZ(),2) );
    }
}
