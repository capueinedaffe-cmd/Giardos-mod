package giardos;

import arc.struct.Seq;
import arc.util.Log;
import mindustry.content.Items;
import mindustry.content.TechTree;
import mindustry.content.TechTree.TechNode;
import mindustry.mod.Mod;
import mindustry.type.Item;
import mindustry.type.ItemStack;
import mindustry.type.UnitType;
import mindustry.Vars;

public class GiardosMod extends Mod {

    @Override
    public void loadContent() {
        // Esperar a que todo el contenido JSON se cargue
        // El contenido de assets/content/ ya está cargado en este punto
        
        // === 1. OBTENER REFERENCIA AL ITEM BIOMASA ===
        Item biomass = Vars.content.item("giardos-biomass");
        
        if (biomass == null) {
            biomass = Vars.content.item("biomass");
        }
        
        if (biomass != null) {
            Log.info("[Giardos] Biomasa encontrada: " + biomass.name);
            
            // === 2. AÑADIR BIOMASA AL TECH TREE ===
            // Crear un nodo de research para el item
            // El item aparecerá en el tech tree como "descubierto" cuando se asocie a un nodo
            
            // Buscar un nodo padre existente (Core: Shard es el inicio de toda rama nueva)
            TechNode coreShard = TechTree.all.find(t -> t.content.name.equals("core-shard"));
            
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
