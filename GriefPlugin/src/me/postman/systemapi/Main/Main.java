package me.postman.systemapi.Main;

import me.postman.systemapi.Listener.Chat;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.UUID;

public class Main extends JavaPlugin
{

    public static String prefix = "§cGriefIT §8| §c";

    public static Main instance;

    public static ArrayList<Player>inmode = new ArrayList<>();


    public void onEnable()
    {
        System.out.println(UUID.randomUUID().toString());

        Bukkit.getPluginManager().registerEvents(new Chat(), this);

    }



}
