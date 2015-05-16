/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tema5_czakozoltan_grupa30225;

import java.util.regex.PatternSyntaxException;
import javax.swing.DefaultListModel;

/**
 *
 * @author Zoli
 */
public interface IDictionary {
    
    public void initDictionary(String path);
    public void addWord(String ENword, String ROword);
    public void removeWord(String ENword, String ROword);
    public boolean checkWholeConsistency();
    public DefaultListModel genericSearch(String pattern) throws PatternSyntaxException;
    
}
