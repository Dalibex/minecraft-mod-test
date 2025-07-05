package com.dalibex.testmod;

import net.minecraft.entity.player.PlayerEntity;

public class TrinketLightHandler {
    private static boolean lightOn = false;

    public static int getLightLevel(PlayerEntity player) {
        boolean hasCandleInMainHand = player.getMainHandStack().getItem() == ModItems.CANDLE;

        if (hasCandleInMainHand) {
            if (!lightOn) {
                lightOn = true;
                onLightTurnedOn(player);
            }
            return 15;  // Max light
        } else {
            if (lightOn) {
                lightOn = false;
                onLightTurnedOff(player);
            }
            return 0;  // No light source
        }
    }

    private static void onLightTurnedOn(PlayerEntity player) {
        System.out.println("Luz encendida para " + player.getName().getString());
    }

    private static void onLightTurnedOff(PlayerEntity player) {
        System.out.println("Luz apagada para " + player.getName().getString());
    }
}
