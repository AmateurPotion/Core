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

import static core.Vars.uic;

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

                /*
                if(!uic.getLayout("frameUI").visible){
                    inv = Seq.with();

                    // 생성전 연산
                    uic.addLayout(new Layout("frameUI", new Table (cont -> {
                        cont.label(() -> Core.bundle.format("frame.title") + "Tier :" + craftTableSize);
                        cont.row();
                        // 여따 조합창 구현 예정

                        cont.row();
                        // 여기서부턴 블럭내 자원 표시 및 자원 선택 패널 설정
                        table.update(() -> {
                            if(updateTest){
                                for(int i = 0; i < items.length(); i++) {
                                    if(items.get(i) > 0) {
                                        inv.add(new ItemStack(content.item(i), items.get(i)));
                                    }
                                }

                                for(int i = 0; i < inv.size; i++) {
                                    int finalI = i;
                                    String emoji = inv.get(finalI).item.emoji();
                                    if (cont.getChildren().find(element -> Objects.equals(element.name, inv.get(finalI).item.emoji())) != null) {
                                        cont.getChildren().find(element -> Objects.equals(element.name, inv.get(finalI).item.emoji())).remove();
                                        Log.info("a");
                                    }
                                    cont.button(b -> {b.label(() -> emoji); b.name = emoji; },() -> {});
                                }
                                updateTest = false;
                            }
                        });
                    }), 0));
                }
                uic.toggleLayout("frameUI");
                */
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