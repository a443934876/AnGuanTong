package cn.fjmz.agt.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.fjmz.agt.R;
import cn.fjmz.agt.bean.MsgEntity;
import com.gongwen.marqueen.MarqueeFactory;

public class ComplexViewMF extends MarqueeFactory<RelativeLayout, MsgEntity> {
    private LayoutInflater inflater;

    public ComplexViewMF(Context mContext) {
        super(mContext);
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public RelativeLayout generateMarqueeItemView(MsgEntity data) {
        @SuppressLint("InflateParams") RelativeLayout mView = (RelativeLayout) inflater.inflate(R.layout.view_complex, null);
//        ((TextView) mView.findViewById(R.id.tv_msg_title)).setText(data.getTitle());
        ((TextView) mView.findViewById(R.id.tv_msg)).setText(data.getMsg());
        return mView;
    }
}
