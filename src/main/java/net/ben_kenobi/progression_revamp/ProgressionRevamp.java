package net.ben_kenobi.progression_revamp;

import net.ben_kenobi.progression_revamp.core.PowerRequirementHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.ben_kenobi.progression_revamp.block.ModBlocks;
import net.ben_kenobi.progression_revamp.event.GameRuleHandler;
import net.ben_kenobi.progression_revamp.item.ModItems;
import net.ben_kenobi.progression_revamp.loot_table_function_type.ModLootFunctionTypes;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;

public class ProgressionRevamp implements ModInitializer {
    public static final String MOD_ID = "progression_revamp";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        PowerRequirementHelper.init_data();
        ModItems.registerModItems();
        ModBlocks.registerModBlocks();
        ModLootFunctionTypes.registerLootFunctions();

        ServerLifecycleEvents.SERVER_STARTED.register(new GameRuleHandler());
    }
}
