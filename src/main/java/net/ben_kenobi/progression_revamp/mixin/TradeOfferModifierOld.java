package net.ben_kenobi.progression_revamp.mixin;

import java.util.Map;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.DyeItem;
import net.minecraft.item.DyeableArmorItem;
import net.minecraft.item.DyeableItem;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.FilledMapItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.SuspiciousStewItem;
import net.minecraft.item.map.MapIcon;
import net.minecraft.item.map.MapState;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtil;
import net.minecraft.recipe.BrewingRecipeRegistry;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.StructureTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.VillagerDataContainer;
import net.minecraft.village.VillagerProfession;
import net.minecraft.village.VillagerType;
import net.minecraft.world.gen.structure.Structure;
import org.jetbrains.annotations.Nullable;
import net.minecraft.village.TradeOffers.BuyForOneEmeraldFactory;
import net.minecraft.village.TradeOffers.EnchantBookFactory;
import net.minecraft.village.TradeOffers.Factory;
import net.minecraft.village.TradeOffers.ProcessItemFactory;
import net.minecraft.village.TradeOffers.SellDyedArmorFactory;
import net.minecraft.village.TradeOffers.SellEnchantedToolFactory;
import net.minecraft.village.TradeOffers.SellItemFactory;
import net.minecraft.village.TradeOffers.SellMapFactory;
import net.minecraft.village.TradeOffers.SellPotionHoldingItemFactory;
import net.minecraft.village.TradeOffers.SellSuspiciousStewFactory;
import net.minecraft.village.TradeOffers.TypeAwareBuyForOneEmeraldFactory;

@Mixin(TradeOffers.class)
public abstract class TradeOfferModifierOld {

    /*
    @ModifyVariable(at = @At("STORE"), ordinal = 14)
    private static Map<VillagerProfession, Int2ObjectMap<Factory[]>> changeVar(Map<VillagerProfession, Int2ObjectMap<Factory[]>> oldMap) {
        Map<VillagerProfession, Int2ObjectMap<Factory[]>> result = Util.make(Maps.newHashMap(), map -> {
        map.put(VillagerProfession.FARMER, copyToFastUtilMap(ImmutableMap.of(
                1, new Factory[]{
                new BuyForOneEmeraldFactory(Items.WHEAT, 20, 16, 2),
                new BuyForOneEmeraldFactory(Items.POTATO, 26, 16, 2),
                new BuyForOneEmeraldFactory(Items.CARROT, 22, 16, 2),
                new BuyForOneEmeraldFactory(Items.BEETROOT, 15, 16, 2),
                new SellItemFactory(Items.BREAD, 1, 6, 16, 1)},
                2, new Factory[]{
                new BuyForOneEmeraldFactory(Blocks.PUMPKIN, 6, 12, 10),
                new SellItemFactory(Items.PUMPKIN_PIE, 1, 4, 5),
                new SellItemFactory(Items.APPLE, 1, 4, 16, 5)},
                3, new Factory[]{
                new SellItemFactory(Items.COOKIE, 3, 18, 10),
                new BuyForOneEmeraldFactory(Blocks.MELON, 4, 12, 20)},
                4, new Factory[]{
                new SellItemFactory(Blocks.CAKE, 1, 1, 12, 15),
                new SellSuspiciousStewFactory(StatusEffects.NIGHT_VISION, 100, 15),
                new SellSuspiciousStewFactory(StatusEffects.JUMP_BOOST, 160, 15),
                new SellSuspiciousStewFactory(StatusEffects.WEAKNESS, 140, 15),
                new SellSuspiciousStewFactory(StatusEffects.BLINDNESS, 120, 15),
                new SellSuspiciousStewFactory(StatusEffects.POISON, 280, 15),
                new SellSuspiciousStewFactory(StatusEffects.SATURATION, 7, 15)},
                5, new Factory[]{
                new SellItemFactory(Items.GOLDEN_CARROT, 3, 3, 30),
                new SellItemFactory(Items.GLISTERING_MELON_SLICE, 4, 3, 30)})));
        map.put(VillagerProfession.FISHERMAN, copyToFastUtilMap(ImmutableMap.of(
                1, new Factory[]{
                new BuyForOneEmeraldFactory(Items.STRING, 20, 16, 2),
                new BuyForOneEmeraldFactory(Items.COAL, 10, 16, 2),
                new ProcessItemFactory(Items.COD, 6, Items.COOKED_COD, 6, 16, 1),
                new SellItemFactory(Items.COD_BUCKET, 3, 1, 16, 1)},
                2, new Factory[]{
                new BuyForOneEmeraldFactory(Items.COD, 15, 16, 10),
                new ProcessItemFactory(Items.SALMON, 6, Items.COOKED_SALMON, 6, 16, 5),
                new SellItemFactory(Items.CAMPFIRE, 2, 1, 5)},
                3, new Factory[]{
                new BuyForOneEmeraldFactory(Items.SALMON, 13, 16, 20),
                new SellEnchantedToolFactory(Items.FISHING_ROD, 3, 3, 10, 0.2f)},
                4, new Factory[]{
                new BuyForOneEmeraldFactory(Items.TROPICAL_FISH, 6, 12, 30)},
                5, new Factory[]{
                new BuyForOneEmeraldFactory(Items.PUFFERFISH, 4, 12, 30),
                new TypeAwareBuyForOneEmeraldFactory(1, 12, 30, ImmutableMap.builder().put(VillagerType.PLAINS, Items.OAK_BOAT).put(VillagerType.TAIGA, Items.SPRUCE_BOAT).put(VillagerType.SNOW, Items.SPRUCE_BOAT).put(VillagerType.DESERT, Items.JUNGLE_BOAT).put(VillagerType.JUNGLE, Items.JUNGLE_BOAT).put(VillagerType.SAVANNA, Items.ACACIA_BOAT).put(VillagerType.SWAMP, Items.DARK_OAK_BOAT).build())})));
        map.put(VillagerProfession.SHEPHERD, copyToFastUtilMap(ImmutableMap.of(
                1, new Factory[]{new BuyForOneEmeraldFactory(Blocks.WHITE_WOOL, 18, 16, 2),
                new BuyForOneEmeraldFactory(Blocks.BROWN_WOOL, 18, 16, 2),
                new BuyForOneEmeraldFactory(Blocks.BLACK_WOOL, 18, 16, 2),
                new BuyForOneEmeraldFactory(Blocks.GRAY_WOOL, 18, 16, 2),
                new SellItemFactory(Items.SHEARS, 2, 1, 1)},
                2, new Factory[]{new BuyForOneEmeraldFactory(Items.WHITE_DYE, 12, 16, 10),
                new BuyForOneEmeraldFactory(Items.GRAY_DYE, 12, 16, 10),
                new BuyForOneEmeraldFactory(Items.BLACK_DYE, 12, 16, 10),
                new BuyForOneEmeraldFactory(Items.LIGHT_BLUE_DYE, 12, 16, 10),
                new BuyForOneEmeraldFactory(Items.LIME_DYE, 12, 16, 10),
                new SellItemFactory(Blocks.WHITE_WOOL, 1, 1, 16, 5),
                new SellItemFactory(Blocks.ORANGE_WOOL, 1, 1, 16, 5),
                new SellItemFactory(Blocks.MAGENTA_WOOL, 1, 1, 16, 5),
                new SellItemFactory(Blocks.LIGHT_BLUE_WOOL, 1, 1, 16, 5),
                new SellItemFactory(Blocks.YELLOW_WOOL, 1, 1, 16, 5),
                new SellItemFactory(Blocks.LIME_WOOL, 1, 1, 16, 5),
                new SellItemFactory(Blocks.PINK_WOOL, 1, 1, 16, 5),
                new SellItemFactory(Blocks.GRAY_WOOL, 1, 1, 16, 5),
                new SellItemFactory(Blocks.LIGHT_GRAY_WOOL, 1, 1, 16, 5),
                new SellItemFactory(Blocks.CYAN_WOOL, 1, 1, 16, 5),
                new SellItemFactory(Blocks.PURPLE_WOOL, 1, 1, 16, 5),
                new SellItemFactory(Blocks.BLUE_WOOL, 1, 1, 16, 5),
                new SellItemFactory(Blocks.BROWN_WOOL, 1, 1, 16, 5),
                new SellItemFactory(Blocks.GREEN_WOOL, 1, 1, 16, 5),
                new SellItemFactory(Blocks.RED_WOOL, 1, 1, 16, 5),
                new SellItemFactory(Blocks.BLACK_WOOL, 1, 1, 16, 5),
                new SellItemFactory(Blocks.WHITE_CARPET, 1, 4, 16, 5),
                new SellItemFactory(Blocks.ORANGE_CARPET, 1, 4, 16, 5),
                new SellItemFactory(Blocks.MAGENTA_CARPET, 1, 4, 16, 5),
                new SellItemFactory(Blocks.LIGHT_BLUE_CARPET, 1, 4, 16, 5),
                new SellItemFactory(Blocks.YELLOW_CARPET, 1, 4, 16, 5),
                new SellItemFactory(Blocks.LIME_CARPET, 1, 4, 16, 5),
                new SellItemFactory(Blocks.PINK_CARPET, 1, 4, 16, 5),
                new SellItemFactory(Blocks.GRAY_CARPET, 1, 4, 16, 5),
                new SellItemFactory(Blocks.LIGHT_GRAY_CARPET, 1, 4, 16, 5),
                new SellItemFactory(Blocks.CYAN_CARPET, 1, 4, 16, 5),
                new SellItemFactory(Blocks.PURPLE_CARPET, 1, 4, 16, 5),
                new SellItemFactory(Blocks.BLUE_CARPET, 1, 4, 16, 5),
                new SellItemFactory(Blocks.BROWN_CARPET, 1, 4, 16, 5),
                new SellItemFactory(Blocks.GREEN_CARPET, 1, 4, 16, 5),
                new SellItemFactory(Blocks.RED_CARPET, 1, 4, 16, 5),
                new SellItemFactory(Blocks.BLACK_CARPET, 1, 4, 16, 5)},
                3, new Factory[]{new BuyForOneEmeraldFactory(Items.YELLOW_DYE, 12, 16, 20),
                new BuyForOneEmeraldFactory(Items.LIGHT_GRAY_DYE, 12, 16, 20),
                new BuyForOneEmeraldFactory(Items.ORANGE_DYE, 12, 16, 20),
                new BuyForOneEmeraldFactory(Items.RED_DYE, 12, 16, 20),
                new BuyForOneEmeraldFactory(Items.PINK_DYE, 12, 16, 20),
                new SellItemFactory(Blocks.WHITE_BED, 3, 1, 12, 10),
                new SellItemFactory(Blocks.YELLOW_BED, 3, 1, 12, 10),
                new SellItemFactory(Blocks.RED_BED, 3, 1, 12, 10),
                new SellItemFactory(Blocks.BLACK_BED, 3, 1, 12, 10),
                new SellItemFactory(Blocks.BLUE_BED, 3, 1, 12, 10),
                new SellItemFactory(Blocks.BROWN_BED, 3, 1, 12, 10),
                new SellItemFactory(Blocks.CYAN_BED, 3, 1, 12, 10),
                new SellItemFactory(Blocks.GRAY_BED, 3, 1, 12, 10),
                new SellItemFactory(Blocks.GREEN_BED, 3, 1, 12, 10),
                new SellItemFactory(Blocks.LIGHT_BLUE_BED, 3, 1, 12, 10),
                new SellItemFactory(Blocks.LIGHT_GRAY_BED, 3, 1, 12, 10),
                new SellItemFactory(Blocks.LIME_BED, 3, 1, 12, 10),
                new SellItemFactory(Blocks.MAGENTA_BED, 3, 1, 12, 10),
                new SellItemFactory(Blocks.ORANGE_BED, 3, 1, 12, 10),
                new SellItemFactory(Blocks.PINK_BED, 3, 1, 12, 10),
                new SellItemFactory(Blocks.PURPLE_BED, 3, 1, 12, 10)},
                4, new Factory[]{new BuyForOneEmeraldFactory(Items.BROWN_DYE, 12, 16, 30),
                new BuyForOneEmeraldFactory(Items.PURPLE_DYE, 12, 16, 30),
                new BuyForOneEmeraldFactory(Items.BLUE_DYE, 12, 16, 30),
                new BuyForOneEmeraldFactory(Items.GREEN_DYE, 12, 16, 30),
                new BuyForOneEmeraldFactory(Items.MAGENTA_DYE, 12, 16, 30),
                new BuyForOneEmeraldFactory(Items.CYAN_DYE, 12, 16, 30),
                new SellItemFactory(Items.WHITE_BANNER, 3, 1, 12, 15),
                new SellItemFactory(Items.BLUE_BANNER, 3, 1, 12, 15),
                new SellItemFactory(Items.LIGHT_BLUE_BANNER, 3, 1, 12, 15),
                new SellItemFactory(Items.RED_BANNER, 3, 1, 12, 15),
                new SellItemFactory(Items.PINK_BANNER, 3, 1, 12, 15),
                new SellItemFactory(Items.GREEN_BANNER, 3, 1, 12, 15),
                new SellItemFactory(Items.LIME_BANNER, 3, 1, 12, 15),
                new SellItemFactory(Items.GRAY_BANNER, 3, 1, 12, 15),
                new SellItemFactory(Items.BLACK_BANNER, 3, 1, 12, 15),
                new SellItemFactory(Items.PURPLE_BANNER, 3, 1, 12, 15),
                new SellItemFactory(Items.MAGENTA_BANNER, 3, 1, 12, 15),
                new SellItemFactory(Items.CYAN_BANNER, 3, 1, 12, 15),
                new SellItemFactory(Items.BROWN_BANNER, 3, 1, 12, 15),
                new SellItemFactory(Items.YELLOW_BANNER, 3, 1, 12, 15),
                new SellItemFactory(Items.ORANGE_BANNER, 3, 1, 12, 15),
                new SellItemFactory(Items.LIGHT_GRAY_BANNER, 3, 1, 12, 15)},
                5, new Factory[]{new SellItemFactory(Items.PAINTING, 2, 3, 30)})));
        map.put(VillagerProfession.FLETCHER, copyToFastUtilMap(ImmutableMap.of(
                1, new Factory[]{new BuyForOneEmeraldFactory(Items.STICK, 32, 16, 2),
                new SellItemFactory(Items.ARROW, 1, 16, 1),
                new ProcessItemFactory(Blocks.GRAVEL, 10, Items.FLINT, 10, 12, 1)},
                2, new Factory[]{new BuyForOneEmeraldFactory(Items.FLINT, 26, 12, 10),
                new SellItemFactory(Items.BOW, 2, 1, 5)},
                3, new Factory[]{new BuyForOneEmeraldFactory(Items.STRING, 14, 16, 20),
                new SellItemFactory(Items.CROSSBOW, 3, 1, 10)},
                4, new Factory[]{new BuyForOneEmeraldFactory(Items.FEATHER, 24, 16, 30),
                new SellEnchantedToolFactory(Items.BOW, 2, 3, 15)},
                5, new Factory[]{new BuyForOneEmeraldFactory(Items.TRIPWIRE_HOOK, 8, 12, 30),
                new SellEnchantedToolFactory(Items.CROSSBOW, 3, 3, 15),
                new SellPotionHoldingItemFactory(Items.ARROW, 5, Items.TIPPED_ARROW, 5, 2, 12, 30)})));
        map.put(VillagerProfession.LIBRARIAN, copyToFastUtilMap(ImmutableMap.builder()
                .put(1, new Factory[]{new BuyForOneEmeraldFactory(Items.PAPER, 24, 16, 2),
                new EnchantBookFactory(1),
                new SellItemFactory(Blocks.BOOKSHELF, 9, 1, 12, 1)})
                .put(2, new Factory[]{new BuyForOneEmeraldFactory(Items.BOOK, 4, 12, 10),
                new EnchantBookFactory(5),
                new SellItemFactory(Items.LANTERN, 1, 1, 5)})
                .put(3, new Factory[]{new BuyForOneEmeraldFactory(Items.INK_SAC, 5, 12, 20),
                new EnchantBookFactory(10),
                new SellItemFactory(Items.GLASS, 1, 4, 10)})
                .put(4, new Factory[]{new BuyForOneEmeraldFactory(Items.WRITABLE_BOOK, 2, 12, 30),
                new EnchantBookFactory(15),
                new SellItemFactory(Items.CLOCK, 5, 1, 15),
                new SellItemFactory(Items.COMPASS, 4, 1, 15)})
                .put(5, new Factory[]{new SellItemFactory(Items.NAME_TAG, 20, 1, 30)}).build()));
        map.put(VillagerProfession.CARTOGRAPHER, copyToFastUtilMap(ImmutableMap.of(
                1, new Factory[]{new BuyForOneEmeraldFactory(Items.PAPER, 24, 16, 2),
                new SellItemFactory(Items.MAP, 7, 1, 1)},
                2, new Factory[]{new BuyForOneEmeraldFactory(Items.GLASS_PANE, 11, 16, 10),
                new SellMapFactory(13, StructureTags.ON_OCEAN_EXPLORER_MAPS, "filled_map.monument", MapIcon.Type.MONUMENT, 12, 5)},
                3, new Factory[]{new BuyForOneEmeraldFactory(Items.COMPASS, 1, 12, 20),
                new SellMapFactory(14, StructureTags.ON_WOODLAND_EXPLORER_MAPS, "filled_map.mansion", MapIcon.Type.MANSION, 12, 10)},
                4, new Factory[]{new SellItemFactory(Items.ITEM_FRAME, 7, 1, 15),
                new SellItemFactory(Items.WHITE_BANNER, 3, 1, 15),
                new SellItemFactory(Items.BLUE_BANNER, 3, 1, 15),
                new SellItemFactory(Items.LIGHT_BLUE_BANNER, 3, 1, 15),
                new SellItemFactory(Items.RED_BANNER, 3, 1, 15),
                new SellItemFactory(Items.PINK_BANNER, 3, 1, 15),
                new SellItemFactory(Items.GREEN_BANNER, 3, 1, 15),
                new SellItemFactory(Items.LIME_BANNER, 3, 1, 15),
                new SellItemFactory(Items.GRAY_BANNER, 3, 1, 15),
                new SellItemFactory(Items.BLACK_BANNER, 3, 1, 15),
                new SellItemFactory(Items.PURPLE_BANNER, 3, 1, 15),
                new SellItemFactory(Items.MAGENTA_BANNER, 3, 1, 15),
                new SellItemFactory(Items.CYAN_BANNER, 3, 1, 15),
                new SellItemFactory(Items.BROWN_BANNER, 3, 1, 15),
                new SellItemFactory(Items.YELLOW_BANNER, 3, 1, 15),
                new SellItemFactory(Items.ORANGE_BANNER, 3, 1, 15),
                new SellItemFactory(Items.LIGHT_GRAY_BANNER, 3, 1, 15)},
                5, new Factory[]{new SellItemFactory(Items.GLOBE_BANNER_PATTERN, 8, 1, 30)})));
        map.put(VillagerProfession.CLERIC, copyToFastUtilMap(ImmutableMap.of(
                1, new Factory[]{new BuyForOneEmeraldFactory(Items.ROTTEN_FLESH, 32, 16, 2),
                new SellItemFactory(Items.REDSTONE, 1, 2, 1)},
                2, new Factory[]{new BuyForOneEmeraldFactory(Items.GOLD_INGOT, 3, 12, 10),
                new SellItemFactory(Items.LAPIS_LAZULI, 1, 1, 5)},
                3, new Factory[]{new BuyForOneEmeraldFactory(Items.RABBIT_FOOT, 2, 12, 20),
                new SellItemFactory(Blocks.GLOWSTONE, 4, 1, 12, 10)},
                4, new Factory[]{new BuyForOneEmeraldFactory(Items.SCUTE, 4, 12, 30),
                new BuyForOneEmeraldFactory(Items.GLASS_BOTTLE, 9, 12, 30),
                new SellItemFactory(Items.ENDER_PEARL, 5, 1, 15)},
                5, new Factory[]{new BuyForOneEmeraldFactory(Items.NETHER_WART, 22, 12, 30),
                new SellItemFactory(Items.EXPERIENCE_BOTTLE, 3, 1, 30)})));
        map.put(VillagerProfession.ARMORER, copyToFastUtilMap(ImmutableMap.of(
                1, new Factory[]{new BuyForOneEmeraldFactory(Items.COAL, 15, 16, 2),
                new SellItemFactory(new ItemStack(Items.IRON_LEGGINGS), 7, 1, 12, 1, 0.2f),
                new SellItemFactory(new ItemStack(Items.IRON_BOOTS), 4, 1, 12, 1, 0.2f),
                new SellItemFactory(new ItemStack(Items.IRON_HELMET), 5, 1, 12, 1, 0.2f),
                new SellItemFactory(new ItemStack(Items.IRON_CHESTPLATE), 9, 1, 12, 1, 0.2f)},
                2, new Factory[]{new BuyForOneEmeraldFactory(Items.IRON_INGOT, 4, 12, 10),
                new SellItemFactory(new ItemStack(Items.BELL), 36, 1, 12, 5, 0.2f),
                new SellItemFactory(new ItemStack(Items.CHAINMAIL_BOOTS), 1, 1, 12, 5, 0.2f),
                new SellItemFactory(new ItemStack(Items.CHAINMAIL_LEGGINGS), 3, 1, 12, 5, 0.2f)},
                3, new Factory[]{new BuyForOneEmeraldFactory(Items.LAVA_BUCKET, 1, 12, 20),
                new BuyForOneEmeraldFactory(Items.DIAMOND, 1, 12, 20),
                new SellItemFactory(new ItemStack(Items.CHAINMAIL_HELMET), 1, 1, 12, 10, 0.2f),
                new SellItemFactory(new ItemStack(Items.CHAINMAIL_CHESTPLATE), 4, 1, 12, 10, 0.2f),
                new SellItemFactory(new ItemStack(Items.SHIELD), 5, 1, 12, 10, 0.2f)},
                4, new Factory[]{new SellEnchantedToolFactory(Items.DIAMOND_LEGGINGS, 14, 3, 15, 0.2f),
                new SellEnchantedToolFactory(Items.DIAMOND_BOOTS, 8, 3, 15, 0.2f)},
                5, new Factory[]{new SellEnchantedToolFactory(Items.DIAMOND_HELMET, 8, 3, 30, 0.2f),
                new SellEnchantedToolFactory(Items.DIAMOND_CHESTPLATE, 16, 3, 30, 0.2f)})));
        map.put(VillagerProfession.WEAPONSMITH, copyToFastUtilMap(ImmutableMap.of(
                1, new Factory[]{new BuyForOneEmeraldFactory(Items.COAL, 15, 16, 2),
                new SellItemFactory(new ItemStack(Items.IRON_AXE), 3, 1, 12, 1, 0.2f),
                new SellEnchantedToolFactory(Items.IRON_SWORD, 2, 3, 1)},
                2, new Factory[]{new BuyForOneEmeraldFactory(Items.IRON_INGOT, 4, 12, 10),
                new SellItemFactory(new ItemStack(Items.BELL), 36, 1, 12, 5, 0.2f)},
                3, new Factory[]{new BuyForOneEmeraldFactory(Items.FLINT, 24, 12, 20)},
                4, new Factory[]{new BuyForOneEmeraldFactory(Items.DIAMOND, 1, 12, 30),
                new SellEnchantedToolFactory(Items.DIAMOND_AXE, 12, 3, 15, 0.2f)},
                5, new Factory[]{new SellEnchantedToolFactory(Items.DIAMOND_SWORD, 8, 3, 30, 0.2f)})));
        map.put(VillagerProfession.TOOLSMITH, copyToFastUtilMap(ImmutableMap.of(
                1, new Factory[]{new BuyForOneEmeraldFactory(Items.COAL, 15, 16, 2),
                new SellItemFactory(new ItemStack(Items.STONE_AXE), 1, 1, 12, 1, 0.2f),
                new SellItemFactory(new ItemStack(Items.STONE_SHOVEL), 1, 1, 12, 1, 0.2f),
                new SellItemFactory(new ItemStack(Items.STONE_PICKAXE), 1, 1, 12, 1, 0.2f),
                new SellItemFactory(new ItemStack(Items.STONE_HOE), 1, 1, 12, 1, 0.2f)},
                2, new Factory[]{new BuyForOneEmeraldFactory(Items.IRON_INGOT, 4, 12, 10),
                new SellItemFactory(new ItemStack(Items.BELL), 36, 1, 12, 5, 0.2f)},
                3, new Factory[]{new BuyForOneEmeraldFactory(Items.FLINT, 30, 12, 20),
                new SellEnchantedToolFactory(Items.IRON_AXE, 1, 3, 10, 0.2f),
                new SellEnchantedToolFactory(Items.IRON_SHOVEL, 2, 3, 10, 0.2f),
                new SellEnchantedToolFactory(Items.IRON_PICKAXE, 3, 3, 10, 0.2f),
                new SellItemFactory(new ItemStack(Items.DIAMOND_HOE), 4, 1, 3, 10, 0.2f)},
                4, new Factory[]{new BuyForOneEmeraldFactory(Items.DIAMOND, 1, 12, 30),
                new SellEnchantedToolFactory(Items.DIAMOND_AXE, 12, 3, 15, 0.2f),
                new SellEnchantedToolFactory(Items.DIAMOND_SHOVEL, 5, 3, 15, 0.2f)},
                5, new Factory[]{new SellEnchantedToolFactory(Items.DIAMOND_PICKAXE, 13, 3, 30, 0.2f)})));
        map.put(VillagerProfession.BUTCHER, copyToFastUtilMap(ImmutableMap.of(
                1, new Factory[]{new BuyForOneEmeraldFactory(Items.CHICKEN, 14, 16, 2),
                new BuyForOneEmeraldFactory(Items.PORKCHOP, 7, 16, 2),
                new BuyForOneEmeraldFactory(Items.RABBIT, 4, 16, 2),
                new SellItemFactory(Items.RABBIT_STEW, 1, 1, 1)},
                2, new Factory[]{new BuyForOneEmeraldFactory(Items.COAL, 15, 16, 2),
                new SellItemFactory(Items.COOKED_PORKCHOP, 1, 5, 16, 5),
                new SellItemFactory(Items.COOKED_CHICKEN, 1, 8, 16, 5)},
                3, new Factory[]{new BuyForOneEmeraldFactory(Items.MUTTON, 7, 16, 20),
                new BuyForOneEmeraldFactory(Items.BEEF, 10, 16, 20)},
                4, new Factory[]{new BuyForOneEmeraldFactory(Items.DRIED_KELP_BLOCK, 10, 12, 30)},
                5, new Factory[]{new BuyForOneEmeraldFactory(Items.SWEET_BERRIES, 10, 12, 30)})));
        map.put(VillagerProfession.LEATHERWORKER, copyToFastUtilMap(ImmutableMap.of(1, new Factory[]{new BuyForOneEmeraldFactory(Items.LEATHER, 6, 16, 2),
                new SellDyedArmorFactory(Items.LEATHER_LEGGINGS, 3),
                new SellDyedArmorFactory(Items.LEATHER_CHESTPLATE, 7)},
                2, new Factory[]{new BuyForOneEmeraldFactory(Items.FLINT, 26, 12, 10),
                new SellDyedArmorFactory(Items.LEATHER_HELMET, 5, 12, 5),
                new SellDyedArmorFactory(Items.LEATHER_BOOTS, 4, 12, 5)},
                3, new Factory[]{new BuyForOneEmeraldFactory(Items.RABBIT_HIDE, 9, 12, 20),
                new SellDyedArmorFactory(Items.LEATHER_CHESTPLATE, 7)},
                4, new Factory[]{new BuyForOneEmeraldFactory(Items.SCUTE, 4, 12, 30),
                new SellDyedArmorFactory(Items.LEATHER_HORSE_ARMOR, 6, 12, 15)},
                5, new Factory[]{new SellItemFactory(new ItemStack(Items.SADDLE), 6, 1, 12, 30, 0.2f),
                new SellDyedArmorFactory(Items.LEATHER_HELMET, 5, 12, 30)})));
        map.put(VillagerProfession.MASON, copyToFastUtilMap(ImmutableMap.of(1, new Factory[]{new BuyForOneEmeraldFactory(Items.CLAY_BALL, 10, 16, 2),
                new SellItemFactory(Items.BRICK, 1, 10, 16, 1)},
                2, new Factory[]{new BuyForOneEmeraldFactory(Blocks.STONE, 20, 16, 10),
                new SellItemFactory(Blocks.CHISELED_STONE_BRICKS, 1, 4, 16, 5)},
                3, new Factory[]{new BuyForOneEmeraldFactory(Blocks.GRANITE, 16, 16, 20),
                new BuyForOneEmeraldFactory(Blocks.ANDESITE, 16, 16, 20),
                new BuyForOneEmeraldFactory(Blocks.DIORITE, 16, 16, 20),
                new SellItemFactory(Blocks.DRIPSTONE_BLOCK, 1, 4, 16, 10),
                new SellItemFactory(Blocks.POLISHED_ANDESITE, 1, 4, 16, 10),
                new SellItemFactory(Blocks.POLISHED_DIORITE, 1, 4, 16, 10),
                new SellItemFactory(Blocks.POLISHED_GRANITE, 1, 4, 16, 10)},
                4, new Factory[]{new BuyForOneEmeraldFactory(Items.QUARTZ, 12, 12, 30),
                new SellItemFactory(Blocks.ORANGE_TERRACOTTA, 1, 1, 12, 15),
                new SellItemFactory(Blocks.WHITE_TERRACOTTA, 1, 1, 12, 15),
                new SellItemFactory(Blocks.BLUE_TERRACOTTA, 1, 1, 12, 15),
                new SellItemFactory(Blocks.LIGHT_BLUE_TERRACOTTA, 1, 1, 12, 15),
                new SellItemFactory(Blocks.GRAY_TERRACOTTA, 1, 1, 12, 15),
                new SellItemFactory(Blocks.LIGHT_GRAY_TERRACOTTA, 1, 1, 12, 15),
                new SellItemFactory(Blocks.BLACK_TERRACOTTA, 1, 1, 12, 15),
                new SellItemFactory(Blocks.RED_TERRACOTTA, 1, 1, 12, 15),
                new SellItemFactory(Blocks.PINK_TERRACOTTA, 1, 1, 12, 15),
                new SellItemFactory(Blocks.MAGENTA_TERRACOTTA, 1, 1, 12, 15),
                new SellItemFactory(Blocks.LIME_TERRACOTTA, 1, 1, 12, 15),
                new SellItemFactory(Blocks.GREEN_TERRACOTTA, 1, 1, 12, 15),
                new SellItemFactory(Blocks.CYAN_TERRACOTTA, 1, 1, 12, 15),
                new SellItemFactory(Blocks.PURPLE_TERRACOTTA, 1, 1, 12, 15),
                new SellItemFactory(Blocks.YELLOW_TERRACOTTA, 1, 1, 12, 15),
                new SellItemFactory(Blocks.BROWN_TERRACOTTA, 1, 1, 12, 15),
                new SellItemFactory(Blocks.ORANGE_GLAZED_TERRACOTTA, 1, 1, 12, 15),
                new SellItemFactory(Blocks.WHITE_GLAZED_TERRACOTTA, 1, 1, 12, 15),
                new SellItemFactory(Blocks.BLUE_GLAZED_TERRACOTTA, 1, 1, 12, 15),
                new SellItemFactory(Blocks.LIGHT_BLUE_GLAZED_TERRACOTTA, 1, 1, 12, 15),
                new SellItemFactory(Blocks.GRAY_GLAZED_TERRACOTTA, 1, 1, 12, 15),
                new SellItemFactory(Blocks.LIGHT_GRAY_GLAZED_TERRACOTTA, 1, 1, 12, 15),
                new SellItemFactory(Blocks.BLACK_GLAZED_TERRACOTTA, 1, 1, 12, 15),
                new SellItemFactory(Blocks.RED_GLAZED_TERRACOTTA, 1, 1, 12, 15),
                new SellItemFactory(Blocks.PINK_GLAZED_TERRACOTTA, 1, 1, 12, 15),
                new SellItemFactory(Blocks.MAGENTA_GLAZED_TERRACOTTA, 1, 1, 12, 15),
                new SellItemFactory(Blocks.LIME_GLAZED_TERRACOTTA, 1, 1, 12, 15),
                new SellItemFactory(Blocks.GREEN_GLAZED_TERRACOTTA, 1, 1, 12, 15),
                new SellItemFactory(Blocks.CYAN_GLAZED_TERRACOTTA, 1, 1, 12, 15),
                new SellItemFactory(Blocks.PURPLE_GLAZED_TERRACOTTA, 1, 1, 12, 15),
                new SellItemFactory(Blocks.YELLOW_GLAZED_TERRACOTTA, 1, 1, 12, 15),
                new SellItemFactory(Blocks.BROWN_GLAZED_TERRACOTTA, 1, 1, 12, 15)},
                5, new Factory[]{new SellItemFactory(Blocks.QUARTZ_PILLAR, 1, 1, 12, 30),
                new SellItemFactory(Blocks.QUARTZ_BLOCK, 1, 1, 12, 30)})));
    });
    return result;
    }*/
}
