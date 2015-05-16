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
public class EmptyStringException extends Exception{
    
    public EmptyStringException() {}
    
    public EmptyStringException(String message)
    {
        super(message);
    }
    
}
