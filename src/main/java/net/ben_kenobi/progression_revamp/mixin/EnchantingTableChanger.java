package net.ben_kenobi.progression_revamp.mixin;

import java.util.ArrayList;
import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import com.google.common.collect.Lists;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.util.Util;
import net.minecraft.util.math.random.Random;

@Mixin(EnchantmentHelper.class)
public abstract class EnchantingTableChanger {
    /**
     * @author Benkenobi
     * @version 1.0
     * @reason too hard to do without overwrite
     * @param random
     * @param stack
     * @param level
     * @param treasureAllowed
     * @return
     */
    @Overwrite
    public static List<EnchantmentLevelEntry> generateEnchantments(Random random, ItemStack stack, int level, boolean treasureAllowed) {
        ArrayList<EnchantmentLevelEntry> list = Lists.newArrayList();
        Item item = stack.getItem();
        int i = item.getEnchantability();
        if (i <= 0) {
            return list;
        }
        List<EnchantmentLevelEntry> list2 = EnchantmentHelper.getPossibleEntries(level, stack, treasureAllowed);
        if (!list2.isEmpty()) {
            list.add(list2.get(random.nextInt(Math.max(0, list2.size() - 1))));
            if (!list.isEmpty())
                EnchantmentHelper.removeConflicts(list2, Util.getLast(list));
            float chance = ((float)i * 4) / 100.0f;
            if (random.nextFloat() >= chance && !list2.isEmpty()) {
                list.add(list2.get(random.nextInt(Math.max(0, list2.size() - 1))));
                if (!list.isEmpty())
                    EnchantmentHelper.removeConflicts(list2, Util.getLast(list));
            }
        }
        return list;
    }

    /**
     * @author BenKenobi
     * @reason therefore
     * @param power
     * @param stack
     * @param treasureAllowed
     * @return
     */
    @Overwrite
    public static List<EnchantmentLevelEntry> getPossibleEntries(int power, ItemStack stack, boolean treasureAllowed) {
        ArrayList<EnchantmentLevelEntry> list = Lists.newArrayList();
        Item item = stack.getItem();
        boolean bl = stack.isOf(Items.BOOK);
        for (Enchantment enchantment : Registries.ENCHANTMENT) {
            if (enchantment.isTreasure() && !treasureAllowed || !enchantment.isAvailableForRandomSelection() || !enchantment.target.isAcceptableItem(item) && !bl) continue;
            for (int i = enchantment.getMaxLevel(); i > enchantment.getMinLevel() - 1; --i) {
                if (power < enchantment.getMinPower(i) || power > enchantment.getMaxPower(i)) continue;
                list.add(new EnchantmentLevelEntry(enchantment, i));
            }
        }
        return list;
    }
}
