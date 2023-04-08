package net.ben_kenobi.progression_revamp.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import net.ben_kenobi.progression_revamp.ProgressionRevamp;
import net.minecraft.client.gui.screen.ingame.AnvilScreen;
import net.minecraft.screen.AnvilScreenHandler;

public abstract class AnvilCostLimitRemover {

    @Mixin(AnvilScreenHandler.class)
    public static abstract class LimitRemover {
        @ModifyConstant(method = "updateResult", constant = @Constant(intValue = 40))
        public int inject(int t) {
            return Integer.MAX_VALUE;
        }
    }

    @Mixin(AnvilScreen.class)
    public static abstract class TextRemover {
        @ModifyConstant(method = "drawForeground", constant = @Constant(intValue = 40))
        public int inject(int x) {
            return Integer.MAX_VALUE;
        }
    }
}
