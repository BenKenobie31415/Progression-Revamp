package net.ben_kenobi.progression_revamp.mixin;

import net.minecraft.enchantment.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.enchantment.DamageEnchantment;
import net.minecraft.enchantment.KnockbackEnchantment;
import net.minecraft.enchantment.PowerEnchantment;
import net.minecraft.enchantment.ProtectionEnchantment;
import net.minecraft.enchantment.UnbreakingEnchantment;

    @Mixin(ProtectionEnchantment.class)
    public static abstract class Protection {
        @Inject(method = "getMaxLevel", at = @At("HEAD"), cancellable = true)
        public void returnMaxLevel(CallbackInfoReturnable<Integer> cir) {
            cir.setReturnValue(2);
            cir.cancel();
        }
    }

    @Mixin(DamageEnchantment.class)
    public static abstract class Damage {
        @Inject(method = "getMaxLevel", at = @At("HEAD"), cancellable = true)
        public void returnMaxLevel(CallbackInfoReturnable<Integer> cir) {
            cir.setReturnValue(3);
            cir.cancel();
        }
    }

    @Mixin(PowerEnchantment.class)
    public static abstract class Power {
        @Inject(method = "getMaxLevel", at = @At("HEAD"), cancellable = true)
        public void returnMaxLevel(CallbackInfoReturnable<Integer> cir) {
            cir.setReturnValue(3);
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

    @Mixin(RespirationEnchantment.class)
    public static abstract class Respiration {
        @Inject(method = "getMaxLevel", at = @At("HEAD"), cancellable = true)
        public void returnMaxLevel(CallbackInfoReturnable<Integer> cir) {
            cir.setReturnValue(2);
            cir.cancel();
        }
    }

    @Mixin(EfficiencyEnchantment.class)
    public static abstract class Efficiency {
        @Inject(method = "getMaxLevel", at = @At("HEAD"), cancellable = true)
        public void returnMaxLevel(CallbackInfoReturnable<Integer> cir) {
            cir.setReturnValue(3);
            cir.cancel();
        }
    }

    @Mixin(LuckEnchantment.class)
    public static abstract class Luck {
        @Inject(method = "getMaxLevel", at = @At("HEAD"), cancellable = true)
        public void returnMaxLevel(CallbackInfoReturnable<Integer> cir) {
            cir.setReturnValue(2);
            cir.cancel();
        }
    }

    @Mixin(ImpalingEnchantment.class)
    public static abstract class Impaling {
        @Inject(method = "getMaxLevel", at = @At("HEAD"), cancellable = true)
        public void returnMaxLevel(CallbackInfoReturnable<Integer> cir) {
            cir.setReturnValue(3);
            cir.cancel();
        }
    }

    @Mixin(RiptideEnchantment.class)
    public static abstract class Riptide {
        @Inject(method = "getMaxLevel", at = @At("HEAD"), cancellable = true)
        public void returnMaxLevel(CallbackInfoReturnable<Integer> cir) {
            cir.setReturnValue(1);
            cir.cancel();
        }
    }
}
