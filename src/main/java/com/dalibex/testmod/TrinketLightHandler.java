package com.dalibex.testmod;

import net.minecraft.entity.player.PlayerEntity;

public class TrinketLightHandler {
    public static int getLightLevel(PlayerEntity player) {

        if (!player.getInventory().isEmpty()) {
            if (player.getInventory().getStack(9).getItem() == ModItems.CANDLE || player.getInventory().getStack(10).getItem() == ModItems.CANDLE) {
                return 15;
            }
        }

        return 0;
    }
}