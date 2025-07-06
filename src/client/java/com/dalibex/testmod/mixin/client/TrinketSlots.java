package com.dalibex.testmod.mixin.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.entity.player.PlayerInventory;


import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HandledScreen.class)
public abstract class TrinketSlots<T extends ScreenHandler> {

    @Shadow protected int x;
    @Shadow protected int y;
    @Shadow protected T handler;

    @Inject(method = "render", at = @At("TAIL"))
    private void onRender(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return;

        PlayerInventory playerInv = client.player.getInventory();

        for (Slot slot : this.handler.slots) {
            if (slot.inventory == playerInv && slot.getIndex() == 9 || slot.inventory == playerInv && slot.getIndex() == 10) {
                int slotX = this.x + slot.x;
                int slotY = this.y + slot.y;

                context.fill(slotX, slotY, slotX + 16, slotY + 16, 0x10FF0000);
            }
        }
    }
}
