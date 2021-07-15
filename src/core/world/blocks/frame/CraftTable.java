package core.world.blocks.frame;

import mindustry.type.ItemStack;
import mindustry.world.Block;

public interface CraftTable {
    Block target();
    ItemStack[] recipe();
    int size();
    String getType();
    static ItemStack[][] to2DArray(CraftTable table) {
        int aSize = table.size();
        ItemStack[][] result = new ItemStack[aSize][aSize];
        ItemStack[] from = table.recipe();

        for (int y = 0; y < aSize; y++) {
            System.arraycopy(from, y * aSize, result[y], 0, aSize);
        }

        return result;
    }
}