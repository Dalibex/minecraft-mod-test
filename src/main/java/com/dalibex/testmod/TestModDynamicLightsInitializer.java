package com.dalibex.testmod;

import dev.lambdaurora.lambdynlights.api.DynamicLightsContext;
import dev.lambdaurora.lambdynlights.api.DynamicLightsInitializer;
import dev.lambdaurora.lambdynlights.api.entity.luminance.EntityLuminance;
import net.minecraft.util.Identifier;

public class TestModDynamicLightsInitializer implements DynamicLightsInitializer {

    public static final EntityLuminance.Type CONSTANT
            = EntityLuminance.Type.registerSimple(
            Identifier.of("testmod", "custom"),
            ConstantEntityLuminance.INSTANCE
    );

    @Override
    public void onInitializeDynamicLights(DynamicLightsContext context) {

    }

    // Initializer code
}
