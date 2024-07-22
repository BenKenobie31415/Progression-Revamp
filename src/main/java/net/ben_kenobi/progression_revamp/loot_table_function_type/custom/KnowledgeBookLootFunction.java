package net.ben_kenobi.progression_revamp.loot_table_function_type.custom;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import net.ben_kenobi.progression_revamp.loot_table_function_type.ModLootFunctionTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.function.ConditionalLootFunction;
import net.minecraft.loot.function.LootFunctionType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.nbt.NbtList;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KnowledgeBookLootFunction extends ConditionalLootFunction {
    private final String type;
    private static final String CUSTOM_MODEL_DATA_KEY = "CustomModelData";
    private static final String TYPE_KEY = "type";
    private static final Map<String, Pair<Integer, List<String>>> TYPES;

    static {
        TYPES = new HashMap<>();
        TYPES.put("trident", new Pair<>(1, List.of("trident")));
    }
    protected KnowledgeBookLootFunction(LootCondition[] conditions, String type) {
        super(conditions);
        this.type = type;
    }

    @Override
    protected ItemStack process(ItemStack stack, LootContext context) {
        writeNbt(stack.getOrCreateNbt());
        return null;
    }

    @Override
    public LootFunctionType getType() {
        return ModLootFunctionTypes.KNOWLEDGE_BOOK;
    }

    private void writeNbt(NbtCompound nbt) {
        Pair<Integer, List<String>> typeValues = TYPES.get(type);
        nbt.putInt(CUSTOM_MODEL_DATA_KEY, typeValues.getLeft());
        //nbt.put()
    }

    /*private NbtList getRecipeList(String type) {
        NbtList result = new NbtList();
        for (String recipe : TYPES.get(type).getRight()) {
            result.add();
        }
    }*/

    public static class Serializer extends ConditionalLootFunction.Serializer<KnowledgeBookLootFunction> {

        @Override
        public KnowledgeBookLootFunction fromJson(JsonObject json, JsonDeserializationContext context, LootCondition[] conditions) {
            return null;
        }

        private static String getCustomModelData(JsonObject jsonObject) {
            if (jsonObject.has(TYPE_KEY)) {
                return JsonHelper.getString(jsonObject, TYPE_KEY);
            }
            return null;
        }
    }
}
