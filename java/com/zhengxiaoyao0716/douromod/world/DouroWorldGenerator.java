package com.zhengxiaoyao0716.douromod.world;

import com.zhengxiaoyao0716.douromod.DouroMod;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class DouroWorldGenerator implements IWorldGenerator{
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        if (world.provider.getDimensionId() == 0)
        {
            for(int i = 0; i < 30; i++) {
                int posX = chunkX + random.nextInt(16);
                int posY = random.nextInt(64);
                int posZ = chunkZ + random.nextInt(16);
                new WorldGenMinable(DouroMod.ironEssenceOre.getDefaultState(), 13).generate(world, random, new BlockPos(posX, posY, posZ));
            }
        }
    }
}
