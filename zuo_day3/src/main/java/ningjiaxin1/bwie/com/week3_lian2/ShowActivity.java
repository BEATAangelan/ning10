package ningjiaxin1.bwie.com.week3_lian2;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioGroup;

import java.util.ArrayList;

import ningjiaxin1.bwie.com.week3_lian2.fragment.Fragmentone;
import ningjiaxin1.bwie.com.week3_lian2.fragment.Fragmentthree;
import ningjiaxin1.bwie.com.week3_lian2.fragment.Fragmenttwo;

public class ShowActivity extends AppCompatActivity {
    private RadioGroup group;
    private ViewPager pager;
    private String pid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        pager = findViewById(R.id.viewpager);
        Intent intent = getIntent();
        String pid = intent.getStringExtra("pid");
        final ArrayList<Fragment> list = new ArrayList<>();
        list.add(new Fragmentone());
        list.add(new Fragmenttwo());
        list.add(new Fragmentthree());
        pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return list.get(i);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });

    }
    public String getData(){
        return pid;
    }
    public void getLayout(int posion){
        pager.setCurrentItem(posion);
    }
}

