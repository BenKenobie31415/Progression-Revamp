package net.ben_kenobi.progression_revamp.item.custom;

import java.util.List;

import net.ben_kenobi.progression_revamp.ModEntityTags;
import net.ben_kenobi.progression_revamp.ModToolMaterials;
import net.ben_kenobi.progression_revamp.block.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.nbt.NbtByte;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Rarity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EtheralSword extends SwordItem {
    public static final String CHARGE_KEY = "Charge";
    public static final byte DISCHARGED_NBT_VALUE = 0;
    public static final byte ENCHANT_CHARGED_NBT_VALUE = 1;
    public static final byte CHARGED_NBT_VALUE = 2;

    public EtheralSword() {
        super(ModToolMaterials.ETHERAL, 4, -2.4f, new Settings().rarity(Rarity.RARE));
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        super.postHit(stack, target, attacker);

        if (target.isAlive()) return false;

        byte chargeValue = stack.getOrCreateNbt().getByte(CHARGE_KEY);

        if (target.getType().isIn(ModEntityTags.ENCHANT_CHARGE_ETHERAL_SWORD_ON_KILL) && chargeValue == DISCHARGED_NBT_VALUE) {
            playEnchantChargeEffect(attacker);
            setChargeValue(stack, ENCHANT_CHARGED_NBT_VALUE);
            return true;
        }
        if (target.getType().isIn(ModEntityTags.DISCHARGE_ETHERAL_SWORD_ON_KILL) && chargeValue == CHARGED_NBT_VALUE) {
            playDischargeEffect(attacker);
            setChargeValue(stack, DISCHARGED_NBT_VALUE);
            return true;
        }
        if (target.getType().isIn(ModEntityTags.ENCHANT_DISCHARGE_ETHERAL_SWORD_ON_KILL) && chargeValue == ENCHANT_CHARGED_NBT_VALUE) {
            playEnchantDischargeEffect(attacker);
            setChargeValue(stack, DISCHARGED_NBT_VALUE);
            return true;
        }
        return false;
    }

    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        super.postMine(stack, world, state, pos, miner);
        if (state.getBlock() == ModBlocks.CHARGED_OBSIDIAN) {
            setChargeValue(stack, CHARGED_NBT_VALUE);
            playChargeEffect(miner);
        }
        return true;
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        byte charge = stack.getNbt().getByte(CHARGE_KEY);
        if (charge == DISCHARGED_NBT_VALUE) {
            tooltip.add(Text.translatable("item.progression_revamp.etheral_sword.tooltip_uncharged").formatted(Formatting.GRAY).formatted(Formatting.ITALIC));
            return;
        }
        if (charge == ENCHANT_CHARGED_NBT_VALUE) {
            tooltip.add(Text.translatable("item.progression_revamp.etheral_sword.tooltip_enchant_charged").formatted(Formatting.GRAY).formatted(Formatting.ITALIC));
            return;
        }
        if (charge == CHARGED_NBT_VALUE) {
            tooltip.add(Text.translatable("item.progression_revamp.etheral_sword.tooltip_charged").formatted(Formatting.GRAY).formatted(Formatting.ITALIC));
            return;
        }
    }

    @Override
    public ItemStack getDefaultStack() {
        ItemStack itemStack = new ItemStack(this);
        setChargeValue(itemStack, CHARGED_NBT_VALUE);
        return itemStack;
    }

    private void setChargeValue(ItemStack itemStack, byte value) {
        NbtCompound nbtCompound = itemStack.getOrCreateNbt();
        nbtCompound.remove(CHARGE_KEY);
        nbtCompound.put(CHARGE_KEY, NbtByte.of(value));
    }

    private void playChargeEffect(LivingEntity attacker) {
        World world = attacker.getWorld();
        world.playSound(null, attacker.getBlockPos(), SoundEvents.BLOCK_AMETHYST_BLOCK_CHIME, SoundCategory.PLAYERS, 1.0F, 10.0F);
    }

    private void playEnchantChargeEffect(LivingEntity attacker) {
        World world = attacker.getWorld();
        world.playSound(null, attacker.getBlockPos(), SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.PLAYERS, 1.0F, 1.0F);
    }

    private void playDischargeEffect(LivingEntity attacker) {
        World world = attacker.getWorld();
        world.playSound(null, attacker.getBlockPos(), SoundEvents.ENTITY_WITHER_SPAWN, SoundCategory.PLAYERS, 0.2F, 2.0F);
    }

    private void playEnchantDischargeEffect(LivingEntity attacker) {
        World world = attacker.getWorld();
        world.playSound(null, attacker.getBlockPos(), SoundEvents.ENTITY_PLAYER_LEVELUP, SoundCategory.PLAYERS, 1.0F, 1.0F);
    }
}
