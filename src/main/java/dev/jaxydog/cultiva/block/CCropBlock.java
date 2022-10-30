package dev.jaxydog.cultiva.block;

import org.jetbrains.annotations.Nullable;

import dev.jaxydog.cultiva.Cultiva;
import dev.jaxydog.cultiva.block.CBlock.Properties;
import dev.jaxydog.cultiva.utility.ClientRegisterable;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropBlock;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.state.StateManager.Builder;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockView;

public class CCropBlock extends CropBlock implements ClientRegisterable {

	protected final Properties _PROPERTIES;
	protected final CropConfig _CROP_CONFIG;
	protected final IntProperty _AGE_PROPERTY;

	public CCropBlock(Settings settings, Properties properties, CropConfig config) {
		super(settings);
		_PROPERTIES = properties;
		_CROP_CONFIG = config;
		_AGE_PROPERTY = IntProperty.of("age", 0, config.getMaxAge());
	}

	@Override
	protected void appendProperties(Builder<Block, BlockState> builder) {
		builder.add(getAgeProperty());
	}

	@Override
	protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
		return floor.isOf(_CROP_CONFIG.getPlantableBlock());
	}

	@Override
	public IntProperty getAgeProperty() {
		return _AGE_PROPERTY;
	}

	@Override
	public int getMaxAge() {
		return _CROP_CONFIG.getMaxAge();
	}

	@Override
	public Identifier getRegistryId() {
		return Cultiva.id(_PROPERTIES.getName());
	}

	@Override
	protected ItemConvertible getSeedsItem() {
		var item = _CROP_CONFIG.getSeedsItem();
		return item != null ? item : Items.AIR;
	}

	@Override
	public CCropBlock register() {
		return Registry.register(Registry.BLOCK, getRegistryId(), this);
	}

	@Override
	public CCropBlock registerClient() {
		BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), this);
		return this;
	}

	public static class CropConfig {

		private int __maxAge = 7;
		private Block __plantableBlock = Blocks.FARMLAND;
		private @Nullable Item __seedsItem = null;

		public int getMaxAge() {
			return __maxAge;
		}

		public Block getPlantableBlock() {
			return __plantableBlock;
		}

		public @Nullable Item getSeedsItem() {
			return __seedsItem;
		}

		public CropConfig maxAge(int age) {
			__maxAge = age;
			return this;
		}

		public CropConfig plantOn(Block block) {
			__plantableBlock = block;
			return this;
		}

		public CropConfig seeds(Item seeds) {
			__seedsItem = seeds;
			return this;
		}

	}

}
