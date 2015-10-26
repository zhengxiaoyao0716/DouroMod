package com.zhengxiaoyao0716.douromod.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Keyboard;

import java.io.IOException;

public class DouroScreen extends GuiScreen {
    @Override
    public void initGui()
    {
        //TODO
    }

    /**
     * 每帧绘图.
     */
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        drawDefaultBackground();
        super.drawScreen(mouseX,mouseY,partialTicks);
        drawRect((int)(width*0.1), (int)(height*0.1), (int)(width*0.9), (int)(height*0.7), 0x80FFFFFF);
        //TODO
    }

    private static boolean closedWithE;         //是否由E键关闭

    /**
     * 在UI界面的按键事件会被这个方法捕获.
     * @param typedChar typedChar
     * @param keyCode 键盘键值
     * @throws IOException
     */
    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        if (keyCode == Keyboard.KEY_E) {
            closedWithE = true;
            super.keyTyped(typedChar, 1);    //即KEY_ESCAPE，关闭界面
        }
        else super.keyTyped(typedChar, keyCode);
    }

    /**
     * 打开UI，这个方法已经做好了重复按键的处理.
     */
    public static void show()
    {
        //如果是E键关闭，则关闭后的第一次调用实际上还是同一次按键，需要忽略
        if (closedWithE) closedWithE = false;
        else Minecraft.getMinecraft().displayGuiScreen(new DouroScreen());
    }
}
