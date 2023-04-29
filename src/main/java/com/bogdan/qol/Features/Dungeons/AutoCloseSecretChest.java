package com.bogdan.qol.Features.Dungeons;

import com.bogdan.qol.Config.Configs;
import com.bogdan.qol.Objects.Inventory;
import com.bogdan.qol.utils.ControlUtils;
import com.bogdan.qol.utils.MinecraftUtils;
import com.bogdan.qol.utils.SkyblockUtils;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class AutoCloseSecretChest {
    @SubscribeEvent
    public void onGuiDraw(GuiScreenEvent.BackgroundDrawnEvent var1) {
        if (true) {
            if (Configs.AutoCloseSecretChest && SkyblockUtils.isInDungeon()) {
                Inventory var2 = ControlUtils.getOpenedInventory();
                if (var2 != null && var2.getName().equals("Chest")) {
                    MinecraftUtils.getPlayer().closeScreen();
                }
            }
        }
    }
}
