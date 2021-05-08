package core.world.planets.alpha;

import arc.graphics.Color;
import arc.math.geom.Vec3;
import mindustry.maps.generators.PlanetGenerator;
import mindustry.world.Tiles;

public class AlphaPlanetGenerator extends PlanetGenerator {
    @Override
    public float getHeight(Vec3 position) {
        return 0;
    }

    @Override
    public Color getColor(Vec3 position) {
        return null;
    }

    @Override
    public void postGenerate(Tiles tiles) {
        super.postGenerate(tiles);
    }
}
