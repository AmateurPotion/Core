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
    public final Seq<Layout> layoutList;
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
        } else if(debugMode) {
            Log.info(layout.id + " is not active because it already exists.");
        }
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