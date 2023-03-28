package net.ben_kenobi.progression_revamp.loot_table_function_type.custom;

import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;

import net.ben_kenobi.progression_revamp.ProgressionRevamp;
import net.ben_kenobi.progression_revamp.loot_table_function_type.ModLootFunctionTypes;
import net.minecraft.item.CompassItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameter;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.function.ConditionalLootFunction;
import net.minecraft.loot.function.LootFunctionType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.nbt.NbtOps;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.StructureTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.Structure;

public class ExplorationCompassLootFunction extends ConditionalLootFunction {
    public static final TagKey<Structure> DEFAULT_DESTINATION = StructureTags.ON_TREASURE_MAPS;
    final TagKey<Structure> destination;
    final int searchRadius;
    final boolean skipExistingChunks;

    protected ExplorationCompassLootFunction(LootCondition[] conditions, TagKey<Structure> destination, int searchRadius, boolean skipExistingChunks) {
        super(conditions);
        this.destination = destination;
        this.searchRadius = searchRadius;
        this.skipExistingChunks = skipExistingChunks;
    }

    @Override
    public LootFunctionType getType() {
        return ModLootFunctionTypes.EXPLORATION_COMPASS;
    }

    @Override
    public Set<LootContextParameter<?>> getRequiredParameters() {
        return ImmutableSet.of(LootContextParameters.ORIGIN);
    }

    @Override
    public ItemStack process(ItemStack stack, LootContext context) {
        ServerWorld serverWorld;
        BlockPos blockPos;
        if (!stack.isOf(Items.COMPASS)) {
            return stack;
        }
        Vec3d vec3d = context.get(LootContextParameters.ORIGIN);
        if (vec3d != null && (blockPos = (serverWorld = context.getWorld()).locateStructure(this.destination, BlockPos.ofFloored(vec3d), this.searchRadius, this.skipExistingChunks)) != null) {
            ProgressionRevamp.LOGGER.info("notnull");
            NbtCompound nbt = stack.getOrCreateNbt();
            this.writeNbt(serverWorld.getRegistryKey(), blockPos, nbt);
            stack.setCustomName(Text.translatable("item.minecraft.compass.loded"));
        }
        return stack;
    }

    private void writeNbt(RegistryKey<World> worldKey, BlockPos pos, NbtCompound nbt) {
        nbt.put(CompassItem.LODESTONE_POS_KEY, NbtHelper.fromBlockPos(pos));
        World.CODEC.encodeStart(NbtOps.INSTANCE, worldKey).resultOrPartial(ProgressionRevamp.LOGGER::error).ifPresent(nbtElement -> nbt.put(CompassItem.LODESTONE_DIMENSION_KEY, (NbtElement)nbtElement));
        nbt.putBoolean(CompassItem.LODESTONE_TRACKED_KEY, false);
    }

    public static class Serializer
    extends ConditionalLootFunction.Serializer<ExplorationCompassLootFunction> {

        @Override
        public ExplorationCompassLootFunction fromJson(JsonObject jsonObject, JsonDeserializationContext var2,
                LootCondition[] lootConditions) {
            TagKey<Structure> tagKey = Serializer.getDestination(jsonObject);
            int i = JsonHelper.getInt(jsonObject, "search_radius", 50);
            boolean bl = JsonHelper.getBoolean(jsonObject, "skip_existing_chunks", true);
            return new ExplorationCompassLootFunction(lootConditions, tagKey, i, bl);
        }

        private static TagKey<Structure> getDestination(JsonObject jsonObject) {
            if (jsonObject.has("destination")) {
                String string = JsonHelper.getString(jsonObject, "destination");
                return TagKey.of(RegistryKeys.STRUCTURE, new Identifier(string));
            }
            return DEFAULT_DESTINATION;
        }
    }
}
