package com.zhengxiaoyao0716.douromod;

import com.zhengxiaoyao0716.douromod.control.DouroControl;
import com.zhengxiaoyao0716.douromod.gui.DouroOverlay;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);

        ItemModelMesher itemModelMesher = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
        //铁母矿模型
        itemModelMesher.register(Item.getItemFromBlock(DouroMod.ironEssenceOre), 0, new ModelResourceLocation(DouroMod.MODID + ":ironEssence_ore", "inventory"));
        //铁母模型
        itemModelMesher.register(DouroMod.ironEssence, 0, new ModelResourceLocation(DouroMod.MODID + ":ironEssence", "inventory"));
        //袖箭模型（手动指定3个变种）
        itemModelMesher.register(DouroMod.sleeveBow, 0, new ModelResourceLocation(DouroMod.MODID + ":sleeveBow", "inventory"));
        ModelBakery.addVariantName(DouroMod.sleeveBow, DouroMod.MODID + ":sleeveBow", DouroMod.MODID + ":sleeveBow_pulling_0",
                DouroMod.MODID + ":sleeveBow_pulling_1", DouroMod.MODID + ":sleeveBow_pulling_2");
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);

        //注册控制器，Input事件是在minecraftforge.fml里的
        FMLCommonHandler.instance().bus().register(new DouroControl());
        //注册主屏UI，UI事件在minecraftforge里，是普通线（EVENT_BUS）
        MinecraftForge.EVENT_BUS.register(new DouroOverlay());
    }
}
