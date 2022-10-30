package dev.jaxydog.cultiva;

import dev.jaxydog.cultiva.block.CBlocks;
import dev.jaxydog.cultiva.item.CItems;
import net.fabricmc.api.DedicatedServerModInitializer;

public class CultivaServer implements DedicatedServerModInitializer {

	@Override
	public void onInitializeServer() {
		Cultiva.LOGGER.info("Loading Cultiva (Server)!");

		CBlocks.registerServer();
		CItems.registerServer();
	}

}
