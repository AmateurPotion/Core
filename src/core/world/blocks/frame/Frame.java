package core.world.blocks.frame;

import arc.func.Cons;
import arc.scene.ui.layout.Table;
import arc.struct.EnumSet;
import mindustry.gen.*;
import mindustry.type.Item;
import mindustry.ui.Styles;
import mindustry.world.Block;
import mindustry.world.meta.*;

import core.Core;

public class Frame extends Block {
    public int craftTableSize = 1;

    public Frame(String name) {
        super(name);
        researchCostMultiplier = 10;
        health = size * 2 * 50;
        solid = true;
        sync = true;
        update = true;
        drawDisabled = false;
        destructible = true;
        configurable = true;
        hasItems = true;
        group = BlockGroup.logic;
        flags = EnumSet.of(BlockFlag.storage);
    }
    private static int num = 0;

    @SuppressWarnings("unused")
    public class FrameBuild extends Building {
        @Override
        public void buildConfiguration(Table table) {
            Table testTable = new Table().marginRight(13f).marginLeft(13f);

            table.button(Icon.upOpen, Styles.clearTransi, () -> {
                Core.var.baseFrag.open(cont -> {
                    num++;
                    cont.label(() -> num+"");
                });
            }).size(40f);
        }

        @Override
        public boolean acceptItem(Building source, Item item){
            return items.get(item) < block.itemCapacity;
        }
    }
}