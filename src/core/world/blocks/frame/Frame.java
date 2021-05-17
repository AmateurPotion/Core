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

import static core.Vars.uic;
import static mindustry.Vars.content;

public class Frame extends Block {
    public int craftTableSize = 1;

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
                Seq<ItemStack> inv;
                if(!uic.getLayout("frameUI").visible){
                    inv = Seq.with();
                    for(int i = 0; i < items.length(); i++) {
                        if(items.get(i) > 0) {
                            inv.add(new ItemStack(content.item(i), items.get(i)));
                        }
                    }
                    // 생성전 연산
                    uic.addLayout(new Layout("frameUI", cont -> {
                        cont.label(() -> Core.bundle.format("frame.title"));
                        cont.row();
                        // 여따 조합창 구현 예정

                        cont.row();
                        // 여기서부턴 블럭내 자원 표시 및 자원 선택 패널 설정
                        for(int i = 0; i < inv.size; i++) {
                            cont.check(inv.get(i).item.emoji(), i == 0, cb -> {});
                        }
                    }, 0));
                }
                uic.toggleLayout("frameUI");
            }).size(40f);
        }
        public boolean acceptItem(Building source, Item item) {
            return items.get(item) < itemCapacity;
        }
    }
}