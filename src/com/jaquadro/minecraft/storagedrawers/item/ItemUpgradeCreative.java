package com.jaquadro.minecraft.storagedrawers.item;

import com.jaquadro.minecraft.storagedrawers.core.ModCreativeTabs;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemUpgradeCreative extends Item
{
    public String[] getResourceVariants () {
        String[] variants = new String[EnumUpgradeCreative.values().length];
        int index = 0;

        for (EnumUpgradeCreative upgrade : EnumUpgradeCreative.values())
            variants[index++] = '_' + upgrade.getName();

        return variants;
    }

    public ItemUpgradeCreative (String name) {
        setUnlocalizedName(name);
        setHasSubtypes(true);
        setCreativeTab(ModCreativeTabs.tabStorageDrawers);
        setMaxDamage(0);
    }

    @Override
    public String getUnlocalizedName (ItemStack itemStack) {
        return super.getUnlocalizedName() + "." + EnumUpgradeCreative.byMetadata(itemStack.getMetadata()).getUnlocalizedName();
    }

    @Override
    public int getMetadata (int damage) {
        return damage;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation (ItemStack itemStack, EntityPlayer player, List<String> list, boolean par4) {
        String name = getUnlocalizedName(itemStack);
        list.add(StatCollector.translateToLocalFormatted(name + ".description"));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems (Item item, CreativeTabs creativeTabs, List<ItemStack> list) {
        for (EnumUpgradeCreative upgrade : EnumUpgradeCreative.values())
            list.add(new ItemStack(item, 1, upgrade.getMetadata()));
    }
}
