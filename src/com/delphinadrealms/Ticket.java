package com.delphinadrealms;

import org.bukkit.entity.Player;

/**
 * Created by henry27 on 10/21/2017.
 */
public class Ticket {

    private Player player;

    private String ticketInfo;



    public Player getTicketSender() {
        return this.player;
    }


    public String getTicketInfo() {
        return ticketInfo;
    }

    public Ticket(Player sender, String ticketInfo) {
        player = sender;
        this.ticketInfo = ticketInfo;
    }
}
