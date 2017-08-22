package com.jiyun.qcloud.dashixummoban.main;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jiyun.qcloud.dashixummoban.R;
import com.jiyun.qcloud.dashixummoban.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class HostoryActivity extends BaseActivity implements View.OnClickListener {
    public static final String EXTRA_KEY_TYPE = "extra_key_type";
    public static final String EXTRA_KEY_KEYWORD = "extra_key_keyword";
    public static final String KEY_SEARCH_HISTORY_KEYWORD = "key_search_history_keyword";
    private EditText mKeywordEt;
    private TextView mOperationTv;
    private String mType;

    private ArrayAdapter<String> mArrAdapter;
    private SharedPreferences mPref;

    private LinearLayout mSearchHistoryLl;
    private List<String> mHistoryKeywords;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mOperationTv = (TextView) this.findViewById(R.id.quxiao);
        mKeywordEt = (EditText) this.findViewById(R.id.ss_editText);

        setView();
    }

    private void setView() {
        mPref = getSharedPreferences("test", Activity.MODE_PRIVATE);

        mType = getIntent().getStringExtra(EXTRA_KEY_TYPE);
        String keyword = getIntent().getStringExtra(EXTRA_KEY_KEYWORD);
        if (!TextUtils.isEmpty(keyword)) {
            mKeywordEt.setText(keyword);
        }
        mHistoryKeywords = new ArrayList<String>();

        final ImageView clearKeywordIv = (ImageView) findViewById(R.id.clear_keyword_iv);
        clearKeywordIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mKeywordEt.setText("");
                v.setVisibility(View.GONE);
            }
        });

        mKeywordEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    return true;
                }
                return false;
            }
        });
        mKeywordEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    mOperationTv.setText("搜索");
                    clearKeywordIv.setVisibility(View.GONE);
                    if (mHistoryKeywords.size() > 0) {
                        mSearchHistoryLl.setVisibility(View.VISIBLE);
                    } else {
                        mSearchHistoryLl.setVisibility(View.GONE);
                    }
                } else {
                    mSearchHistoryLl.setVisibility(View.GONE);
                    mOperationTv.setText("搜索");
                    clearKeywordIv.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mKeywordEt.requestFocus();
        mOperationTv.setText("搜索");
        mOperationTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mKeywordEt.getText().length() > 0) {
                    save();
                } else {
                    finish();
                }
            }
        });
        initSearchHistory();
    }

    public void initSearchHistory() {
        mSearchHistoryLl = (LinearLayout) findViewById(R.id.search_history_ll);
        ListView listView = (ListView) findViewById(R.id.search_history_lv);
        findViewById(R.id.clear_history_btn).setOnClickListener(this);
        String history = mPref.getString(KEY_SEARCH_HISTORY_KEYWORD, "");
        if (!TextUtils.isEmpty(history)) {
            List<String> list = new ArrayList<String>();
            for (Object o : history.split(",")) {
                list.add((String) o);
            }
            mHistoryKeywords = list;
        }
        if (mHistoryKeywords.size() > 0) {
            mSearchHistoryLl.setVisibility(View.VISIBLE);
        } else {
            mSearchHistoryLl.setVisibility(View.GONE);
        }
        mArrAdapter = new ArrayAdapter<String>(this, R.layout.item_search_history, mHistoryKeywords);
        listView.setAdapter(mArrAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mKeywordEt.setText(mHistoryKeywords.get(i));
                mSearchHistoryLl.setVisibility(View.GONE);
            }
        });
        mArrAdapter.notifyDataSetChanged();
    }

    public void save() {
        String text = mKeywordEt.getText().toString();
        String oldText = mPref.getString(KEY_SEARCH_HISTORY_KEYWORD, "");
        if (!TextUtils.isEmpty(text) && !oldText.contains(text)) {
            if (mHistoryKeywords.size() > 4) {
                Toast.makeText(this, "最多保存5条历史", Toast.LENGTH_SHORT).show();
                return;
            }
            mPref.edit().putString(KEY_SEARCH_HISTORY_KEYWORD, text + "," + oldText);
            mHistoryKeywords.add(0, text);
        }
        mArrAdapter.notifyDataSetChanged();
    }

    public void cleanHistory() {
        mPref.edit().clear();
        mHistoryKeywords.clear();
        mArrAdapter.notifyDataSetChanged();
        mSearchHistoryLl.setVisibility(View.GONE);
        Toast.makeText(this, "清楚搜索历史成功", Toast.LENGTH_SHORT);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        String type = intent.getStringExtra(EXTRA_KEY_TYPE);
        String keyword = intent.getStringExtra(EXTRA_KEY_KEYWORD);

        if (!TextUtils.isEmpty(keyword)) {
            mKeywordEt.setText(keyword);
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_hostory;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.clear_history_btn:
                cleanHistory();
                break;
        }
    }
}
