package core;

import arc.*;
import arc.util.*;
import mindustry.*;
import mindustry.game.EventType;
import mindustry.mod.*;
import core.cotents.ContentLoader;

@SuppressWarnings("unused")
public class Core extends Mod {
    public static Vars var;
    public Core() {
        Events.on(EventType.ClientLoadEvent.class, e -> {
            var = new Vars();
        });
    }

    @Override
    public void loadContent() {
        new ContentLoader().load();
    }
}