package core.ui;

import arc.func.Boolp;
import arc.scene.Group;
import arc.scene.ui.layout.Table;
import arc.struct.Seq;
import arc.util.Log;
import core.ui.layouts.Layout;
import mindustry.gen.Tex;
import mindustry.ui.fragments.Fragment;

import java.util.ArrayList;
import java.util.Objects;

import static core.Vars.*;

public final class UICollection {
    public final Seq<Layout> layoutList;
    private boolean init = true;
    public boolean updateTick = false;
    private Thread layoutDrawer;
    private ArrayList<String> deleteList = new ArrayList<>();

    public UICollection() {
        layoutList = Seq.with();
    }

    public void build(Group parent) {
        Fragment[] fragments = new Fragment[]{
                new FragmentDialog()
        };
        for(Fragment fragment: fragments) {
            fragment.build(parent);
        }
    }

    public void addLayout(Layout layout) {
        if(layoutList.find(cont -> Objects.equals(cont.id, layout.id)) == null){
            layoutList.add(layout);
        } else if(debugMode) {
            Log.info(layout.id + " is not active because it already exists.");
        }
    }

    /* TODO 레이아웃 활성화/비활성화가 정상적으로 되지않음 ㅜㅜㅜㅜㅜㅜㅜㅜㅜㅜㅜㅜ
    public void setLayout(Layout layout) {
        layoutList.removeAll(remove -> Objects.equals(remove.id, layout.id));
        layoutList.add(layout);
        update = true;
    }
    */

    public Layout getLayout(String id) {
        return layoutList.find(layout -> Objects.equals(layout.id, id)) != null ? layoutList.find(layout -> Objects.equals(layout.id, id)) : new Layout(id, new Table(cont -> cont.name = "nullTable"), 0);
    }

    public void toggleLayout(String layoutId) {
        uic.layoutList.find(layout -> Objects.equals(layout.id, layoutId)).visible = !uic.layoutList.find(layout -> Objects.equals(layout.id, layoutId)).visible;
        uic.layoutList.find(layout -> Objects.equals(layout.id, layoutId)).update = true;
    }

    public void delLayout(String layoutId) {
        deleteList.add(layoutId);
    }

    private class FragmentDialog extends Fragment {

        @Override
        public void build(Group parent) {
            parent.fill(cont -> {
                cont.visible(() -> true);
                cont.table(Tex.buttonTrans, table -> {
                    final int[] ints = {0, 0, 0};
                    cont.visible(() -> {
                        ints[0] = 0;
                        layoutList.filter(layout -> {
                            if (layout.visible) {
                                ints[0]++;
                            }
                            return true;
                        });
                        return ints[0] > 0;
                    });
                    cont.update(() -> {
                        if (init) {
                            table.clear();
                            init = false;
                            layoutDrawer = new Thread(() -> {
                                for(;;) {
                                    if(updateTick || ((Boolp) () -> {
                                        ints[1] = 0;
                                        layoutList.filter(layout -> {
                                            if(layout.update) {
                                                ints[1]++;
                                            }
                                            return true;
                                        });
                                        return ints[1] > 0;
                                    } ).get()) {

                                        if(debugMode) {
                                            table.getChildren().filter(c -> { Log.info(c.name + " / " + ints[2]); return true;});
                                            layoutList.filter(layout -> {
                                                ints[2] = 0;
                                                if (layout.update) {
                                                    ints[2]++;
                                                }
                                                return true;
                                            });
                                        }

                                        layoutList.sort(layout -> layout.priority);
                                        for (Layout layout : layoutList.toArray()) {
                                            layoutList.find(l -> l == layout).update = false;
                                            if (layout.visible) {
                                                if(!table.getChildren().contains(c -> Objects.equals(c.name, layout.id))) {
                                                    layout.content.name = layout.id;
                                                    table.add(layout.content);
                                                }
                                                table.getChildren().find(l -> Objects.equals(l.name, layout.id)).visible = true;
                                                table.getChildren().find(l -> Objects.equals(l.name, layout.id)).visibility = () -> true;
                                            } else if (deleteList.contains(layout.id)) {
                                                table.getChildren().find(l -> Objects.equals(l.name, layout.id)).remove();
                                                layoutList.remove(l -> l == layout);
                                            } else {
                                                table.getChildren().find(l -> Objects.equals(l.name, layout.id)).visible = false;
                                                table.getChildren().find(l -> Objects.equals(l.name, layout.id)).visibility = () -> false;
                                            }
                                        }
                                    }
                                    try {
                                        Thread.sleep(100);
                                    } catch (InterruptedException e) {
                                        Log.errTag("Layout error", e.getLocalizedMessage());
                                    }
                                }
                            });
                            layoutDrawer.setName("Layout Drawer");
                            layoutDrawer.start();
                        }
                    });
                }).margin(14f).minWidth(360f);
            });
        }
    }
}