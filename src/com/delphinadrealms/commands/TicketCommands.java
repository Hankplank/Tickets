package com.delphinadrealms.commands;

import com.delphinadrealms.Main;
import com.delphinadrealms.Ticket;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

/**
 * Created by henry27 on 10/21/2017.
 */
public class TicketCommands implements CommandExecutor {


    public int playerTickets(Player player) {
        int count = 0;
        for (int i =0;i < Main.tickets.size();i++) {
            if (Main.tickets.get(i).getTicketSender().getName() == player.getName())
                count++;
        }
        return count;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length > 0) {
            switch (args[0]) {
                case "help":
                    if (sender instanceof Player) {
                        sender.sendMessage(ChatColor.LIGHT_PURPLE + "[Tickets]\n/ticket help  : Shows this message\n/ticket create  : Creates new ticket");
                    }
                    break;
                case "create":
                    if (sender instanceof Player && playerTickets(((Player) sender).getPlayer()) < 3 && sender.hasPermission("tickets.staff")) {
                        List<String> ticketInfo = new ArrayList<>();
                        for (int x =1;x<args.length;x++) {
                            ticketInfo.add(args[x]);
                        }
                        String ticketz = ticketInfo.toString().replace("[","");
                        ticketz = ticketz.replace("]","");
                        ticketz = ticketz.replace(",","");
                        Main.tickets.add(new Ticket(((Player) sender).getPlayer(),ticketz));
                        sender.sendMessage("You have a created a ticket with the info: " + ticketz);
                    }
                    break;
                case "list":
                    if (sender instanceof Player && sender.hasPermission("tickets.staff")) {
                        if (!Main.tickets.isEmpty()) {
                            String ticketInfo = "";
                            for (int i =0;i < Main.tickets.size();i++) {
                                ticketInfo = ticketInfo + (i+1) + ". Player: " + Main.tickets.get(i).getTicketSender().getName() + " Info: " + Main.tickets.get(i).getTicketInfo() + "\n";
                            }
                            sender.sendMessage("Number of tickets: " + Main.tickets.size());
                            sender.sendMessage(ticketInfo);
                        } else {
                            sender.sendMessage("No tickets!");
                        }

                        } else {
                        sender.sendMessage("No permissions or you already created too many tickets.");
                    }
                     break;
                case "answer":
                    if (sender instanceof Player && sender.hasPermission("tickets.staff") && !Main.tickets.isEmpty()) {
                        int x;
                        try {
                            x = Integer.parseInt(args[1]);

                        } catch (NullPointerException e) {
                            sender.sendMessage("Use /ticket answer 1-10");
                            return true;
                        } catch (InputMismatchException e) {
                            sender.sendMessage("Use /ticket answer 1-10");
                            return true;
                        }
                        Main.tickets.get(x-1).getTicketSender().sendMessage(((Player) sender).getDisplayName() + ChatColor.BLUE + " is now assisting you.");
                        Main.tickets.remove(x-1);

                    } else {
                        sender.sendMessage(ChatColor.RED + "You don't have permission for that!");
                    }
                    break;
                case "stafflist":
                    String stafflist = "";
                    for (int i =0;i< Main.staffList.size();i++){
                        stafflist.concat(Main.staffList.get(i).getName() + ", ");
                    }
                    sender.sendMessage("There are: " + Main.staffList.size() + "staff members online.\n" +  stafflist);
                    break;
                default:
                    sender.sendMessage(ChatColor.BLUE + "Command not found. Try /ticket help");
            }
        } else {
            sender.sendMessage("Ticket help."); //TODO
        }
        return true;
    }
    public TicketCommands() {

    }
}
