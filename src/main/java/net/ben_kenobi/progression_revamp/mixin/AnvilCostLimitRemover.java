package net.ben_kenobi.progression_revamp.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import net.minecraft.client.gui.screen.ingame.AnvilScreen;
import net.minecraft.screen.AnvilScreenHandler;

/**
 * Removes the anvil level limit
 */
public abstract class AnvilCostLimitRemover {

    /**
     * Removes the 40 level limit on anvil usage.
     */
    @Mixin(AnvilScreenHandler.class)
    public static abstract class LimitRemover {
        @ModifyConstant(method = "updateResult", constant = @Constant(intValue = 40))
        public int inject(int t) {
            return Integer.MAX_VALUE;
        }
    }

    /**
     * Removes the cross pattern from the anvil ui if level costs are above 40.
     */
    @Mixin(AnvilScreen.class)
    public static abstract class TextRemover {
        @ModifyConstant(method = "drawForeground", constant = @Constant(intValue = 40))
        public int inject(int x) {
            return Integer.MAX_VALUE;
        }
    }
}
