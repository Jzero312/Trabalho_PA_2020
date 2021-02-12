/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.Grafics;


import java.util.HashMap;
import java.util.Map;
import javafx.scene.image.Image;

/**
 *
 * @author ze1
 */
public class Imagens {
    private static final Map<String, Image> imagens = new HashMap<>();
    
    static {
        //fundos
        imagens.put(ConstantesGui.FUNDO, new Image(Resources.getResourceFile(ConstantesGui.PATH_IMAGEM_FUNDO)));
        imagens.put(ConstantesGui.FUNDO_ESCOLHE_SHIP, new Image(Resources.getResourceFile(ConstantesGui.PATH_FUNDO_ESCOLHE_SHIP)));
        imagens.put(ConstantesGui.FUNDO_WHITE_SECTOR, new Image(Resources.getResourceFile(ConstantesGui.PATH_FUNDO_WHITE_SECTOR)));
        imagens.put(ConstantesGui.FUNDO_LAND, new Image(Resources.getResourceFile(ConstantesGui.PATH_FUNDO_LAND)));
        imagens.put(ConstantesGui.FUNDO_LOST, new Image(Resources.getResourceFile(ConstantesGui.PATH_FUNDO_LOST)));
        imagens.put(ConstantesGui.FUNDO_RED_SECTOR, new Image(Resources.getResourceFile(ConstantesGui.PATH_FUNDO_RED_SECTOR)));
        imagens.put(ConstantesGui.FUNDO_VITORIA, new Image(Resources.getResourceFile(ConstantesGui.PATH_FUNDO_VITORIA)));
        imagens.put(ConstantesGui.FUNDO_CONVERT, new Image(Resources.getResourceFile(ConstantesGui.PATH_FUNDO_CONVERT)));
        
        //planets
        imagens.put(ConstantesGui.BLACKPLANET, new Image(Resources.getResourceFile(ConstantesGui.PATH_BLACKPLANET)));
        imagens.put(ConstantesGui.REDPLANET, new Image(Resources.getResourceFile(ConstantesGui.PATH_REDPLANET)));
        imagens.put(ConstantesGui.GREENPLANET, new Image(Resources.getResourceFile(ConstantesGui.PATH_GREENPLANET)));
        imagens.put(ConstantesGui.BLUEPLANET, new Image(Resources.getResourceFile(ConstantesGui.PATH_BLUEPLANET)));
        
        //RECURSOS
        imagens.put(ConstantesGui.BLACKRESOURCE, new Image(Resources.getResourceFile(ConstantesGui.PATH_BLACKRESOURCE)));
        imagens.put(ConstantesGui.REDRESOURCE, new Image(Resources.getResourceFile(ConstantesGui.PATH_REDRESOURCE)));
        imagens.put(ConstantesGui.GREENRESOURCE, new Image(Resources.getResourceFile(ConstantesGui.PATH_GREENRESOURCE)));
        imagens.put(ConstantesGui.BLUERESOURCE, new Image(Resources.getResourceFile(ConstantesGui.PATH_BLUERESOURCE)));
        
        //ENTIDADES
        imagens.put(ConstantesGui.ALIEN, new Image(Resources.getResourceFile(ConstantesGui.PATH_ALIEN)));
        imagens.put(ConstantesGui.ARTFACT, new Image(Resources.getResourceFile(ConstantesGui.PATH_ARTFACT)));
        imagens.put(ConstantesGui.DRONE, new Image(Resources.getResourceFile(ConstantesGui.PATH_DRONE)));
        imagens.put(ConstantesGui.SHIP, new Image(Resources.getResourceFile(ConstantesGui.PATH_SHIP)));
        
    }
    
    public static Image getImagem(String nome) {
         return imagens.get(nome);
    }
}
