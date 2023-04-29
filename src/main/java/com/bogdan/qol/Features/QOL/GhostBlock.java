package com.bogdan.qol.Features.QOL;

import com.bogdan.qol.Config.Configs;
import com.bogdan.qol.Objects.KeyBind;
import com.bogdan.qol.bogdanqol;
import com.bogdan.qol.utils.BlockUtils;
import com.bogdan.qol.utils.ControlUtils;
import com.bogdan.qol.utils.MinecraftUtils;
import com.bogdan.qol.utils.TimeUtils;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GhostBlock {

    private static final KeyBind ghostBlockKeyBind = new KeyBind("Ghost Block", 0);
    private long lastSwing = 0L;

    @SubscribeEvent
    public void onRenderWorld(RenderWorldLastEvent var1) {
        if (Configs.GhostBlockWithKeyBind && ghostBlockKeyBind.isKeyDown()) {
            BlockPos var2 = MinecraftUtils.getPlayer().rayTrace(bogdanqol.mc.playerController.getBlockReachDistance(), 1.0F).getBlockPos();
            if (var2 != null) {
                Block var3 = MinecraftUtils.getWorld().getBlockState(var2).getBlock();
                if (BlockUtils.canGhostBlock(var3)) {
                    if (this.lastSwing + 250L < TimeUtils.curTime()) {
                        MinecraftUtils.getPlayer().swingItem();
                        this.lastSwing = TimeUtils.curTime();
                    }

                    bogdanqol.mc.theWorld.setBlockToAir(var2);
                }
            }
        }

    }

    @SubscribeEvent(
            priority = EventPriority.LOW
    )
    public void onPlayerInteract(PlayerInteractEvent var1) {
        if (true) {
            if (Configs.GhostBlockWithPickaxe) {
                try {
                    if (var1.action == Action.RIGHT_CLICK_BLOCK) {
                        if (!BlockUtils.canGhostBlock(bogdanqol.mc.theWorld.getBlockState(var1.pos).getBlock())) {
                            return;
                        }

                        ItemStack var2 = ControlUtils.getHeldItemStack();
                        if (var2 == null || !var2.hasDisplayName()) {
                            return;
                        }

                        String var3 = var2.getDisplayName();
                        if (var3.contains("Stonk") || var3.contains("Pickaxe")) {
                            bogdanqol.mc.theWorld.setBlockToAir(var1.pos);
                            if (this.lastSwing + 250L < TimeUtils.curTime()) {
                                MinecraftUtils.getPlayer().swingItem();
                                this.lastSwing = TimeUtils.curTime();
                            }

                            var1.setCanceled(true);
                        }
                    }
                } catch (Exception var4) {
                    var4.printStackTrace();
                }

            }
        }
    }
}
