package core.world.blocks.frame;

import mindustry.type.ItemStack;
import mindustry.world.Block;

import static core.cotents.ItemList.blank;
import static core.Vars.*;
import static mindustry.content.Blocks.copperWall;

public class CraftTable {
    public Block result;
    public final boolean error;
    public final int size;
    public final String name;
    private final ItemStack[][] table;

    public CraftTable(String name) {
        this(name, 1);
    }

    public CraftTable(String name, int size) {
        this.name = name;
        error = size <= 0 || size >= 17;
        this.size = size;
        table = dummyTableBuilder(size);
    }

    public CraftTable(String name, ItemStack[][] combination){
        this(name, combination, copperWall);
    }

    public CraftTable(String name, ItemStack[][] combination, Block result) {
        this.name = name;
        int maxSize = combination.length;
        for (ItemStack[] items : combination) {
            if (items.length > maxSize) maxSize = items.length;
        }
        error = maxSize <= 0 || maxSize >= 17;
        table = combination;
        size = maxSize;
    }

    private ItemStack[][] dummyTableBuilder(int size) {
        ItemStack[][] temp;
        temp = new ItemStack[size][size];
        for(ItemStack[] line : temp) {
            for(ItemStack item : line) {
                item = new ItemStack(blank, craftDefaultStack);
            }
        }
        return temp;
    }

    public CraftTable setMaterial(int x, int y, ItemStack item) {
        table[x][y] = item;
        return this;
    }
}
