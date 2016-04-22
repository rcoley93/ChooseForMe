package tk.rcoleyprogramming.chooseforme;

import com.orm.SugarRecord;

/**
 * Created by Ryan on 4/20/2016.
 */
public class Choices extends SugarRecord {
    private String strName;
    private Groups grpsGroup;
    public Choices(){}

    public Choices(String strChoice, Groups grpsGroup){
        this.strName = strChoice;
        this.grpsGroup = grpsGroup;
    }

    public String getName(){
        return this.strName;
    }

    public Groups getGroup(){
        return this.grpsGroup;
    }
}
