package core.cotents;

import arc.struct.Seq;
import arc.util.Log;
import core.world.blocks.autoTile.ItemPlate;
import core.world.blocks.frame.Frame;
import mindustry.content.Items;
import mindustry.ctype.ContentList;
import mindustry.type.Category;
import mindustry.type.Item;
import mindustry.world.Block;
import mindustry.world.blocks.environment.OverlayFloor;
import mindustry.world.meta.BuildVisibility;

import static mindustry.Vars.content;
import static mindustry.type.ItemStack.with;

public class BlockList implements ContentList {
    // Block - tile
    public static Block test,
    // Block - structure
            frame, doublicFrame;
    // Block - autoTile
    public static Seq<Block> plates;

    @Override
    public void load() {

        frame = new Frame("basic-frame"){{
            requirements(Category.effect, with(Items.scrap, 50));
            itemCapacity = 10;
            size = 1;
            craftTableSize = 1;
        }};

        doublicFrame = new Frame("doublic-frame"){{
            requirements(Category.effect, with(Items.scrap, 50, Items.copper, 30, Items.lead, 50)) ;
            itemCapacity = 40;
            size = 2;
            craftTableSize = 2;
        }};

        plates = Seq.with();

        for(int i = 0; i < content.items().size; i++) {
            plates.add(
                    new ItemPlate(content.items().get(i).name +"-plate", content.items().get(i), 1){{
                        buildVisibility = BuildVisibility.editorOnly;
                    }}
            );
        }
    }
}
