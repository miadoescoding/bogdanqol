package com.bogdan.qol.Features.Dungeons;

import net.minecraft.util.BlockPos;

import java.awt.*;

class DragonInfo {

    String textureName;

    BlockPos blockPos;

    String headName;

    Color color;

    String prefix;

    public DragonInfo(BlockPos var1, String var2, Color var3, String var4, String var5) {
        this.blockPos = var1;
        this.prefix = var2;
        this.color = var3;
        this.textureName = var4;
        this.headName = var5;
    }

    public int hashCode() {
        return this.prefix.hashCode();
    }

    public boolean equals(Object var1) {
        if (var1 instanceof DragonInfo) {
            return var1.hashCode() == this.hashCode();
        } else {
            return false;
        }
    }
}
