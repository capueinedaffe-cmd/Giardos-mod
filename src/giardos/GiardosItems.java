package giardos;

import arc.graphics.Color;
import mindustry.type.Item;

public class GiardosItems {
    
    public static Item biomass;
    
    public static void load() {
        biomass = new Item("biomass", Color.valueOf("8DB360")) {{
            hardness = 0;
            cost = 0.5f;
            flammability = 0.3f;
            explosiveness = 0f;
            radioactivity = 0f;
            charge = 0f;
        }};
    }
}
