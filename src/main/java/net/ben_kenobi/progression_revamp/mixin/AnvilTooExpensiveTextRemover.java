package net.ben_kenobi.progression_revamp.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import net.minecraft.client.gui.screen.ingame.AnvilScreen;

@Mixin(AnvilScreen.class)
public class AnvilTooExpensiveTextRemover {
    @ModifyConstant(method = "drawForeground", constant = @Constant(intValue = 40))
    public int inject(int x) {
        return Integer.MAX_VALUE;
    }
}
