package com.bogdan.qol.Config.ButtonsNew;

import com.bogdan.qol.Config.ConfigGuiNew;
import com.bogdan.qol.Config.Setting.TextSetting;
import com.bogdan.qol.utils.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class ClearTextButton extends Button {

    public int sy;

    public int sx;

    public ClearTextButton(ConfigGuiNew var1, TextSetting var2, int var3, int var4) {
        super(var1, var2, var3, var4);
        this.sx = var3 + var2.width - 5 - TextInput.width - 15;
        this.sy = var4 + 4;
    }

    public boolean mousePressed(Minecraft var1, int var2, int var3) {
        if (this.hover(var2, var3)) {
            this.setting.set("");
            this.gui.update(this.setting, true);
            return true;
        } else {
            return false;
        }
    }

    public boolean hover(int var1, int var2) {
        return var1 >= this.sx && var1 <= this.sx + 10 && var2 >= this.sy && var2 <= this.sy + 10;
    }

    public void draw(Minecraft var1, int var2, int var3) {
        GuiUtils.drawTexture(new ResourceLocation("bogdanqol:trashbin.png"), this.sx, this.sy, 10, 10);
    }
}
