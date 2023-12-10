package net.ben_kenobi.progression_revamp.mixin;

import net.ben_kenobi.progression_revamp.core.PowerRequirementHelper;
import net.minecraft.enchantment.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Changes the range of levels in which an enchantment can be obtained in the enchanting table.
 */
public abstract class EnchantmentPowerRequirementChanger {

    @Mixin(ProtectionEnchantment.class)
    public static abstract class Protection {
        @Inject(method = "getMinPower", at = @At("HEAD"), cancellable = true)
        public void returnMinPower(int level, CallbackInfoReturnable<Integer> cir) {
            cir.setReturnValue(PowerRequirementHelper.getMinPower(PowerRequirementHelper.Patterns.PROTECTION, level));
            cir.cancel();
        }

        @Inject(method = "getMaxPower", at = @At("HEAD"), cancellable = true)
        public void returnMaxPower(int level, CallbackInfoReturnable<Integer> cir) {
            cir.setReturnValue(PowerRequirementHelper.getMaxPower(PowerRequirementHelper.Patterns.PROTECTION, level));
            cir.cancel();
        }
    }

    @Mixin(ThornsEnchantment.class)
    public static abstract class Thorns {
        @Inject(method = "getMinPower", at = @At("HEAD"), cancellable = true)
        public void returnMinPower(int level, CallbackInfoReturnable<Integer> cir) {
            cir.setReturnValue(PowerRequirementHelper.getMinPower(PowerRequirementHelper.Patterns.THORNS, level));
            cir.cancel();
        }

        @Inject(method = "getMaxPower", at = @At("HEAD"), cancellable = true)
        public void returnMaxPower(int level, CallbackInfoReturnable<Integer> cir) {
            cir.setReturnValue(PowerRequirementHelper.getMaxPower(PowerRequirementHelper.Patterns.THORNS, level));
            cir.cancel();
        }
    }

    @Mixin(FrostWalkerEnchantment.class)
    public static abstract class FrostWalker {
        @Inject(method = "getMinPower", at = @At("HEAD"), cancellable = true)
        public void returnMinPower(int level, CallbackInfoReturnable<Integer> cir) {
            cir.setReturnValue(10);
            cir.cancel();
        }

        @Inject(method = "getMaxPower", at = @At("HEAD"), cancellable = true)
        public void returnMaxPower(int level, CallbackInfoReturnable<Integer> cir) {
            cir.setReturnValue(15);
            cir.cancel();
        }
    }

    @Mixin(DamageEnchantment.class)
    public static abstract class Damage {
        @Inject(method = "getMinPower", at = @At("HEAD"), cancellable = true)
        public void returnMinPower(int level, CallbackInfoReturnable<Integer> cir) {
            cir.setReturnValue(PowerRequirementHelper.getMinPower(PowerRequirementHelper.Patterns.DAMAGE, level));
            cir.cancel();
        }

        @Inject(method = "getMaxPower", at = @At("HEAD"), cancellable = true)
        public void returnMaxPower(int level, CallbackInfoReturnable<Integer> cir) {
            cir.setReturnValue(PowerRequirementHelper.getMaxPower(PowerRequirementHelper.Patterns.DAMAGE, level));
            cir.cancel();
        }
    }

    @Mixin(KnockbackEnchantment.class)
    public static abstract class Knockback {
        @Inject(method = "getMinPower", at = @At("HEAD"), cancellable = true)
        public void returnMinPower(int level, CallbackInfoReturnable<Integer> cir) {
            cir.setReturnValue(PowerRequirementHelper.getMinPower(PowerRequirementHelper.Patterns.KNOCKBACK, level));
            cir.cancel();
        }

        @Inject(method = "getMaxPower", at = @At("HEAD"), cancellable = true)
        public void returnMaxPower(int level, CallbackInfoReturnable<Integer> cir) {
            cir.setReturnValue(PowerRequirementHelper.getMaxPower(PowerRequirementHelper.Patterns.KNOCKBACK, level));
            cir.cancel();
        }
    }

    @Mixin(SweepingEnchantment.class)
    public static abstract class Sweeping {
        @Inject(method = "getMinPower", at = @At("HEAD"), cancellable = true)
        public void returnMinPower(int level, CallbackInfoReturnable<Integer> cir) {
            cir.setReturnValue(PowerRequirementHelper.getMinPower(PowerRequirementHelper.Patterns.SWEEPING, level));
            cir.cancel();
        }

        @Inject(method = "getMaxPower", at = @At("HEAD"), cancellable = true)
        public void returnMaxPower(int level, CallbackInfoReturnable<Integer> cir) {
            cir.setReturnValue(PowerRequirementHelper.getMaxPower(PowerRequirementHelper.Patterns.SWEEPING, level));
            cir.cancel();
        }
    }

    @Mixin(EfficiencyEnchantment.class)
    public static abstract class Efficiency {
        @Inject(method = "getMinPower", at = @At("HEAD"), cancellable = true)
        public void returnMinPower(int level, CallbackInfoReturnable<Integer> cir) {
            cir.setReturnValue(PowerRequirementHelper.getMinPower(PowerRequirementHelper.Patterns.DAMAGE, level));
            cir.cancel();
        }

        @Inject(method = "getMaxPower", at = @At("HEAD"), cancellable = true)
        public void returnMaxPower(int level, CallbackInfoReturnable<Integer> cir) {
            cir.setReturnValue(PowerRequirementHelper.getMaxPower(PowerRequirementHelper.Patterns.DAMAGE, level));
            cir.cancel();
        }
    }

    @Mixin(SilkTouchEnchantment.class)
    public static abstract class SilkTouch {
        @Inject(method = "getMinPower", at = @At("HEAD"), cancellable = true)
        public void returnMinPower(int level, CallbackInfoReturnable<Integer> cir) {
            cir.setReturnValue(PowerRequirementHelper.getMinPower(PowerRequirementHelper.Patterns.INFINITY, level));
            cir.cancel();
        }

        @Inject(method = "getMaxPower", at = @At("HEAD"), cancellable = true)
        public void returnMaxPower(int level, CallbackInfoReturnable<Integer> cir) {
            cir.setReturnValue(PowerRequirementHelper.getMaxPower(PowerRequirementHelper.Patterns.INFINITY, level));
            cir.cancel();
        }
    }

    @Mixin(PowerEnchantment.class)
    public static abstract class Power {
        @Inject(method = "getMinPower", at = @At("HEAD"), cancellable = true)
        public void returnMinPower(int level, CallbackInfoReturnable<Integer> cir) {
            cir.setReturnValue(PowerRequirementHelper.getMinPower(PowerRequirementHelper.Patterns.DAMAGE, level));
            cir.cancel();
        }

        @Inject(method = "getMaxPower", at = @At("HEAD"), cancellable = true)
        public void returnMaxPower(int level, CallbackInfoReturnable<Integer> cir) {
            cir.setReturnValue(PowerRequirementHelper.getMaxPower(PowerRequirementHelper.Patterns.DAMAGE, level));
            cir.cancel();
        }
    }

    @Mixin(PunchEnchantment.class)
    public static abstract class Punch {
        @Inject(method = "getMinPower", at = @At("HEAD"), cancellable = true)
        public void returnMinPower(int level, CallbackInfoReturnable<Integer> cir) {
            cir.setReturnValue(PowerRequirementHelper.getMinPower(PowerRequirementHelper.Patterns.KNOCKBACK, level));
            cir.cancel();
        }

        @Inject(method = "getMaxPower", at = @At("HEAD"), cancellable = true)
        public void returnMaxPower(int level, CallbackInfoReturnable<Integer> cir) {
            cir.setReturnValue(PowerRequirementHelper.getMaxPower(PowerRequirementHelper.Patterns.KNOCKBACK, level));
            cir.cancel();
        }
    }

    @Mixin(FlameEnchantment.class)
    public static abstract class Flame {
        @Inject(method = "getMinPower", at = @At("HEAD"), cancellable = true)
        public void returnMinPower(int level, CallbackInfoReturnable<Integer> cir) {
            cir.setReturnValue(PowerRequirementHelper.getMinPower(PowerRequirementHelper.Patterns.FIRE_ASPECT, level));
            cir.cancel();
        }

        @Inject(method = "getMaxPower", at = @At("HEAD"), cancellable = true)
        public void returnMaxPower(int level, CallbackInfoReturnable<Integer> cir) {
            cir.setReturnValue(PowerRequirementHelper.getMaxPower(PowerRequirementHelper.Patterns.FIRE_ASPECT, level));
            cir.cancel();
        }
    }

    @Mixin(LuckEnchantment.class)
    public static abstract class Luck {
        @Inject(method = "getMinPower", at = @At("HEAD"), cancellable = true)
        public void returnMinPower(int level, CallbackInfoReturnable<Integer> cir) {
            cir.setReturnValue(PowerRequirementHelper.getMinPower(PowerRequirementHelper.Patterns.INFINITY, level));
            cir.cancel();
        }

        @Inject(method = "getMaxPower", at = @At("HEAD"), cancellable = true)
        public void returnMaxPower(int level, CallbackInfoReturnable<Integer> cir) {
            cir.setReturnValue(PowerRequirementHelper.getMaxPower(PowerRequirementHelper.Patterns.INFINITY, level));
            cir.cancel();
        }
    }

    @Mixin(LureEnchantment.class)
    public static abstract class Lure {
        @Inject(method = "getMinPower", at = @At("HEAD"), cancellable = true)
        public void returnMinPower(int level, CallbackInfoReturnable<Integer> cir) {
            cir.setReturnValue(PowerRequirementHelper.getMinPower(PowerRequirementHelper.Patterns.SWEEPING, level));
            cir.cancel();
        }

        @Inject(method = "getMaxPower", at = @At("HEAD"), cancellable = true)
        public void returnMaxPower(int level, CallbackInfoReturnable<Integer> cir) {
            cir.setReturnValue(PowerRequirementHelper.getMaxPower(PowerRequirementHelper.Patterns.SWEEPING, level));
            cir.cancel();
        }
    }

    @Mixin(LoyaltyEnchantment.class)
    public static abstract class Loyalty {
        @Inject(method = "getMinPower", at = @At("HEAD"), cancellable = true)
        public void returnMinPower(int level, CallbackInfoReturnable<Integer> cir) {
            cir.setReturnValue(PowerRequirementHelper.getMinPower(PowerRequirementHelper.Patterns.DEPTH_STRIDER, level));
            cir.cancel();
        }

        @Inject(method = "getMaxPower", at = @At("HEAD"), cancellable = true)
        public void returnMaxPower(int level, CallbackInfoReturnable<Integer> cir) {
            cir.setReturnValue(PowerRequirementHelper.getMaxPower(PowerRequirementHelper.Patterns.DEPTH_STRIDER, level));
            cir.cancel();
        }
    }

    @Mixin(ImpalingEnchantment.class)
    public static abstract class Impaling {
        @Inject(method = "getMinPower", at = @At("HEAD"), cancellable = true)
        public void returnMinPower(int level, CallbackInfoReturnable<Integer> cir) {
            cir.setReturnValue(PowerRequirementHelper.getMinPower(PowerRequirementHelper.Patterns.DAMAGE, level));
            cir.cancel();
        }

        @Inject(method = "getMaxPower", at = @At("HEAD"), cancellable = true)
        public void returnMaxPower(int level, CallbackInfoReturnable<Integer> cir) {
            cir.setReturnValue(PowerRequirementHelper.getMaxPower(PowerRequirementHelper.Patterns.DAMAGE, level));
            cir.cancel();
        }
    }

    @Mixin(RiptideEnchantment.class)
    public static abstract class Riptide {
        @Inject(method = "getMinPower", at = @At("HEAD"), cancellable = true)
        public void returnMinPower(int level, CallbackInfoReturnable<Integer> cir) {
            cir.setReturnValue(PowerRequirementHelper.getMinPower(PowerRequirementHelper.Patterns.INFINITY, level));
            cir.cancel();
        }

        @Inject(method = "getMaxPower", at = @At("HEAD"), cancellable = true)
        public void returnMaxPower(int level, CallbackInfoReturnable<Integer> cir) {
            cir.setReturnValue(PowerRequirementHelper.getMaxPower(PowerRequirementHelper.Patterns.INFINITY, level));
            cir.cancel();
        }
    }

    @Mixin(MultishotEnchantment.class)
    public static abstract class Multishot {
        @Inject(method = "getMinPower", at = @At("HEAD"), cancellable = true)
        public void returnMinPower(int level, CallbackInfoReturnable<Integer> cir) {
            cir.setReturnValue(1);
            cir.cancel();
        }

        @Inject(method = "getMaxPower", at = @At("HEAD"), cancellable = true)
        public void returnMaxPower(int level, CallbackInfoReturnable<Integer> cir) {
            cir.setReturnValue(8);
            cir.cancel();
        }
    }

    @Mixin(QuickChargeEnchantment.class)
    public static abstract class QuickCharge {
        @Inject(method = "getMinPower", at = @At("HEAD"), cancellable = true)
        public void returnMinPower(int level, CallbackInfoReturnable<Integer> cir) {
            cir.setReturnValue(PowerRequirementHelper.getMinPower(PowerRequirementHelper.Patterns.THORNS, level));
            cir.cancel();
        }

        @Inject(method = "getMaxPower", at = @At("HEAD"), cancellable = true)
        public void returnMaxPower(int level, CallbackInfoReturnable<Integer> cir) {
            cir.setReturnValue(PowerRequirementHelper.getMaxPower(PowerRequirementHelper.Patterns.THORNS, level));
            cir.cancel();
        }
    }

    @Mixin(PiercingEnchantment.class)
    public static abstract class Piercing {
        @Inject(method = "getMinPower", at = @At("HEAD"), cancellable = true)
        public void returnMinPower(int level, CallbackInfoReturnable<Integer> cir) {
            cir.setReturnValue(PowerRequirementHelper.getMinPower(PowerRequirementHelper.Patterns.DAMAGE, level));
            cir.cancel();
        }

        @Inject(method = "getMaxPower", at = @At("HEAD"), cancellable = true)
        public void returnMaxPower(int level, CallbackInfoReturnable<Integer> cir) {
            cir.setReturnValue(PowerRequirementHelper.getMaxPower(PowerRequirementHelper.Patterns.DAMAGE, level));
            cir.cancel();
        }
    }

    @Mixin(DepthStriderEnchantment.class)
    public static abstract class DepthStrider {
        @Inject(method = "getMinPower", at = @At("HEAD"), cancellable = true)
        public void returnMinPower(int level, CallbackInfoReturnable<Integer> cir) {
            cir.setReturnValue(PowerRequirementHelper.getMinPower(PowerRequirementHelper.Patterns.DEPTH_STRIDER, level));
            cir.cancel();
        }

        @Inject(method = "getMaxPower", at = @At("HEAD"), cancellable = true)
        public void returnMaxPower(int level, CallbackInfoReturnable<Integer> cir) {
            cir.setReturnValue(PowerRequirementHelper.getMaxPower(PowerRequirementHelper.Patterns.DEPTH_STRIDER, level));
            cir.cancel();
        }
    }

    @Mixin(FireAspectEnchantment.class)
    public static abstract class FireAspect {
        @Inject(method = "getMinPower", at = @At("HEAD"), cancellable = true)
        public void returnMinPower(int level, CallbackInfoReturnable<Integer> cir) {
            cir.setReturnValue(PowerRequirementHelper.getMinPower(PowerRequirementHelper.Patterns.FIRE_ASPECT, level));
            cir.cancel();
        }

        @Inject(method = "getMaxPower", at = @At("HEAD"), cancellable = true)
        public void returnMaxPower(int level, CallbackInfoReturnable<Integer> cir) {
            cir.setReturnValue(PowerRequirementHelper.getMaxPower(PowerRequirementHelper.Patterns.FIRE_ASPECT, level));
            cir.cancel();
        }
    }
}
