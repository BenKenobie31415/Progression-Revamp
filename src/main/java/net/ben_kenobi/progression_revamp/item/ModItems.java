package net.ben_kenobi.progression_revamp.item;

import net.ben_kenobi.progression_revamp.ProgressionRevamp;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    //public static final Item ENCHANTING_CATALYST = registerItem("enchanting_catalyst", new Item(new Item.Settings()));
    public static final Item SOUL_CATALYST = registerItem("soul_catalyst", new Item(new Item.Settings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(ProgressionRevamp.MOD_ID, name), item);
    }

    public static void registerModItems() {
        ProgressionRevamp.LOGGER.info("Registering items for " + ProgressionRevamp.MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> entries.add(SOUL_CATALYST));
    }
}
