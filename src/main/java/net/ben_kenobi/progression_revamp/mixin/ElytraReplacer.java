package net.ben_kenobi.progression_revamp.mixin;

import net.minecraft.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import net.minecraft.item.ItemConvertible;
import net.minecraft.structure.EndCityGenerator;

@Mixin(EndCityGenerator.Piece.class)
public abstract class ElytraReplacer {
    @ModifyArg(method = "handleMetadata",
        at = @At( value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;<init>(Lnet/minecraft/item/ItemConvertible;)V", ordinal = 0), index = 0)
    public ItemConvertible changeItem(ItemConvertible item) {
        return Blocks.ACACIA_BUTTON.asItem();
    }
}