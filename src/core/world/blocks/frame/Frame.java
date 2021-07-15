package core.world.blocks.frame;

import arc.Core;
import arc.scene.ui.layout.Table;
import arc.struct.Seq;
import arc.util.Log;
import mindustry.gen.*;
import mindustry.type.Item;
import mindustry.type.ItemStack;
import mindustry.ui.Styles;
import mindustry.world.Block;

import static core.Variable.uic;

public class Frame extends Block {
    public int craftTableSize = 1;

    public Frame(String name) {
        super(name);
        researchCostMultiplier = 10;
        health = size * 2 * 50;
        configurable = true;
        hasItems = true;
    }

    @SuppressWarnings("unused")
    public class FrameBuild extends Building {
        private boolean invUpdate = true;

        public void buildConfiguration(Table table) {

            table.button(Icon.upOpen, Styles.clearTransi, () -> {
                Seq<ItemStack> inv;
                uic.addChild("frameUI", cont -> {
                    cont.margin(14f);
                    cont.getMinWidth();
                    cont.label(() -> Core.bundle.format("frame.title"));

                    cont.visible = false;
                });

                uic.toggle("frameUI");

            }).size(40f);
        }
        public boolean acceptItem(Building source, Item item) {
            return items.get(item) < itemCapacity;
        }

        public void handleItem(Building source, Item item) {
            invUpdate = true;
            Log.info(source.getDisplayName() + " / " + item.name);
            items.add(item, 1);
        }

        public void handleStack(Item item, int amount, Teamc source) {
            invUpdate = true;
            Log.info(source.team().name + " / " + item.name);
            noSleep();
            items.add(item, amount);
        }
    }
}