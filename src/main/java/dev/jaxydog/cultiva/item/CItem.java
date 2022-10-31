package dev.jaxydog.cultiva.item;

import java.util.ArrayList;
import java.util.List;

import dev.jaxydog.cultiva.Cultiva;
import dev.jaxydog.cultiva.utility.Generatable;
import dev.jaxydog.cultiva.utility.Overwrite;
import dev.jaxydog.cultiva.utility.Registerable;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.BinomialLootNumberProvider;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class CItem extends Item implements Generatable.Model<ItemModelGenerator>, Registerable {

	public static final ItemGroup CROPS_GROUP = FabricItemGroupBuilder.build(Cultiva.id("crops"),
			() -> Items.WHEAT.getDefaultStack());
	public static final ItemGroup FOODS_GROUP = FabricItemGroupBuilder.build(Cultiva.id("foods"),
			() -> Items.BREAD.getDefaultStack());
	public static final ItemGroup TOOLS_GROUP = FabricItemGroupBuilder.build(Cultiva.id("tools"),
			() -> Items.SHEARS.getDefaultStack());

	protected final Properties _PROPERTIES;

	public CItem(Settings settings, Properties properties) {
		super(settings);
		_PROPERTIES = properties;
	}

	@Override
	public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
		if (_PROPERTIES.hasTooltip()) {
			var key = stack.getItem().getTranslationKey() + ".tooltip_";
			var count = 0;

			while (I18n.hasTranslation(key + count)) {
				tooltip.add(Text.translatable(key + count).formatted(Formatting.GRAY, Formatting.ITALIC));
				count += 1;
			}
		}

		super.appendTooltip(stack, world, tooltip, context);
	}

	@Override
	public CItem generateModel(ItemModelGenerator generator) {
		generator.register(this, Models.GENERATED);
		return null;
	}

	@Override
	public Identifier getRegistryId() {
		return Cultiva.id(_PROPERTIES.getName());
	}

	@Override
	public boolean hasGlint(ItemStack stack) {
		switch (_PROPERTIES.getGlintOverwrite()) {
			case Always:
				return true;
			case Never:
				return false;
			default:
				return super.hasGlint(stack);
		}
	}

	@Override
	public CItem register() {
		for (var modifier : _PROPERTIES.getLootModifiers()) {
			modifier.register(this);
		}

		return Registry.register(Registry.ITEM, getRegistryId(), this);
	}

	public static class Properties {

		private final String __NAME;

		private Overwrite __glint = Overwrite.Super;
		private ArrayList<LootModifier> __lootModifiers = new ArrayList<>();
		private boolean __tooltip = false;

		public Properties(String name) {
			__NAME = name;
		}

		public String getName() {
			return __NAME;
		}

		public Overwrite getGlintOverwrite() {
			return __glint;
		}

		public ArrayList<LootModifier> getLootModifiers() {
			return __lootModifiers;
		}

		public boolean hasTooltip() {
			return __tooltip;
		}

		public Properties glintDisabled() {
			__glint = Overwrite.Always;
			return this;
		}

		public Properties glintForced() {
			__glint = Overwrite.Always;
			return this;
		}

		public Properties lootModifier(LootModifier modifier) {
			__lootModifiers.add(modifier);
			return this;
		}

		public Properties tooltip() {
			__tooltip = true;
			return this;
		}

	}

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

}
