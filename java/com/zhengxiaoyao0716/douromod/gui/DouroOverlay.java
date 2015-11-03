package com.zhengxiaoyao0716.douromod.gui;

import com.zhengxiaoyao0716.douromod.control.DouroControl;
import com.zhengxiaoyao0716.douromod.entity.player.DouroSoul;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.GuiIngameForge;
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
                break;
            case EXPERIENCE:
                event.setCanceled(DouroControl.douroSwitch);
                break;
            case HOTBAR:
                event.setCanceled(DouroControl.douroSwitch);
                break;
        }
        if (DouroControl.douroSwitch)
        {
            //替换成魂力值
            drawPointBar(event);
            //替换成魂力经验
            drawExpBar(event);
            //替换成魂技
            drawMagicBar(event);
        }
    }
    private void drawPointBar(RenderGameOverlayEvent.Pre event) {
        int percent = 100 * DouroSoul.INSTANCE.getSoulMp() / DouroSoul.INSTANCE.getMaxMp();
        Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(
                String.format("MP:%3d%%", percent),
                event.resolution.getScaledWidth() / 2 + 70,
                event.resolution.getScaledHeight() - GuiIngameForge.right_height,
                0xFFFFFF
        );
    }
    private void drawExpBar(RenderGameOverlayEvent.Pre event) {
        int percent = 100 * DouroSoul.INSTANCE.getSoulExp() / DouroSoul.INSTANCE.getMaxExp();
        Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(
                String.format("Exp:%3d%%", percent),
                event.resolution.getScaledWidth() / 2 - 91,
                event.resolution.getScaledHeight() - 29,
                0xFFFFFF
        );
    }
    private void drawMagicBar(RenderGameOverlayEvent.Pre event) {
        DouroSoul.INSTANCE.getSoulMagic();
    }
}
