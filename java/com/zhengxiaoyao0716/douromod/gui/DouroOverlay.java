package com.zhengxiaoyao0716.douromod.gui;

import com.zhengxiaoyao0716.douromod.control.DouroControl;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class DouroOverlay {
    /**
     * 主屏UI的重新绘制.
     * @param event UI绘制前触发的事件，在这里拦截以重新绘制.
     */
    @SubscribeEvent
    public void preRenderEvent(RenderGameOverlayEvent.Pre event) {
        switch (event.type)
        {
            case FOOD:
                event.setCanceled(DouroControl.douroSwitch);
                if (!DouroControl.douroSwitch) break;
                //替换成魂力值
                replaceFoodBar();
                break;
            case EXPERIENCE:
                event.setCanceled(DouroControl.douroSwitch);
                if (!DouroControl.douroSwitch) break;
                //替换成魂力经验
                replaceExpBar();
                break;
            case HOTBAR:
                event.setCanceled(DouroControl.douroSwitch);
                if (!DouroControl.douroSwitch) break;
                //替换成魂技
                replaceItemBar();
                break;
        }
    }
    private void replaceFoodBar() {

    }
    private void replaceExpBar() {

    }
    private void replaceItemBar() {

    }
}
