package net.ben_kenobi.progression_revamp.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.enchantment.DamageEnchantment;
import net.minecraft.enchantment.PowerEnchantment;
import net.minecraft.enchantment.ProtectionEnchantment;

public abstract class EnchantmentLevelOverrider {
    @Mixin(ProtectionEnchantment.class)
    public static abstract class Protection {
        @Inject(method = "getMaxLevel", at = @At("HEAD"), cancellable = true)
        public void returnMaxLevel(CallbackInfoReturnable<Integer> cir) {
            cir.setReturnValue(3);
            cir.cancel();
        }
    }

    @Mixin(DamageEnchantment.class)
    public static abstract class Damage {
        @Inject(method = "getMaxLevel", at = @At("HEAD"), cancellable = true)
        public void returnMaxLevel(CallbackInfoReturnable<Integer> cir) {
            cir.setReturnValue(4);
            cir.cancel();
        }
    }

    @Mixin(PowerEnchantment.class)
    public static abstract class Power {
        @Inject(method = "getMaxLevel", at = @At("HEAD"), cancellable = true)
        public void returnMaxLevel(CallbackInfoReturnable<Integer> cir) {
            cir.setReturnValue(4);
            cir.cancel();
        }
    }
}
