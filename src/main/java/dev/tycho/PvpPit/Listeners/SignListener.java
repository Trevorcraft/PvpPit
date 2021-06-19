package dev.tycho.PvpPit.Listeners;

import dev.tycho.PvpPit.Util.Values;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class SignListener implements Listener {
    private final JavaPlugin _plugin;

    public SignListener(JavaPlugin plugin) {
        _plugin = plugin;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if(e.getClickedBlock() == null) {
            return;
        }

        if(!e.getClickedBlock().getBlockData().getMaterial().equals(Material.OAK_WALL_SIGN)) {
            return;
        }

        var config = _plugin.getConfig();

        var sign = (Sign) e.getClickedBlock().getState();
        if(!sign.getLine(0).equals("[PvpPit]")) {
            return;
        }

        if(sign.getLine(1).equals("Join")) {
            if(config.getInt("maxPlayers") <= Values.Players.size()) {
                e.getPlayer().sendMessage("Arena is already full!");
                return;
            }
            Values.Players.add(e.getPlayer());
            e.getPlayer().teleport(new Location(e.getPlayer().getWorld(), config.getInt("enterCoords.x"), config.getInt("enterCoords.y"), config.getInt("enterCoords.z")));
            e.getPlayer().setGameMode(GameMode.ADVENTURE);
        }

        if(sign.getLine(1).equals("Leave")) {
            Values.Players.remove(e.getPlayer());
            e.getPlayer().teleport(new Location(e.getPlayer().getWorld(), config.getInt("exitCoords.x"), config.getInt("exitCoords.y"), config.getInt("exitCoords.z")));
            e.getPlayer().setGameMode(GameMode.SURVIVAL);
        }
    }
}
