/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.Grafics;

/**
 *
 * @author ze1
 */
import java.io.InputStream;

public class Resources {
	
	public static InputStream getResourceFile(String name){
		// Getting named resource from Resources.class location...
		InputStream in=Resources.class.getResourceAsStream(name);
		return in; 
	}

}