package com.bogdan.qol.Features.Skills;

import com.bogdan.qol.Config.Configs;
import com.bogdan.qol.Features.Nether.BlockESP;
import com.bogdan.qol.utils.BlockUtils;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;

import java.awt.*;

public class TitaniumESP extends BlockESP {
    public boolean check(int var1, int var2, int var3) {
        IBlockState var4 = BlockUtils.getBlockStateAt(new BlockPos(var1, var2, var3));
        if (var4 == null) {
            return false;
        } else {
            Block var5 = var4.getBlock();
            int var6 = BlockUtils.getMetaFromIBS(var4);
            return var5 == this.block && var6 == 4;
        }
    }

    public Color getColor() {
        return new Color(68, 232, 218, 120);
    }

    public boolean isEnabled() {
        return Configs.TitaniumESP;
    }

    public Block getBlock() {
        return Blocks.stone;
    }
}
