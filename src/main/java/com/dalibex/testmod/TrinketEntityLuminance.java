package com.dalibex.testmod;

import dev.lambdaurora.lambdynlights.api.entity.luminance.EntityLuminance;
import dev.lambdaurora.lambdynlights.api.item.ItemLightSourceManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import org.jetbrains.annotations.Range;

public final class TrinketEntityLuminance implements EntityLuminance {
    public static final TrinketEntityLuminance INSTANCE = new TrinketEntityLuminance();

    private TrinketEntityLuminance() {}

    @Override
    public Type type() {
        return TestModDynamicLightsInitializer.TRINKET_LIGHT;
    }

    @Override
    public @Range(from = 0, to = 15) int getLuminance(ItemLightSourceManager itemLightSourceManager, Entity entity) {
        if (entity instanceof PlayerEntity player) {
            return TrinketLightHandler.getLightLevel(player);
        }
        return 0;
    }
}