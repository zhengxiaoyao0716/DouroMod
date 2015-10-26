package com.zhengxiaoyao0716.douromod.control;

import com.zhengxiaoyao0716.douromod.gui.DouroScreen;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

public class DouroControl {
    private KeyBinding douroSwitchKey;      //斗罗模式切换键

    /**
     * 构造器，里面完成了需要绑定事件的按键的创建及注册.
     */
    public DouroControl()
    {
        douroSwitchKey = new KeyBinding("douromod.key.douroSwitchKey", Keyboard.KEY_LMENU, "douromod.keytitle");
        //注册按键捆绑事件
        ClientRegistry.registerKeyBinding(douroSwitchKey);
    }

    /**
     * 是否切换到了斗罗模式.
     */
    public static boolean douroSwitch;
    /**
     * 键盘监听器，当接受到按键按下时触发.
     */
    @SubscribeEvent
    public void keyInputEvent(InputEvent.KeyInputEvent event) {
        //切换斗罗/普通模式
        if (douroSwitchKey.isPressed()) douroSwitch = !douroSwitch;
        if (douroSwitch)
        {
            switch (Keyboard.getEventKey())
            {
                case Keyboard.KEY_E:
                    //打开斗罗界面
                    DouroScreen.show();
                    break;
            }
        }
    }
}
