package giardos;

import arc.struct.Seq;
import arc.util.Log;
import mindustry.content.TechTree;
import mindustry.type.UnitType;
import mindustry.Vars;

import static mindustry.content.TechTree.*;

public class GiardosMod extends Mod {

    @Override
    public void loadContent() {
        Log.info("[Giardos] Cargando contenido...");
        
        // 1. Cargar items PRIMERO
        GiardosItems.load();
        Log.info("[Giardos] Biomasa registrada: " + GiardosItems.biomass.name);
        
        // 2. Añadir biomasa al tech tree
        nodeProduce(GiardosItems.biomass, Seq::new);
        Log.info("[Giardos] Biomasa añadida al tech tree");
        
        // 3. Modificar mineItems de unidades
        UnitType vyper = Vars.content.unit("giardos-vyper");
        if (vyper != null) {
            if (vyper.mineItems == null) vyper.mineItems = new Seq<>();
            if (!vyper.mineItems.contains(GiardosItems.biomass)) {
                vyper.mineItems.add(GiardosItems.biomass);
                Log.info("[Giardos] Biomasa añadida a Vyper");
            }
        }
        
        UnitType harvester = Vars.content.unit("giardos-mutatio-harvester");
        if (harvester != null) {
            if (harvester.mineItems == null) harvester.mineItems = new Seq<>();
            if (!harvester.mineItems.contains(GiardosItems.biomass)) {
                harvester.mineItems.add(GiardosItems.biomass);
                Log.info("[Giardos] Biomasa añadida a Mutatio Harvester");
            }
        }
        
        Log.info("[Giardos] Mod cargado correctamente.");
    }
}
            
            if (coreShard != null) {
                // Crear nodo para biomasa como hijo de core-shard
                TechNode biomassNode = new TechNode(coreShard, biomass, ItemStack.with(Items.copper, 100));
                biomassNode.name = "Biomasa";
                Log.info("[Giardos] Nodo de tech tree creado para biomasa");
            } else {
                Log.warn("[Giardos] No se encontró core-shard para tech tree");
            }
            
            // === 3. AÑADIR BIOMASA A MINEITEMS DE UNIDADES ===
            // Vyper
            UnitType vyper = Vars.content.unit("giardos-vyper");
            if (vyper != null) {
                if (vyper.mineItems == null) {
                    vyper.mineItems = new Seq<>();
                }
                if (!vyper.mineItems.contains(biomass)) {
                    vyper.mineItems.add(biomass);
                    Log.info("[Giardos] Biomasa añadida a mineItems de Vyper");
                }
            }
            
            // Mutatio Harvester
            UnitType harvester = Vars.content.unit("giardos-mutatio-harvester");
            if (harvester != null) {
                if (harvester.mineItems == null) {
                    harvester.mineItems = new Seq<>();
                }
                if (!harvester.mineItems.contains(biomass)) {
                    harvester.mineItems.add(biomass);
                    Log.info("[Giardos] Biomasa añadida a mineItems de Mutatio Harvester");
                }
            }
            
        } else {
            Log.err("[Giardos] ERROR: Item biomasa no encontrado!");
        }
    }
}
