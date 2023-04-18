package net.ben_kenobi.progression_revamp.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.village.TradeOffers;

public abstract class TradeOfferModifier {
    @Mixin(Enchantment.class)
    public static abstract class BookEnchantmentsAvailable {
        @Shadow
        public abstract boolean isTreasure();

        @Inject(method = "isAvailableForEnchantedBookOffer", at = @At("HEAD"), cancellable = true)
        public void inject(CallbackInfoReturnable<Boolean> cir) {
            cir.setReturnValue(!isTreasure());
            cir.cancel();
        }
    }

    @Mixin(TradeOffers.EnchantBookFactory.class)
    public static abstract class BookEnchantmentLevels {
        @ModifyArg(method = "create", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/EnchantedBookItem;forEnchantment(Lnet/minecraft/enchantment/EnchantmentLevelEntry;)Lnet/minecraft/item/ItemStack;"), index = 0)
        public EnchantmentLevelEntry changeList(EnchantmentLevelEntry oldEntry) {
            Enchantment enchantment = oldEntry.enchantment;
            int level = (oldEntry.level < (int)((float)enchantment.getMaxLevel() / 2.0f)) ? 2 : 1;
            return new EnchantmentLevelEntry(enchantment, level);
        }
    }
}
