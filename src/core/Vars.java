package core;

import static mindustry.Vars.*;

import core.ui.UICollection;

public class Vars {
    public static final UICollection uic = new UICollection();
    public Vars() {

    }
    public static void load() {
        uic.build(ui.hudGroup);
    }
}