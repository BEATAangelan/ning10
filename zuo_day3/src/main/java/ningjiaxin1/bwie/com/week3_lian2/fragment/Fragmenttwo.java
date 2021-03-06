package ningjiaxin1.bwie.com.week3_lian2.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import ningjiaxin1.bwie.com.week3_lian2.R;
import ningjiaxin1.bwie.com.week3_lian2.bean.Chuan;

public class Fragmenttwo extends Fragment {
    private String data;
    TextView text;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmenttwo, container, false);
        return view;

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getName(Chuan chuan) {
        if (chuan.getFlag().equals("XQname")) {
            data = (String) chuan.getData();
            Toast.makeText(getActivity(), data, Toast.LENGTH_SHORT).show();
            text.setText(data);
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
