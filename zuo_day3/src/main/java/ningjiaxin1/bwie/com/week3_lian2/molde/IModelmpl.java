package ningjiaxin1.bwie.com.week3_lian2.molde;

import java.util.HashMap;
import java.util.Map;

import ningjiaxin1.bwie.com.week3_lian2.untils.MCallBack;
import ningjiaxin1.bwie.com.week3_lian2.untils.OkHttpUntils;

public class IModelmpl implements IModel{
    MCallBack mCallBack;
    @Override
    public void getRequ(String url, Map<String, String> params, Class clazz, MCallBack callBack) {
        mCallBack=callBack;
        OkHttpUntils.getInstance().postRequest(url, new HashMap<String, String>(), clazz, new OkHttpUntils.OkCallBack() {
            @Override
            public void onSuccess(Object o) {
                mCallBack.setdata(o);
            }

            @Override
            public void fail(Exception e) {
               mCallBack.setdata(e);
            }
        });
    }
}
