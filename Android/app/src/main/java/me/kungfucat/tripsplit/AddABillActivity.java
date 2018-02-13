package me.kungfucat.tripsplit;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class AddABillActivity extends AppCompatActivity {

    private EditText member1,member2,member3,member4,group;
    private EditText bill1;
    private EditText bill2;
    private EditText bill3;
    private EditText bill4;

    private int transaction_id;
    private int CAMERA_PIC_REQUEST=100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_abill);
        setTitle("Add a bill");
        member1= findViewById(R.id.editTextmember1);
        member2= findViewById(R.id.editTextmember2);
        member3= findViewById(R.id.editTextmember3);
        member4= findViewById(R.id.editTextmember4);

        bill1= findViewById(R.id.member_1_bill);
        bill2= findViewById(R.id.member_2_bill);
        bill3= findViewById(R.id.member_3_bill);
        bill4= findViewById(R.id.member_4_bill);

        group = findViewById(R.id.group_name);

        //transaction_id returned
        FloatingActionButton fab = findViewById(R.id.camera_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivity(intent);

            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_PIC_REQUEST) {
            Bitmap image = (Bitmap) data.getExtras().get("data");

        }
    }

}
