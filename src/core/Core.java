package core;

import arc.*;
import mindustry.game.EventType;
import mindustry.mod.*;

import static mindustry.Vars.*;

import core.cotents.ContentLoader;

@SuppressWarnings("unused")
public class Core extends Mod {
    public Core() {
        Events.on(EventType.ClientLoadEvent.class, e -> {
            Vars.load();
        });
    }

    @Override
    public void loadContent() {
        content.setCurrentMod(null);
        new ContentLoader().load();
    }
}