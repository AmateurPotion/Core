package core.world.blocks.autoTile;

import arc.Core;
import arc.Events;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.struct.Seq;
import arc.util.Log;
import core.Variable;
import core.ui.graphics.TextureEditor;
import mindustry.game.EventType;
import mindustry.type.Item;
import mindustry.world.Tile;
import mindustry.world.blocks.environment.OverlayFloor;

public class ItemPlate extends OverlayFloor {
    public final Item itemProperty;
    private final String shape;
    public static final Seq<String> shapes = Seq.with("none", "ore");

    public ItemPlate(String name, Item property) {
        this(name, property, shape(0));
    }

    public ItemPlate(String name, Item property, int plateShape) {
        this(name, property, shape(plateShape));
    }

    // 기본 판 모양(shape) 종류 : none, ore
    public ItemPlate(String name, Item property, String plateShape) {
        super(name);
        itemProperty = property;
        shape = shapes.contains(plateShape) ? plateShape : "none";
        inEditor = true;
        Events.on(EventType.ClientLoadEvent.class, e -> {
            PixmapRegion pr = Core.atlas.getPixmap("plate-" + shape);
            Pixmap region = TextureEditor.paint(pr, itemProperty.color);
            uiIcon = new TextureRegion(new Texture(region));
            variantRegions[1] = new TextureRegion(new Texture(TextureEditor.resize(region, variantRegions[1].width, variantRegions[1].height)));
        });
    }

    @Override
    public void drawBase(Tile tile){
        if(tile.build != null){
            tile.build.draw();
        } else {
            Draw.color(itemProperty.color);
            Draw.rect(Variable.modName + "-plate-" + shape, tile.drawx(), tile.drawy());
            Draw.color();
        }
    }

    public static String shape(int shapeId) {
        return shapeId >= shapes.size ? "none" : shapes.get(shapeId);
    }
}
