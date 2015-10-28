package com.zhengxiaoyao0716.douromod;

import com.zhengxiaoyao0716.douromod.block.IronEssenceOre;
import com.zhengxiaoyao0716.douromod.item.IronEssence;
import com.zhengxiaoyao0716.douromod.item.SleeveBow;
import com.zhengxiaoyao0716.douromod.world.DouroWorldGenerator;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event) {
        //创造模式暗器标签
        DouroMod.hiddenWeapons = new CreativeTabs("hiddenWeapons") { @Override public Item getTabIconItem() { return DouroMod.sleeveBow; }};
        //铁母矿
        DouroMod.ironEssenceOre = new IronEssenceOre();
        GameRegistry.registerBlock(DouroMod.ironEssenceOre, DouroMod.MODID + ":ironEssenceOre");
        GameRegistry.registerWorldGenerator(new DouroWorldGenerator(), 0);
        //铁母
        DouroMod.ironEssence = new IronEssence();
        GameRegistry.registerItem(DouroMod.ironEssence, DouroMod.MODID + ":ironEssence");
        //袖箭
        DouroMod.sleeveBow = new SleeveBow();
        GameRegistry.registerItem(DouroMod.sleeveBow, DouroMod.MODID + ":sleeveBow");
    }

    public void init(FMLInitializationEvent event) {
        //铁母矿熔炼
        GameRegistry.addSmelting(DouroMod.ironEssenceOre, new ItemStack(DouroMod.ironEssence), 0.6f);
        //袖箭的合成
        GameRegistry.addRecipe(new ItemStack(DouroMod.sleeveBow), new Object[]{"a ", " b", 'a', DouroMod.ironEssence, 'b', Items.bow});
    }

    public void postInit(FMLPostInitializationEvent event) {
        //注册生成矿石监听，Gen事件是在minecraftforge里的，且显然是ORE_GEN_BUS线
        //MinecraftForge.ORE_GEN_BUS.register(new DouroOreGen());           //不用了，换成生成巨型铁矿
    }
}
