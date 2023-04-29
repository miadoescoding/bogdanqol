package com.bogdan.qol.Features.Miscellaneous;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;

import java.util.HashMap;

public class bogdanKeyBind {

    public static HashMap keyBinds = new HashMap();
    private final String command;
    private final KeyBinding bind;

    public bogdanKeyBind(String var1, int var2) {
        this.command = var1;
        this.bind = new KeyBinding(var1, var2, "Addons - bogdanqol KeyBind");
        ClientRegistry.registerKeyBinding(this.bind);
    }

    public static bogdanKeyBind getKeybind(String var0, int var1) {
        bogdanKeyBind var2 = (bogdanKeyBind) keyBinds.get(var0);
        return var2 == null || var1 != -1 && var2.getBind().getKeyCode() != var1 ? null : var2;
    }

    public KeyBinding getBind() {
        return this.bind;
    }

    public int hashCode() {
        return this.command.hashCode();
    }

    public String getCommand() {
        return this.command;
    }

    public boolean equals(Object var1) {
        return var1 instanceof bogdanKeyBind && ((bogdanKeyBind) var1).command.equals(this.command);
    }
}
