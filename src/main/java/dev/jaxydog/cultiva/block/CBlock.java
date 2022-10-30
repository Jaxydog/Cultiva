package dev.jaxydog.cultiva.block;

import org.jetbrains.annotations.Nullable;

import dev.jaxydog.cultiva.Cultiva;
import dev.jaxydog.cultiva.utility.Registerable;
import net.minecraft.block.Block;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class CBlock extends Block implements Registerable {

	protected final Properties _PROPERTIES;

	public CBlock(Settings settings, Properties properties) {
		super(settings);
		_PROPERTIES = properties;
	}

	@Override
	public @Nullable Identifier getRegistryId() {
		return Cultiva.id(_PROPERTIES.getName());
	}

	@Override
	public CBlock register() {
		return Registry.register(Registry.BLOCK, getRegistryId(), this);
	}

	public static class Properties {

		private final String __NAME;

		public Properties(String name) {
			__NAME = name;
		}

		public String getName() {
			return __NAME;
		}

	}

}
