package giardos;

import arc.struct.Seq;
import arc.util.Log;
import mindustry.content.TechTree;
import mindustry.type.UnitType;
import mindustry.Vars;

public class GiardosMod extends mindustry.mod.Mod {

    @Override
    public void loadContent() {
        Log.info("[Giardos] Cargando contenido...");
        
        GiardosItems.load();
        Log.info("[Giardos] Biomasa registrada: " + GiardosItems.biomass.name);
        
        TechTree.nodeProduce(GiardosItems.biomass, Seq::new);
        Log.info("[Giardos] Biomasa añadida al tech tree");
        
        UnitType vyper = Vars.content.unit("giardos-vyper");
        if (vyper != null) {
            if (vyper.mineItems == null) {
                vyper.mineItems = new Seq<>();
            }
            if (!vyper.mineItems.contains(GiardosItems.biomass)) {
                vyper.mineItems.add(GiardosItems.biomass);
                Log.info("[Giardos] Biomasa añadida a Vyper");
            }
        }
        
        UnitType harvester = Vars.content.unit("giardos-mutatio-harvester");
        if (harvester != null) {
            if (harvester.mineItems == null) {
                harvester.mineItems = new Seq<>();
            }
            if (!harvester.mineItems.contains(GiardosItems.biomass)) {
                harvester.mineItems.add(GiardosItems.biomass);
                Log.info("[Giardos] Biomasa añadida a Mutatio Harvester");
            }
        }
        
        Log.info("[Giardos] Mod cargado correctamente.");
    }
}
