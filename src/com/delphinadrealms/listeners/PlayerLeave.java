package com.delphinadrealms.listeners;

import com.delphinadrealms.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Created by henry27 on 10/21/2017.
 */
public class PlayerLeave implements Listener{

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        if (event.getPlayer().hasPermission("tickets.staff")) {
            try {
                Main.staffList.remove(event.getPlayer());
            } catch (Exception e) {

            }
        }
    }
}

