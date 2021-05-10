package core.cotents;

import arc.struct.Seq;
import core.world.blocks.frame.Frame;
import mindustry.content.Items;
import mindustry.ctype.ContentList;
import mindustry.type.Category;
import mindustry.world.Block;
import mindustry.world.blocks.defense.Wall;

import static mindustry.type.ItemStack.with;

public class BlockList implements ContentList {
    // Block - tile
    public static Block
    // Block - structure
            frame, doublicFrame;
    // Block - autoTile
    public static Seq<Block> orePlate;

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
        }};

        orePlate = Seq.with(

        );
    }
}
