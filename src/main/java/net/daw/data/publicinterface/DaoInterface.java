package net.daw.data.publicinterface;



import java.util.ArrayList;
import java.util.HashMap;
import net.daw.helper.FilterBean;
import net.daw.pojos.pojoPersona;
 
public interface DaoInterface {
 
    public ArrayList<String> getColumnsNames() throws Exception;
 
    public ArrayList<String> getPrettyColumnsNames() throws Exception;
 
    public pojoPersona set(pojoPersona oPersona) throws Exception;
 
    public void remove(pojoPersona oPersona) throws Exception;
     
    public int getPages(int intRegsPerPag, ArrayList<FilterBean> hmFilter) throws Exception;
 
    public int getCount(ArrayList<FilterBean> hmFilter) throws Exception;
 
    public ArrayList<pojoPersona> getPage(int intRegsPerPag, int intPage, ArrayList<FilterBean> hmFilter, HashMap<String, String> hmOrder) throws Exception;
 
    public pojoPersona get(pojoPersona oPersona) throws Exception;
}