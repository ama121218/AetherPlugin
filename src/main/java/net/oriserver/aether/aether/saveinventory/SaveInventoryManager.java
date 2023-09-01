package net.oriserver.aether.aether.saveinventory;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;

public class SaveInventoryManager {

    HashMap<String,SaveInventory> saveInventoryMap = new HashMap<String,SaveInventory>();

    public void createInventory(Player p,String s){
        if(saveInventoryMap.get(String.valueOf(p.getUniqueId()))==null){
            SaveInventory saveInventory = new SaveInventory(p);
            saveInventory.createInventory(p,s);
            saveInventoryMap.put(String.valueOf(p.getUniqueId()),saveInventory);
        }else{
            saveInventoryMap.get(String.valueOf(p.getUniqueId())).createInventory(p,s);
        }
    }
    public void setInventory1(Player p){
        if(saveInventoryMap.get(String.valueOf(p.getUniqueId()))==null)p.sendMessage("保存されているインベントリーはありません");
        else saveInventoryMap.get(String.valueOf(p.getUniqueId())).setInventory1(p);
    }
    public void setInventory2(Player p){
        if(saveInventoryMap.get(String.valueOf(p.getUniqueId()))==null)p.sendMessage("保存されているインベントリーはありません");
        else saveInventoryMap.get(String.valueOf(p.getUniqueId())).setInventory2(p);
    }
    public void deleteInventory(Player p){
        if(saveInventoryMap.get(String.valueOf(p.getUniqueId()))==null)p.sendMessage("保存されているインベントリーはありません");
        else saveInventoryMap.get(String.valueOf(p.getUniqueId())).deleteInventory(p);
    }
    public void changeName(Player p,String s){
        if(saveInventoryMap.get(String.valueOf(p.getUniqueId()))==null){
            SaveInventory saveInventory = new SaveInventory(p);
            saveInventory.changeName(s);
            saveInventoryMap.put(String.valueOf(p.getUniqueId()),saveInventory);
        }else{
            saveInventoryMap.get(String.valueOf(p.getUniqueId())).changeName(s);
        }
    }
}
