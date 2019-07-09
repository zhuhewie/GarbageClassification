package com.zz.garbageclassification.constomView;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.zz.garbageclassification.R;

/**
 * <p> 文件描述 : <p>
 * <p> 作者 : zhuhewie <p>
 * <p> 创建时间 : 2019/3/29 <p>
 * <p> 更改时间 : 2019/3/29 <p>
 * <p> 版本号 : 1 <p>
 */
public class TitleView extends FrameLayout {


    private TextView mTitle;
    private TextView mRightFunction;
    private TextView mBackText;
    private ImageView mImgBack;
    private ImageView mIimgFunIc;

    private LinearLayout mLeftView;

    private OnRightClickListener onRightClickListener;


    private String titleName;
    private String rightText;
    private Boolean showRight = true;

    public TitleView(Context context) {
        super(context);
//        this(context,null);
        initView();
    }

    public TitleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.title);
        try {
            titleName = typedArray.getString(R.styleable.title_title);
            rightText = typedArray.getString(R.styleable.title_right_text);
            showRight = typedArray.getBoolean(R.styleable.title_show_right, true);
        } finally {
            typedArray.recycle();
        }

        initView();
    }

    public TitleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
        this(context,attrs);
    }

    public void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_back_layout, this);
        mTitle = findViewById(R.id.titleName);
        mRightFunction = findViewById(R.id.tvRight);
        mBackText = findViewById(R.id.tvBack);
        mImgBack = findViewById(R.id.imgBack);
        mIimgFunIc = findViewById(R.id.imgFunIc);
        mLeftView = findViewById(R.id.llLeftBackView);

        if (!TextUtils.isEmpty(titleName)) {
            mTitle.setText(titleName);
        }

        showRightView();


        /**
         * 左边默认返回点击事件
         */
        mLeftView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) getContext()).finish();
            }
        });

        mRightFunction.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onRightClickListener != null) {
                    onRightClickListener.onClick(v);
                }
            }
        });

        mTitle.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mIimgFunIc.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void showRightView() {
        if (showRight) {
            mRightFunction.setVisibility(VISIBLE);
            if (!TextUtils.isEmpty(rightText)) {
                mRightFunction.setText(rightText);
            }
        } else {
            mRightFunction.setVisibility(GONE);
        }
    }

    public void setShowRight(Boolean showRight){
        this.showRight = showRight;
        showRightView();
    }

    /**
     * 设置title文字
     *
     * @param titleName
     */
    public void setTitleName(String titleName) {
        if (TextUtils.isEmpty(titleName)) return;
        mTitle.setText(titleName);
    }

    /**
     * 设置右侧文字
     *
     * @param rightText
     */
    public void setRightText(String rightText) {
        if (TextUtils.isEmpty(rightText)) return;
        mRightFunction.setText(rightText);
    }

    /**
     * 右侧文字点击事件
     *
     * @param onRightClickListener
     */
    public void setOnRightClickListener(OnRightClickListener onRightClickListener) {
        if (onRightClickListener == null) return;
        this.onRightClickListener = onRightClickListener;
    }

    public interface OnRightClickListener {
        void onClick(View views);
    }
}
