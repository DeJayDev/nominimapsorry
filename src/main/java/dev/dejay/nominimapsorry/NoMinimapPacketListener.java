package dev.dejay.nominimapsorry;

import com.github.retrooper.packetevents.event.PacketListenerAbstract;
import com.github.retrooper.packetevents.event.PacketListenerPriority;
import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.protocol.potion.PotionType;
import com.github.retrooper.packetevents.resources.ResourceLocation;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerEntityEffect;

import java.util.Map;

public class NoMinimapPacketListener extends PacketListenerAbstract {
    final Map<Integer, XaeroEffect> durationToXaeroEffect = Map.of(
            60, new XaeroEffect(34, new ResourceLocation("xaerominimap", "no_minimap")),
            61, new XaeroEffect(36, new ResourceLocation("xaerominimap", "no_entity_radar")),
            62, new XaeroEffect(38, new ResourceLocation("xaerominimap", "no_waypoints")),
            63, new XaeroEffect(40, new ResourceLocation("xaerominimap", "no_cave_maps")),
            64, new XaeroEffect(42, new ResourceLocation("xaeroworldmap", "no_world_map")),
            65, new XaeroEffect(44, new ResourceLocation("xaeroworldmap", "no_cave_maps"))
    );

    public NoMinimapPacketListener() {
        super(PacketListenerPriority.LOWEST);
    }

    @Override
    public void onPacketSend(PacketSendEvent event) {
        if (event.getPacketType() != PacketType.Play.Server.ENTITY_EFFECT) {
            return;
        }
        WrapperPlayServerEntityEffect packet = new WrapperPlayServerEntityEffect(event);

        // Ignore packets that aren't dolphins grace or already modded.
        ResourceLocation resourceLocation = packet.getPotionType().getName();
        if (resourceLocation.getNamespace().contains("xaero") || !resourceLocation.getKey().equalsIgnoreCase("DOLPHINS_GRACE")) {
            return;
        }

        XaeroEffect xaeroEffect = durationToXaeroEffect.get(packet.getEffectDurationTicks());
        if (xaeroEffect == null) {
            return; // wtf
        }
        packet.setPotionType(new PotionType() {
            @Override
            public int getId() {
                return xaeroEffect.id();
            }

            @Override
            public ResourceLocation getName() {
                return xaeroEffect.key();
            }
        });
    }

    public record XaeroEffect(int id, ResourceLocation key) {
    }

}
