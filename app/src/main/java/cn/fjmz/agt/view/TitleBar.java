package cn.fjmz.agt.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.lang.reflect.Method;

import cn.fjmz.agt.R;

public class TitleBar extends RelativeLayout {
    private Context mContext;
    private ImageView mBtLeft;
    private ImageView mBtRight;
    private TextView mTvTitle;
    private String rightOnClick;
    private OnClickListener mOnClickListener;

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        if (isInEditMode()) {
            return;
        }
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater != null) {
            inflater.inflate(R.layout.title_bar, this);
        }
        mBtLeft = findViewById(R.id.left_button);
        mBtRight = findViewById(R.id.right_button);
        mTvTitle = findViewById(R.id.title);
        RelativeLayout mRelativeLayout = findViewById(R.id.rl_title_bar);
        TextView mTvRightTitle = findViewById(R.id.right_title);
        @SuppressLint("CustomViewStyleable") TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.MyTabItem);
        String myText = a.getString(R.styleable.MyTabItem_title_oa);
        String right_title = a.getString(R.styleable.MyTabItem_right_title);
        boolean leftEnable = a.getBoolean(R.styleable.MyTabItem_left_enable,
                false);
        boolean rightEnable = a.getBoolean(R.styleable.MyTabItem_right_enable,
                false);
        int rightID = a.getResourceId(R.styleable.MyTabItem_right_icon, -1);
        int leftIcon = a.getResourceId(R.styleable.MyTabItem_left_icon, -1);
        int titleColor = a.getResourceId(R.styleable.MyTabItem_title_color, -1);
        int backgroundId = a.getResourceId(R.styleable.MyTabItem_background_oa, -1);
        rightOnClick = a.getString(R.styleable.MyTabItem_right_onclick);
        a.recycle();
        if (!TextUtils.isEmpty(myText)) {
            mTvTitle.setText(myText);
        }
        if (!TextUtils.isEmpty(right_title)) {
            mTvRightTitle.setVisibility(VISIBLE);
            mTvRightTitle.setText(right_title);
            mTvRightTitle.setOnClickListener(view -> mOnClickListener.onClick(view));
        }
        mTvTitle.setSelected(true);
        mBtLeft.setVisibility(leftEnable ? View.VISIBLE : View.INVISIBLE);
        mBtRight.setVisibility(rightEnable ? View.VISIBLE : View.INVISIBLE);
        mBtLeft.setOnClickListener(mOnLeftButtonPressed);
        if (rightID != -1)
            mBtRight.setImageResource(rightID);
        if (rightOnClick != null) {
            mBtRight.setOnClickListener(mOnBtRightButtonPressed);
        }
        if (backgroundId != -1) {
            mRelativeLayout.setBackgroundColor(ContextCompat.getColor(mContext, backgroundId));
        }
        if (titleColor != -1) {
            mTvTitle.setTextColor(ContextCompat.getColor(mContext, titleColor));
        }
        if (leftIcon != -1) {
            mBtLeft.setImageResource(leftIcon);
        }
    }

    public void setOnLeftButtonPressed(OnClickListener listener) {
        if (mBtLeft == null || listener == null) {
            return;
        }
        mBtLeft.setOnClickListener(listener);
    }


    public void setOnRightButtonPressed(OnClickListener listener) {
        if (mBtRight == null || listener == null) {
            return;
        }
        mBtRight.setOnClickListener(listener);
    }

    public OnClickListener mOnLeftButtonPressed = new OnClickListener() {

        @Override
        public void onClick(View v) {
            Activity activity = (Activity) mContext;
            activity.finish();
        }
    };
    public OnClickListener mOnBtRightButtonPressed = new OnClickListener() {

        @Override
        public void onClick(View v) {

            try {
                Method mHandler = getContext().getClass().getMethod(
                        rightOnClick, View.class);
                mHandler.invoke(getContext(), mBtRight);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    public void setBtRightClickListener(OnClickListener mOnClickListener) {
        mBtRight.setOnClickListener(mOnClickListener);
    }

    public void setRightTitleClickListener(OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }


    public void setTitle(String text) {
        mTvTitle.setText(text);
    }

    public void setShowBtLeft(boolean isShow) {
        mBtLeft.setVisibility(isShow ? View.VISIBLE : View.INVISIBLE);
    }

    public void setLeftImage(int res) {
        mBtLeft.setImageResource(res);
    }

    public void setRightImage(int res) {
        mBtRight.setImageResource(res);
    }

    public void setLeftClickEnable(boolean isLeftClickable) {
        mBtLeft.setClickable(isLeftClickable);
    }

}
