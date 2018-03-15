package me.kungfucat.tripsplit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class MakeAGroupActivity extends AppCompatActivity {

    private EditText member1,member2,member3,member4,group_name;
    int gp_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_agroup);
        member1 = findViewById(R.id.make_group_member1);
        member2 = findViewById(R.id.make_group_member2);
        member3 = findViewById(R.id.make_group_member3);
        member4 = findViewById(R.id.make_group_member4);

        group_name = findViewById(R.id.make_group_name);

        //group id returned
       // Utility.groups.put(group_name.getText().toString(),gp_id);

    }
}
