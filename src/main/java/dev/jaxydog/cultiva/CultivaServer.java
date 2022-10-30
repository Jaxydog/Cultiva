package dev.jaxydog.cultiva;

import dev.jaxydog.cultiva.block.CBlocks;
import dev.jaxydog.cultiva.item.CItems;
import net.fabricmc.api.DedicatedServerModInitializer;

public class CultivaServer implements DedicatedServerModInitializer {

	@Override
	public void onInitializeServer() {
		CBlocks.registerServer();
		CItems.registerServer();

		Cultiva.LOGGER.info("Cultiva (server) has been loaded successfully!");
	}

}
