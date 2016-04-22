package tk.rcoleyprogramming.chooseforme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView tvResult;
    Button btnChoose;
    Spinner spnGroup;
    List<String> groupsString;
    ArrayAdapter<String> dataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResult = (TextView) findViewById(R.id.tvResult);
        btnChoose = (Button) findViewById(R.id.btnChoose);
        spnGroup = (Spinner) findViewById(R.id.spnGroup);
        groupsString = new ArrayList<>();

        dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, groupsString);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spnGroup.setAdapter(dataAdapter);
        dataAdapter.notifyDataSetChanged();
        getGroups();
    }
    @Override
    protected void onResume(){
        super.onResume();

        getGroups();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {
            Intent i = new Intent(MainActivity.this, add_choice.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void getGroups() {
        groupsString.clear();
        List<Groups> groups = Groups.listAll(Groups.class);

        for(Groups g : groups){
            groupsString.add(g.getName());
        }
        dataAdapter.notifyDataSetChanged();
    }

    public void makeChoice(View v){
        String strGroup = spnGroup.getSelectedItem().toString();
        String[] strArguments = {strGroup};
        List<Groups> group = Groups.find(Groups.class,"str_name=?",strArguments);

        strArguments[0] = group.get(0).getId().toString();
        List<Choices> lstChoices= Choices.find(Choices.class,"grps_groups=?",strArguments);

        Random rInt = new Random(lstChoices.size());
        int iChoice = rInt.nextInt();

        tvResult.setText(lstChoices.get(iChoice).getName());
    }
}
