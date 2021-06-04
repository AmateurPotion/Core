package core.ui;

import arc.func.Cons;
import arc.scene.Group;
import arc.scene.ui.layout.Table;
import arc.util.Log;
import mindustry.ui.fragments.Fragment;

import java.util.Objects;

public final class UICollection {
    private boolean update = true;
    private static int updateState = 0;
    private static Table temp;
    private String tempName;
    private final FragmentDialog layoutList = new FragmentDialog();
    public void build(Group parent) {
        layoutList.build(parent);
    }

    public void addChild(String name, Cons<Table> content) {
        temp = new Table(content);
        temp.name = name;
        updateState = 1;
        update = true;
    }

    public void removeChild(String name) {
        tempName = name;
        updateState = 2;
        update = true;
    }
    public void toggle(String name) {
        tempName = name;
        updateState = 3;
        update = true;
    }

    private class FragmentDialog extends Fragment {

        @Override
        public void build(Group parent) {
            parent.fill(cont -> cont.update(() -> {
                if(update) {
                    Log.info("adasda");
                    if(updateState == 1) {
                        Log.info("aaaa");
                        if(!cont.getChildren().contains(child -> Objects.equals(child.name, temp.name))) cont.add(temp);
                        temp = new Table(c -> c.name = "null");
                    }
                    switch (updateState) {
                        case 2:
                            if(cont.getChildren().find(child -> Objects.equals(child.name, tempName)) != null) cont.getChildren().find(child -> Objects.equals(child.name, tempName)).remove();
                            break;
                        case 3:
                            if(cont.getChildren().find(child -> Objects.equals(child.name, tempName)) != null) cont.getChildren().find(child -> Objects.equals(child.name, tempName)).visible = !cont.getChildren().find(child -> Objects.equals(child.name, tempName)).visible;
                            break;
                        default:
                            break;
                    }
                    updateState = 0;
                    update = false;
                }
            }));
        }
    }
}