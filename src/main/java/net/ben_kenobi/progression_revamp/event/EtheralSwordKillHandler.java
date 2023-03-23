package net.ben_kenobi.progression_revamp.event;

import net.ben_kenobi.progression_revamp.ModEntityTags;
import net.ben_kenobi.progression_revamp.item.custom.EtheralSword;
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityCombatEvents.AfterKilledOtherEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtFloat;
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
        if (etheralSwordStack == null) return;

        if (etheralSwordStack.getNbt().get("Charge") != null) {
            //discharge(etheralSwordStack);
            return;
        }

        if (killedEntity.getType().isIn(ModEntityTags.ENCHANT_CHARGE_ETHERAL_SWORD_ON_KILL)) {
            enchantCharge(etheralSwordStack);
        }
        if (killedEntity.getType().isIn(ModEntityTags.CHARGE_ETHERAL_SWORD_ON_KILL)) {
            charge(etheralSwordStack);
        }
    }

    private void enchantCharge(ItemStack itemStack) {
        NbtCompound nbtCompound = itemStack.getOrCreateNbt();
        nbtCompound.put("Charge", NbtFloat.of(0.5f));
    }

    private void enchantDischarge(ItemStack itemStack) {
        NbtCompound nbtCompound = itemStack.getOrCreateNbt();
        nbtCompound.remove("Charge");
    }

    private void charge(ItemStack itemStack) {
        NbtCompound nbtCompound = itemStack.getOrCreateNbt();
        nbtCompound.put("Charge", NbtFloat.of(1.0f));
    }

    private void discharge(ItemStack itemStack) {
        NbtCompound nbtCompound = itemStack.getOrCreateNbt();
        nbtCompound.remove("Charge");
    }
}
