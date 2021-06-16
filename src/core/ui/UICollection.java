package core.ui;

import arc.func.Cons;
import arc.scene.Group;
import arc.scene.event.Touchable;
import arc.scene.ui.layout.Cell;
import arc.scene.ui.layout.Table;
import mindustry.ui.fragments.Fragment;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public final class UICollection {
    private boolean update = true;
    private final Queue<Integer> updateState = new LinkedList<>();
    private final Queue<Table> tables = new LinkedList<>();
    private final Queue<String> names = new LinkedList<>();
    private final FragmentDialog layoutList = new FragmentDialog();
    public void build(Group parent) {
        layoutList.build(parent);
    }

    public void addChild(String name, Cons<Table> content) {
        Table temp = new Table(content);
        temp.name = name;
        tables.add(temp);
        updateState.add(1);
        update = true;
    }

    public void removeChild(String name) {
        names.add(name);
        updateState.add(2);
        update = true;
    }
    public void toggle(String name) {
        names.add(name);
        updateState.add(3);
        update = true;
    }

    private class FragmentDialog extends Fragment {

        @Override
        public void build(Group parent) {
            parent.fill(cont -> cont.update(() -> {
                String t;
                Table tempTable;
                if(updateState.size() > 0) {
                    switch (updateState.poll()) {
                        case 1:
                            tempTable = tables.poll();
                            if(!cont.getChildren().contains(child -> Objects.equals(child.name, tempTable != null ? tempTable.name : null))) cont.add(tempTable);
                            break;
                        case 2:
                            t = names.poll();
                            if(cont.getChildren().find(child -> Objects.equals(child.name, t)) != null) cont.getChildren().find(child -> Objects.equals(child.name, t)).remove();
                            break;
                        case 3:
                            t = names.poll();
                            if(cont.getChildren().find(child -> Objects.equals(child.name, t)) != null) cont.getChildren().find(child -> Objects.equals(child.name, t)).visible = !cont.getChildren().find(child -> Objects.equals(child.name, t)).visible;
                            break;
                        default:
                            break;
                    }
                    update = false;
                }
            }));
        }
    }
}