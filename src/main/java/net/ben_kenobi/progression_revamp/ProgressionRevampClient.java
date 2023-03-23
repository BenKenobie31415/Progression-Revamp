package net.ben_kenobi.progression_revamp;

import net.ben_kenobi.progression_revamp.item.ModItems;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.util.Identifier;

public class ProgressionRevampClient implements ClientModInitializer{
    private static final String CHARGE_DATA_KEY = "Charge";

    @Override
    public void onInitializeClient() {
        ModelPredicateProviderRegistry.register(ModItems.ETHERAL_SWORD, new Identifier("etheral_charge"), (itemStack, clientWorld, livingEntity, number) ->
            itemStack.hasNbt() ? itemStack.getNbt().getFloat(CHARGE_DATA_KEY) : 0);
    }
}
