package core;

import static mindustry.Vars.*;

import arc.struct.Seq;
import core.cotents.craftTables.UnlockableList;
import core.cotents.craftTables.UnsignedList;
import core.ui.UICollection;
import core.world.blocks.frame.CraftTable;

import java.util.LinkedList;
import java.util.List;

public class Variable {
    public static boolean debugMode = false;
    public static int craftDefaultStack = 10;
    public static final String modName = "core";
    public static final Seq<CraftTable> cExpressions = Seq.with();
    public static final UICollection uic = new UICollection();
    public Variable() {

    }
    public static void load() {
        new UnsignedList().addExpressions();
        new UnlockableList().addExpressions();
        uic.build(ui.hudGroup);
    }
}