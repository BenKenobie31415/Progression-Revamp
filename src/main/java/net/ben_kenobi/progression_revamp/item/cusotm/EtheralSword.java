package net.ben_kenobi.progression_revamp.item.cusotm;

import java.util.List;

import net.ben_kenobi.progression_revamp.ProgressionRevamp;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Rarity;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

public class EtheralSword extends SwordItem {

    public EtheralSword() {
        super(ToolMaterials.GOLD, 4, -2.4f, new Settings().rarity(Rarity.EPIC));
    }
}
