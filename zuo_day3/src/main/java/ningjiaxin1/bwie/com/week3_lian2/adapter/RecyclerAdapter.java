package ningjiaxin1.bwie.com.week3_lian2.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;


import ningjiaxin1.bwie.com.week3_lian2.R;
import ningjiaxin1.bwie.com.week3_lian2.bean.News;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private Context context;
    private List<News.DataBean> list;
    private boolean isGrid=true;
    public RecyclerAdapter(Context context, boolean isGrid) {
        this.context = context;
        this.isGrid = isGrid;
        list=new ArrayList<>();
    }

    public void setList(List<News.DataBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public List<News.DataBean> getList() {
        return list;
    }
    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ViewHolder holder=null;
        if(isGrid){
            View view = LayoutInflater.from(context).inflate(R.layout.item,viewGroup,false);
            holder=new ViewHolder(view);
        }else{
            View view = LayoutInflater.from(context).inflate(R.layout.item1, viewGroup, false);
            holder=new ViewHolder(view);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder viewHolder, final int i) {
        final News.DataBean dataBean = list.get(i);
        viewHolder.text_price.setText(list.get(i).getPrice()+"");
        viewHolder.text_price.setText(list.get(i).getSellerid()+"");
        viewHolder.text_price.setText(list.get(i).getPrice()+"");
        viewHolder.text_title.setText(list.get(i).getTitle());
        String url = list.get(i).getImages().split("\\|")[0].replace("https", "http");
        Glide.with(context).load(url).into(viewHolder.tu);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.click(dataBean.getPid());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView text_title,text_price,text_sum;
        ImageView tu;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text_price= itemView.findViewById(R.id.text_price);
            text_title = itemView.findViewById(R.id.text_title);
            text_sum = itemView.findViewById(R.id.text_sum);
            tu= itemView.findViewById(R.id.tu);
        }
    }
    OnClick onClick;

    public void setOnClick(OnClick onClick){
        this.onClick = onClick;
    }

    public interface OnClick{
        void click(int pid);
    }
}
