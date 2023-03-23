package net.ben_kenobi.progression_revamp.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.util.Rarity;

public class EtheralSword extends SwordItem {

    public EtheralSword() {
        super(ToolMaterials.GOLD, 4, -2.4f, new Settings().rarity(Rarity.EPIC));
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        super.postHit(stack, target, attacker);
        //attacker.sendMessage(Text.literal("test"));
        return true;
    }
}
