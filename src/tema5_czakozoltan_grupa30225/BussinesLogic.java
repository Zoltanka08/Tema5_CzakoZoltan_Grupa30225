/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tema5_czakozoltan_grupa30225;

/**
 *
 * @author Zoli
 */
public class BussinesLogic {
    
    public static void verifyTextField(String text) throws EmptyStringException
    {
        text = text.trim();
        if(text.equalsIgnoreCase(""))
            throw new EmptyStringException("Empty TextField!");
    }
    
}
