package core.cotents;

import mindustry.ctype.ContentList;

public class ContentLoader implements ContentList {
    private final ContentList[] contents = new ContentList[]{
        new BlockList(),
        new ItemList(),
        new LiquidList(),
        new PlanetList(),
        new WeatherList(),
        new LoadoutList()
    };

    @Override
    public void load() {
        for (ContentList content: contents) {
            content.load();
        }
    }
}
