package com.gg.nnr.littlemeng.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gg.nnr.littlemeng.R;
import com.gg.nnr.littlemeng.db.DBManager;
import com.gg.nnr.littlemeng.widget.LabelLayout;


/**
 * Created by Administrator on 2016/12/19 0019.
 */

public class ChangeItemActivity extends AppCompatActivity implements View.OnClickListener {
    ViewGroup.MarginLayoutParams lp;
    LabelLayout myItem;
    LabelLayout allItem;
    DBManager mgr;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_content);
        mgr=new DBManager(this);
        initView();
    }

    private void initView() {
        lp = new ViewGroup.MarginLayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.leftMargin = 5;
        lp.rightMargin = 5;
        lp.topMargin = 5;
        lp.bottomMargin = 5;

        myItem = (LabelLayout) findViewById(R.id.my_item);
        allItem = (LabelLayout) findViewById(R.id.all_item);

        for (String aLableName : mgr.query("1")) {
            TextView view = new TextView(this);
            view.setText(aLableName);
            view.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_file_pressed));
            view.setTag("up");
            myItem.addView(view, lp);
            view.setOnClickListener(this);
        }

        for (String aLableName : mgr.query("0")) {
            TextView view = new TextView(this);
            view.setText(aLableName);
            view.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_file_pressed));
            view.setTag("down");
            allItem.addView(view, lp);
            view.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {
        String txt = ((TextView) view).getText().toString();
        if (view.getTag().equals("up")) {
            myItem.removeView(view);
            myItem.invalidate();

            TextView view1 = new TextView(this);
            view1.setText(txt);
            view1.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_file_pressed));
            view1.setTag("down");
            view1.setOnClickListener(this);

            allItem.addView(view1, lp);
            allItem.invalidate();
            mgr.updateItem(txt,"0");

        } else if (view.getTag().equals("down")) {
            allItem.removeView(view);
            allItem.invalidate();

            TextView view1 = new TextView(this);
            view1.setText(txt);
            view1.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_file_pressed));
            view1.setTag("up");
            view1.setOnClickListener(this);

            myItem.addView(view1, lp);
            myItem.invalidate();
            mgr.updateItem(txt,"1");
        }
    }

    @Override
    public void onBackPressed() {
        setResult(0);
        super.onBackPressed();
    }
}
