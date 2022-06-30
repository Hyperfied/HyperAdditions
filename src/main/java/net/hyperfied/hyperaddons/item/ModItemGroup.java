package net.hyperfied.hyperaddons.item;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.hyperfied.hyperaddons.HyperAddons;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class ModItemGroup {
    public static final ItemGroup HYPER = FabricItemGroupBuilder.build(new Identifier(HyperAddons.MOD_ID,"hyper"), () -> new ItemStack((ModItems.DIAMOND_NUGGET)));
}
