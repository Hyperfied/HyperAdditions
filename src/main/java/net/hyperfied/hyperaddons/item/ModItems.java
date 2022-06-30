package net.hyperfied.hyperaddons.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.hyperfied.hyperaddons.HyperAddons;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {

    public static final Item DIAMOND_NUGGET = registerItem("diamond_nugget", new Item(new FabricItemSettings().group(ItemGroup.MISC)));
    public static final Item EMERALD_NUGGET = registerItem("emerald_nugget", new Item(new FabricItemSettings().group(ItemGroup.MISC)));

    private static Item registerItem(String name, Item item){
        return Registry.register(Registry.ITEM, new Identifier(HyperAddons.MOD_ID, name), item);
    }

    public static void registerModItems() {
        HyperAddons.LOGGER.info("Registering Mod Items for " + HyperAddons.MOD_ID);
    }

}
