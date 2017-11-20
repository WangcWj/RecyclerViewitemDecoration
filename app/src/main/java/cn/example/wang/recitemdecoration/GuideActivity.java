package cn.example.wang.recitemdecoration;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GuideActivity extends AppCompatActivity implements View.OnClickListener{
    Button button1, button2,button3,button4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.buton1:

                break;
            case  R.id.buton2:
                break;
            case  R.id.buton3:
                break;
            case  R.id.buton4:
                break;
        }
    }
}
