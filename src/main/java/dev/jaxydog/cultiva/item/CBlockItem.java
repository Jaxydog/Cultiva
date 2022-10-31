package dev.jaxydog.cultiva.item;

import java.util.List;

import dev.jaxydog.cultiva.Cultiva;
import dev.jaxydog.cultiva.item.CItem.Properties;
import dev.jaxydog.cultiva.utility.Generatable;
import dev.jaxydog.cultiva.utility.Registerable;
import net.minecraft.block.Block;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class CBlockItem extends BlockItem implements Generatable.Model<ItemModelGenerator>, Registerable {

	protected final Properties _PROPERTIES;

	public CBlockItem(Block block, Settings settings, Properties properties) {
		super(block, settings);
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
	public CBlockItem generateModel(ItemModelGenerator generator) {
		generator.register(this, Models.CUBE_ALL);
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
	public CBlockItem register() {
		for (var modifier : _PROPERTIES.getLootModifiers()) {
			modifier.register(this);
		}

		return Registry.register(Registry.ITEM, getRegistryId(), this);
	}

}
