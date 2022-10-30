package dev.jaxydog.cultiva.item;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.item.Item;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.BinomialLootNumberProvider;
import net.minecraft.util.Identifier;

public class LootModifier {

	private final Identifier __TABLE_ID;
	private final boolean __REQUIRE_BUILTIN;

	private float __chance = 0.0f;
	private int __rolls = 1;

	public LootModifier(Identifier table) {
		this(table, true);
	}

	public LootModifier(Identifier table, boolean requireBuiltin) {
		__TABLE_ID = table;
		__REQUIRE_BUILTIN = requireBuiltin;
	}

	public LootModifier chance(float chance) {
		__chance = chance;
		return this;
	}

	public LootModifier rolls(int rolls) {
		__rolls = Math.max(1, rolls);
		return this;
	}

	public void register(Item item) {
		LootTableEvents.MODIFY.register((resource, loot, id, table, source) -> {
			if (!id.equals(__TABLE_ID) || (__REQUIRE_BUILTIN && !source.isBuiltin())) {
				return;
			}

			table.pool(LootPool.builder().rolls(BinomialLootNumberProvider.create(__rolls, __chance))
					.with(ItemEntry.builder(item)));
		});
	}

}
