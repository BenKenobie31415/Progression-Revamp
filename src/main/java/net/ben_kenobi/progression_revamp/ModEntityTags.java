package net.ben_kenobi.progression_revamp;

import net.minecraft.entity.EntityType;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModEntityTags {
    public static final TagKey<EntityType<?>> CHARGE_ETHERAL_SWORD_ON_KILL = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(ProgressionRevamp.MOD_ID, "charge_etheral_sword_on_kill"));
    public static final TagKey<EntityType<?>> ENCHANT_CHARGE_ETHERAL_SWORD_ON_KILL = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(ProgressionRevamp.MOD_ID, "enchant_charge_etheral_sword_on_kill"));
    public static final TagKey<EntityType<?>> ENCHANT_DISCHARGE_ETHERAL_SWORD_ON_KILL = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(ProgressionRevamp.MOD_ID, "enchant_discharge_etheral_sword_on_kill"));
}
