package dev.jaxydog.cultiva;

import dev.jaxydog.cultiva.data.CBlockLootTableGenerator;
import dev.jaxydog.cultiva.data.CModelGenerator;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class CultivaDataGen implements DataGeneratorEntrypoint {

	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		Cultiva.LOGGER.info("Loading Cultiva (Data Generator)!");

		fabricDataGenerator.addProvider(CBlockLootTableGenerator::new);
		fabricDataGenerator.addProvider(CModelGenerator::new);
	}

}
