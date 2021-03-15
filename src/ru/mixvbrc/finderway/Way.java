package ru.mixvbrc.finderway;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public class Way {

    public static Location start;
    public static Location end;

    public static List<Location> getWay(Location sl, Location el)
    {
        start = Area.standardizeLocation(sl);
        end = Area.standardizeLocation(el);

        Weight.DISTANCE = Calculate.getDistance(start, end);

        Location now = Area.standardizeLocation(sl);

        for (int i =0; i < 5000; i++)
//        while (!Area.compareLocations(end, now))
        {
            Area.open.addAll(Area.getLocations(now, 1,1,1));

            double weight = 0;

            for (Location l : Area.open)
            {
                if (Weight.weightList.get(l) > weight)
                {
                    weight = Weight.weightList.get(l);
                    now = l;
                }
            }

            if (Area.compareLocations(end, now)) break;
            Weight.TRAVELED_DISTANCE += weight;

            Area.open.remove(now);
            Area.close.add(now);
        }

        Bukkit.broadcastMessage("Finish!");

        List<Location> result = new ArrayList<>();
        while (!Area.compareLocations(start, now))
        {
            now = Connections.getConnection(now);
            result.add(now);
        }

        Handler.la = new ArrayList<>(Area.open);
        Area.close.clear();
        Area.open.clear();
        Weight.weightList.clear();
        Connections.connectionList.clear();
        Area.locationList.clear();

        return result;
    }
}
