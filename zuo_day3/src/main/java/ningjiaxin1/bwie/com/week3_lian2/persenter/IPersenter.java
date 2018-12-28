package ningjiaxin1.bwie.com.week3_lian2.persenter;

import java.util.Map;

public interface IPersenter {
    void startRequest(String url, Map<String,String> params,Class clazz);
}
