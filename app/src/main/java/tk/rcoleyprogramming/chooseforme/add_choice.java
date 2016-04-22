package tk.rcoleyprogramming.chooseforme;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.orm.query.Condition;
import com.orm.query.Select;

import java.util.ArrayList;
import java.util.List;

public class add_choice extends AppCompatActivity {
    EditText etChoice;
    AutoCompleteTextView actvCategory;
    List<String> groupsString;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_choice);

        etChoice = (EditText) findViewById(R.id.etChoice);
        actvCategory = (AutoCompleteTextView) findViewById(R.id.actvCategory);
        groupsString = new ArrayList<>();

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, groupsString);
        actvCategory.setAdapter(adapter);

        getGroups();
    }

    private void getGroups() {
        groupsString.clear();
        List<Groups> groups = Groups.listAll(Groups.class);

        for(Groups g : groups){
            groupsString.add(g.getName());
        }
        adapter.notifyDataSetChanged();
    }

    public void addChoice(View v){
        String[] strCategory = {actvCategory.getText().toString()};

        long lngDoesItExist = lngDoesItExist = Select.from(Groups.class).where(Condition.prop("str_name").eq(strCategory[0])).count();

        Groups g;

        if(lngDoesItExist > 0){
            g = Select.from(Groups.class).where(Condition.prop("str_name").eq(strCategory[0])).first();
        }else{
            g = new Groups(actvCategory.getText().toString());
            g.save();
        }

        Choices c = new Choices(etChoice.getText().toString(),g);
        c.save();

        etChoice.setText("");
        actvCategory.setText("");

        getGroups();
    }
}
