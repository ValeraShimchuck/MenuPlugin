package com.menuplugin;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.meta.SkullMeta;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class InvChecks {
    private Inventory playersMenu = null;
    public Main plugin;
    public InvChecks(Main plugin) {
        this.plugin = plugin;
    }
    public void spawnMenuFun(InventoryClickEvent e){
        Inventory inv = e.getInventory();
        Inventory c = e.getClickedInventory();
        if(inv.equals(plugin.pmenu)){
            if(c == null) return;
            if(!c.equals(plugin.pmenu)) {
                e.setCancelled(true);
                return;
            }
            if(e.getCurrentItem().getType().equals(Material.AIR)) return;
            plugin.getLogger().info(String.valueOf(e.getCurrentItem()));
            if(e.getCurrentItem().equals(plugin.it.spawnItem())){


                Player p = (Player) e.getWhoClicked();
                plugin.getLogger().info(String.valueOf(p));
                p.closeInventory();
                p.openInventory(plugin.spawnMenu);
            }
            e.setCancelled(true);
            //if(c == null) return;
            //if(c.equals(inv)) e.setCancelled(true);
        }
    }
    public void spawnSettingsFun(InventoryClickEvent e){
        Inventory inv = e.getInventory();
        Inventory c = e.getClickedInventory();
        if(inv.equals(plugin.spawnMenu)){
            if(c == null) return;
            if(!c.equals(plugin.spawnMenu)){
                e.setCancelled(true);
                return;
            }
            if(e.getCurrentItem().getType().equals(Material.AIR))return;
            if(e.getCurrentItem().equals(plugin.it.spawnChangeItem())){
                Player p = (Player) e.getWhoClicked();
                Location loc = p.getLocation();
                if(loc.getWorld().equals(Bukkit.getServer().getWorld("world"))){
                   List<Integer> spawnLoc = new ArrayList<>();
                   spawnLoc.add(loc.getBlockX());
                   spawnLoc.add(loc.getBlockY());
                   spawnLoc.add(loc.getBlockZ());
                   plugin.data.set("spawn",spawnLoc);
                   try {
                       plugin.data.save(plugin.datafile);
                   } catch (IOException ioException) {
                       ioException.printStackTrace();
                   }
                   plugin.inventories.spawnChangeItem = plugin.it.spawnChangeItem();
                   plugin.spawnMenu = plugin.inventories.spawnMenu();
                   p.closeInventory();
                   p.sendMessage("Спавн успешно установлен!");
                }
            }
            if(e.getCurrentItem().equals(plugin.it.spawnTeleportItem())){
                Player p = (Player) e.getWhoClicked();
                List<Integer> ints = plugin.data.getIntegerList("spawn");
                World overworld = Bukkit.getServer().getWorld("world");
                Location loc = new Location(overworld,ints.get(0)+0.5,ints.get(1),ints.get(2)+0.5);
                p.teleport(loc);
                p.closeInventory();
                p.sendMessage("Телепорт произошел успешно!");
            }
            if(e.getCurrentItem().equals(plugin.it.spawnPlayerTeleportItem())){
                Player p = (Player) e.getWhoClicked();
                p.closeInventory();
                playersMenu = plugin.inventories.playerMenu();
                p.openInventory(playersMenu);
            }
            e.setCancelled(true);
        }
    }
    public void spawnPlayersTeleport(InventoryClickEvent e){
        Inventory inv = e.getInventory();
        Inventory c = e.getClickedInventory();
        //Inventory playersinv = plugin.inventories.playerMenu();
        if(playersMenu == null)return;
        if(inv.equals(playersMenu)){
            if(c == null)return;
            if(!c.equals(playersMenu)){
                e.setCancelled(true);
                return;
            }
            if(e.getCurrentItem().equals(Material.AIR))return;
            if(e.getCurrentItem().getType().equals(Material.PLAYER_HEAD)){
                plugin.getLogger().info("pass");
                SkullMeta skull = (SkullMeta) e.getCurrentItem().getItemMeta();
                Player p = skull.getOwningPlayer().getPlayer();
                plugin.getLogger().info(String.valueOf(p));
                if(p != null){
                    plugin.getLogger().info("pass");
                    List<Integer> ints = plugin.data.getIntegerList("spawn");
                    World overworld = Bukkit.getServer().getWorld("world");
                    plugin.getLogger().info("pass");
                    Location loc = new Location(overworld,ints.get(0)+0.5,ints.get(1),ints.get(2)+0.5);
                    p.teleport(loc);
                    //p.closeInventory();
                    p.sendMessage("Вас телепортировали!");

                }
            }
            if(e.getCurrentItem().equals(plugin.it.teleportAllPlayers())){
                Collection<Player> players = (Collection<Player>) Bukkit.getServer().getOnlinePlayers();
                for(Player p:players){
                    if(p != null){
                        plugin.getLogger().info("pass");
                        List<Integer> ints = plugin.data.getIntegerList("spawn");
                        World overworld = Bukkit.getServer().getWorld("world");
                        plugin.getLogger().info("pass");
                        Location loc = new Location(overworld,ints.get(0)+0.5,ints.get(1),ints.get(2)+0.5);
                        p.teleport(loc);
                        //p.closeInventory();
                        p.sendMessage("Вас телепортировали!");

                    }
                }
            }
            e.setCancelled(true);
        }
    }
}
