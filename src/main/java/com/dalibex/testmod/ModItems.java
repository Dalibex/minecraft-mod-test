package com.dalibex.testmod;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.FuelRegistryEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModItems {

    // Inventory tab creative mode
    public static final RegistryKey<ItemGroup> CUSTOM_ITEM_GROUP_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of(TestMod.MOD_ID, "trinkets"));
    public static final ItemGroup CUSTOM_ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItems.CANDLE))
            .displayName(Text.translatable("itemGroup.testmod.trinkets"))
            .build();

    public static final Item SUSPICIOUS_SUBSTANCE = register("suspicious_substance", Item::new, new Item.Settings());
    public static final Item CANDLE = register("candle", Item::new, new Item.Settings());

    public static void initialize() {
        // Get the event for modifying entries in the ingredients group.
        // And register an event handler that adds our suspicious item to the ingredients group.
        registerCustomItemGroups();
        registerItemsIntoCustomItemGroups();
        registerFuels();
    }

    public static Item register(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {
        // Create the item key.
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(TestMod.MOD_ID, name));

        // Create the item instance.
        Item item = itemFactory.apply(settings.registryKey(itemKey));

        // Register the item.
        Registry.register(Registries.ITEM, itemKey, item);

        return item;
    }

    public static void registerFuels() {
        // 20 ticks = 1 second, coal has 1600 ticks, so 80x20
        FuelRegistryEvents.BUILD.register((builder, context) -> {
            builder.add(ModItems.SUSPICIOUS_SUBSTANCE, 80 * 20);
        });
    }

    public static void registerCustomItemGroups() {
        Registry.register(Registries.ITEM_GROUP, CUSTOM_ITEM_GROUP_KEY, CUSTOM_ITEM_GROUP);
    }

    public static void registerItemsIntoCustomItemGroups() {
        ItemGroupEvents.modifyEntriesEvent(CUSTOM_ITEM_GROUP_KEY).register(itemGroup -> {
            itemGroup.add(ModItems.SUSPICIOUS_SUBSTANCE);
            itemGroup.add(ModItems.CANDLE);
            // ...
        });
    }

    public static Item getItemById(String id) {
        return switch(id) {
            case "candle" -> CANDLE;
            case "suspicious_substance" -> SUSPICIOUS_SUBSTANCE;
            default -> Items.AIR;
        };
    }

}
