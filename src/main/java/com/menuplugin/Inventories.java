package com.menuplugin;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;

public class Inventories {
    //public Inventory menu = pmenu();
    public ItemStack spawnChangeItem;
    private final Main plugin;
    public Inventories(Main plugin) {
        this.plugin = plugin;
    }
    public Inventory pmenu(){
        Inventory i = Bukkit.createInventory(null,3*9,"INV");
        //ItemStack item = new ItemStack(Material.ARROW);
        i.setItem(13,plugin.it.spawnItem());
        return i;
    }
    public Inventory spawnMenu(){
        Inventory i = Bukkit.createInventory(null,3*9,"spawn options");
        spawnChangeItem = plugin.it.spawnChangeItem();
        i.setItem(14,plugin.it.spawnPlayerTeleportItem());
        i.setItem(13,plugin.it.spawnInfo());
        i.setItem(12,spawnChangeItem);
        i.setItem(4,plugin.it.spawnTeleportItem());
        return i;
    }
    public Inventory playerMenu(){
        Inventory i = Bukkit.createInventory(null,3*9,"Players");
        Collection<Player> players = (Collection<Player>) Bukkit.getServer().getOnlinePlayers();
        Integer iter = 0;
        if(players.size() <= 18){
            for(Player p: players){
                i.setItem(iter,plugin.it.playerHead(p));
                iter++;
            }
            i.setItem(26,plugin.it.teleportAllPlayers());
        }else{
            for(int cyc=0; cyc<27;cyc++){
                i.setItem(cyc,plugin.it.errorItem());
            }
        }

        return i;
    }
}
