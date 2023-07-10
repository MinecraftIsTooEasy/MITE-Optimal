package ink.huix.optimal.api;

import net.minecraft.*;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class BlocksExtend extends Block {

    public static void registerAnvil(BlockAnvil block, int itemID, String resourceLocation){
        block.setUnlocalizedName(resourceLocation);
        block.setTextureNameForMITE(resourceLocation);
        Item item = new ItemAnvil(block).setUnlocalizedName(resourceLocation);
        Item.itemsList[itemID] = item;
        item.setMaxStackSize(block.getItemStackLimit());
    }

    public static void registerBlock(Block block, int itemID, String resourceLocation){
        block.setUnlocalizedName(resourceLocation);
        block.setTextureNameForMITE(resourceLocation);
        Item item = new ItemBlock(block).setUnlocalizedName(resourceLocation);
        item.setMaxStackSize(block.getItemStackLimit());
        Item.itemsList[itemID] = item;
    }

    static {
        try {
            Field field = Block.class.getDeclaredField("is_normal_cube_lookup");
            field.setAccessible(true);
            Field modifiers = field.getClass().getDeclaredField("modifiers");
            modifiers.setAccessible(true);
            modifiers.setInt(field, field.getModifiers() & ~Modifier.FINAL);
            field.set(null,new boolean[4096]);
            boolean[] is_normal_block = (boolean[]) field.get(null);
            for (Block block : Block.blocksList) {
                if (block !=null) {
                    is_normal_block[block.blockID] = block.is_normal_cube;
                }
            }
            modifiers.setInt(field, field.getModifiers() & ~Modifier.FINAL);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }

    }
    protected BlocksExtend(int par1, Material par2Material, BlockConstants constants) {
        super(par1, par2Material, constants);
    }
}
