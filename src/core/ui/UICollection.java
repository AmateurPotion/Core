package core.ui;

import arc.scene.Group;
import arc.struct.Seq;
import core.ui.layouts.Layout;
import mindustry.gen.Tex;
import mindustry.ui.fragments.Fragment;

public final class UICollection {
    private final Seq<Layout> layoutList;

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
    private class FragmentDialog extends Fragment {

        @Override
        public void build(Group parent) {
            parent.fill(cont -> {
                cont.visible(() -> true);
                cont.table(Tex.buttonTrans, table -> {
                    table.clear();
                    layoutList.sort(layout -> layout.priority);
                    for(Layout layout: layoutList.toArray()) {
                        if(layout.visible) layout.content.get(table);
                    }
                }).margin(14f).minWidth(360f);;
            });
        }
    }
}
