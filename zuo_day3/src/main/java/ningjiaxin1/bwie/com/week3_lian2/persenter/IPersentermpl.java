package ningjiaxin1.bwie.com.week3_lian2.persenter;

import java.util.HashMap;
import java.util.Map;

import ningjiaxin1.bwie.com.week3_lian2.molde.IModelmpl;
import ningjiaxin1.bwie.com.week3_lian2.untils.MCallBack;
import ningjiaxin1.bwie.com.week3_lian2.view.IView;

public class IPersentermpl implements IPersenter {
    IModelmpl iModelmpl;
    IView miview;
    public IPersentermpl(IView iView){
       miview= iView;
       iModelmpl = new IModelmpl();
    }
    @Override
    public void startRequest(String url, Map<String, String> params, Class clazz) {
        iModelmpl.getRequ(url, new HashMap<String, String>(), clazz, new MCallBack() {
            @Override
            public void setdata(Object o) {
                miview.setonSuccess(o);
            }
        });
    }
    //qingchu
    public void dectory(){
        if(miview!=null){
            miview=null;
        }if(iModelmpl!=null){
            iModelmpl=null;
        }
    }
}
