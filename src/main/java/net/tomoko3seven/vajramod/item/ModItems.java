package net.tomoko3seven.vajramod.item;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Item.Properties;
import net.tomoko3seven.vajramod.VajraTier;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "vajramod");

    public static final RegistryObject<Item> VAJRA = ITEMS.register("vajra",
            () -> new VajraItem(new VajraTier(), new Item.Properties()));
}