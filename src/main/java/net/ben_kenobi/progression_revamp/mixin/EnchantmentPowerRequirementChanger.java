package net.ben_kenobi.progression_revamp.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import net.minecraft.enchantment.DamageEnchantment;
import net.minecraft.enchantment.EfficiencyEnchantment;
import net.minecraft.enchantment.FlameEnchantment;
import net.minecraft.enchantment.FrostWalkerEnchantment;
import net.minecraft.enchantment.ImpalingEnchantment;
import net.minecraft.enchantment.KnockbackEnchantment;
import net.minecraft.enchantment.LoyaltyEnchantment;
import net.minecraft.enchantment.LuckEnchantment;
import net.minecraft.enchantment.LureEnchantment;
import net.minecraft.enchantment.MultishotEnchantment;
import net.minecraft.enchantment.PiercingEnchantment;
import net.minecraft.enchantment.PowerEnchantment;
import net.minecraft.enchantment.ProtectionEnchantment;
import net.minecraft.enchantment.PunchEnchantment;
import net.minecraft.enchantment.QuickChargeEnchantment;
import net.minecraft.enchantment.RiptideEnchantment;
import net.minecraft.enchantment.SilkTouchEnchantment;
import net.minecraft.enchantment.SweepingEnchantment;
import net.minecraft.enchantment.ThornsEnchantment;

public abstract class EnchantmentPowerRequirementChanger {
    @Mixin(ProtectionEnchantment.class)
    public static abstract class Protection {
        @Inject(method = "getMinPower", at = @At("HEAD"), cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
        public void returnMinPower(int level, CallbackInfoReturnable<Integer> cir) {
            switch (level) {
                case 1:
                    cir.setReturnValue(1);
                    cir.cancel();
                    break;
                case 2:
                    cir.setReturnValue(25);
                    cir.cancel();
                    break;
                case 3:
                    cir.setReturnValue(50);
                    cir.cancel();
                    break;
            }
        }

        @Inject(method = "getMaxPower", at = @At("HEAD"), cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
        public void returnMaxPower(int level, CallbackInfoReturnable<Integer> cir) {
            switch (level) {
                case 1:
                    cir.setReturnValue(30);
                    cir.cancel();
                    break;
                case 2:
                    cir.setReturnValue(50);
                    cir.cancel();
                    break;
                case 3:
                    cir.setReturnValue(Integer.MAX_VALUE);
                    cir.cancel();
                    break;
            }
        }
    }

    @Mixin(ThornsEnchantment.class)
    public static abstract class Thorns {
        @Inject(method = "getMinPower", at = @At("HEAD"), cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
        public void returnMinPower(int level, CallbackInfoReturnable<Integer> cir) {
            switch (level) {
                case 1:
                    cir.setReturnValue(1);
                    cir.cancel();
                    break;
                case 2:
                    cir.setReturnValue(15);
                    cir.cancel();
                    break;
                case 3:
                    cir.setReturnValue(50);
                    cir.cancel();
                    break;
            }
        }

        @Inject(method = "getMaxPower", at = @At("HEAD"), cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
        public void returnMaxPower(int level, CallbackInfoReturnable<Integer> cir) {
            switch (level) {
                case 1:
                    cir.setReturnValue(20);
                    cir.cancel();
                    break;
                case 2:
                    cir.setReturnValue(50);
                    cir.cancel();
                    break;
                case 3:
                    cir.setReturnValue(Integer.MAX_VALUE);
                    cir.cancel();
                    break;
            }
        }
    }

    @Mixin(FrostWalkerEnchantment.class)
    public static abstract class FrostWalker {
        @Inject(method = "getMinPower", at = @At("HEAD"), cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
        public void returnMinPower(int level, CallbackInfoReturnable<Integer> cir) {
            switch (level) {
                case 1:
                    cir.setReturnValue(1);
                    cir.cancel();
                    break;
                case 2:
                    cir.setReturnValue(15);
                    cir.cancel();
                    break;
            }
        }

        @Inject(method = "getMaxPower", at = @At("HEAD"), cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
        public void returnMaxPower(int level, CallbackInfoReturnable<Integer> cir) {
            switch (level) {
                case 1:
                    cir.setReturnValue(15);
                    cir.cancel();
                    break;
                case 2:
                    cir.setReturnValue(30);
                    cir.cancel();
                    break;
            }
        }
    }

    @Mixin(DamageEnchantment.class)
    public static abstract class Damage {
        @Inject(method = "getMinPower", at = @At("HEAD"), cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
        public void returnMinPower(int level, CallbackInfoReturnable<Integer> cir) {
            switch (level) {
                case 1:
                    cir.setReturnValue(1);
                    cir.cancel();
                    break;
                case 2:
                    cir.setReturnValue(15);
                    cir.cancel();
                    break;
                case 3:
                    cir.setReturnValue(25);
                    cir.cancel();
                    break;
                case 4:
                    cir.setReturnValue(50);
                    cir.cancel();
                    break;
            }
        }

        @Inject(method = "getMaxPower", at = @At("HEAD"), cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
        public void returnMaxPower(int level, CallbackInfoReturnable<Integer> cir) {
            switch (level) {
                case 1:
                    cir.setReturnValue(20);
                    cir.cancel();
                    break;
                case 2:
                    cir.setReturnValue(30);
                    cir.cancel();
                    break;
                case 3:
                    cir.setReturnValue(50);
                    cir.cancel();
                    break;
                case 4:
                    cir.setReturnValue(Integer.MAX_VALUE);
                    cir.cancel();
                    break;
            }
        }
    }

    @Mixin(KnockbackEnchantment.class)
    public static abstract class Knockback {
        @Inject(method = "getMinPower", at = @At("HEAD"), cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
        public void returnMinPower(int level, CallbackInfoReturnable<Integer> cir) {
            switch (level) {
                case 1:
                    cir.setReturnValue(1);
                    cir.cancel();
                    break;
                case 2:
                    cir.setReturnValue(10);
                    cir.cancel();
                    break;
                case 3:
                    cir.setReturnValue(23);
                    cir.cancel();
                    break;
            }
        }

        @Inject(method = "getMaxPower", at = @At("HEAD"), cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
        public void returnMaxPower(int level, CallbackInfoReturnable<Integer> cir) {
            switch (level) {
                case 1:
                    cir.setReturnValue(15);
                    cir.cancel();
                    break;
                case 2:
                    cir.setReturnValue(25);
                    cir.cancel();
                    break;
                case 3:
                    cir.setReturnValue(34);
                    cir.cancel();
                    break;
            }
        }
    }

    @Mixin(SweepingEnchantment.class)
    public static abstract class Sweeping {
        @Inject(method = "getMinPower", at = @At("HEAD"), cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
        public void returnMinPower(int level, CallbackInfoReturnable<Integer> cir) {
            switch (level) {
                case 1:
                    cir.setReturnValue(29);
                    cir.cancel();
                    break;
                case 2:
                    cir.setReturnValue(38);
                    cir.cancel();
                    break;
                case 3:
                    cir.setReturnValue(50);
                    cir.cancel();
                    break;
            }
        }

        @Inject(method = "getMaxPower", at = @At("HEAD"), cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
        public void returnMaxPower(int level, CallbackInfoReturnable<Integer> cir) {
            switch (level) {
                case 1:
                    cir.setReturnValue(44);
                    cir.cancel();
                    break;
                case 2:
                    cir.setReturnValue(50);
                    cir.cancel();
                    break;
                case 3:
                    cir.setReturnValue(Integer.MAX_VALUE);
                    cir.cancel();
                    break;
            }
        }
    }

    @Mixin(EfficiencyEnchantment.class)
    public static abstract class Efficiency {
        @Inject(method = "getMinPower", at = @At("HEAD"), cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
        public void returnMinPower(int level, CallbackInfoReturnable<Integer> cir) {
            switch (level) {
                case 1:
                    cir.setReturnValue(1);
                    cir.cancel();
                    break;
                case 2:
                    cir.setReturnValue(19);
                    cir.cancel();
                    break;
                case 3:
                    cir.setReturnValue(34);
                    cir.cancel();
                    break;
                case 4:
                    cir.setReturnValue(50);
                    cir.cancel();
                    break;
                case 5:
                    cir.setReturnValue(Integer.MAX_VALUE);
                    cir.cancel();
                    break;
            }
        }

        @Inject(method = "getMaxPower", at = @At("HEAD"), cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
        public void returnMaxPower(int level, CallbackInfoReturnable<Integer> cir) {
            switch (level) {
                case 1:
                    cir.setReturnValue(23);
                    cir.cancel();
                    break;
                case 2:
                    cir.setReturnValue(37);
                    cir.cancel();
                    break;
                case 3:
                    cir.setReturnValue(50);
                    cir.cancel();
                    break;
                case 4:
                    cir.setReturnValue(Integer.MAX_VALUE);
                    cir.cancel();
                    break;
                case 5:
                    cir.setReturnValue(Integer.MAX_VALUE);
                    cir.cancel();
                    break;
            }
        }
    }

    @Mixin(SilkTouchEnchantment.class)
    public static abstract class SilkTouch {
        @Inject(method = "getMinPower", at = @At("HEAD"), cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
        public void returnMinPower(int level, CallbackInfoReturnable<Integer> cir) {
            switch (level) {
                case 1:
                    cir.setReturnValue(38);
                    cir.cancel();
                    break;
            }
        }

        @Inject(method = "getMaxPower", at = @At("HEAD"), cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
        public void returnMaxPower(int level, CallbackInfoReturnable<Integer> cir) {
            switch (level) {
                case 1:
                    cir.setReturnValue(Integer.MAX_VALUE);
                    cir.cancel();
                    break;
            }
        }
    }

    @Mixin(PowerEnchantment.class)
    public static abstract class Power {
        @Inject(method = "getMinPower", at = @At("HEAD"), cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
        public void returnMinPower(int level, CallbackInfoReturnable<Integer> cir) {
            switch (level) {
                case 1:
                    cir.setReturnValue(1);
                    cir.cancel();
                    break;
                case 2:
                    cir.setReturnValue(17);
                    cir.cancel();
                    break;
                case 3:
                    cir.setReturnValue(32);
                    cir.cancel();
                    break;
                case 4:
                    cir.setReturnValue(Integer.MAX_VALUE);
                    cir.cancel();
                    break;
            }
        }

        @Inject(method = "getMaxPower", at = @At("HEAD"), cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
        public void returnMaxPower(int level, CallbackInfoReturnable<Integer> cir) {
            switch (level) {
                case 1:
                    cir.setReturnValue(22);
                    cir.cancel();
                    break;
                case 2:
                    cir.setReturnValue(35);
                    cir.cancel();
                    break;
                case 3:
                    cir.setReturnValue(Integer.MAX_VALUE);
                    cir.cancel();
                    break;
                case 4:
                    cir.setReturnValue(Integer.MAX_VALUE);
                    cir.cancel();
                    break;
            }
        }
    }

    @Mixin(PunchEnchantment.class)
    public static abstract class Punch {
        @Inject(method = "getMinPower", at = @At("HEAD"), cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
        public void returnMinPower(int level, CallbackInfoReturnable<Integer> cir) {
            switch (level) {
                case 1:
                    cir.setReturnValue(23);
                    cir.cancel();
                    break;
                case 2:
                    cir.setReturnValue(32);
                    cir.cancel();
                    break;
            }
        }

        @Inject(method = "getMaxPower", at = @At("HEAD"), cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
        public void returnMaxPower(int level, CallbackInfoReturnable<Integer> cir) {
            switch (level) {
                case 1:
                    cir.setReturnValue(37);
                    cir.cancel();
                    break;
                case 2:
                    cir.setReturnValue(45);
                    cir.cancel();
                    break;
            }
        }
    }

    @Mixin(FlameEnchantment.class)
    public static abstract class Flame {
        @Inject(method = "getMinPower", at = @At("HEAD"), cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
        public void returnMinPower(int level, CallbackInfoReturnable<Integer> cir) {
            switch (level) {
                case 1:
                    cir.setReturnValue(44);
                    cir.cancel();
                    break;
            }
        }

        @Inject(method = "getMaxPower", at = @At("HEAD"), cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
        public void returnMaxPower(int level, CallbackInfoReturnable<Integer> cir) {
            switch (level) {
                case 1:
                    cir.setReturnValue(Integer.MAX_VALUE);
                    cir.cancel();
                    break;
            }
        }
    }

    @Mixin(LuckEnchantment.class)
    public static abstract class Luck {
        @Inject(method = "getMinPower", at = @At("HEAD"), cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
        public void returnMinPower(int level, CallbackInfoReturnable<Integer> cir) {
            switch (level) {
                case 1:
                    cir.setReturnValue(34);
                    cir.cancel();
                    break;
                case 2:
                    cir.setReturnValue(44);
                    cir.cancel();
                    break;
                case 3:
                    cir.setReturnValue(Integer.MAX_VALUE);
                    cir.cancel();
                    break;
            }
        }

        @Inject(method = "getMaxPower", at = @At("HEAD"), cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
        public void returnMaxPower(int level, CallbackInfoReturnable<Integer> cir) {
            switch (level) {
                case 1:
                    cir.setReturnValue(50);
                    cir.cancel();
                    break;
                case 2:
                    cir.setReturnValue(Integer.MAX_VALUE);
                    cir.cancel();
                    break;
                case 3:
                    cir.setReturnValue(Integer.MAX_VALUE);
                    cir.cancel();
                    break;
            }
        }
    }

    @Mixin(LureEnchantment.class)
    public static abstract class Lure {
        @Inject(method = "getMinPower", at = @At("HEAD"), cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
        public void returnMinPower(int level, CallbackInfoReturnable<Integer> cir) {
            switch (level) {
                case 1:
                    cir.setReturnValue(12);
                    cir.cancel();
                    break;
                case 2:
                    cir.setReturnValue(20);
                    cir.cancel();
                    break;
                case 3:
                    cir.setReturnValue(34);
                    cir.cancel();
                    break;
            }
        }

        @Inject(method = "getMaxPower", at = @At("HEAD"), cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
        public void returnMaxPower(int level, CallbackInfoReturnable<Integer> cir) {
            switch (level) {
                case 1:
                    cir.setReturnValue(23);
                    cir.cancel();
                    break;
                case 2:
                    cir.setReturnValue(39);
                    cir.cancel();
                    break;
                case 3:
                    cir.setReturnValue(Integer.MAX_VALUE);
                    cir.cancel();
                    break;
            }
        }
    }

    @Mixin(LoyaltyEnchantment.class)
    public static abstract class Loyalty {
        @Inject(method = "getMinPower", at = @At("HEAD"), cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
        public void returnMinPower(int level, CallbackInfoReturnable<Integer> cir) {
            switch (level) {
                case 1:
                    cir.setReturnValue(29);
                    cir.cancel();
                    break;
                case 2:
                    cir.setReturnValue(38);
                    cir.cancel();
                    break;
                case 3:
                    cir.setReturnValue(Integer.MAX_VALUE);
                    cir.cancel();
                    break;
            }
        }

        @Inject(method = "getMaxPower", at = @At("HEAD"), cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
        public void returnMaxPower(int level, CallbackInfoReturnable<Integer> cir) {
            switch (level) {
                case 1:
                    cir.setReturnValue(45);
                    cir.cancel();
                    break;
                case 2:
                    cir.setReturnValue(Integer.MAX_VALUE);
                    cir.cancel();
                    break;
                case 3:
                    cir.setReturnValue(Integer.MAX_VALUE);
                    cir.cancel();
                    break;
            }
        }
    }

    @Mixin(ImpalingEnchantment.class)
    public static abstract class Impaling {
        @Inject(method = "getMinPower", at = @At("HEAD"), cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
        public void returnMinPower(int level, CallbackInfoReturnable<Integer> cir) {
            switch (level) {
                case 1:
                    cir.setReturnValue(13);
                    cir.cancel();
                    break;
                case 2:
                    cir.setReturnValue(19);
                    cir.cancel();
                    break;
                case 3:
                    cir.setReturnValue(27);
                    cir.cancel();
                    break;
                case 4:
                    cir.setReturnValue(43);
                    cir.cancel();
                    break;
                case 5:
                    cir.setReturnValue(Integer.MAX_VALUE);
                    cir.cancel();
                    break;
            }
        }

        @Inject(method = "getMaxPower", at = @At("HEAD"), cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
        public void returnMaxPower(int level, CallbackInfoReturnable<Integer> cir) {
            switch (level) {
                case 1:
                    cir.setReturnValue(25);
                    cir.cancel();
                    break;
                case 2:
                    cir.setReturnValue(33);
                    cir.cancel();
                    break;
                case 3:
                    cir.setReturnValue(45);
                    cir.cancel();
                    break;
                case 4:
                    cir.setReturnValue(Integer.MAX_VALUE);
                    cir.cancel();
                    break;
                case 5:
                    cir.setReturnValue(Integer.MAX_VALUE);
                    cir.cancel();
                    break;
            }
        }
    }

    @Mixin(RiptideEnchantment.class)
    public static abstract class Riptide {
        @Inject(method = "getMinPower", at = @At("HEAD"), cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
        public void returnMinPower(int level, CallbackInfoReturnable<Integer> cir) {
            switch (level) {
                case 1:
                    cir.setReturnValue(35);
                    cir.cancel();
                    break;
                case 2:
                    cir.setReturnValue(43);
                    cir.cancel();
                    break;
                case 3:
                    cir.setReturnValue(Integer.MAX_VALUE);
                    cir.cancel();
                    break;
            }
        }

        @Inject(method = "getMaxPower", at = @At("HEAD"), cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
        public void returnMaxPower(int level, CallbackInfoReturnable<Integer> cir) {
            switch (level) {
                case 1:
                    cir.setReturnValue(47);
                    cir.cancel();
                    break;
                case 2:
                    cir.setReturnValue(Integer.MAX_VALUE);
                    cir.cancel();
                    break;
                case 3:
                    cir.setReturnValue(Integer.MAX_VALUE);
                    cir.cancel();
                    break;
            }
        }
    }

    @Mixin(MultishotEnchantment.class)
    public static abstract class Multishot {
        @Inject(method = "getMinPower", at = @At("HEAD"), cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
        public void returnMinPower(int level, CallbackInfoReturnable<Integer> cir) {
            switch (level) {
                case 1:
                    cir.setReturnValue(34);
                    cir.cancel();
                    break;
            }
        }

        @Inject(method = "getMaxPower", at = @At("HEAD"), cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
        public void returnMaxPower(int level, CallbackInfoReturnable<Integer> cir) {
            switch (level) {
                case 1:
                    cir.setReturnValue(Integer.MAX_VALUE);
                    cir.cancel();
                    break;
            }
        }
    }

    @Mixin(QuickChargeEnchantment.class)
    public static abstract class QuickCharge {
        @Inject(method = "getMinPower", at = @At("HEAD"), cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
        public void returnMinPower(int level, CallbackInfoReturnable<Integer> cir) {
            switch (level) {
                case 1:
                    cir.setReturnValue(23);
                    cir.cancel();
                    break;
                case 2:
                    cir.setReturnValue(29);
                    cir.cancel();
                    break;
                case 3:
                    cir.setReturnValue(40);
                    cir.cancel();
                    break;
            }
        }

        @Inject(method = "getMaxPower", at = @At("HEAD"), cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
        public void returnMaxPower(int level, CallbackInfoReturnable<Integer> cir) {
            switch (level) {
                case 1:
                    cir.setReturnValue(34);
                    cir.cancel();
                    break;
                case 2:
                    cir.setReturnValue(45);
                    cir.cancel();
                    break;
                case 3:
                    cir.setReturnValue(Integer.MAX_VALUE);
                    cir.cancel();
                    break;
            }
        }
    }

    @Mixin(PiercingEnchantment.class)
    public static abstract class Piercing {
        @Inject(method = "getMinPower", at = @At("HEAD"), cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
        public void returnMinPower(int level, CallbackInfoReturnable<Integer> cir) {
            switch (level) {
                case 1:
                    cir.setReturnValue(24);
                    cir.cancel();
                    break;
                case 2:
                    cir.setReturnValue(28);
                    cir.cancel();
                    break;
                case 3:
                    cir.setReturnValue(38);
                    cir.cancel();
                    break;
                case 4:
                    cir.setReturnValue(Integer.MAX_VALUE);
                    cir.cancel();
                    break;
            }
        }

        @Inject(method = "getMaxPower", at = @At("HEAD"), cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
        public void returnMaxPower(int level, CallbackInfoReturnable<Integer> cir) {
            switch (level) {
                case 1:
                    cir.setReturnValue(33);
                    cir.cancel();
                    break;
                case 2:
                    cir.setReturnValue(42);
                    cir.cancel();
                    break;
                case 3:
                    cir.setReturnValue(Integer.MAX_VALUE);
                    cir.cancel();
                    break;
                case 4:
                    cir.setReturnValue(Integer.MAX_VALUE);
                    cir.cancel();
                    break;
            }
        }
    }
}
