package core;

import static mindustry.Vars.*;

import arc.scene.ui.layout.Table;
import arc.struct.Seq;
import core.ui.UICollection;
import core.ui.fragments.*;
import core.ui.layouts.Layout;

public class Vars {
    public static final UICollection uic = new UICollection();
    public Vars() {

    }
    public static void load() {
        uic.build(ui.hudGroup);
    }
}