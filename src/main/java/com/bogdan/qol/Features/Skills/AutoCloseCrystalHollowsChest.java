package com.bogdan.qol.Features.Skills;

import com.bogdan.qol.Config.Configs;
import com.bogdan.qol.Objects.Inventory;
import com.bogdan.qol.utils.ControlUtils;
import com.bogdan.qol.utils.MinecraftUtils;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class AutoCloseCrystalHollowsChest {
    @SubscribeEvent
    public void onGuiDraw(GuiScreenEvent.BackgroundDrawnEvent var1) {
        if (Configs.AutoCloseCrystalHollowsChest) {
            Inventory var2 = ControlUtils.getOpenedInventory();
            if (var2 != null && var2.getName().contains("Loot Chest")) {
                MinecraftUtils.getPlayer().closeScreen();
            }
        }
    }
}
