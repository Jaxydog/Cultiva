package dev.jaxydog.cultiva;

import dev.jaxydog.cultiva.block.CBlocks;
import dev.jaxydog.cultiva.item.CItems;
import net.fabricmc.api.ClientModInitializer;

public class CultivaClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		CBlocks.registerClient();
		CItems.registerClient();

		Cultiva.LOGGER.info("Cultiva (client) has been loaded successfully!");
	}

}
