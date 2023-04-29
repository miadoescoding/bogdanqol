package com.bogdan.qol.Features.Nether;

import com.bogdan.qol.Config.Configs;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

import java.awt.*;

public class PrismarineESP extends BlockESP {
    public Color getColor() {
        return new Color(68, 232, 218, 120);
    }

    public boolean isEnabled() {
        return Configs.PrismarineESP;
    }

    public Block getBlock() {
        return Blocks.prismarine;
    }
}
