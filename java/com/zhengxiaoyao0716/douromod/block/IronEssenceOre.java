package com.zhengxiaoyao0716.douromod.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class IronEssenceOre extends Block {
    public IronEssenceOre() {
        super(Material.rock);
        this.setUnlocalizedName("ironEssenceOre")
                .setCreativeTab(CreativeTabs.tabBlock)
                .setHardness(3.0f)
                .setHarvestLevel("pickaxe", 1);
    }
}
