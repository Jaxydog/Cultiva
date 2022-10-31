package dev.jaxydog.cultiva.item;

import dev.jaxydog.cultiva.item.CItem.Properties;
import net.minecraft.block.Block;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class CSeedItem extends CBlockItem {

	public CSeedItem(Block block, Settings settings, Properties properties) {
		super(block, settings, properties);
	}

	@Override
	public CSeedItem generateModel(ItemModelGenerator generator) {
		generator.register(this, Models.GENERATED);
		return null;
	}

	@Override
	public String getTranslationKey() {
		return getOrCreateTranslationKey();
	}

}
