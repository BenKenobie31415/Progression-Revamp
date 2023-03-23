package net.ben_kenobi.progression_revamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.ben_kenobi.progression_revamp.event.EtheralSwordKillHandler;
import net.ben_kenobi.progression_revamp.event.GameRuleHandler;
import net.ben_kenobi.progression_revamp.item.ModItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityCombatEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;

public class ProgressionRevamp implements ModInitializer {
    public static final String MOD_ID = "progression_revamp";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModItems.registerModItems();

        ServerLifecycleEvents.START_DATA_PACK_RELOAD.register(new GameRuleHandler());
        ServerEntityCombatEvents.AFTER_KILLED_OTHER_ENTITY.register(new EtheralSwordKillHandler());
    }
}
