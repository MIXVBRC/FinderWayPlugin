package ru.mixvbrc.finderway;

import org.bukkit.Location;
import org.bukkit.Material;

import java.util.HashMap;
import java.util.Map;

public class Weight {

    private static final double WALK_ONE_BLOCK_COST = 4.317; // 4.633
    private static final double RUN_ONE_BLOCK_COST = 5.6; // 3.571
    private static final double SIT_ONE_BLOCK_COST = 1.3; // 15.384

    private static final double SWIMMING_ONE_BLOCK_COST = 2.20; // 9.090

    private static final double LADDER_UP_ONE_COST = 2.35; // 8.511
    private static final double LADDER_DOWN_ONE_COST = 3.0; // 6.667

    private static final double BUILD_UP_ONE_BLOCK = 2.0; // 6.667

    private static final double[] FALLING_COSTS = generateFallingCosts();

    public static double DISTANCE = Calculate.getDistance(Way.start, Way.end);

    public static double TRAVELED_DISTANCE = 0;

    public static Map<Location, Double> weightList = new HashMap<>();

    private static double[] generateFallingCosts()
    {
        double[] costs = new double[257];
        for (int a = 0; a < 257; a++)
            if (a > 0)
                costs[a] = a;
            else
                costs[a] = 0;
        return costs;
    }

    public static void setWeight(Location l)
    {
        double weight = DISTANCE - Calculate.getDistance(l, Way.end);

        weight = weight + getFactor(l);

        weightList.put(l, weight * 0.001);
    }

    private static double getFactor(Location l)
    {
        double factor = 0;

        if (l.getY() <= Connections.getConnection(l).getY() && Area.checkAir(Area.getLocation(l, 0,-1,0)))
        {

            Location nl = Area.getLocation(l, 0,0,0);
            for (int i = 1; i <= 257; i++)
            {
                nl = Area.getLocation(l, 0,-i,0);
                if (!Area.checkAir(nl)) break;

                factor = i / 257D;
            }
            Area.addLocation(nl.add(0,1,0), l);

        } else if (l.getY() > Connections.getConnection(l).getY() && Area.checkAir(Area.getLocation(l, 0,-1,0))) {
            factor = BUILD_UP_ONE_BLOCK;
        } else if (l.getY() > Connections.getConnection(l).getY()) {
            factor = WALK_ONE_BLOCK_COST / 1.5;
        } else {
            factor = WALK_ONE_BLOCK_COST;
        }

        if (l.getBlock().getType().equals(Material.WATER) || l.getBlock().getType().equals(Material.LAVA))
            factor += SWIMMING_ONE_BLOCK_COST;

        return factor;
    }


}
