package core.ui;

import arc.scene.Group;
import arc.struct.Seq;
import arc.util.Log;
import core.ui.layouts.Layout;
import mindustry.gen.Tex;
import mindustry.ui.fragments.Fragment;

import java.util.Objects;

import static core.Vars.*;

public final class UICollection {
    private final Seq<Layout> layoutList;
    public boolean update = true;
    private boolean threadRunning = false;
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
            update = true;
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
        return layoutList.find(layout -> Objects.equals(layout.id, id)) != null ? layoutList.find(layout -> Objects.equals(layout.id, id)) : new Layout(id, cont ->{}, 0);
    }

    public void setLayoutVisible(String id, boolean visible) {
        layoutList.find(layout -> Objects.equals(layout.id, id)).visible = visible;
        if(layoutList.find(layout -> Objects.equals(layout.id, id)).visible != visible) {
            update = true;
        }
    }

    public void delLayout(String id) {
        layoutList.removeAll(layout -> Objects.equals(layout.id, id));
        update = true;
    }

    public void toggleLayout(String layoutId) {
        uic.layoutList.find(layout -> Objects.equals(layout.id, layoutId)).visible = !uic.layoutList.find(layout -> Objects.equals(layout.id, layoutId)).visible;
        update = true;
    }

    private class FragmentDialog extends Fragment {

        @Override
        public void build(Group parent) {
            parent.fill(cont -> {
                cont.visible(() -> true);
                cont.table(Tex.buttonTrans, table -> {
                    cont.visible(() -> layoutList.filter(layout -> layout.visible).size > 0);
                    cont.update(() -> {
                        if (update && !threadRunning) {
                            new Thread(() -> {
                                table.clear();
                                layoutList.sort(layout -> layout.priority);
                                for (Layout layout : layoutList.toArray()) {
                                    if (layout.visible) layout.content.get(table);
                                }
                                threadRunning = false;
                            }).start();
                            update = false;
                        }
                    });
                }).margin(14f).minWidth(360f);
            });
        }
    }
}