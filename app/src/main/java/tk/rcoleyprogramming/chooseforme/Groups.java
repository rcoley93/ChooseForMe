package tk.rcoleyprogramming.chooseforme;

import com.orm.SugarRecord;

/**
 * Created by Ryan on 4/20/2016.
 */
public class Groups extends SugarRecord {
    private String strName;

    public Groups(){}

    public Groups(String strName){
        this.strName = strName;
    }

    public String getName(){
        return this.strName;
    }

    public void setName(String strNewName){
        this.strName = strNewName;
    }
}
