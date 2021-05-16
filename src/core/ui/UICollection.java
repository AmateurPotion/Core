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
        } else {
            Log.info(layout.id + " is not active because it already exists.");
        }
    }

    public void setLayout(Layout layout) {
        layoutList.removeAll(remove -> Objects.equals(remove.id, layout.id));
        layoutList.add(layout);
    }

    public void setLayoutVisible(String id, boolean visible) {
        layoutList.find(layout -> Objects.equals(layout.id, id)).visible = visible;
    }

    public void delLayout(String id) {
        layoutList.removeAll(layout -> Objects.equals(layout.id, id));
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
                        if (update) {
                            table.clear();
                            layoutList.sort(layout -> layout.priority);
                            for (Layout layout : layoutList.toArray()) {
                                if (layout.visible) layout.content.get(table);
                            }
                            update = false;
                        }
                    });
                }).margin(14f).minWidth(360f);
            });
        }
    }
}