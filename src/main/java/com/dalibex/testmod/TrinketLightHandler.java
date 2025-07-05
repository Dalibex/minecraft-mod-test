package com.dalibex.testmod;

import net.minecraft.entity.player.PlayerEntity;

public class TrinketLightHandler {
    public static int getLightLevel(PlayerEntity player) {

        boolean hasCandleInMainHand = player.getMainHandStack().getItem() == ModItems.CANDLE;
        return hasCandleInMainHand ? 15 : 0;
    }
}