package net.ben_kenobi.progression_revamp.event;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents.ServerStarted;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

public class GameRuleHandler implements ServerStarted {
    @Override
    public void onServerStarted(MinecraftServer server) {
        for (World world : server.getWorlds()) {
            world.getGameRules().get(GameRules.DO_LIMITED_CRAFTING).set(true, server);
        }
    }
}
