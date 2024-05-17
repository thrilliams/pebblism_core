package co.aethre.create_enchancement;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateEnchancement implements ModInitializer {
	public static final String ID = "create_enchancement";
	public static final String NAME = "Create: Enchancement";
	public static final Logger LOGGER = LoggerFactory.getLogger(NAME);

	@Override
	public void onInitialize() {
		LOGGER.info("get ready to rock and roll idiots");
	}
}
