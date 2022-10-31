package dev.jaxydog.cultiva.item;

import dev.jaxydog.cultiva.item.CItem.LootModifier;
import dev.jaxydog.cultiva.item.CItem.Properties;
import net.minecraft.block.Blocks;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item.Settings;

public class CItems {

	public static final CItem AVOCADO = new CItem(
			new Settings().food(new FoodComponent.Builder().hunger(2).saturationModifier(1.25f).build())
					.group(CItem.FOODS_GROUP),
			new Properties("avocado")
					.lootModifier(new LootModifier(Blocks.JUNGLE_LEAVES.getLootTableId()).chance(0.0125f)).tooltip());

	public static final CItem BANANA = new CItem(
			new Settings().food(new FoodComponent.Builder().hunger(1).saturationModifier(1.25f).snack().build())
					.group(CItem.FOODS_GROUP),
			new Properties("banana")
					.lootModifier(new LootModifier(Blocks.JUNGLE_LEAVES.getLootTableId()).chance(0.0125f).rolls(3))
					.tooltip());

	public static final CItem CHEESE = new CItem(
			new Settings().food(new FoodComponent.Builder().hunger(1).saturationModifier(1.25f).snack().build())
					.group(CItem.FOODS_GROUP),
			new Properties("cheese").tooltip());

	public static final CItem TOMATO = new CItem(
			new Settings().food(new FoodComponent.Builder().hunger(1).saturationModifier(1.25f).snack().build())
					.group(CItem.FOODS_GROUP),
			new Properties("tomato").tooltip());

	public static void generateModels(ItemModelGenerator generator) {
		AVOCADO.generateModel(generator);
		BANANA.generateModel(generator);
		CHEESE.generateModel(generator);
		TOMATO.generateModel(generator);
	}

	public static void register() {
		AVOCADO.register();
		BANANA.register();
		CHEESE.register();
		TOMATO.register();
	}

	public static void registerClient() {

	}

	public static void registerServer() {

	}

}
