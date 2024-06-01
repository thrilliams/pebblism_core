package co.aethre.pebblism_core;

import java.util.List;

import com.bawnorton.mixinsquared.api.MixinCanceller;

public class PebblismCoreMixinCanceller implements MixinCanceller {
	@Override
	public boolean shouldCancel(List<String> targetClassNames, String mixinClassName) {
		return mixinClassName.equals("com.hidoni.transmog.mixin.ItemRendererMixin");
	}
}
