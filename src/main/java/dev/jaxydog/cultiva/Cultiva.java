package dev.jaxydog.cultiva;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.jaxydog.cultiva.block.CBlocks;
import dev.jaxydog.cultiva.item.CItems;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public class Cultiva implements ModInitializer {
	public static final String MOD_ID = "cultiva";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static Identifier id(String path) {
		return new Identifier(MOD_ID, path);
	}

	@Override
	public void onInitialize() {
		CBlocks.register();
		CItems.register();

		LOGGER.info("Cultiva has been loaded successfully!");
	}
}
