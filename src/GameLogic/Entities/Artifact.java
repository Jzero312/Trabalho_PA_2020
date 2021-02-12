/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameLogic.Entities;

/**
 *
 * @author ze1
 */
public class Artifact extends Entitie{
    
    public Artifact(){
        super("Artifact", 'A');
    }

    @Override
    public Boolean isArtifact() {
        return true;
    }
    
    
}
