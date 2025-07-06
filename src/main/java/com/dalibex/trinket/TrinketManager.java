package com.dalibex.trinket;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TrinketManager {

    public static void tickPlayer(PlayerEntity player) {
        for (int slot = 0; slot < player.getInventory().size(); slot++) {
            ItemStack stack = player.getInventory().getStack(slot);
            if (!stack.isEmpty()) {
                TrinketType type = getTrinketTypeFromItem(stack.getItem());
                if (type != null) {
                    applyEffect(player, type);
                }
            }
        }
    }

    private static TrinketType getTrinketTypeFromItem(Item item) {
        for (TrinketType type : TrinketType.values()) {
            if (item == ModItems.getItemById(type.getId())) {
                return type;
            }
        }
        return null;
    }

    private static void applyEffect(PlayerEntity player, TrinketType type) {
        switch(type) {
            case CANDLE -> effectLightTierOne(player);
            case SUSPICIOUS_SUBSTANCE -> testingEffect(player);
            // más casos...
        }
    }

    private static void effectLightTierOne(PlayerEntity player) {
        // Código efecto luz...
    }

    private static void testingEffect(PlayerEntity player) {
        // Otro efecto...
    }
}
