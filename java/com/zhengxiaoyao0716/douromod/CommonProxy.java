package com.zhengxiaoyao0716.douromod;

import com.zhengxiaoyao0716.douromod.blocks.IronEssenceOre;
import com.zhengxiaoyao0716.douromod.entities.BlueShineGrass;
import com.zhengxiaoyao0716.douromod.items.IronEssence;
import com.zhengxiaoyao0716.douromod.items.SleeveBow;
import com.zhengxiaoyao0716.douromod.world.DouroWorldGenerator;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.Random;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event) {
        //创造模式暗器类标签
        DouroMod.anqi = new CreativeTabs("anqi") {//TODO 翻译
            @Override
            public Item getTabIconItem() { return DouroMod.sleeveBow; }
        };
        //创造模式魂兽类标签
        DouroMod.soulBeast = new CreativeTabs("soulBeast") {
            @Override
            public Item getTabIconItem() { return DouroMod.sleeveBow; }
        };
        //铁母矿
        DouroMod.ironEssenceOre = new IronEssenceOre();
        GameRegistry.registerBlock(DouroMod.ironEssenceOre, DouroMod.MODID + ":ironEssence_ore");

        //铁母
        DouroMod.ironEssence = new IronEssence();
        GameRegistry.registerItem(DouroMod.ironEssence, DouroMod.MODID + ":ironEssence");
        //袖箭
        DouroMod.sleeveBow = new SleeveBow();
        GameRegistry.registerItem(DouroMod.sleeveBow, DouroMod.MODID + ":sleeveBow");
    }

    public void init(FMLInitializationEvent event) {
        //铁母矿自然生成
        GameRegistry.registerWorldGenerator(new DouroWorldGenerator(), 0);
        //铁母矿熔炼
        GameRegistry.addSmelting(DouroMod.ironEssenceOre, new ItemStack(DouroMod.ironEssence), 0.6f);
        //袖箭的合成
        GameRegistry.addRecipe(new ItemStack(DouroMod.sleeveBow), new Object[]{"##", "#*", '#', Items.arrow, '*', DouroMod.ironEssence});
    }

    public void postInit(FMLPostInitializationEvent event) {
    }
}
