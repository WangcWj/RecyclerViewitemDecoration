package cn.example.wang.recitemdecoration;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

import java.util.List;

/**
 *
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button button1, button2,button3,button4;
    RecyclerView recyclerView;
    RecAdapter adapter;
    private LinearHeadItemDecoration itemDecoration;
    private HeadItemDecoration headItemDecoration;
    private LinearItemDecoration linearItemDecoration;
    private GridItemdecoration gridItemdecoration;
    private LinearHeadItemDecoration linearHeadItemDecoration;

    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initRecyclerView();
        initData();

    }

    private void initView() {
        button1 = findViewById(R.id.buton1);
        button2 = findViewById(R.id.buton2);
        button3 = findViewById(R.id.buton3);
        button4 = findViewById(R.id.buton4);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecAdapter(this);

        headItemDecoration = new HeadItemDecoration(this);
        linearItemDecoration = new LinearItemDecoration(this);
        gridItemdecoration = new GridItemdecoration();
        linearHeadItemDecoration = new LinearHeadItemDecoration(this);

        recyclerView.addItemDecoration(headItemDecoration);
        recyclerView.setAdapter(adapter);
    }

    private void initData() {
        int coutn = 0;
        String cityJson = CityJson.cityJson;
        Gson gson = new Gson();
        try {
            RecBean recBean = gson.fromJson(cityJson, RecBean.class);
            if (recBean != null) {
                List<RecBean.CitiListBean> citiList = recBean.getCitiList();
                for (int i = 0; i < citiList.size(); i++) {
                    if (i % 5 == 0) {
                        coutn++;
                    }
                    citiList.get(i).setTage(coutn);
                }
                if (citiList != null) {
                    headItemDecoration.setCitiList(citiList);
                    linearHeadItemDecoration.setCitiList(citiList);
                    adapter.setRefreshData(citiList);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View v) {
         switch (v.getId()){
             case  R.id.buton1:
                 recyclerView.removeItemDecoration(headItemDecoration);
                 recyclerView.removeItemDecoration(gridItemdecoration);
                 recyclerView.removeItemDecoration(linearHeadItemDecoration);
                 recyclerView.addItemDecoration(linearItemDecoration);
                 recyclerView.setLayoutManager(new LinearLayoutManager(this));
                 adapter.notifyDataSetChanged();
                 break;
             case  R.id.buton2:
                 recyclerView.removeItemDecoration(headItemDecoration);
                 recyclerView.removeItemDecoration(linearItemDecoration);
                 recyclerView.removeItemDecoration(linearHeadItemDecoration);
                 recyclerView.addItemDecoration(gridItemdecoration);
                 recyclerView.setLayoutManager(new GridLayoutManager(this,3));
                 adapter.notifyDataSetChanged();
                 break;
             case  R.id.buton3:
                 recyclerView.removeItemDecoration(headItemDecoration);
                 recyclerView.removeItemDecoration(linearItemDecoration);
                 recyclerView.removeItemDecoration(gridItemdecoration);
                 recyclerView.addItemDecoration(linearHeadItemDecoration);
                 recyclerView.setLayoutManager(new LinearLayoutManager(this));
                 adapter.notifyDataSetChanged();
                 break;
             case  R.id.buton4:
                 recyclerView.removeItemDecoration(linearHeadItemDecoration);
                 recyclerView.removeItemDecoration(linearItemDecoration);
                 recyclerView.removeItemDecoration(gridItemdecoration);
                 recyclerView.addItemDecoration(headItemDecoration);
                 recyclerView.setLayoutManager(new LinearLayoutManager(this));
                 adapter.notifyDataSetChanged();
                 break;
         }
    }


}
