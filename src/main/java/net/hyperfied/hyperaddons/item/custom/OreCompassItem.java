package net.hyperfied.hyperaddons.item.custom;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public class OreCompassItem extends Item {
    private static int searchRadius = 5;
    private static int glowDuration = 20;
    private Block searchBlock;
    public OreCompassItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {

        PlayerEntity user = context.getPlayer();
        ItemStack currentItem = user.getStackInHand(context.getHand());

        if(context.getWorld().isClient()){
            if (user.isSneaking()) {
                Block blockUsedOn = context.getWorld().getBlockState(context.getBlockPos()).getBlock();
                NbtCompound nbtData = new NbtCompound();
                if (blockUsedOn == Blocks.GRINDSTONE) {
                    searchBlock = null;
                    currentItem.setNbt(nbtData);
                } else {
                    searchBlock = blockUsedOn;
                    nbtData.putString("hyperaddons.searchblock", blockUsedOn.asItem().getName().getString());
                    currentItem.setNbt(nbtData);
                }
            }
        }

        return super.useOnBlock(context);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (world.isClient()){
            BlockPos locationUsed = user.getBlockPos();

            for (int x = locationUsed.getX() - searchRadius; x <= locationUsed.getX() + searchRadius; x++){
                for (int z = locationUsed.getZ() - searchRadius; z <= locationUsed.getZ() + searchRadius; z++){
                    for (int y = locationUsed.getY() - searchRadius; y <= locationUsed.getY() + searchRadius; y++){
                        Block currentBlock = world.getBlockState(new BlockPos(x,y,z)).getBlock();
                        if (currentBlock == searchBlock){
                            user.sendMessage(Text.literal("Found " + currentBlock.asItem().getName().getString() + " at " + x + ", " + y + ", " + z));
                        }
                    }
                }
            }

        }
        return super.use(world, user, hand);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return stack.hasNbt();
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (stack.hasNbt()){
            String currentBlock = stack.getNbt().getString("hyperaddons.searchblock");
            tooltip.add(Text.literal(currentBlock));
        }
    }
}
