package net.minecraft.world.biome.gen;

import net.minecraft.world.gen.feature.WorldGenLiquids;

public abstract class LiquidGenListEntry extends StandaloneGenListEntry
{
	protected LiquidGenListEntry(int liquidId, int genLoops)
	{
		super(new WorldGenLiquids(liquidId), genLoops);
	}
}
