package core.world.blocks.autoTile;

import arc.graphics.g2d.Draw;
import mindustry.type.Item;
import mindustry.world.Tile;
import mindustry.world.blocks.environment.Floor;

public class ItemPlate extends Floor {
    public final Item itemProperty;
    private final String shape;

    public ItemPlate(String name, Item property) {
        this(name, property, "none");
    }
    public ItemPlate(String name, Item property, String plateShape) {
        super(name);
        itemProperty = property;
        shape = plateShape;
    }

    @Override
    public void drawBase(Tile tile){
        Draw.color(itemProperty.color);
        Draw.rect("plate-" + shape, tile.worldx(), tile.worldy());
        Draw.color();
    }

}
