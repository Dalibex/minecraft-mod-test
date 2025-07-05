package com.dalibex.testmod;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.lambdaurora.lambdynlights.api.entity.luminance.EntityLuminance;
import dev.lambdaurora.lambdynlights.api.item.ItemLightSourceManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import org.jetbrains.annotations.Range;

public record CustomEntityLuminance(boolean invert) implements EntityLuminance {
    public static final MapCodec<CustomEntityLuminance> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                    Codec.BOOL.fieldOf("invert").forGetter(CustomEntityLuminance::invert)
            ).apply(instance, CustomEntityLuminance::new)
    );

    @Override
    public Type type() {
        return TestModDynamicLightsInitializer.CUSTOM_ENTITY_LUMINANCE;
    }

    @Override
    public @Range(from = 0, to = 15) int getLuminance(
            ItemLightSourceManager itemLightSourceManager,
            Entity entity
    ) {
        if (!(entity instanceof PlayerEntity player)) {
            return 0;
        }

        // Check for trinket
        boolean hasTrinket = !player.getInventory().getStack(0).isEmpty()
                && player.getInventory().getStack(0).getItem() == ModItems.CANDLE;

        return hasTrinket ? 15 : 0;
    }
}