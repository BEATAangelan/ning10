package ningjiaxin1.bwie.com.week3_lian2.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ningjiaxin1.bwie.com.week3_lian2.R;
import ningjiaxin1.bwie.com.week3_lian2.ShowActivity;
import ningjiaxin1.bwie.com.week3_lian2.bean.Chuan;
import ningjiaxin1.bwie.com.week3_lian2.bean.GoodsBean;
import ningjiaxin1.bwie.com.week3_lian2.persenter.IPersentermpl;
import ningjiaxin1.bwie.com.week3_lian2.view.IView;

public class Fragmentone extends Fragment implements IView {
    private IPersentermpl iPersenter;
    private String path = "http://www.zhaoapi.cn/product/getProductDetail";
    String data2;
    private TextView title,price;
    private Button xq,pl;
    private GoodsBean.DataBean data1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentone, container, false);
        title = view.findViewById(R.id.title);
        price = view.findViewById(R.id.price);
        xq= view.findViewById(R.id.xq) ;
        pl= view.findViewById(R.id.pl) ;
        iPersenter = new IPersentermpl(this);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        data2 = ((ShowActivity) getActivity()).getData();
        init();
        initData();

        xq.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                EventBus.getDefault().post(new Chuan(title.getText()+"","xq"));
                ((ShowActivity) getActivity()).getLayout(1);
            }
        });
        pl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().postSticky(new Chuan(price.getText()+"","pl"));
                ((ShowActivity) getActivity()).getLayout(2);
            }
        });
    }
    public void init(){
        iPersenter = new IPersentermpl(this);
    }
    public void initData(){
        HashMap<String,String>map = new HashMap<>();
        map.put("pid",data2);
        iPersenter.startRequest(path,map,GoodsBean.class);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    @Override
    public void setonSuccess(Object data) {
        if (data instanceof GoodsBean){
            GoodsBean bean = (GoodsBean) data;
            GoodsBean.DataBean data1 = bean.getData();
            String images = data1.getImages();
            String[] split = images.split("\\|");
            List<String> list = new ArrayList<>();
            for (int i=0;i<split.length;i++){
                list.add(split[i]);
            }

            title.setText(data1.getTitle());
            price.setText(data1.getPrice()+"");
        }
    }
}
