package net.ben_kenobi.progression_revamp;

import net.ben_kenobi.progression_revamp.item.ModItems;
import net.ben_kenobi.progression_revamp.item.custom.EtheralSword;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.util.Identifier;

public class ProgressionRevampClient implements ClientModInitializer{
    @Override
    public void onInitializeClient() {
        ModelPredicateProviderRegistry.register(ModItems.ETHERAL_SWORD, new Identifier("etheral_charge"), (itemStack, clientWorld, livingEntity, number) -> {
            if (!itemStack.hasNbt()) return 0;
            float value = (float)itemStack.getNbt().getByte(EtheralSword.CHARGE_KEY) / 2.0F;
            return value;
        });
    }
}
