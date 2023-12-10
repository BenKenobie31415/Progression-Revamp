package net.ben_kenobi.progression_revamp.mixin;

import net.minecraft.enchantment.*;
import net.minecraft.entity.EquipmentSlot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Compatibility changes:
 * <p>Mutually incompatible:
 * [fire_protection, protection, blast_protection, projectile_protection, feather_falling],
 * [respiration, aqua_affinity], [soul_speed, depth_strider]</p>
 * <p>Compatible:
 * [mending, infinity]</p>
 */
public abstract class EnchantmentIncompatChanger {
    /**
     * Makes Respiration and Aqua Affinity incompatible
     */
    @Mixin(RespirationEnchantment.class)
    public static abstract class Respiration extends Enchantment {
        protected Respiration(Rarity weight, EnchantmentTarget target, EquipmentSlot[] slotTypes) {
            super(weight, target, slotTypes);
        }

        @Override
        protected boolean canAccept(Enchantment other) {
            return !(other instanceof AquaAffinityEnchantment);
        }
    }

    /**
     * Makes all protection-types (including featherfalling) mutually incompatible
     */
    @Mixin(ProtectionEnchantment.class)
    public static abstract class Protection {
        @Inject(method = "canAccept", at = @At("HEAD"), cancellable = true)
        private void inject(Enchantment other, CallbackInfoReturnable<Boolean> cir) {
            if (other instanceof ProtectionEnchantment) {
                cir.setReturnValue(false);
                cir.cancel();
            }
        }
    }

    /**
     * Makes Infinity and Mending compatible
     * Makes Infinity and Punch incompatible
     */
    @Mixin(InfinityEnchantment.class)
    public static abstract class Infinity {
        @Inject(method = "canAccept", at = @At("HEAD"), cancellable = true)
        private void inject(Enchantment other, CallbackInfoReturnable<Boolean> cir) {
            if (other instanceof PunchEnchantment) {
                cir.setReturnValue(false);
                cir.cancel();
            }
            if (other instanceof MendingEnchantment) {
                cir.setReturnValue(true);
                cir.cancel();
            }
        }
    }

    /**
     * Makes Soulspeed and Depthstrider incompatible
     */
    @Mixin(DepthStriderEnchantment.class)
    public static abstract class DepthStrider {
        @Inject(method = "canAccept", at = @At(value = "TAIL"), cancellable = true)
        private void inject(Enchantment other, CallbackInfoReturnable<Boolean> cir) {
            boolean currentReturn = cir.getReturnValue();
            cir.setReturnValue(currentReturn && !(other instanceof SoulSpeedEnchantment));
            cir.cancel();
        }
    }
}

