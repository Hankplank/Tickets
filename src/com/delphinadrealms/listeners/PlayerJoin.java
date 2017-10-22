package com.delphinadrealms.listeners;

import com.delphinadrealms.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Created by henry27 on 10/21/2017.
 */
public class PlayerJoin implements Listener {


    public PlayerJoin() {

    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (event.getPlayer().hasPermission("tickets.staff")) {
            Main.staffList.add(event.getPlayer().getUniqueId());
        }
    }
}
