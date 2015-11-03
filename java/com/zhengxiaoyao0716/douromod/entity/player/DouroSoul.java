package com.zhengxiaoyao0716.douromod.entity.player;

import com.zhengxiaoyao0716.douromod.item.SoulMagic;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

@SideOnly(Side.CLIENT)
public enum DouroSoul {
    INSTANCE;

    private NBTTagCompound douroPlayerData;
    private int soulLevel;              //武魂等级
    private int soulMp;              //武魂魂力
    private int maxMp;               //level * level
    private int soulExp;                //武魂经验
    private int maxExp;                 //level * level
    private List<SoulMagic> soulMagic;  //魂技列表

    DouroSoul()
    {
        douroPlayerData = Minecraft.getMinecraft().thePlayer.getEntityData();
        soulLevel = 1;
        soulMp = 0; maxMp = 1;
        soulExp = 0; maxExp = 1;
        soulMagic = new ArrayList<SoulMagic>();
    }

    public int getSoulLevel() {
        return soulLevel;
    }
    public int getSoulMp() {
        return soulMp;
    }
    public int getMaxMp() {
        return maxMp;
    }
    public int getSoulExp() {
        return soulExp;
    }
    public int getMaxExp() {
        return maxExp;
    }
    public List<SoulMagic> getSoulMagic() {
        return soulMagic;
    }

    /**
     * 等级提升/跌落.
     * @param value 变化值
     * @param autoFix 是否自动处理越界
     * @return 是否越界
     */
    public boolean changeLevel(int value, boolean autoFix)
    {
        if (soulLevel >= 100 && value > 0) return false;

        soulLevel += value;

        if (soulLevel <= 100)
        {
            if (soulLevel >= 1)
            {
                maxMp = soulLevel * soulLevel;
                maxExp = soulLevel * soulLevel;
                return true;
            }
            else
            {
                if (autoFix)
                {
                    soulLevel = 1;
                    maxMp = 1;
                    maxExp = 1;
                }
                else soulLevel -= value;
            }
        }
        else
        {
            if (autoFix)
            {
                soulLevel = 100;
                maxMp = 10000;
                maxExp = 10000;
            }
            else soulLevel -= value;
        }
        return false;
    }
    /**
     * MP恢复/消耗..
     * @param value 变化值
     * @param autoFix 是否自动处理越界
     * @return 是否越界
     */
    public boolean changeMp(int value, boolean autoFix)
    {
        if (soulMp >= maxMp && value > 0) return false;

        soulMp += value;

        if (soulMp <= maxMp)
        {
            if (soulMp >= 0) return true;
            else
            {
                if (autoFix) soulMp = 0;
                else soulMp -= value;
            }
        }
        else
        {
            if (autoFix) soulMp = maxMp;
            else soulMp -= value;
        }
        return false;
    }
    /**
     * 经验增加/减少.
     * @param value 变化值
     * @param autoFix 是否自动处理越界
     * @return 是否越界
     */
    public boolean changeExp(int value, boolean autoFix)
    {
        soulExp += value;

        if (soulExp <= maxExp)
        {
            if (soulExp >= 0) return true;
            else
            {
                if (autoFix)
                {
                    do {
                        if (changeLevel(-1, true)) soulExp += maxExp + 1;
                        else soulExp = maxExp;
                    } while (soulExp < 0);
                }
                else soulExp -= value;
            }
        }
        else
        {
            if (autoFix)
            {
                do {
                    soulExp -= maxExp + 1;
                    if (!changeLevel(1, true)) soulExp = maxExp;
                } while (soulExp > maxExp);
            }
            else soulExp -= value;
        }
        return false;
    }

    /**
     * 武魂数据的修改指令.
     */
    public static class dataCommand extends CommandBase
    {
        @Override
        public String getName() {
            return "douroSoul";
        }

        @Override
        public String getCommandUsage(ICommandSender sender) {
            return "/douroSoul change <level/mp/exp> <(int)value> <(bool)autoFix>\n/douroSoul print";
        }

        @Override
        public void execute(ICommandSender sender, String[] args) throws CommandException {
            if (args[0].equals("change"))
            {
                if (args[1].equals("level")) DouroSoul.INSTANCE.changeLevel(Integer.valueOf(args[2]), Boolean.valueOf(args[3]));
                else if (args[1].equals("mp")) DouroSoul.INSTANCE.changeMp(Integer.valueOf(args[2]), Boolean.valueOf(args[3]));
                else if (args[1].equals("exp")) DouroSoul.INSTANCE.changeExp(Integer.valueOf(args[2]), Boolean.valueOf(args[3]));
                else return;
            }
            else if (args[0].equals("print")) sender.addChatMessage(new ChatComponentText(
                    String.format(
                            "DouroSoul: level_%d mp_%d exp_%d",
                            DouroSoul.INSTANCE.getSoulLevel(),
                            DouroSoul.INSTANCE.getSoulMp(),
                            DouroSoul.INSTANCE.getSoulExp()
                    )
            ));
            else return;
        }
    }
}