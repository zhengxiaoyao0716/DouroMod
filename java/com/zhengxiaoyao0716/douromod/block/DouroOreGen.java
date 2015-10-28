package com.zhengxiaoyao0716.douromod.block;

import com.zhengxiaoyao0716.douromod.DouroMod;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class DouroOreGen {
    private WorldGenMinable ironEssenceOreGenerator, ironOreGenerator;
    public DouroOreGen()
    {
        ironEssenceOreGenerator = new WorldGenMinable(DouroMod.ironEssenceOre.getDefaultState(), 12);    //每个矿脉最多生成4个铁母矿
        ironOreGenerator = new WorldGenMinable(Blocks.iron_ore.getDefaultState(), 36);                  //每个矿脉最多生成12个铁矿
    }
    /**
     * 可开采矿石生成监听器，这里的一个关键是其实际被触发的时间，
     * 不是真的生成矿物时，而是某个区块要生存矿物的时候
     * 由于这个设定，所以pos是区块原点，并且生成次数也要自己把握.
     * @param event 可开采矿石生成事件
     */
    @SubscribeEvent
    public void minableOreGenEvent(OreGenEvent.GenerateMinable event)
    {
        switch (event.type)
        {
            case IRON:          //每个区块生成铁时，增加含有铁母的巨型铁矿
                for(int i = 0; i < 3; i++)          //每个区块生成3条这样的矿脉
                {
                    event.setResult(Event.Result.DENY);
                    BlockPos genPos = new BlockPos(
                            event.pos.getX() + event.rand.nextInt(16),
                            event.rand.nextInt(64),
                            event.pos.getZ() + event.rand.nextInt(16));
                    ironEssenceOreGenerator.generate(event.world, event.rand, genPos);
                    ironOreGenerator.generate(event.world, event.rand, genPos);
                }break;
        }
    }
}
