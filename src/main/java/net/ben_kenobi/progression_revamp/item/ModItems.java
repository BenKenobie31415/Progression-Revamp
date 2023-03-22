package net.ben_kenobi.progression_revamp.item;

import net.ben_kenobi.progression_revamp.ProgressionRevamp;
import net.ben_kenobi.progression_revamp.item.cusotm.EtheralSword;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item DIMANESIONAL_INGOT = registerItem("dimensional_ingot",
            new Item(new Item.Settings()), ItemGroups.INGREDIENTS);

    public static final Item ETHERAL_SWORD = registerItem("etheral_sword", new EtheralSword(), ItemGroups.COMBAT);
    public static final Item BROKEN_SWORD = registerItem("broken_sword", new Item(new Item.Settings()), ItemGroups.INGREDIENTS);

    private static Item registerItem(String name, Item item, ItemGroup group) {
        ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.add(item));
        return Registry.register(Registries.ITEM, new Identifier(ProgressionRevamp.MOD_ID, name), item);
    }

    public static void registerModItems() {
        ProgressionRevamp.LOGGER.debug("Registerring Mod Items for " + ProgressionRevamp.MOD_ID);
    }
}
