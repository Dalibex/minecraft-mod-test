package com.dalibex.trinket;

import dev.lambdaurora.lambdynlights.api.entity.luminance.EntityLuminance;
import dev.lambdaurora.lambdynlights.api.item.ItemLightSourceManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import org.jetbrains.annotations.Range;

public final class ConstantEntityLuminance implements EntityLuminance {
    // The singleton instance.
    public static final ConstantEntityLuminance INSTANCE = new ConstantEntityLuminance();

    private ConstantEntityLuminance() {}

    @Override
    public Type type() {
        // This is the registered type of this entity luminance provider.
        return TestModDynamicLightsInitializer.CONSTANT;
    }

    @Override
    public @Range(from = 0, to = 15) int getLuminance(
            ItemLightSourceManager itemLightSourceManager,
            Entity entity
    ) {
        if (!(entity instanceof PlayerEntity player)) {
            return 0;
        }
        return TrinketLightHandler.getLightLevel(player);
    }
}
