package me.braden;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

import static me.braden.Mongo.send;

public final class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {

        getServer().getPluginManager().registerEvents(this, this);

    }

    @EventHandler
    public void onDamage(EntityDamageEvent e) {

        if(e.getEntity() instanceof Player) {
            List<String> shockList = new ArrayList<>();
            shockList.add("vhs2");
            shockList.add("btcbraden");

            Player p = (Player) e.getEntity();
            System.out.println("[+] " + p.getDisplayName() + " was damaged.");
            if(shockList.contains(p.getDisplayName())) {
                // SHOCK ON
                send("on");
                getServer().broadcastMessage(ChatColor.AQUA + p.getDisplayName() + ChatColor.GREEN + " --> " + ChatColor.YELLOW + "⚡" + ChatColor.GOLD + "⚡" + ChatColor.RED + "⚡");
                Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
                    public void run() {
                        // SHOCK OFF
                        send("off");
                    }
                }, 3L);
            }

        }

    }
}
