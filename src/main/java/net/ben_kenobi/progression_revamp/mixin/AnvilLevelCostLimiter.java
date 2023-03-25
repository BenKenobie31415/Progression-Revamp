package net.ben_kenobi.progression_revamp.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import net.minecraft.screen.AnvilScreenHandler;

@Mixin(AnvilScreenHandler.class)
public abstract class AnvilLevelCostLimiter {
    @ModifyArg(method = "updateResult",
        at = @At( value = "INVOKE", target = "Lnet/minecraft/screen/Property;set(I)V", ordinal = 5), index = 0)
    public int changeT(int t) {
        return Math.min(t, 39);
    }
}
