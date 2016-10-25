package com.shellever.popupwindow;

import android.annotation.SuppressLint;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mCenterPopupWindowBtn;
    private Button mBottomPopupWindowBtn;
    private Button mPositionPopupWindowBtn;

    private PopupWindow mPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCenterPopupWindowBtn = (Button) findViewById(R.id.btn_center_pw);
        mBottomPopupWindowBtn = (Button) findViewById(R.id.btn_bottom_pw);
        mPositionPopupWindowBtn = (Button) findViewById(R.id.btn_position_pw);

        mCenterPopupWindowBtn.setOnClickListener(this);
        mBottomPopupWindowBtn.setOnClickListener(this);
        mPositionPopupWindowBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_center_pw:
                showCenterPopupWindow();
                break;
            case R.id.btn_bottom_pw:
                showBottomPopupWindow();
                break;
            case R.id.btn_position_pw:
                showPositionPopupWindow();
                break;
            case R.id.btn_open:
                Toast.makeText(MainActivity.this, "Open", Toast.LENGTH_SHORT).show();
                mPopupWindow.dismiss();
                break;
            case R.id.btn_save_as:
                Toast.makeText(MainActivity.this, "Save as", Toast.LENGTH_SHORT).show();
                mPopupWindow.dismiss();
                break;
            case R.id.btn_close:
                Toast.makeText(MainActivity.this, "Close", Toast.LENGTH_SHORT).show();
                mPopupWindow.dismiss();
                break;
            case R.id.btn_edit:
                Toast.makeText(MainActivity.this, "Edit", Toast.LENGTH_SHORT).show();
                mPopupWindow.dismiss();
                break;
            case R.id.btn_cancel:
                Toast.makeText(MainActivity.this, "Cancel", Toast.LENGTH_SHORT).show();
                mPopupWindow.dismiss();
                break;
            case R.id.btn_share:
                Toast.makeText(MainActivity.this, "Share", Toast.LENGTH_SHORT).show();
                mPopupWindow.dismiss();
                break;
            case R.id.btn_collect:
                Toast.makeText(MainActivity.this, "Collect", Toast.LENGTH_SHORT).show();
                mPopupWindow.dismiss();
                break;
            case R.id.btn_exit:
                Toast.makeText(MainActivity.this, "Exit", Toast.LENGTH_SHORT).show();
                mPopupWindow.dismiss();
                break;
        }
    }

    @SuppressLint("InflateParams")
    private void showCenterPopupWindow() {
        View view = getLayoutInflater().inflate(R.layout.layout_popupwindow_center, null);
        Button mEditBtn = (Button) view.findViewById(R.id.btn_edit);
        Button mCancelBtn = (Button) view.findViewById(R.id.btn_cancel);
        mEditBtn.setOnClickListener(this);
        mCancelBtn.setOnClickListener(this);

        mPopupWindow = new PopupWindow(view);       // 点击外部区域,弹窗不消失

        // 为PopupWindow菜单添加阴影时，其宽度和高度必须都设置为MATCH_PARENT
        mPopupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        mPopupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);

        ColorDrawable mColorDrawable = new ColorDrawable(0);
        mPopupWindow.setBackgroundDrawable(mColorDrawable);

        mPopupWindow.setAnimationStyle(R.style.contextMenuAnim);    // 自定义动画样式
        // mPopupWindow.setAnimationStyle(android.R.style.Animation_Translucent);

        mPopupWindow.getBackground().setAlpha(100);
        mPopupWindow.setOutsideTouchable(true);         // 点击PopupWindow以外区域，自动消失，这里PopupWindow为MATCH_PARENT，故无效
        mPopupWindow.setFocusable(true);                // 如果PopupWindow中有Editor的话，focusable要为true
        mPopupWindow.setTouchable(true);                //
        mPopupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        View rootView = LayoutInflater.from(MainActivity.this).inflate(R.layout.activity_main, null);
        mPopupWindow.showAtLocation(rootView, Gravity.CENTER, 0, 0);
    }

    @SuppressLint("InflateParams")
    private void showBottomPopupWindow() {
        setBackgroundAlpha(0.5f);               // 设置背景透明度

        View view = getLayoutInflater().inflate(R.layout.layout_popupwindow_bottom, null);
        Button mOpenBtn = (Button) view.findViewById(R.id.btn_open);
        Button mSaveAsBtn = (Button) view.findViewById(R.id.btn_save_as);
        Button mCloseBtn = (Button) view.findViewById(R.id.btn_close);
        mOpenBtn.setOnClickListener(this);
        mSaveAsBtn.setOnClickListener(this);
        mCloseBtn.setOnClickListener(this);

        // PopupWindow会丢弃掉最外层的父局部，故第二个LinearLayout才会起作用
        mPopupWindow = new PopupWindow(view);
        mPopupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        mPopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        // 背景
        ColorDrawable cd = new ColorDrawable(0);
        mPopupWindow.setBackgroundDrawable(cd);

        mPopupWindow.setAnimationStyle(android.R.style.Animation_Translucent);

        View rootView = LayoutInflater.from(MainActivity.this).inflate(R.layout.activity_main, null);
        mPopupWindow.showAtLocation(rootView, Gravity.BOTTOM, 0, 0);    // showAtLocation()显示在指定位置
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setBackgroundAlpha(1.0f);       // 恢复背景透明度
            }
        });
    }

    @SuppressLint("InflateParams")
    private void showPositionPopupWindow() {
        View view = getLayoutInflater().inflate(R.layout.layout_popupwindow_position, null);
        Button mShareBtn = (Button) view.findViewById(R.id.btn_share);
        Button mCollectBtn = (Button) view.findViewById(R.id.btn_collect);
        Button mExitBtn = (Button) view.findViewById(R.id.btn_exit);
        mShareBtn.setOnClickListener(this);
        mCollectBtn.setOnClickListener(this);
        mExitBtn.setOnClickListener(this);

        mPopupWindow = new PopupWindow(view);
        mPopupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        mPopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        ColorDrawable cd = new ColorDrawable(0);
        mPopupWindow.setBackgroundDrawable(cd);

        mPopupWindow.setAnimationStyle(android.R.style.Animation_Translucent);

        mPopupWindow.showAsDropDown(mPositionPopupWindowBtn);   // showAsDropDown()显示在一个参照视图View的周围
    }

    // 设置屏幕背景透明度
    private void setBackgroundAlpha(float alpha){
        WindowManager.LayoutParams mLayoutParams = getWindow().getAttributes();
        mLayoutParams.alpha = alpha;
        getWindow().setAttributes(mLayoutParams);
    }
}
