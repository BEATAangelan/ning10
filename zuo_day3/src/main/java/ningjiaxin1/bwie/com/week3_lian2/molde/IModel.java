package ningjiaxin1.bwie.com.week3_lian2.molde;

import java.util.Map;

import ningjiaxin1.bwie.com.week3_lian2.untils.MCallBack;

public interface IModel {
    void getRequ(String url, Map<String,String> params, Class clazz, MCallBack callBack);
}
