package net.tomoko3seven.vajramod;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;

public class VajraTier implements Tier {
    @Override
    public int getLevel() {
        return 1000; // Позволяет ломать всё
    }

    @Override
    public int getUses() {
        return Integer.MAX_VALUE; // Бесконечная прочность
    }

    @Override
    public float getSpeed() {
        return 1000.0f; // Супер-быстрая добыча
    }

    @Override
    public float getAttackDamageBonus() {
        return 10.0f;
    }

    @Override
    public int getEnchantmentValue() {
        return 0;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.of(Blocks.DIAMOND_BLOCK); // Можно настроить на что угодно
    }
}