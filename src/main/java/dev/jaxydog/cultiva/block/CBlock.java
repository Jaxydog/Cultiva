package dev.jaxydog.cultiva.block;

import java.util.function.BiConsumer;

import dev.jaxydog.cultiva.Cultiva;
import dev.jaxydog.cultiva.utility.Generatable;
import dev.jaxydog.cultiva.utility.Registerable;
import net.minecraft.block.Block;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.server.BlockLootTableGenerator;
import net.minecraft.item.Item;
import net.minecraft.loot.LootTable.Builder;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class CBlock extends Block
		implements Generatable.Loot<Item>, Generatable.Model<BlockStateModelGenerator>, Registerable {

	protected final Properties _PROPERTIES;

	public CBlock(Settings settings, Properties properties) {
		super(settings);
		_PROPERTIES = properties;
	}

	@Override
	public CBlock generateLoot(BiConsumer<Identifier, Builder> generator, Item item) {
		if (_PROPERTIES.isLootGenerated()) {
			generator.accept(getLootTableId(), BlockLootTableGenerator.drops(item));
		}

		return this;
	}

	@Override
	public CBlock generateModel(BlockStateModelGenerator generator) {
		generator.registerSimpleCubeAll(this);
		return null;
	}

	@Override
	public Identifier getRegistryId() {
		return Cultiva.id(_PROPERTIES.getName());
	}

	@Override
	public CBlock register() {
		return Registry.register(Registry.BLOCK, getRegistryId(), this);
	}

	public static class Properties {

		private final String __NAME;
		private boolean __generateLoot = false;

		public Properties(String name) {
			__NAME = name;
		}

		public String getName() {
			return __NAME;
		}

		public boolean isLootGenerated() {
			return __generateLoot;
		}

		public Properties generateLoot() {
			__generateLoot = true;
			return this;
		}

	}

}
