package net.ben_kenobi.progression_revamp.mixin;

import java.util.ArrayList;
import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.FrostWalkerEnchantment;

public abstract class TreasureEnchantmentChanger {
    //@Mixin(FrostWalkerEnchantment.class)
    public static abstract class FrostWalker {
        //@Inject(method = "isTreasure", at = @At("HEAD"), cancellable = true)
        public void inject(CallbackInfoReturnable<Boolean> cir) {
            cir.setReturnValue(false);
            cir.cancel();
        }
    }

    //@Mixin(Enchantment.class)
    public static abstract class Others {
        private List<Enchantment> treasureEnchantments = new ArrayList<>();

        //@Inject(method = "isTreasure", at = @At("HEAD"), cancellable = true)
        public void inject(CallbackInfoReturnable<Boolean> cir) {
            treasureEnchantments.add(Enchantments.PROTECTION);
            treasureEnchantments.add(Enchantments.FEATHER_FALLING);
            treasureEnchantments.add(Enchantments.RESPIRATION);
            treasureEnchantments.add(Enchantments.AQUA_AFFINITY);
            treasureEnchantments.add(Enchantments.DEPTH_STRIDER);
            treasureEnchantments.add(Enchantments.SOUL_SPEED);
            treasureEnchantments.add(Enchantments.SWIFT_SNEAK);
            treasureEnchantments.add(Enchantments.SHARPNESS);
            treasureEnchantments.add(Enchantments.FIRE_ASPECT);
            treasureEnchantments.add(Enchantments.LOOTING);
            treasureEnchantments.add(Enchantments.UNBREAKING);
            treasureEnchantments.add(Enchantments.FORTUNE);
            treasureEnchantments.add(Enchantments.INFINITY);
            treasureEnchantments.add(Enchantments.CHANNELING);
            treasureEnchantments.add(Enchantments.MENDING);
            treasureEnchantments.add(Enchantments.VANISHING_CURSE);
            treasureEnchantments.add(Enchantments.BINDING_CURSE);

            if (treasureEnchantments.contains((Object)this)) {
                cir.setReturnValue(true);
                cir.cancel();
                return;
            }
            cir.setReturnValue(false);
            cir.cancel();
        }
    }
}
