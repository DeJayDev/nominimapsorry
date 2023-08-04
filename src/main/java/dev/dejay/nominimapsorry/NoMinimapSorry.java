package dev.dejay.nominimapsorry;

import com.destroystokyo.paper.event.player.PlayerPostRespawnEvent;
import com.github.retrooper.packetevents.PacketEvents;
import io.github.retrooper.packetevents.factory.spigot.SpigotPacketEventsBuilder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public final class NoMinimapSorry extends JavaPlugin implements Listener {

    final List<PotionEffect> effects = List.of(
            new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 60, 69, true, false, false),
            new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 61, 69, true, false, false),
            new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 62, 69, true, false, false),
            new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 63, 69, true, false, false),
            new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 64, 69, true, false, false),
            new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 65, 69, true, false, false)
    );

    @Override
    public void onEnable() {
        PacketEvents.setAPI(SpigotPacketEventsBuilder.build(this));
        PacketEvents.getAPI().getSettings()
                .checkForUpdates(false)
                .bStats(false)
                .debug(true);
        PacketEvents.getAPI().load();

        PacketEvents.getAPI().getEventManager().registerListener(new NoMinimapPacketListener());
        PacketEvents.getAPI().init();

        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void disableMapOnJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (player.hasPermission("nominimap.bypass")) return;
        player.addPotionEffects(effects);
    }

    @EventHandler
    public void disableMapOnRespawn(PlayerPostRespawnEvent event) {
        Player player = event.getPlayer();

        if (player.hasPermission("nominimap.bypass")) return;
        player.addPotionEffects(effects);
    }

}
