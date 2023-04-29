package com.bogdan.qol.Features.Miscellaneous;

import com.bogdan.qol.Config.Configs;
import com.bogdan.qol.Events.ItemDropEvent;
import com.bogdan.qol.Events.WindowClickEvent;
import com.bogdan.qol.Features.Remote.LowestBin;
import com.bogdan.qol.utils.ChatLib;
import com.bogdan.qol.utils.ControlUtils;
import com.bogdan.qol.utils.MinecraftUtils;
import com.bogdan.qol.utils.SkyblockUtils;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ProtectItems {

    private void protect(Event var1, String var2) {
        if (true) {
            if (Configs.ProtectItems) {
                var1.setCanceled(true);
                ChatLib.chat("Stopped from " + var2 + " that item.");
                MinecraftUtils.getPlayer().playSound("note.bass", 1000.0F, 0.5F);
            }
        }
    }

    @SubscribeEvent
    public void onWindowClick(WindowClickEvent var1) {
        ItemStack var2 = null;
        if (var1.slotId == -999 && var1.mode == 0) {
            var2 = MinecraftUtils.getPlayer().inventory.getItemStack();
        }

        if (var1.slotId != -999 && var1.mode == 4 && (var1.mouseButtonClicked == 0 || var1.mouseButtonClicked == 1)) {
            var2 = ControlUtils.getOpenedInventory().getItemInSlot(var1.slotId);
        }

        if (var2 != null && this.shouldProtect(var2)) {
            this.protect(var1, "dropping");
        }

    }

    @SubscribeEvent
    public void onWindowClickSelling(WindowClickEvent var1) {
        ItemStack var2 = null;
        if (ControlUtils.getOpenedInventoryName().equals("Trades") && var1.slotId != 49 && var1.slotId != -999) {
            var2 = ControlUtils.getOpenedInventory().getItemInSlot(var1.slotId);
        }

        if (var2 != null && this.shouldProtect(var2)) {
            this.protect(var1, "selling");
        }

    }

    @SubscribeEvent
    public void onItemDrop(ItemDropEvent var1) {
        if (true) {
            if (Configs.ProtectItems) {
                if (!SkyblockUtils.isInDungeon()) {
                    if (this.shouldProtect(var1.itemStack)) {
                        this.protect(var1, "dropping");
                    }

                }
            }
        }
    }

    private boolean shouldProtect(ItemStack var1) {
        try {
            return LowestBin.getItemValue(var1) > (double) (Configs.ProtectItemValue * 1000);
        } catch (Exception var3) {
            return false;
        }
    }
}
