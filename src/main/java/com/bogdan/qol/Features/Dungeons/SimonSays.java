package com.bogdan.qol.Features.Dungeons;

import com.bogdan.qol.Config.Configs;
import com.bogdan.qol.Events.BlockChangeEvent;
import com.bogdan.qol.Events.TickEndEvent;
import com.bogdan.qol.utils.*;
import net.minecraft.block.Block;
import net.minecraft.block.BlockButtonStone;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.awt.*;
import java.util.ArrayList;

public class SimonSays {

    private static final BlockPos startButton = new BlockPos(110, 121, 91);
    private static final ArrayList clicks = new ArrayList();
    private static int clickIndex = 0;
    private static boolean canStartClick = false;
    private Thread thread = null;

    @SubscribeEvent
    public void onTick(TickEndEvent event) {
        if (Configs.SimonSaysAutoChangeDirection) {
            if (MathUtils.distanceSquareFromPlayer(109.0, 121.0, 93.0) <= 9.0) {
                if (canStartClick && (this.thread == null || !this.thread.isAlive()) && clickIndex < clicks.size()) {
                    canStartClick = false;
                    this.thread = new Thread(() -> {
                        BlockPos var0 = ((BlockPos) clicks.get(clickIndex)).east();

                        try {
                            ControlUtils.faceSlowly(var0.getX(), (double) var0.getY() + 0.5, (double) var0.getZ() + 0.5);
                        } catch (InterruptedException var2) {
                            var2.printStackTrace();
                        }

                    });
                    this.thread.start();
                }

            }
        }
    }

    @SubscribeEvent
    public void onRenderWorld(RenderWorldLastEvent var1) {
        if (true) {
            if (Configs.SimonSaysSolver) {
                if (clickIndex < clicks.size()) {
                    BlockPos var2 = (BlockPos) clicks.get(clickIndex);
                    GuiUtils.drawBoxAtBlock(var2, new Color(0, 255, 0, 80), 1, 1, 0.002F);
                }
            }
        }
    }

    @SubscribeEvent(
            priority = EventPriority.HIGH
    )
    public void onRightClick(PlayerInteractEvent var1) {
        if (true) {
            if (Configs.SimonSaysSolver) {
                if (Configs.SimonSaysBlockWrongClicks) {
                    if (SkyblockUtils.isInDungeon()) {
                        if (SkyblockUtils.getDungeon().contains("7")) {
                            if (var1.action == Action.RIGHT_CLICK_BLOCK) {
                                if (clickIndex < clicks.size()) {
                                    Block var2 = BlockUtils.getBlockAt(var1.pos);
                                    if (var2 == Blocks.stone_button) {
                                        if (!var1.pos.equals(clicks.get(clickIndex)) && !var1.pos.equals(startButton)) {
                                            ChatLib.chat("Blocked wrong click in simon says!");
                                            var1.setCanceled(true);
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

    @SubscribeEvent
    public void onWorldLoad(WorldEvent.Load var1) {
        clickIndex = 0;
        clicks.clear();
        canStartClick = false;
    }

    @SubscribeEvent
    public void onBlockChange(BlockChangeEvent var1) {
        if (true) {
            if (SkyblockUtils.isInDungeon()) {
                if (Configs.SimonSaysSolver) {
                    if (SkyblockUtils.getDungeon().contains("7")) {
                        BlockPos var2 = var1.position;
                        Block var3 = var1.newBlock.getBlock();
                        Block var4 = var1.oldBlock.getBlock();
                        if (var2.equals(startButton) && (var3 == Blocks.air || var3 == Blocks.stone_button && var1.newBlock.getValue(BlockButtonStone.POWERED))) {
                            clicks.clear();
                            clickIndex = 0;
                        }

                        if (var2.getY() >= 120 && var2.getY() <= 123 && var2.getZ() >= 92 && var2.getZ() <= 95) {
                            if (var2.getX() == 111) {
                                if (var3 == Blocks.sea_lantern && !clicks.contains(var2.west())) {
                                    clicks.add(var2.west());
                                }
                            } else if (var2.getX() == 110) {
                                if (var3 == Blocks.air) {
                                    clickIndex = 0;
                                    canStartClick = false;
                                } else if (var3 == Blocks.stone_button && var4 == Blocks.stone_button && var1.newBlock.getValue(BlockButtonStone.POWERED) && clickIndex < clicks.size() && var2.equals(clicks.get(clickIndex))) {
                                    ++clickIndex;
                                    canStartClick = true;
                                } else if (var3 == Blocks.stone_button && var4 == Blocks.air) {
                                    canStartClick = true;
                                }
                            }

                        }
                    }
                }
            }
        }
    }
}
