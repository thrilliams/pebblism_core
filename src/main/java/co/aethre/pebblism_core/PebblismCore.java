package co.aethre.pebblism_core;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PebblismCore implements ModInitializer {
	public static final String ID = "pebblism_core";
	public static final String NAME = "Pebblism Core";
	public static final Logger LOGGER = LoggerFactory.getLogger(NAME);

	@Override
	public void onInitialize() {
		LOGGER.info("get ready to rock and roll idiots");
	}
}
