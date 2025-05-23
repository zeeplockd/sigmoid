package me.zeeplockd.sigmoid.modules;

import me.zeeplockd.sigmoid.Sigmoid;

import meteordevelopment.meteorclient.events.world.TickEvent;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

public class UkPubSimulator extends Module {

    public UkPubSimulator() {
        super(Sigmoid.CATEGORY, "uk-pub-simulator", "A module that simulates a shit show with Killaura.");
    }

    @EventHandler
    private void onTick(TickEvent.Post event) {
        if (mc.player == null || mc.world == null || mc.interactionManager == null) return;

        Entity nearest = null;
        double closestDistance = Double.MAX_VALUE;

        for (Entity entity : mc.world.getEntities()) {
            if (entity == mc.player) continue;
            if (!(entity instanceof LivingEntity)) continue;

            double distance = mc.player.distanceTo(entity);
            if (distance < closestDistance) {
                closestDistance = distance;
                nearest = entity;
            }
        }

        float maxReach = 6.0f;

        for (LivingEntity entity : mc.world.getEntitiesByClass(LivingEntity.class, mc.player.getBoundingBox().expand(5), e -> e != mc.player)) {
            if (mc.player.isAlive() && mc.player.distanceTo(nearest) <= maxReach) {
                for (int i = 0; i < 50; i++) {
                    mc.interactionManager.attackEntity(mc.player, entity);
                }
            }
        }
    }
};
