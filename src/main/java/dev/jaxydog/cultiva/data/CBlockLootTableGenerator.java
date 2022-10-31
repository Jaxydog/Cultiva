package dev.jaxydog.cultiva.data;

import java.util.function.BiConsumer;

import dev.jaxydog.cultiva.block.CBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.loot.LootTable.Builder;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.util.Identifier;

public class CBlockLootTableGenerator extends SimpleFabricLootTableProvider {

	public CBlockLootTableGenerator(FabricDataGenerator dataGenerator) {
		super(dataGenerator, LootContextTypes.BLOCK);
	}

	@Override
	public void accept(BiConsumer<Identifier, Builder> generator) {
		CBlocks.generateLoot(generator);
	}

}
