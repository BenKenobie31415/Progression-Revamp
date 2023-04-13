package net.ben_kenobi.progression_revamp.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.enchantment.DamageEnchantment;
import net.minecraft.enchantment.KnockbackEnchantment;
import net.minecraft.enchantment.PowerEnchantment;
import net.minecraft.enchantment.ProtectionEnchantment;
import net.minecraft.enchantment.UnbreakingEnchantment;

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

    @Mixin(UnbreakingEnchantment.class)
    public static abstract class Unbreaking {
        @Inject(method = "getMaxLevel", at = @At("HEAD"), cancellable = true)
        public void returnMaxLevel(CallbackInfoReturnable<Integer> cir) {
            cir.setReturnValue(1);
            cir.cancel();
        }
    }

    @Mixin(KnockbackEnchantment.class)
    public static abstract class Knockback {
        @Inject(method = "getMaxLevel", at = @At("HEAD"), cancellable = true)
        public void returnMaxLevel(CallbackInfoReturnable<Integer> cir) {
            cir.setReturnValue(3);
            cir.cancel();
        }
    }
}
