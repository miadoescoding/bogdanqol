package com.bogdan.qol.Objects;

import com.bogdan.qol.bogdanqol;
import com.bogdan.qol.utils.ChatLib;
import com.bogdan.qol.utils.ControlUtils;
import com.bogdan.qol.utils.MinecraftUtils;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.ContainerMerchant;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Inventory {

    public final Container container;

    public Inventory(Container var1) {
        this.container = var1;
    }

    public List getSlots() {
        return this.container.inventorySlots;
    }

    public final ItemStack getItemInSlot(int var1) {
        if (this.getSize() <= var1) {
            return null;
        } else {
            try {
                return this.container.getSlot(var1).getStack();
            } catch (Exception var3) {
                return null;
            }
        }
    }

    public void click(int var1, boolean var2, String var3) {
        this.click(var1, var2, var3, 0);
    }

    public final String getName() {
        if (this.container instanceof ContainerChest) {
            return ((ContainerChest) this.container).getLowerChestInventory().getName();
        } else {
            return this.container instanceof ContainerMerchant ? ((ContainerMerchant) this.container).getMerchantInventory().getName() : "container";
        }
    }

    public int getWindowId() {
        return this.container.windowId;
    }

    public final ArrayList getItemStacks() {
        ArrayList var1 = new ArrayList();

        for (int var2 = 0; var2 < this.getSize(); ++var2) {
            var1.add(this.getItemInSlot(var2));
        }

        return var1;
    }

    public void click(int var1, boolean var2, String var3, int var4) {
        int var5 = this.getWindowId() + var4;
        byte var7 = 0;
        int var6;
        switch (var3) {
            case "MIDDLE":
                var6 = 2;
                if (var2) {
                    var7 = 1;
                } else {
                    var7 = 3;
                }
                break;
            case "RIGHT":
                var6 = 1;
                var7 = 3;
                break;
            case "SWAP":
                var6 = ControlUtils.getHeldItemIndex();
                var7 = 2;
                break;
            default:
                var6 = 0;
                if (var2) {
                    var7 = 1;
                }
        }

        bogdanqol.mc.playerController.windowClick(var5, var1, var6, var7, MinecraftUtils.getPlayer());
        ChatLib.debug(String.format("%d %d %d %d", var5, var1, var6, Integer.valueOf(var7)));
    }

    public int getSize() {
        return this.container.inventoryItemStacks.size();
    }

    public void click(int var1) {
        this.click(var1, false, "LEFT");
    }
}
