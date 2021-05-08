package core.ui.fragments;

import arc.scene.Group;
import arc.scene.ui.layout.Table;
import mindustry.gen.Tex;
import mindustry.ui.fragments.Fragment;

import static core.Vars.*;

public class BaseFragment extends Fragment {
    public arc.func.Cons<Table> content = cont -> {
    };
    public boolean visible = false;
    private boolean update = true;

    @Override
    public void build(Group parent) {
        parent.fill(cont -> {
            cont.visible(() -> visible);
            cont.table(Tex.buttonTrans, table -> cont.update(()-> {
                if(update) {
                    table.clear();
                    content.get(table);
                    update = false;
                }
            })).margin(14f).minWidth(360f);
        });
    }

    public void open(arc.func.Cons<Table> table) {
        content = table;
        update = true;
        visible = true;
    }
}
