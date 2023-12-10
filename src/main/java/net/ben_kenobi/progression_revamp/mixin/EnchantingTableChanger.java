package net.ben_kenobi.progression_revamp.mixin;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(EnchantmentHelper.class)
public abstract class EnchantingTableChanger {
    @Inject(method = "generateEnchantments", at = @At(value = "TAIL"), cancellable = true)
    private static void changeEnchantments(Random random, ItemStack stack, int level, boolean treasureAllowed, CallbackInfoReturnable<List<EnchantmentLevelEntry>> cir) {
        List<EnchantmentLevelEntry> currentReturn = cir.getReturnValue();
        while (currentReturn.size() >= 2) {
            currentReturn.remove(currentReturn.size() - 1);
        }
        cir.setReturnValue(currentReturn);
        cir.cancel();
    }
}
