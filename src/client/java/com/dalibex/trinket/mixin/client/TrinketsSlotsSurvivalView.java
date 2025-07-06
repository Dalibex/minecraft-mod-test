package com.dalibex.trinket.mixin.client;

import com.dalibex.trinket.mixin.access.HandledScreenAccessor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.entity.player.PlayerInventory;

import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InventoryScreen.class)
public abstract class TrinketsSlotsSurvivalView extends HandledScreen {

    public TrinketsSlotsSurvivalView(ScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Inject(method = "render", at = @At("TAIL"))
    private void onRender(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return;

        PlayerInventory playerInv = client.player.getInventory();

        HandledScreenAccessor accessor = (HandledScreenAccessor) (Object) this;
        int screenX = accessor.getX();
        int screenY = accessor.getY();

        for (Slot slot : this.handler.slots) {
            if (slot.inventory == playerInv && slot.getIndex() == 9 || slot.inventory == playerInv && slot.getIndex() == 10) {
                int slotX = screenX + slot.x;
                int slotY = screenY + slot.y;

                context.fill(slotX, slotY, slotX + 16, slotY + 16, 0x10FF0000);
            }
        }
    }
}
