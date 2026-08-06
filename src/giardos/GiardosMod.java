package giardos;

import arc.struct.Seq;
import arc.util.Log;
import mindustry.mod.Mod;
import mindustry.type.Item;
import mindustry.type.UnitType;
import mindustry.Vars;

public class GiardosMod extends Mod {

    @Override
    public void loadContent() {
        // El contenido JSON ya está cargado en este punto
        
        // Obtener referencia al item biomass
        // Mindustry añade el prefijo del mod: "giardos-biomass"
        Item biomass = Vars.content.item("giardos-biomass");
        
        if (biomass == null) {
            // Fallback: intentar sin prefijo
            biomass = Vars.content.item("biomass");
        }
        
        if (biomass != null) {
            Log.info("[Giardos] Biomasa registrada: " + biomass.name);
            
            // === VYPER ===
            UnitType vyper = Vars.content.unit("giardos-vyper");
            if (vyper != null) {
                if (vyper.mineItems == null) {
                    vyper.mineItems = new Seq<>();
                }
                if (!vyper.mineItems.contains(biomass)) {
                    vyper.mineItems.add(biomass);
                    Log.info("[Giardos] Biomasa añadida a Vyper");
                }
            }
            
            // === MUTATIO HARVESTER ===
            UnitType harvester = Vars.content.unit("giardos-mutatio-harvester");
            if (harvester != null) {
                if (harvester.mineItems == null) {
                    harvester.mineItems = new Seq<>();
                }
                if (!harvester.mineItems.contains(biomass)) {
                    harvester.mineItems.add(biomass);
                    Log.info("[Giardos] Biomasa añadida a Mutatio Harvester");
                }
            }
            
        } else {
            Log.err("[Giardos] ERROR: Item biomasa no encontrado!");
        }
    }
}
