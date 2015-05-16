/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tema5_czakozoltan_grupa30225;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import javax.swing.DefaultListModel;

/**
 *
 * @author Zoli
 */
public class Dictionary implements IDictionary{
    
    private HashMap<String,String> words;
    private ArrayList<String> keys;
    
    public Dictionary()
    {
        words = new HashMap<String,String>();
        keys = new ArrayList<String>();
    }
    
    @Override
    public void initDictionary(String path)
    {
        try (BufferedReader br = new BufferedReader(new FileReader(path)))
	{
            String CurrentLine;
                        
            while ((CurrentLine = br.readLine()) != null) {
		System.out.println(CurrentLine);
                String[] tokens = CurrentLine.split(" "); 
                try
                {
                    this.addWord(tokens[0], tokens[1]);
                }catch(ArrayIndexOutOfBoundsException ex)
                {
                    continue;
                }
            }
 
	} catch (IOException e) {
            e.printStackTrace();
	} 
    }
    
    public String searchMeaning(String word)
    {
        return this.words.get(word);
    }
    
    @Override
    public void addWord(String ENword, String ROword)
    {
        if(!this.words.containsKey(ENword))
        {
            this.words.put(ENword, ROword);
            this.words.put(ROword, ENword);
            this.keys.add(ENword);
            this.keys.add(ROword);
        }
    }
    
    public void simpleAddWord(String ENword, String ROword)
    {
        if(!this.words.containsKey(ENword))
        {
            this.words.put(ENword, ROword);
            this.keys.add(ENword);
        }
    }        
    
    @Override
    public void removeWord(String ENword, String ROword)
    {
        if(this.words.containsKey(ENword))
        {
            this.words.remove(ENword, ROword);
            this.words.remove(ROword, ENword);
            this.keys.remove(ROword);
            this.keys.remove(ENword);
        }
    }
    
    public boolean checkConsistency(String definedWord, String definingWord)
    {
        if(this.words.containsKey(definedWord))
            if(this.words.containsKey(definingWord) && this.words.get(definedWord).equalsIgnoreCase(definingWord))
                return true;
        return false;
    }
    
    @Override
    public boolean checkWholeConsistency()
    {
        String meaning;
        
        for(String word : this.keys)
        {
            meaning = this.words.get(word);
            if(!this.words.containsKey(meaning))
                return false;
        }
        
        return true;
    }
    
    @Override
    public DefaultListModel genericSearch(String pattern) throws PatternSyntaxException
    {
        int i=0;
        int index = 0;
        String tokens[];
        HashMap<String,String> localMap = (HashMap<String,String>) this.words.clone();

        DefaultListModel list = new DefaultListModel();
        
        pattern = "^" + pattern;
        
        index = pattern.indexOf("?");
        
        if(index>=0)
        {
            pattern = pattern.replace("?", "[a-z]");
        }
        
        index = pattern.lastIndexOf("]");
        
        if(index == pattern.length()-1)
        {
            pattern = pattern.concat("$");
        }
        
        if(!(pattern.endsWith("*")))
            pattern = pattern.concat("$");
        
        index = pattern.indexOf("*");
        
        if(index>=0)
        {
            pattern = pattern.replace("*", "+");
        }

        
        
        
        Pattern r = Pattern.compile(pattern);
        
        for(String word : this.keys)
        {
            Matcher m = r.matcher(word);
            if (m.find())
            {
                list.addElement(word + " = " + this.words.get(word));
                i++;
            }
        }
        if(i==0)
            list.addElement("NO MATCH!");
        
        return list;
    }
    
    public DefaultListModel showDictionary()
    {
        DefaultListModel list = new DefaultListModel();
        
        for(String word : this.keys)
        {
            list.addElement(word + " = " + this.words.get(word));
        }
        
        return list;
    }
}
