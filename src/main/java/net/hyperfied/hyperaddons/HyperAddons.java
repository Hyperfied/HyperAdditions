package net.hyperfied.hyperaddons;

import net.fabricmc.api.ModInitializer;
import net.hyperfied.hyperaddons.block.ModBlocks;
import net.hyperfied.hyperaddons.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HyperAddons implements ModInitializer {
	public static final String MOD_ID = "hyperaddons";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
	}
}
