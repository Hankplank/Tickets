package com.delphinadrealms;

import com.delphinadrealms.commands.TicketCommands;
import com.delphinadrealms.listeners.PlayerJoin;
import com.delphinadrealms.listeners.PlayerLeave;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
 * Created by henry27 on 10/21/2017.
 */
public class Main extends JavaPlugin {

    public static ArrayList<Player> staffList;
    public static ArrayList<Ticket> tickets;

    @Override
    public void onEnable() {
        staffList = new ArrayList<>();
        tickets = new ArrayList<>();
        for (Player player : this.getServer().getOnlinePlayers()) {
            if (player.hasPermission("tickets.staff")){
                staffList.add(player);
            }

            }
        getServer().getPluginManager().registerEvents(new PlayerJoin(),this);
        getServer().getPluginManager().registerEvents(new PlayerLeave(),this);
        this.getCommand("ticket").setExecutor(new TicketCommands());

    }

    @Override
    public void onDisable() {

    }

}
