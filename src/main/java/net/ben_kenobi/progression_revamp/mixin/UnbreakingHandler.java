package net.ben_kenobi.progression_revamp.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import net.minecraft.enchantment.UnbreakingEnchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtList;
import net.minecraft.util.math.random.Random;

public abstract class UnbreakingHandler {
    @Mixin(UnbreakingEnchantment.class)
    public static abstract class UnbreakableMaker {
        @Inject(method = "shouldPreventDamage", at = @At("HEAD"), cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
        private static void inject(ItemStack item, int level, Random random, CallbackInfoReturnable<Boolean> cir) {
            cir.setReturnValue(false);
            if (item.getDamage() >= item.getMaxDamage() - 1
                    && level > 0) {
                cir.setReturnValue(true);
                item.setDamage(item.getMaxDamage() + 1);
            }
            cir.cancel();
        }
    }

    @Mixin(ItemStack.class)
    public static abstract class MiningToolSpeedReducer {
        @Shadow
        public abstract NbtList getEnchantments();
        @Shadow
        public abstract int getDamage();
        @Shadow
        public abstract int getMaxDamage();


        @Inject(method = "isSuitableFor", at = @At("HEAD"), cancellable = true)
        private void inject(CallbackInfoReturnable<Boolean> cir) {
            if (getMaxDamage() - getDamage() == -1) {
                cir.setReturnValue(false);
                cir.cancel();
            }
        }
    }
}
