package net.tomoko3seven.vajramod.item;

import net.minecraft.world.item.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;

import java.util.List;

public class VajraItem extends TieredItem implements Vanishable {
    private static final int MAX_ENERGY = 1000000;
    private static final int ENERGY_PER_USE = 100;

    public VajraItem(Tier tier, Properties properties) {
        super(tier, properties.durability(MAX_ENERGY)); // Используем прочность как шкалу энергии
    }

    @Override
    public boolean isCorrectToolForDrops(BlockState blockState) {
        return true;
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        return hasEnergy(stack) ? 10000.0F : 1.0F;
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (hasEnergy(stack)) {
            extractEnergy(stack, ENERGY_PER_USE);
            target.hurt(target.damageSources().playerAttack((Player) attacker), 15.0F);
        }
        return super.hurtEnemy(stack, target, attacker);
    }

    @Override
    public boolean mineBlock(ItemStack stack, Level world, BlockState state, BlockPos pos, LivingEntity entity) {
        if (hasEnergy(stack)) {
            extractEnergy(stack, ENERGY_PER_USE);
            return super.mineBlock(stack, world, state, pos, entity);
        }
        return false;
    }

    @Override
    public void appendHoverText(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.literal("§eEnergy: " + getEnergy(stack) + " FE"));
    }


    @Override
    public boolean isDamageable(ItemStack stack) {
        return true;
    }

    @Override
    public boolean isRepairable(ItemStack stack) {
        return false;
    }


    @Override
    public int getBarColor(ItemStack stack) {
        return 0x00FFFF;
    }

    @Override
    public int getBarWidth(ItemStack stack) {
        return (int) (13 * ((double) getEnergy(stack) / MAX_ENERGY));
    }

    // Методы для работы с энергией
    private boolean hasEnergy(ItemStack stack) {
        return getEnergy(stack) >= ENERGY_PER_USE;
    }

    private int getEnergy(ItemStack stack) {
        return MAX_ENERGY - stack.getDamageValue();
    }

    private void extractEnergy(ItemStack stack, int amount) {
        int energy = getEnergy(stack);
        stack.setDamageValue(MAX_ENERGY - Math.max(0, energy - amount));
    }
}
