package com.menuplugin;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Main extends JavaPlugin implements Listener {
    public NamespacedKey key = new NamespacedKey(this,"glow");
    public static Glow glow;
    public Items it = new Items(this);
    public Inventories inventories;
    public Inventory pmenu;
    public Inventory spawnMenu;
    public InvChecks infun = new InvChecks(this);
    public File datafile;
    public FileConfiguration data;
    //private Inventory i;
    @Override
    public void onEnable() {
        getDataFolder().mkdirs();
        datafile = new File(getDataFolder(),"data.yml");
        if(!datafile.exists()){
            try {
                datafile.createNewFile();
                data = YamlConfiguration.loadConfiguration(datafile);
                data.createSection("spawn");
                List<Integer> spawnLoc = new ArrayList<>();
                spawnLoc.add(0);
                spawnLoc.add(0);
                spawnLoc.add(0);
                data.set("spawn",spawnLoc);
                data.save(datafile);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }

        }
        data = YamlConfiguration.loadConfiguration(datafile);
        registerGlow();
        File config = new File(getDataFolder(),"config.yml");
        if(!config.exists()){
            getLogger().info("Creating new config file...");
            getConfig().options().copyDefaults(true);
            saveDefaultConfig();
        }
        getLogger().info(String.valueOf(it.spawnItem()));
        //getLogger().info(String.valueOf(Bukkit.getServer().getWorld("world")));

        getLogger().info("MenuPlugin Enabled!");
        Bukkit.getPluginManager().registerEvents((Listener) this,this);
        getCommand("pmenu").setExecutor(this);
        getCommand("spawn").setExecutor(this);

        inventories = new Inventories(this);
        pmenu = inventories.pmenu();
        spawnMenu = inventories.spawnMenu();
    }

    @Override
    public void onDisable() {
        getLogger().info("MenuPlugin Disabled!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equals("spawn")){
            List<Integer> ints = data.getIntegerList("spawn");
            Player p = (Player) sender;

            World overworld = Bukkit.getServer().getWorld("world");
            Location loc = new Location(overworld,ints.get(0)+0.5,ints.get(1),ints.get(2)+0.5);
            p.teleport(loc);
            return true;
        }else{
            Player p = (Player) sender;
            p.openInventory(pmenu);
            return true;
        }

    }
    @EventHandler
    public void handle(InventoryClickEvent e){
        infun.spawnMenuFun(e);
        infun.spawnSettingsFun(e);
        infun.spawnPlayersTeleport(e);



    }
    public void registerGlow(){
        try{
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null,true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try{

            glow = new Glow(key);
            Enchantment.registerEnchantment(glow);
        }catch (IllegalArgumentException e){
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
