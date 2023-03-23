package net.ben_kenobi.progression_revamp.event;

import net.ben_kenobi.progression_revamp.ModEntityTags;
import net.ben_kenobi.progression_revamp.item.custom.EtheralSword;
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityCombatEvents.AfterKilledOtherEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtFloat;
import net.minecraft.nbt.NbtInt;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;

public class EtheralSwordKillHandler implements AfterKilledOtherEntity {

    @Override
    public void afterKilledOtherEntity(ServerWorld world, Entity entity, LivingEntity killedEntity) {
        ItemStack etheralSwordStack = null;
        for (ItemStack itemStack : entity.getItemsEquipped()) {
            if (itemStack.getItem() instanceof EtheralSword) {
                etheralSwordStack = itemStack;
            }
        }
        if (etheralSwordStack != null) {
            NbtCompound nbtCompound = etheralSwordStack.getOrCreateNbt();
            if (killedEntity.getType().isIn(ModEntityTags.ENCHANT_CHARGE_ETHERAL_SWORD_ON_KILL)) {
                entity.sendMessage(Text.literal("enchantcharge sword"));
                nbtCompound.put("Charge", NbtFloat.of(0.5f));
            }
            if (killedEntity.getType().isIn(ModEntityTags.CHARGE_ETHERAL_SWORD_ON_KILL)) {
                entity.sendMessage(Text.literal("charge sword"));
                nbtCompound.put("Charge", NbtFloat.of(1));
            }
        }
    }
}
