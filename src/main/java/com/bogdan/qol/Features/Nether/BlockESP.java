package com.bogdan.qol.Features.Nether;

import com.bogdan.qol.Events.BlockChangeEvent;
import com.bogdan.qol.Events.TickEndEvent;
import com.bogdan.qol.utils.*;
import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.awt.*;
import java.util.HashSet;
import java.util.Iterator;

public abstract class BlockESP {

    private static long lastCheck = 0L;
    public final Block block = this.getBlock();
    private final Color color = this.getColor();
    private final HashSet blocks = new HashSet();
    private Thread scanThread = null;

    public abstract Color getColor();

    @SubscribeEvent
    public void onBlockChange(BlockChangeEvent var1) {
        if (var1.oldBlock.getBlock() == this.block && var1.newBlock.getBlock() != this.block) {
            this.blocks.remove(var1.position);
        }

    }

    public boolean check(int var1, int var2, int var3) {
        return BlockUtils.getBlockAt(var1, var2, var3) == this.block;
    }

    public void deal(int var1, int var2, int var3) {
        if (this.check(var1, var2, var3)) {
            synchronized (this.blocks) {
                this.blocks.add(new BlockPos(var1, var2, var3));
            }
        }

    }

    @SubscribeEvent
    public void onTickCheck(TickEndEvent var1) {
        if (true) {
            if (MinecraftUtils.getWorld() != null) {
                if (MinecraftUtils.getPlayer() != null) {
                    if (TimeUtils.curTime() - lastCheck > 1000L) {
                        lastCheck = TimeUtils.curTime();
                        synchronized (this.blocks) {
                            this.blocks.removeIf((var1_) -> {
                                BlockPos var1x = (BlockPos) var1_;
                                return MinecraftUtils.getPlayer().getDistanceSq(var1x) < 10000.0 && !this.check(var1x.getX(), var1x.getY(), var1x.getZ());
                            });
                        }
                    }

                }
            }
        }
    }

    public abstract Block getBlock();

    @SubscribeEvent
    public void onTick(TickEndEvent event) {
        if (true) {
            if (this.isEnabled()) {
                if (this.scanThread == null || !this.scanThread.isAlive()) {
                    this.scanThread = new Thread(() -> {
                        try {
                            int var1 = MathUtils.floor(MathUtils.getX(MinecraftUtils.getPlayer()));
                            int var2 = MathUtils.floor(MathUtils.getZ(MinecraftUtils.getPlayer()));

                            for (int var3 = 0; var3 < 400; ++var3) {
                                for (int var4 = 0; var4 < 4; ++var4) {
                                    for (int var5 = 40; var5 <= 240; ++var5) {
                                        if (!this.isEnabled()) {
                                            return;
                                        }

                                        int var6 = MathUtils.floor(MathUtils.getX(MinecraftUtils.getPlayer()));
                                        int var7 = MathUtils.floor(MathUtils.getZ(MinecraftUtils.getPlayer()));
                                        if (Math.abs(var6 - var1) > 10 || Math.abs(var7 - var2) > 10) {
                                            return;
                                        }

                                        int var8;
                                        for (var8 = var1 - var3; var8 <= var1 + var3; ++var8) {
                                            this.deal(var8, var5, var2 - var3);
                                            this.deal(var8, var5, var2 + var3);
                                        }

                                        for (var8 = var2 - var3; var8 <= var2 + var3; ++var8) {
                                            this.deal(var1 - var3, var5, var8);
                                            this.deal(var1 + var3, var5, var8);
                                        }
                                    }
                                }
                            }
                        } catch (Exception var9) {
                            var9.printStackTrace();
                        }

                    });
                    this.scanThread.start();
                }

            }
        }
    }

    @SubscribeEvent
    public void onRenderWorld(RenderWorldLastEvent var1) {
        if (true) {
            if (this.isEnabled()) {
                synchronized (this.blocks) {
                    Iterator var3 = this.blocks.iterator();

                    while (var3.hasNext()) {
                        BlockPos var4 = (BlockPos) var3.next();
                        GuiUtils.enableESP();
                        GuiUtils.drawBoxAtBlock(var4, this.color, 1, 1, 0.002F);
                        GuiUtils.disableESP();
                    }

                }
            }
        }
    }

    public abstract boolean isEnabled();

    @SubscribeEvent
    public void onReset(WorldEvent.Load var1) {
        synchronized (this.blocks) {
            this.blocks.clear();
        }
    }
}
