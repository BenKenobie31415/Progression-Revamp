package net.ben_kenobi.progression_revamp.event;

import net.fabricmc.fabric.api.entity.event.v1.ServerEntityCombatEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents.ServerStarted;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents.StartDataPackReload;
import net.minecraft.resource.LifecycledResourceManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

public class GameRuleHandler implements StartDataPackReload {

    @Override
    public void startDataPackReload(MinecraftServer server, LifecycledResourceManager resourceManager) {
        for (World world : server.getWorlds()) {
            world.getGameRules().get(GameRules.DO_LIMITED_CRAFTING).set(true, server);
        }
    }
}
