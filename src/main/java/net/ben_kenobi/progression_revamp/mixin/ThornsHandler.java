package net.ben_kenobi.progression_revamp.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import net.minecraft.enchantment.ThornsEnchantment;

@Mixin(ThornsEnchantment.class)
public abstract class ThornsHandler {
    @ModifyConstant(method = "onUserDamaged", constant = @Constant(intValue = 2))
    public int returnItemDamageValue(int value) {
        return 0;
    }
}
