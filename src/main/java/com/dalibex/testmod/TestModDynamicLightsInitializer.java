package com.dalibex.testmod;

import dev.lambdaurora.lambdynlights.api.DynamicLightsContext;
import dev.lambdaurora.lambdynlights.api.DynamicLightsInitializer;
import dev.lambdaurora.lambdynlights.api.entity.luminance.EntityLuminance;
import net.minecraft.util.Identifier;

public class TestModDynamicLightsInitializer implements DynamicLightsInitializer {

    public static final EntityLuminance.Type CONSTANT
            = EntityLuminance.Type.registerSimple(
            Identifier.of("testmod", "light"),
            ConstantEntityLuminance.INSTANCE
    );

    public static final EntityLuminance.Type TRINKET_LIGHT =
            EntityLuminance.Type.registerSimple(
                    Identifier.of("testmod", "trinket_light"),
                    TrinketEntityLuminance.INSTANCE
            );

    @Override
    public void onInitializeDynamicLights(DynamicLightsContext context) {
        context.entityLightSourceManager().onRegisterEvent().register(registry -> {
            registry.register(net.minecraft.entity.EntityType.PLAYER, TrinketEntityLuminance.INSTANCE);
        });
    }
}
