package net.ben_kenobi.progression_revamp.block;

import net.ben_kenobi.progression_revamp.ProgressionRevamp;
import net.ben_kenobi.progression_revamp.block.custom.ChargedObsidian;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {

    public static final Block CHARGED_OBSIDIAN = registerBlock("charged_obsidian",
            new ChargedObsidian(FabricBlockSettings
                    .copyOf(Blocks.AMETHYST_BLOCK)
                    .luminance(10)
                    .sounds(BlockSoundGroup.AMETHYST_BLOCK)
                    .requiresTool()
                    .strength(50.0f, 1200.0f)));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(ProgressionRevamp.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        Item item = Registry.register(Registries.ITEM, new Identifier(ProgressionRevamp.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
        return item;
    }

    public static void registerModBlocks() {
        ProgressionRevamp.LOGGER.info("Registering blocks for " + ProgressionRevamp.MOD_ID);
    }
}
