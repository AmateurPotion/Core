package core.ui.layouts;

import arc.scene.ui.layout.Table;

public class Layout {
    public final String id;
    public boolean visible = true;
    public Integer priority;
    public arc.func.Cons<Table> content;
    public Layout(String layoutId) {
        id = layoutId;
        content = cont -> {
        };
        priority = 0;
    }
    public Layout(String layoutId, arc.func.Cons<Table> table) {
        id = layoutId;
        content = table;
        priority = 0;
    }
    public Layout(String layoutId, arc.func.Cons<Table> table, Integer layoutPriority) {
        id = layoutId;
        content = table;
        priority = layoutPriority;
    }
}
