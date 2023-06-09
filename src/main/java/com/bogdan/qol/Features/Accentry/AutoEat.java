package com.bogdan.qol.Features.Accentry;

import com.bogdan.qol.Config.Configs;
import com.bogdan.qol.Events.TickEndEvent;
import com.bogdan.qol.Objects.Inventory;
import com.bogdan.qol.bogdanqol;
import com.bogdan.qol.utils.ControlUtils;
import com.bogdan.qol.utils.MinecraftUtils;
import com.bogdan.qol.utils.TimeUtils;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class AutoEat {

    public static boolean autoEating = false;

    private long lastEat = 0L;

    private Thread eatingThread = null;

    @SubscribeEvent
    public void onTick(TickEndEvent event) {
        if (Configs.AutoEat) {
            EntityPlayerSP var2 = MinecraftUtils.getPlayer();
            Inventory var3 = ControlUtils.getOpenedInventory();
            if (var2 != null && var3 != null && var3.getSize() >= 9) {
                int var4 = var2.getFoodStats().getFoodLevel();
                long var5 = TimeUtils.curTime();
                autoEating = var4 <= Configs.AutoEatHunger || this.eatingThread != null && this.eatingThread.isAlive();
                if (var4 <= Configs.AutoEatHunger && var5 - this.lastEat > 5000L) {
                    int var7 = -1;

                    int var8;
                    ItemStack var9;
                    for (var8 = 0; var8 < 9; ++var8) {
                        var9 = var3.getItemInSlot(var3.getSize() - 9 + var8);
                        if (var9 != null && var9.getItem() instanceof ItemFood) {
                            var7 = var8;
                            break;
                        }
                    }

                    if (var7 == -1) {
                        return;
                    }

                    this.lastEat = var5;
                    var8 = ControlUtils.getHeldItemIndex();
                    ControlUtils.setHeldItemIndex(var7);
                    var9 = var3.getItemInSlot(var3.getSize() - 9 + var7);
                    if (bogdanqol.mc.playerController.sendUseItem(var2, MinecraftUtils.getWorld(), var9)) {
                        bogdanqol.mc.entityRenderer.itemRenderer.resetEquippedProgress2();
                    }

                    int finalVar = var8;
                    this.eatingThread = new Thread(() -> {
                        try {
                            ControlUtils.holdRightClick();
                            Thread.sleep(200L);
                            ControlUtils.setHeldItemIndex(finalVar);
                            ControlUtils.releaseRightClick();
                        } catch (Exception var2_) {
                            var2_.printStackTrace();
                        }

                        autoEating = false;
                    });
                    this.eatingThread.start();
                }

            }
        }
    }
}
