package core.cotents;

import arc.graphics.Color;
import mindustry.content.Planets;
import mindustry.ctype.ContentList;
import mindustry.graphics.g3d.HexMesh;
import mindustry.maps.planet.SerpuloPlanetGenerator;
import mindustry.type.Planet;

import core.world.planets.alpha.AlphaSectorPresets;

public class PlanetList implements ContentList {
    public static Planet alpha;

    private final ContentList[] Sectors = new ContentList[]{
        new AlphaSectorPresets()
    };

    @Override
    public void load() {
        alpha = new Planet("alpha", Planets.sun, 3, 3) {{
            generator = new SerpuloPlanetGenerator();
            meshLoader = () -> new HexMesh(this, 6);
            atmosphereColor = Color.valueOf("3c1b8f");
            atmosphereRadIn = 0.02f;
            atmosphereRadOut = 0.3f;
            startSector = 15;
        }};

        for (ContentList content: Sectors) {
            content.load();
        }
    }
}
