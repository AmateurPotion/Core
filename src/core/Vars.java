package core;

import static mindustry.Vars.*;

import arc.scene.ui.layout.Table;
import arc.struct.Seq;
import core.ui.fragments.*;

public class Vars {
    public BaseFragment baseFrag;
    public Seq<arc.func.Cons<Table>> fragmentDialog;
    public Vars() {
        fragmentDialog = new Seq<arc.func.Cons<Table>>();
        fragmentDialog.add(table -> {});
        int i = fragmentDialog.size;
        baseFrag = new BaseFragment();

        baseFrag.build(ui.hudGroup);
    }
}