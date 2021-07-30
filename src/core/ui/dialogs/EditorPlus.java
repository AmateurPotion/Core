package core.ui.dialogs;

import arc.Events;
import arc.scene.ui.layout.Table;
import arc.util.Log;
import mindustry.Vars;
import mindustry.game.EventType;
import mindustry.world.Tile;
import mindustry.world.Tiles;

import static mindustry.Vars.state;
import static mindustry.Vars.ui;

public class EditorPlus {
    public static Tile startPoint, endPoint;
    public static Tiles copyData;
    public static int copyState = 0, max = 3;

    public void load() {
        ui.hudGroup.addChild(new Table(table -> {
            table.left();
            table.visible(() -> state.isEditor());
            table.fillParent = true;
            //table.label(()->"test").left().padRight(40 * 8f);
            table.button(b -> {
                b.label(() -> "" + copyState);
            }, () -> {
                if(copyState < max) {
                    copyState++;
                } else {
                    copyState = 0;
                }
            }).left();
            table.row();
            table.label(()->startPoint != null ? "start : " + startPoint.x + " / " + startPoint.y + " - " + startPoint.block().name : "start").left();
            table.row();
            table.label(()->endPoint != null ? "end : " + endPoint.x + " / " + endPoint.y + " - " + endPoint.block().name : "end").left();
            table.row();
            table.button("copy", () -> {
                if(startPoint != null && endPoint != null) {
                    int tw = endPoint.x - startPoint.x, th = endPoint.y - startPoint.y, sx = startPoint.x < endPoint.x ? startPoint.x : endPoint.x, sy = (startPoint.y < endPoint.y ? startPoint.y : endPoint.y);
                    copyData = new Tiles((tw > 0 ? tw : -tw) + 1, (th > 0 ? th : -th) + 1);
                    copyData.fill();
                    for(int y = 0; y < copyData.height; y++) {
                        for(int x = 0; x < copyData.width; x++) {
                            copyData.set(x, y, Vars.world.tile(sx + x, sy + y));
                        }
                    }
                }
            }).left();
            table.row();
            table.label(()->copyData != null ? copyData.width + " / " + copyData.height : "null").left();
        }));

        Events.on(EventType.TapEvent.class, e -> {
            if(state.isEditor()) {
                switch(copyState) {
                    case 1:
                        startPoint = e.tile;
                        break;
                    case 2:
                        endPoint = e.tile;
                        break;
                    case 3:
                        break;
                }
                copyState = 0;
            }
        });
    }
}
