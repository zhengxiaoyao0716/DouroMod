package com.zhengxiaoyao0716.douromod.world;

import com.zhengxiaoyao0716.douromod.DouroMod;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class DouroWorldGenerator implements IWorldGenerator{
    //可开采矿物生成器，一个实例就够了。网上教程也是醉了，每次generate()都创建新的实例，有必要么.
    private WorldGenMinable ironEssenceOreGenerator, ironOreGenerator;
    public DouroWorldGenerator()
    {
        ironEssenceOreGenerator = new WorldGenMinable(DouroMod.ironEssenceOre.getDefaultState(), 12);    //每条矿脉最多生成12个铁母矿
        ironOreGenerator = new WorldGenMinable(Blocks.iron_ore.getDefaultState(), 16);  //每条矿脉最多生成16个铁矿，但实际上会被铁母矿占据大半
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        int posX = 16 * chunkX, posZ = 16 * chunkZ;
        switch (world.provider.getDimensionId())
        {
            case -1:
                //地狱
                generateHellWorld(random, posX, posZ, world);
                break;
            case 0:
                //主世界
                generateMainWorld(random, posX, posZ, world);
                break;
            case 1:
                //末地
                generateEndWorld(random, posX, posZ, world);
                break;
        }
    }
    private void generateHellWorld(Random random, int posX, int posZ, World world) {

    }
    private void generateMainWorld(Random random, int posX, int posZ, World world) {
        //每个区块仅生成1条含有铁母的巨型矿脉
        BlockPos genPos = new BlockPos(
                posX + random.nextInt(16),
                random.nextInt(64),
                posZ + random.nextInt(16));
        ironEssenceOreGenerator.generate(world, random, genPos);
        ironOreGenerator.generate(world, random, genPos);
    }
    private void generateEndWorld(Random random, int posX, int posZ, World world) {

    }
}
