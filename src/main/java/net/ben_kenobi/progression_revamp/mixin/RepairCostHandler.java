package net.ben_kenobi.progression_revamp.mixin;

import java.util.Map;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;

@Mixin(ItemStack.class)
public abstract class RepairCostHandler {
    @Shadow
    public abstract NbtCompound getOrCreateNbt();
    @Shadow
    public abstract NbtList getEnchantments();
    @Shadow
    public abstract boolean hasNbt();
    @Shadow
    private NbtCompound nbt;

    @Inject(method = "getRepairCost", at = @At("RETURN"), cancellable = true)
    public void getRepairCost(CallbackInfoReturnable<Integer> cir) {
        int valueFromNbt = 0;
        if (hasNbt() && nbt.contains("RepairCost", NbtElement.INT_TYPE)) {
            valueFromNbt = nbt.getInt("RepairCost");
        }
        cir.setReturnValue(valueFromNbt);
        if (hasEnchantment(Enchantments.MENDING)) {
            cir.setReturnValue(Math.min(valueFromNbt, 24));
        }
        cir.cancel();
    }

    private boolean hasEnchantment(Enchantment enchantment) {
        NbtList enchantmentsNbt = getEnchantments();
        Map<Enchantment, Integer> map = EnchantmentHelper.fromNbt(enchantmentsNbt);
        Integer value = map.get(enchantment);
        if (value == null) return false;
        return true;
    }
}
