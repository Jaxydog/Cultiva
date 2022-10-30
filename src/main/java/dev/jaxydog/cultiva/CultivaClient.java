package dev.jaxydog.cultiva;

import dev.jaxydog.cultiva.block.CBlocks;
import dev.jaxydog.cultiva.item.CItems;
import net.fabricmc.api.ClientModInitializer;

public class CultivaClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		Cultiva.LOGGER.info("Loading Cultiva (Client)!");

		CBlocks.registerClient();
		CItems.registerClient();
	}

}
