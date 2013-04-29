package com.github.jamesnorris.util;

import java.io.Serializable;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class GameObjectOrientation implements Serializable {
    private static final long serialVersionUID = -3122300037618945597L;
    public String type, worldName;
    public int x, y, z, x2 = 0, y2 = 0, z2 = 0, Xdif, Ydif, Zdif, Xdif2 = 0, Ydif2 = 0, Zdif2 = 0, typeid, typeid2;

    public GameObjectOrientation(Location self, Location key, String type) {
        worldName = self.getWorld().getName();
        this.type = type;
        x = self.getBlockX();
        y = self.getBlockY();
        z = self.getBlockZ();
        Xdif = key.getBlockX() - x;
        Ydif = key.getBlockY() - y;
        Zdif = key.getBlockZ() - z;
        typeid = self.getBlock().getTypeId();
    }

    public void addSecondLocation(Location loc2, Location key) {
        x2 = loc2.getBlockX();
        y2 = loc2.getBlockY();
        z2 = loc2.getBlockZ();
        Xdif2 = key.getBlockX() - x2;
        Ydif2 = key.getBlockY() - y2;
        Zdif2 = key.getBlockZ() - z2;
        typeid2 = loc2.getBlock().getTypeId();
    }

    public Location getLocation() {
        return new Location(Bukkit.getWorld(worldName), x, y, z);
    }

    public Location getSecondLocation() {
        return new Location(Bukkit.getWorld(worldName), x2, y2, z2);
    }
}
