package com.bogdan.qol.Features.Dungeons;

import com.bogdan.qol.Config.Configs;
import com.bogdan.qol.Events.GuiContainerEvent;
import com.bogdan.qol.Events.TickEndEvent;
import com.bogdan.qol.Features.Dungeons.Map.Dungeon;
import com.bogdan.qol.Objects.Inventory;
import com.bogdan.qol.utils.*;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.awt.*;
import java.util.List;

public class AutoLeap {

    private final String[] names = new String[10];

    private long lastClick = 0L;

    @SubscribeEvent
    public void onTick(TickEndEvent event) {
        if (true) {
            if (SkyblockUtils.isInDungeon()) {
                Inventory var2 = ControlUtils.getOpenedInventory();
                if (var2 != null && var2.getName().contains("Spirit Leap")) {
                    int var3;
                    ItemStack var4;
                    String var5;
                    if (Configs.AutoTpToMage && TimeUtils.curTime() - this.lastClick > 1000L && Dungeon.bloodOpen < Dungeon.runStarted) {
                        for (var3 = 9; var3 < 18; ++var3) {
                            var4 = ControlUtils.getItemStackInSlot(var3, false);
                            if (var4 != null && Item.getIdFromItem(var4.getItem()) != 160) {
                                var5 = ChatLib.removeFormatting(var4.getDisplayName());
                                if (Dungeon.isPlayerMage(var5)) {
                                    var2.click(var3);
                                    this.lastClick = TimeUtils.curTime();
                                }
                            }
                        }
                    }

                    if (Configs.SpiritLeapName) {
                        for (var3 = 9; var3 < 18; ++var3) {
                            var4 = ControlUtils.getItemStackInSlot(var3, false);
                            if (var4 != null && Item.getIdFromItem(var4.getItem()) != 160) {
                                var5 = var4.getDisplayName();
                                String var6 = ChatLib.removeFormatting(var5);
                                if (var6.length() > 5) {
                                    var6 = var6.substring(0, 5);
                                }

                                this.names[var3 - 9] = var5.substring(0, var5.indexOf(var6) + var6.length());
                            } else {
                                this.names[var3 - 9] = "";
                            }
                        }
                    }

                }
            }
        }
    }

    @SubscribeEvent
    public void onGuiRender(GuiScreenEvent.BackgroundDrawnEvent var1) {
        Inventory var2 = ControlUtils.getOpenedInventory();
        if (SkyblockUtils.isInDungeon() && var2 != null && Configs.SpiritLeapName) {
            String var3 = var2.getName();
            if (var3.contains("Spirit Leap")) {
                List var4 = var2.getSlots();

                for (int var5 = 0; var5 < 9; ++var5) {
                    if (this.names[var5] != null && !this.names[var5].equals("")) {
                        int var6 = var5 % 2 == 0 ? var5 : var5 + 18;
                        Slot var7 = (Slot) var4.get(var6);
                        GuiUtils.drawStringOnSlot(this.names[var5], var4.size(), var7.xDisplayPosition, var7.yDisplayPosition, new Color(63, 206, 63, 255));
                    }
                }

            }
        }
    }

    @SubscribeEvent
    public void onSlotDraw(GuiContainerEvent.DrawSlotEvent.Pre var1) {
        Inventory var2 = ControlUtils.getOpenedInventory();
        if (SkyblockUtils.isInDungeon() && var2 != null && Configs.SpiritLeapName) {
            String var3 = var2.getName();
            if (var3.contains("Spirit Leap")) {
                try {
                    int var4 = var1.slot.slotNumber;
                    if (var4 >= var2.getSize() - 36) {
                        return;
                    }

                    if (var4 < 9 || var4 >= 18 || this.names[var4 - 9] == null || this.names[var4 - 9].equals("")) {
                        var1.setCanceled(true);
                    }
                } catch (Exception var5) {
                    var5.printStackTrace();
                }

            }
        }
    }
}
