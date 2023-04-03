package net.ben_kenobi.progression_revamp.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import net.ben_kenobi.progression_revamp.item.ModItems;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.EnchantmentScreenHandler;
import net.minecraft.screen.slot.Slot;

@Mixin(EnchantmentScreenHandler.class)
public class EnchantingTableItemCostOverrider {

    @Shadow
    private Inventory inventory;

    @ModifyArg(method = "<init>(ILnet/minecraft/entity/player/PlayerInventory;Lnet/minecraft/screen/ScreenHandlerContext;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/EnchantmentScreenHandler;addSlot(Lnet/minecraft/screen/slot/Slot;)Lnet/minecraft/screen/slot/Slot;", ordinal = 1), index = 0)
    public Slot changeSlot(Slot oldSlot) {
        return new Slot(this.inventory, oldSlot.getIndex(), oldSlot.x, oldSlot.y){
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.isOf(ModItems.ENCHANTING_CATALYST);
            }
        };
    }

    @ModifyArg(method = "quickMove",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z", ordinal = 0), index = 0)
    public Item changeItem(Item oldItem) {
        return ModItems.ENCHANTING_CATALYST;
    }
}
