package core;

import arc.*;
import core.cotents.ContentLoader;
import mindustry.game.EventType;
import mindustry.mod.*;


@SuppressWarnings("unused")
public class Core extends Mod {
    public Core() {
        Events.on(EventType.ClientLoadEvent.class, e -> {
            Variable.load();
        });
    }

    @Override
    public void loadContent() {
        //content.setCurrentMod(null);
        new ContentLoader().load();
    }
}