package core.ui.layouts;

import arc.scene.ui.layout.Table;

public class Layout {
    public final String id;
    public boolean visible = false;
    public Integer priority = 0;
    public arc.func.Cons<Table> content = cont -> {};

    public Layout(String layoutId) {
        id = layoutId;
    }
    public Layout(String layoutId, arc.func.Cons<Table> table) {
        id = layoutId;
        content = table;
    }
    public Layout(String layoutId, arc.func.Cons<Table> table, Integer layoutPriority) {
        id = layoutId;
        content = table;
        priority = layoutPriority;
    }
}
