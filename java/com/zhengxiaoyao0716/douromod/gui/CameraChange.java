package com.zhengxiaoyao0716.douromod.gui;

import com.zhengxiaoyao0716.douromod.DouroMod;
import com.zhengxiaoyao0716.douromod.item.SleeveBow;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CameraChange {
    private EntityPlayerSP playerSP;        //fovUpdateEvent会调用N多次，所以hold it
    /**
     * 下面这一段修改自EntityPlayerSP的getFOVMultiplier方法，感谢szszss大神在http://blog.hakugyokurou.net/?p=340这个帖子里的思路提醒.
     * @param event FOV更新事件
     */
    @SubscribeEvent
    public void fovUpdateEvent(FOVUpdateEvent event)
    {
        if (playerSP == null) playerSP = Minecraft.getMinecraft().thePlayer;
        //玩家正在使用道具，且这个道具是sleeveBow，否则跳过
        if (!playerSP.isUsingItem() || playerSP.getItemInUse().getItem() != DouroMod.sleeveBow) return;

        float f = 1.0F;

        if (playerSP.capabilities.isFlying)
        {
            f *= 1.1F;
        }

        IAttributeInstance iattributeinstance = playerSP.getEntityAttribute(SharedMonsterAttributes.movementSpeed);
        f = (float)((double)f * ((iattributeinstance.getAttributeValue() / (double)playerSP.capabilities.getWalkSpeed() + 1.0D) / 2.0D));

        if (playerSP.capabilities.getWalkSpeed() == 0.0F || Float.isNaN(f) || Float.isInfinite(f))
        {
            f = 1.0F;
        }

        int i = playerSP.getItemInUseDuration();
        //f1被我提升了接近1.4倍（/20.0F变成/14.1F），因为袖箭的响应速度是弓的三倍，且f1会被平方一次
        float f1 = (float)i / 14.1F;

        if (f1 > 1.0F)
        {
            f1 = 1.0F;
        }
        else
        {
            f1 *= f1;
        }

        f *= 1.0F - f1 * 0.15F;
        event.newfov = f;
    }
}
