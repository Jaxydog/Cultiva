package dev.jaxydog.cultiva.utility;

import java.util.function.BiConsumer;

import net.minecraft.loot.LootTable;
import net.minecraft.util.Identifier;

public interface Generatable {

	public static interface Loot<T> extends Generatable {

		public Loot<T> generateLoot(BiConsumer<Identifier, LootTable.Builder> generator, T args);

	}

	public static interface Model<T> extends Generatable {

		public Model<T> generateModel(T generator);

	}

}
