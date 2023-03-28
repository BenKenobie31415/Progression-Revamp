package net.ben_kenobi.progression_revamp.loot_table_function_type;

import net.ben_kenobi.progression_revamp.ProgressionRevamp;
import net.ben_kenobi.progression_revamp.loot_table_function_type.custom.ExplorationCompassLootFunction;
import net.minecraft.loot.function.LootFunction;
import net.minecraft.loot.function.LootFunctionType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonSerializer;

public class ModLootFunctionTypes {
    public static final LootFunctionType EXPLORATION_COMPASS = registerLootFunctionType("exploration_compass", new ExplorationCompassLootFunction.Serializer());

    private static LootFunctionType registerLootFunctionType(String id, JsonSerializer<? extends LootFunction> jsonSerializer) {
        return Registry.register(Registries.LOOT_FUNCTION_TYPE, new Identifier(ProgressionRevamp.MOD_ID, id), new LootFunctionType(jsonSerializer));
    }

    public static void registerLootFunctions() {
        ProgressionRevamp.LOGGER.info("Registering loot function types for " + ProgressionRevamp.MOD_ID);
    }
}
