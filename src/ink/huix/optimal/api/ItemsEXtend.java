package ink.huix.optimal.api;

import net.minecraft.CreativeModeTab;
import net.minecraft.Item;

public class ItemsEXtend extends Item {

    public static Item register(String resourceLocation, Item item, CreativeModeTab tab) {
        item.setTextureNameForMITE(resourceLocation);
        item.setUnlocalizedName(resourceLocation);
        item.setCreativeTab(tab);
        return item;
    }

    public static Item register(String resourceLocation, Item item) {
        item.setTextureNameForMITE(resourceLocation);
        item.setUnlocalizedName(resourceLocation);
        return item;
    }
}
