package com.zhengxiaoyao0716.douromod.entity.player;

import com.zhengxiaoyao0716.douromod.item.SoulMagic;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

public class DouroSoul {
    private String playerName;      //玩家标识
    private int soulLevel;          //武魂等级
    private int soulExp;            //武魂经验
    private int soulPoint;          //武魂魂力
    private List<SoulMagic> soulMagic;   //魂技列表

    @SideOnly(Side.CLIENT)
    private DouroSoul()
    {
        playerName = Minecraft.getMinecraft().thePlayer.getName();
        soulLevel = 1;
        soulExp = 0;
        soulPoint = 0;
        soulMagic = new ArrayList<SoulMagic>();
    }
}