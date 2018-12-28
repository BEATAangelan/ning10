package ningjiaxin1.bwie.com.week3_lian2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ningjiaxin1.bwie.com.week3_lian2.adapter.RecyclerAdapter;
import ningjiaxin1.bwie.com.week3_lian2.bean.Chuan;
import ningjiaxin1.bwie.com.week3_lian2.bean.News;
import ningjiaxin1.bwie.com.week3_lian2.persenter.IPersentermpl;
import ningjiaxin1.bwie.com.week3_lian2.view.IView;

public class MainActivity extends AppCompatActivity implements IView,View.OnClickListener {
    private Button button;
    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;
    private boolean isGrid=true;
    private int ITEM_COUNT=2;
    IPersentermpl iPersentermpl;
    String path="http://www.zhaoapi.cn/product/searchProducts?keywords=笔记本&page=1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        button.setOnClickListener(this);
    }
    public void init(){
        iPersentermpl = new IPersentermpl(this);
        recyclerView = findViewById(R.id.recycler);
        button = findViewById(R.id.button_huan);
        changeView();
        HashMap<String, String> map = new HashMap<>();
        map.put("keywords", "手机");
        map.put("page", 1 + "");
        iPersentermpl.startRequest(path,map,News.class);
        adapter.setOnClick(new RecyclerAdapter.OnClick() {
            @Override
            public void click(int pid) {
                Intent intent = new Intent(MainActivity.this,ShowActivity.class);
                intent.putExtra("pid",String.valueOf(pid));
                startActivity(intent);
            }
        });
    }
    private void initData(String name) {
        HashMap<String, String> map = new HashMap<>();
        map.put("keywords", "手机");
        map.put("page", 1 + "");
        iPersentermpl.startRequest(path,map,News.class);
    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.button_huan:
                Toast.makeText(MainActivity.this,"aaaa",Toast.LENGTH_SHORT).show();
                List<News.DataBean> list = adapter.getList();
                changeView();
                adapter.setList(list);
        }
    }

    private void changeView() {
        if(isGrid){
            GridLayoutManager layoutManager = new GridLayoutManager(MainActivity.this,ITEM_COUNT);
            layoutManager.setOrientation(OrientationHelper.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);
        }else{
            LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);
            adapter = new RecyclerAdapter(MainActivity.this, isGrid);
            recyclerView.setAdapter(adapter);
        }
        adapter = new RecyclerAdapter(MainActivity.this, isGrid);
        recyclerView.setAdapter(adapter);
        isGrid=!isGrid;
    }

    @Override
    public void setonSuccess(Object data) {
        if (data instanceof News){
            News bean= (News) data;
            List<News.DataBean> data1 = bean.getData();
            adapter.setList(data1);
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void getName(Chuan chuan){
        if (chuan.getFlag().equals("shop_name")) {
            initData(chuan.getData().toString());
        }
    }
}
