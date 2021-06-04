package core.ui.layouts;

import arc.scene.ui.layout.Table;

public class Layout {
    public final String id;
    public boolean visible = false;
    public boolean update = true;
    public Integer priority;
    public Table content;

    public Layout(String layoutId) {
        this(layoutId, new Table(cont -> cont.name = "nullTable"));
    }

    public Layout(String layoutId, Table table) {
        this(layoutId,table, 0);
    }

    public Layout(String layoutId, Table table, Integer layoutPriority) {
        id = layoutId;
        content = table;
        priority = layoutPriority;
    }
}
