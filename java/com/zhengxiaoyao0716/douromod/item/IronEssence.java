package com.zhengxiaoyao0716.douromod.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class IronEssence extends Item {
    /**
     * 构造器.
     */
    public IronEssence()
    {
        this.setUnlocalizedName("ironEssence")
                .setCreativeTab(CreativeTabs.tabMaterials)
                .setMaxStackSize(16);
    }
}