package net.ben_kenobi.progression_revamp.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import net.minecraft.enchantment.EnchantmentHelper;

@Mixin(EnchantmentHelper.class)
public class EnchantmentTableMaxBookshelfCountChanger {
    @ModifyConstant(method = "calculateRequiredExperienceLevel", constant = @Constant(intValue = 15))
    private static int returnMaxBookshelfCount(int value) {
        return 25;
    }
}
