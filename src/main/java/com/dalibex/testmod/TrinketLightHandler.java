package com.dalibex.testmod;

import net.minecraft.entity.player.PlayerEntity;

public class TrinketLightHandler {
    public static int getLightLevel(PlayerEntity player) {
        if (player.getMainHandStack().getItem() == ModItems.CANDLE) {
            return 15;
        }
        return 0;
    }
}