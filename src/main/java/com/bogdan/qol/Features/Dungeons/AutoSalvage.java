package com.bogdan.qol.Features.Dungeons;

import com.bogdan.qol.Config.Configs;
import com.bogdan.qol.Events.TickEndEvent;
import com.bogdan.qol.Objects.Inventory;
import com.bogdan.qol.utils.ControlUtils;
import com.bogdan.qol.utils.NBTUtils;
import com.bogdan.qol.utils.TimeUtils;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class AutoSalvage {

    private long lastSalvage = 0L;

    @SubscribeEvent
    public void onTick(TickEndEvent event) {
        if (true) {
            if (Configs.AutoClickSalvage) {
                Inventory var2 = ControlUtils.getOpenedInventory();
                if (var2 != null) {
                    String var3 = var2.getName();
                    if (var3.equals("Salvage Item")) {
                        ItemStack var4 = var2.getItemInSlot(22);
                        if (!NBTUtils.isItemStarred(var4)) {
                            ItemStack var5 = var2.getItemInSlot(31);
                            if (var5 != null) {
                                String var6 = var5.getDisplayName();
                                if (var6.equals("§aSalvage Item")) {
                                    if (var5.getItem().getRegistryName().contains("beacon") && TimeUtils.curTime() > this.lastSalvage + 200L) {
                                        this.lastSalvage = TimeUtils.curTime();
                                        var2.click(31);
                                    }

                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
