package com.dalibex.testmod;

import dev.lambdaurora.lambdynlights.api.DynamicLightsContext;
import dev.lambdaurora.lambdynlights.api.DynamicLightsInitializer;
import dev.lambdaurora.lambdynlights.api.entity.luminance.EntityLuminance;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;

public class TestModDynamicLightsInitializer implements DynamicLightsInitializer {

    public static final EntityLuminance.Type CUSTOM_ENTITY_LUMINANCE =
            EntityLuminance.Type.register(
                    Identifier.of("testmod", "trinket_light"),
                    CustomEntityLuminance.CODEC
            );

    static {
        System.out.println("[DEBUG] TestModDynamicLightsInitializer cargado");
    }

    @Override
    public void onInitializeDynamicLights(DynamicLightsContext context) {
        System.out.println("[DEBUG] onInitializeDynamicLights ejecutado");

        context.entityLightSourceManager().onRegisterEvent().register(registry -> {
            registry.register(EntityType.PLAYER,
                    new CustomEntityLuminance(false)
            );
        });
    }
}
