package dev.jaxydog.cultiva.utility;

import org.jetbrains.annotations.Nullable;

import net.minecraft.util.Identifier;

public interface Registerable {

	@Nullable
	public Identifier getRegistryId();

	public Registerable register();

}
