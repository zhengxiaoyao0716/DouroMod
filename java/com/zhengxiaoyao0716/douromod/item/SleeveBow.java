package com.zhengxiaoyao0716.douromod.item;

import com.zhengxiaoyao0716.douromod.DouroMod;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SleeveBow extends ItemBow {
    /**
     * 构造器.
     */
    public SleeveBow()
    {
        this.setUnlocalizedName("sleeveBow")
                .setCreativeTab(DouroMod.hiddenWeapons)
                .setMaxDamage(2);   //根据斗罗大陆原著，袖箭只有三次发射机会
    }

    /**
     * 当玩家按下右键
     * @param itemStackIn 物品栈
     * @param worldIn 所在世界
     * @param playerIn 玩家
     * @return 物品栈
     */
    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn)
    {
        net.minecraftforge.event.entity.player.ArrowNockEvent event = new net.minecraftforge.event.entity.player.ArrowNockEvent(playerIn, itemStackIn);
        if (net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event)) return event.result;

        //袖箭箭矢其实是就是袖箭的一部分，无需另拥有箭矢或者创造模式
        //if (playerIn.capabilities.isCreativeMode || playerIn.inventory.hasItem(Items.arrow))
        //{
        playerIn.setItemInUse(itemStackIn, this.getMaxItemUseDuration(itemStackIn));
        //}

        return itemStackIn;
    }
    /**
     * 袖箭不同力度的模型.
     * @param itemStack 物品栈
     * @param player 玩家
     * @param useRemaining 右键持续按住的时间
     * @return 模型资源的位置
     */
    @Override
    public ModelResourceLocation getModel(ItemStack itemStack, EntityPlayer player, int useRemaining) {
        ModelResourceLocation modelResourceLocation = new ModelResourceLocation(DouroMod.MODID + ":sleeveBow", "inventory");
        useRemaining = this.getMaxItemUseDuration(itemStack) - useRemaining;
        if(player.getItemInUse() != null) {
            if(useRemaining >= 6) {
                modelResourceLocation = new ModelResourceLocation(DouroMod.MODID + ":sleeveBow_pulling_2", "inventory");
            } else if(useRemaining > 3) {
                modelResourceLocation = new ModelResourceLocation(DouroMod.MODID + ":sleeveBow_pulling_1", "inventory");
            } else if(useRemaining > 0) {
                modelResourceLocation = new ModelResourceLocation(DouroMod.MODID + ":sleeveBow_pulling_0", "inventory");
            }
        }
        return modelResourceLocation;
    }
    /**
     * 当玩家松开右键，发射箭矢
     * @param timeLeft The amount of ticks left before the using would have been complete
     */
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityPlayer playerIn, int timeLeft)
    {
        int j = this.getMaxItemUseDuration(stack) - timeLeft;
        //不用发出ArrowLooseEvent事件广播
        /*
        net.minecraftforge.event.entity.player.ArrowLooseEvent event = new net.minecraftforge.event.entity.player.ArrowLooseEvent(playerIn, stack, j);
        if (net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event)) return;
        j = event.charge;
        */

        //袖箭箭矢就是其本身，无需创造模式或者附魔效果
        //boolean flag = playerIn.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, stack) > 0;

        //袖箭箭矢就是其本身，无需拥有箭矢等条件发射
        //if (flag || playerIn.inventory.hasItem(Items.arrow))
        //{
        float f = (float)j / 20.0F;
        //f设为了原来的三倍（去掉了/3.0F），这样可以更快的到达必杀点
        f = (f * f + f * 2.0F);
        //但不代表可以立刻发射，所以发射起点x3倍以补偿
        if ((double)f < 0.3D)
        {
            return;
        }

        if (f > 1.0F)
        {
            f = 1.0F;
        }

        //袖箭的速度设置为弓箭的两倍（* 2.0F变成* 4.0f)
        EntityArrow entityarrow = new EntityArrow(worldIn, playerIn, f * 4.0F);
        //袖箭威力暂时设置为普通弓箭10倍，因为机括类暗器发射应该更快捷
        entityarrow.setDamage(20.0D);

        if (f == 1.0F)
        {
            entityarrow.setIsCritical(true);
        }
        //附魔效果全部去除
        /*
        int k = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, stack);

        if (k > 0)
        {
            entityarrow.setDamage(entityarrow.getDamage() + (double)k * 0.5D + 0.5D);
        }

        int l = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, stack);

        if (l > 0)
        {
            entityarrow.setKnockbackStrength(l);
        }

        if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, stack) > 0)
        {
            entityarrow.setFire(100);
        }
        */
        //每次攻击消耗一次机会
        stack.damageItem(1, playerIn);
        //无声袖箭
        //worldIn.playSoundAtEntity(playerIn, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

        //if (flag)
        //{
        //袖箭射出的箭矢不应该被作为普通箭矢回收
        entityarrow.canBePickedUp = 2;
        //}
        //else
        //{
        //袖箭不需要消耗玩家的箭矢
        //    playerIn.inventory.consumeInventoryItem(Items.arrow);
        //}

        playerIn.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);

        if (!worldIn.isRemote)
        {
            worldIn.spawnEntityInWorld(entityarrow);
        }
        //}
    }
}