package core.world.blocks.frame;

import arc.Core;
import arc.scene.ui.layout.Table;
import arc.struct.Seq;
import arc.util.Log;
import core.ui.layouts.Layout;
import mindustry.gen.*;
import mindustry.type.Item;
import mindustry.type.ItemStack;
import mindustry.ui.Styles;
import mindustry.world.Block;

import java.util.Objects;

import static core.Vars.uic;

public class Frame extends Block {
    public int craftTableSize = 1;
    public Seq<ItemStack> storage = Seq.with();

    public Frame(String name) {
        super(name);
        researchCostMultiplier = 10;
        health = size * 2 * 50;
        destructible = true;
        configurable = true;
        hasItems = true;
    }

    @SuppressWarnings("unused")
    public class FrameBuild extends Building {
        @Override
        public void buildConfiguration(Table table) {
            table.button(Icon.upOpen, Styles.clearTransi, () -> {
                for(int i = 0; i < storage.size; i++) {
                    Log.info(storage.get(i).item.localizedName + " / " + storage.get(i).amount + " / " + storage.get(i).item.emoji());
                }
                uic.setLayout(new Layout("frameUI", cont -> {
                    cont.label(() -> Core.bundle.format("frame.title"));
                }, 0));
                uic.toggleLayout("frameUI");
            }).size(40f);
        }

        @Override
        public boolean acceptItem(Building source, Item item) {
            return items.get(item) < itemCapacity;
        }

        @Override
        public void handleItem(Building source, Item item) {

            Log.info(source.getDisplayName() + " / " + item.name);
            items.add(item, 1);
        }

        @Override
        public void handleStack(Item item, int amount, Teamc source) {
            Log.info(item.localizedName + " / " + amount + " / " + source.team().name);
            noSleep();
            items.add(item, amount);
            if(storage.find(items -> Objects.equals(items.item.name, item.name)) != null) {
                storage.find(items -> Objects.equals(items.item.name, item.name)).amount += amount;
            } else {
                storage.add(new ItemStack(item, amount));
            }
        }
    }
}