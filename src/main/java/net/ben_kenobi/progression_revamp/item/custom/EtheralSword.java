package net.ben_kenobi.progression_revamp.item.custom;

import java.util.List;

import net.ben_kenobi.progression_revamp.ModEntityTags;
import net.ben_kenobi.progression_revamp.ModToolMaterials;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtFloat;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;

public class EtheralSword extends SwordItem {
    private static final String CHARGE_NBT_KEY = "Charge";
    private static final float ENCHANT_CHARGE_NBT_VALUE = 0.5F;
    private static final float CHARGE_NBT_VALUE = 1.0F;

    public EtheralSword() {
        super(ModToolMaterials.ETHERAL, 4, -2.4f, new Settings().rarity(Rarity.RARE));
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        super.postHit(stack, target, attacker);

        if (target.isAlive()) return true;

        if (stack.getNbt().get(CHARGE_NBT_KEY) != null
            && target.getType().isIn(ModEntityTags.ENCHANT_DISCHARGE_ETHERAL_SWORD_ON_KILL)) {
            if (stack.getNbt().getFloat(CHARGE_NBT_KEY) == ENCHANT_CHARGE_NBT_VALUE) {
                playEnchantDischargeEffect(attacker);
            }
            if (stack.getNbt().getFloat(CHARGE_NBT_KEY) == CHARGE_NBT_VALUE) {
                playDischargeEffect(attacker);
            }
            discharge(stack);
            return true;
        }

        if (target.getType().isIn(ModEntityTags.CHARGE_ETHERAL_SWORD_ON_KILL)) {
            playChargeEffect(attacker);
            charge(stack, attacker);
        }
        if (target.getType().isIn(ModEntityTags.ENCHANT_CHARGE_ETHERAL_SWORD_ON_KILL)) {
            playEnchantChargeEffect(attacker);
            enchantCharge(stack, attacker);
        }

        return true;
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        float charge = stack.getNbt().getFloat(CHARGE_NBT_KEY);
        if (charge == 0.0F) {
            tooltip.add(Text.translatable("item.progression_revamp.etheral_sword.tooltip_uncharged").formatted(Formatting.GRAY).formatted(Formatting.ITALIC));
            return;
        }
        if (charge == 0.5F) {
            tooltip.add(Text.translatable("item.progression_revamp.etheral_sword.tooltip_enchant_charged").formatted(Formatting.GRAY).formatted(Formatting.ITALIC));
            return;
        }
        if (charge == 1.0F) {
            tooltip.add(Text.translatable("item.progression_revamp.etheral_sword.tooltip_charged").formatted(Formatting.GRAY).formatted(Formatting.ITALIC));
            return;
        }
    }

    private void charge(ItemStack itemStack, LivingEntity attacker) {
        NbtCompound nbtCompound = itemStack.getOrCreateNbt();
        nbtCompound.put(CHARGE_NBT_KEY, NbtFloat.of(CHARGE_NBT_VALUE));
    }

    private void enchantCharge(ItemStack itemStack, LivingEntity attacker) {
        NbtCompound nbtCompound = itemStack.getOrCreateNbt();
        nbtCompound.put(CHARGE_NBT_KEY, NbtFloat.of(ENCHANT_CHARGE_NBT_VALUE));
    }

    private void playChargeEffect(LivingEntity attacker) {
        World world = attacker.getWorld();
        world.playSound(null, attacker.getBlockPos(), SoundEvents.BLOCK_SCULK_CATALYST_BLOOM, SoundCategory.PLAYERS, 1.0F, 10.0F);
    }

    private void playEnchantChargeEffect(LivingEntity attacker) {
        World world = attacker.getWorld();
        world.playSound(null, attacker.getBlockPos(), SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.PLAYERS, 1.0F, 1.0F);
    }

    private void discharge(ItemStack itemStack) {
        NbtCompound nbtCompound = itemStack.getOrCreateNbt();
        nbtCompound.remove(CHARGE_NBT_KEY);
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
