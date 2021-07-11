package com.menuplugin;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public class Items {
    private final Main plugin;
    public Items(Main plugin){
        this.plugin = plugin;
    }
    public ItemStack spawnItem(){
        //NamespacedKey key = new NamespacedKey(plugin, "71");
        //Glow glow = new Glow(plugin.key);
        ItemStack item = new ItemStack(Material.GOLD_BLOCK);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD+""+ChatColor.BOLD+"Меню спавна");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.BLUE+"Открывает меню спавна");
        lore.add(ChatColor.BLUE+"Также сдесь можно настроить спавн");
        //plugin.getLogger().info(String.valueOf(Enchantment.ARROW_DAMAGE));
        //plugin.getLogger().info(String.valueOf(plugin.glow));

        meta.addEnchant(plugin.glow,1,true);
        meta.setLore(lore);
        item.setItemMeta(meta);
        //plugin.getLogger().info(String.valueOf(item));
        return item;
    }
    public ItemStack spawnInfo(){
        ItemStack item = new ItemStack(Material.ENDER_EYE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN+""+ChatColor.BOLD+"Информация о спавне!");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.LIGHT_PURPLE+"Координаты спавна");
        lore.add(ChatColor.WHITE+"x: "+String.valueOf(plugin.data.getIntegerList("spawn").get(0)) );
        lore.add(ChatColor.WHITE+"y: "+String.valueOf(plugin.data.getIntegerList("spawn").get(1)) );
        lore.add(ChatColor.WHITE+"z: "+String.valueOf(plugin.data.getIntegerList("spawn").get(2)) );
        meta.addEnchant(plugin.glow,1,true);
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
    public ItemStack spawnChangeItem(){
        ItemStack item = new ItemStack(Material.DIAMOND_BLOCK);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.AQUA+""+ChatColor.BOLD+"Установить спавн");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_AQUA+"Нажмите, что бы");
        lore.add(ChatColor.DARK_AQUA+"установить новый спавн");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
    public  ItemStack spawnTeleportItem(){
        ItemStack item = new ItemStack(Material.ENDER_PEARL);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD+""+ChatColor.BOLD+"Телепорт на спавн");
        item.setItemMeta(meta);
        return item;
    }
    public ItemStack spawnPlayerTeleportItem(){
        ItemStack item = new ItemStack(Material.EMERALD_BLOCK);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.BLUE+""+ChatColor.BOLD+"Телепортировать игрока на спавн");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_BLUE+"Позволяет телепортировать");
        lore.add(ChatColor.DARK_BLUE+"игроков на спавн");
        lore.add(ChatColor.DARK_RED+"В разработке!");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
    public ItemStack playerHead(OfflinePlayer p){
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta smeta = (SkullMeta) item.getItemMeta();
        smeta.setOwningPlayer(p);
        item.setItemMeta(smeta);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.WHITE+"Нажмите на голову игрока,");
        lore.add(ChatColor.WHITE+"что бы телепортировать его");
        lore.add(ChatColor.WHITE+"на спавн");
        meta.setLore(lore);
        meta.setDisplayName(ChatColor.BOLD+""+p.getName());
        item.setItemMeta(meta);
        return item;
    }
    public ItemStack teleportAllPlayers(){
        ItemStack item = new ItemStack(Material.PAPER);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN+""+ChatColor.BOLD+"Телепортировать всех игроков");
        meta.addEnchant(plugin.glow,1,true);
        item.setItemMeta(meta);
        return item;
    }
    public  ItemStack errorItem(){
        ItemStack item = new ItemStack(Material.REDSTONE_BLOCK);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.RED+""+ChatColor.BOLD+"Слишком много игроков!");
        item.setItemMeta(meta);
        return item;
    }
}
