package dev.jaxydog.cultiva.data;

import dev.jaxydog.cultiva.block.CBlocks;
import dev.jaxydog.cultiva.item.CItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;

public class CModelGenerator extends FabricModelProvider {

	public CModelGenerator(FabricDataGenerator dataGenerator) {
		super(dataGenerator);
	}

	@Override
	public void generateBlockStateModels(BlockStateModelGenerator generator) {
		CBlocks.generateModels(generator);
	}

	@Override
	public void generateItemModels(ItemModelGenerator generator) {
		CItems.generateModels(generator);
	}

}
