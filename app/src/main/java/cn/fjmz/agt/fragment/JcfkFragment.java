package cn.fjmz.agt.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import cn.fjmz.agt.YhfcInfo;
import cn.fjmz.agt.activity.YhzgUploadActivity;

/**
 *隐患整改反馈
 * Created by Administrator on 2016/4/23.
 */
public class JcfkFragment extends RectificationFragment {

    public static JcfkFragment newInstance() {
        
        Bundle args = new Bundle();
        
        JcfkFragment fragment = new JcfkFragment();
        fragment.setArguments(args);
        return fragment;
    }



    public AdapterView.OnItemClickListener getListItemClick(){
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> pAdapterView, View pView, int pI, long pL) {
                YhfcInfo info = (YhfcInfo) pAdapterView.getItemAtPosition(pI);
                try{
                    YhzgUploadActivity.start(getActivity(),Integer.parseInt(info.getHTroubleID()),info.getSafetyTrouble());
                }catch (NumberFormatException ignored){

                }
            }
        };
    }

    @Override
    public int getReview() {
        return 0;
    }

    @Override
    public int getFinished() {
        return 2;
    }

    @Override
    public boolean isYhzg() {
        return true;
    }

}
