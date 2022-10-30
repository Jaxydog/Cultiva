package dev.jaxydog.cultiva.utility;

import org.jetbrains.annotations.Nullable;

import net.minecraft.util.Identifier;

public interface Registerable {

	@Nullable
	public Identifier getRegistryId();

	public Registerable register();

	public static interface Client extends Registerable {

		public Client registerClient();

	}

	public static interface Server extends Registerable {

		public Server registerServer();

	}

}
