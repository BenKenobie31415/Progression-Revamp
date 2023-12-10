package net.ben_kenobi.progression_revamp.mixin;

import java.util.Collection;
import java.util.Set;
import java.util.function.Consumer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.UnbreakingEnchantment;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.random.Random;

/**
 * Changes the behavior of the Unbreaking enchantment.
 * Now it will only prevent items from fully breaking but does not slower the rate of damage.
 */
public abstract class UnbreakingHandler {
    /**
     * Disables the normal effect of Unbreaking.
     */
    @Mixin(UnbreakingEnchantment.class)
    public static abstract class UnbreakableMaker {
        @Inject(method = "shouldPreventDamage", at = @At("HEAD"), cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
        private static void inject(ItemStack item, int level, Random random, CallbackInfoReturnable<Boolean> cir) {
            cir.setReturnValue(false);
            int durability = item.getMaxDamage() - item.getDamage();
            if (durability <= 1 && level > 0) {
                cir.setReturnValue(true);
                if (durability == 1) {
                    item.setDamage(item.getMaxDamage() + 1);
                }
            }
            cir.cancel();
        }
    }

    /**
     * Reduces the armor-value of an entity if armor pieces are broken.
     */
    @Mixin(LivingEntity.class)
    public static abstract class ArmorReducer {
        @Shadow
        public abstract Iterable<ItemStack> getArmorItems();

        @Redirect(method = "getArmor", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;getAttributeValue(Lnet/minecraft/entity/attribute/EntityAttribute;)D"))
        private double reduceArmorIfBroken(LivingEntity livingEntity, EntityAttribute attribute) {
            double armorReduction = 0.0d;
            Iterable<ItemStack> armorItems = getArmorItems();

            /*
                * Should make more accurate.
                * Currently, would also reduce armor if a helmet would be worn on the head and was broken but had armor-value for chest-slot.
             */
            for (ItemStack itemStack : armorItems) {
                if (itemStack.getMaxDamage() - itemStack.getDamage() < 0) {
                    Collection<EntityAttributeModifier> modifiers = itemStack.getAttributeModifiers(EquipmentSlot.HEAD).get(EntityAttributes.GENERIC_ARMOR);
                    modifiers.addAll(itemStack.getAttributeModifiers(EquipmentSlot.CHEST).get(EntityAttributes.GENERIC_ARMOR));
                    modifiers.addAll(itemStack.getAttributeModifiers(EquipmentSlot.LEGS).get(EntityAttributes.GENERIC_ARMOR));
                    modifiers.addAll(itemStack.getAttributeModifiers(EquipmentSlot.FEET).get(EntityAttributes.GENERIC_ARMOR));
                    for (EntityAttributeModifier modifier : modifiers) {
                        armorReduction += modifier.getValue();
                    }
                }
            }
            return livingEntity.getAttributeValue(attribute) - armorReduction;
        }
    }

    /**
     * Reduces the attack damage of a player if the weapon is broken.
     */
    @Mixin(PlayerEntity.class)
    public static abstract class AttackDamageReducer {
        @Redirect(method = "attack", at =  @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;getAttributeValue(Lnet/minecraft/entity/attribute/EntityAttribute;)D"))
        private double reduceWeaponDamageIfBroken(PlayerEntity playerEntity, EntityAttribute attribute) {
            if (attribute != EntityAttributes.GENERIC_ATTACK_DAMAGE) {
                return playerEntity.getAttributeValue(attribute);
            }
            ItemStack mainHandStack = playerEntity.getMainHandStack();
            double reduction = 0.0d;

            if (mainHandStack.getMaxDamage() - mainHandStack.getDamage() < 0) {
                EntityAttributeInstance instance = playerEntity.getAttributeInstance(attribute);
                if (instance == null) {
                    return 0.0d;
                }
                Set<EntityAttributeModifier> modifiers = instance.getModifiers();
                for (EntityAttributeModifier modifier : modifiers) {
                    String modifierName = modifier.getName();
                    if (modifierName.equals("Weapon modifier") || modifierName.equals("Tool modifier")) {
                        reduction += modifier.getValue();
                    }
                }
            }
            return playerEntity.getAttributeValue(attribute) - reduction;
        }

        @Redirect(method = "attack", at = @At(value = "INVOKE", target = "Lnet/minecraft/enchantment/EnchantmentHelper;getAttackDamage(Lnet/minecraft/item/ItemStack;Lnet/minecraft/entity/EntityGroup;)F"))
        private float removeEnchantmentDamageIfBroken(ItemStack stack, EntityGroup group) {
            if (stack.getMaxDamage() - stack.getDamage() < 0) {
                return 0.0f;
            }
            return EnchantmentHelper.getAttackDamage(stack, group);
        }
    }

    /**
     * Disables all tool functionality if the tool is broken.
     */
    @Mixin(ItemStack.class)
    public static abstract class ToolFunctionalityDisabler {
        @Shadow
        public abstract int getDamage();
        @Shadow
        public abstract int getMaxDamage();
        @Shadow
        public abstract boolean isDamageable();
        @Shadow
        public abstract Item getItem();

        @Inject(method = "isSuitableFor", at = @At("HEAD"), cancellable = true)
        private void inject(CallbackInfoReturnable<Boolean> cir) {
            if (getMaxDamage() - getDamage() < 0) {
                cir.setReturnValue(false);
                cir.cancel();
            }
        }

        @Inject(method = "getMiningSpeedMultiplier", at = @At("HEAD"), cancellable = true)
        private void inject(BlockState state, CallbackInfoReturnable<Float> cir) {
            if (getMaxDamage() - getDamage() < 0) {
                cir.setReturnValue(1.0f);
                cir.cancel();
            }
        }

        @Inject(method = "damage(ILnet/minecraft/entity/LivingEntity;Ljava/util/function/Consumer;)V", at = @At(value = "HEAD"))
        private <T extends LivingEntity> void inject(int amount, T entity, Consumer<T> breakCallback, CallbackInfo ci) {
            boolean hasUnbreaking = EnchantmentHelper.getLevel(Enchantments.UNBREAKING, (ItemStack)(Object)this) >= 1;
            if (getMaxDamage() - getDamage() == 1 && hasUnbreaking && isDamageable()) {
                breakCallback.accept(entity);
                Item item = getItem();
                ((PlayerEntity)entity).incrementStat(Stats.BROKEN.getOrCreateStat(item));
            }
        }

        @Inject(method = "useOnBlock", at = @At(value = "HEAD"), cancellable = true)
        private void inject(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir) {
            if (getMaxDamage() - getDamage() < 0) {
                cir.setReturnValue(ActionResult.PASS);
                cir.cancel();
            }
        }
    }
}
