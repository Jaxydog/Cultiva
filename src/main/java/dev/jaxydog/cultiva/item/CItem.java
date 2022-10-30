package dev.jaxydog.cultiva.item;

import java.util.ArrayList;
import java.util.List;

import dev.jaxydog.cultiva.Cultiva;
import dev.jaxydog.cultiva.utility.Overwrite;
import dev.jaxydog.cultiva.utility.Registerable;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class CItem extends Item implements Registerable {

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

}
