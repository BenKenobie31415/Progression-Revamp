package net.ben_kenobi.progression_revamp.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import net.minecraft.screen.AnvilScreenHandler;

@Mixin(AnvilScreenHandler.class)
public class RepairCostOverwriter {
    @ModifyArg(method = "updateResult",
        at = @At( value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;setRepairCost(I)V", ordinal = 0), index = 0)
    public int changeT(int t) {
        return 4;
    }
}
