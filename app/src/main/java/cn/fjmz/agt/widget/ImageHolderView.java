package cn.fjmz.agt.widget;

import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;

import cn.fjmz.agt.R;

public class ImageHolderView extends Holder<Integer> {

    private ImageView mImageView;

    public ImageHolderView(View itemView) {
        super(itemView);
    }

    @Override
    protected void initView(View itemView) {
        mImageView = itemView.findViewById(R.id.iv_banner_item_image);
    }

    @Override
    public void updateUI(Integer data) {
        mImageView.setImageResource(data);
    }

}