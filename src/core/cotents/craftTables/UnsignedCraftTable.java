package core.cotents.craftTables;

import core.world.blocks.frame.CraftTable;
import mindustry.type.ItemStack;
import mindustry.world.Block;

public class UnsignedCraftTable implements CraftTable {
    public final Block target;
    public final ItemStack[] recipe;

    public UnsignedCraftTable(Block target) {
        this.target = target;
        recipe = new ItemStack[target.size * target.size];
    }

    public UnsignedCraftTable setRecipe(int x, int y, ItemStack material) {
        recipe[x + y * target.size] = material;
        return this;
    }

    @Override
    public Block target() {
        return target;
    }

    @Override
    public ItemStack[] recipe() {
        return recipe;
    }

    @Override
    public int size() {
        return target.size;
    }

    @Override
    public String getType() {
        return "Unsigned";
    }
}
