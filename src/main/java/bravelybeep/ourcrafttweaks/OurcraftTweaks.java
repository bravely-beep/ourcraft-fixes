package bravelybeep.ourcrafttweaks;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.util.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OurcraftTweaks implements ModInitializer {
	public static final String MOD_ID = "ourcraft-tweaks";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	static Identifier INCENDIUM_TOXIC_HEAP = Identifier.of("incendium", "toxic_heap");

	@Override
	public void onInitialize() {
		ServerEntityEvents.ENTITY_LOAD.register((entity, world) -> {
			if (!world.isClient() && entity instanceof SlimeEntity slime && slime.getType() == EntityType.SLIME && world.getBiome(slime.getBlockPos()).getIdAsString() == "incendium:toxic_heap")
				LOGGER.info("SLIME SUMMONED");
			if (!world.isClient()
					&& entity instanceof SlimeEntity slime
					&& slime.getType() == EntityType.SLIME
					&& world.getBiome(slime.getBlockPos()).matchesId(INCENDIUM_TOXIC_HEAP)) {
				slime.addStatusEffect(new StatusEffectInstance(
						StatusEffects.FIRE_RESISTANCE,
						StatusEffectInstance.INFINITE,
						3,
						false,
						false));
			}

		});
	}
}