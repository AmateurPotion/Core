package core;

import arc.*;
import arc.util.*;
import mindustry.*;
import mindustry.ctype.ContentType;
import mindustry.game.EventType;
import mindustry.mod.*;
import core.cotents.ContentLoader;

import java.util.Arrays;

@SuppressWarnings("unused")
public class Core extends Mod {
    public Core() {
        Events.on(EventType.ClientLoadEvent.class, e -> {
            Vars.load();
        });
    }

    @Override
    public void loadContent() {
        new ContentLoader().load();
    }
}