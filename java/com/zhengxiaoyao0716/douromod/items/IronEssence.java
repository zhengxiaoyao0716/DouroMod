package com.zhengxiaoyao0716.douromod.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class IronEssence extends Item {
    public IronEssence()
    {
        this.setUnlocalizedName("ironEssence")
                .setCreativeTab(CreativeTabs.tabMaterials)
                .setMaxStackSize(16);
    }
}