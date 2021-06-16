package core.cotents;

import arc.graphics.Color;
import mindustry.ctype.ContentList;
import mindustry.type.Item;

public class ItemList implements ContentList {
    public static Item blank;
    @Override
    public void load() {
        blank = new Item("blank", Color.valueOf("ff0000")){{
            hardness = 0;
            flammability = 10f;
            explosiveness = 10f;
            cost = 100f;
        }};
    }
}
