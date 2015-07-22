package com.example.administrator.medical.ui;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.medical.R;
import com.example.administrator.medical.http.NewsHttpManager;
import com.example.administrator.medical.http.NewsResultListener;
import com.example.administrator.medical.imageloader.NewImageDisplay;
import com.example.administrator.medical.pojo.NewsDecriptionPojo;
import com.example.administrator.medical.pojo.NewsListPolo;
import com.example.administrator.medical.utils.DataUtils;

/**
 * Created by Administrator on 2015/7/21.
 */
public class NewsDescriptionActivity extends Activity implements Handler.Callback{

    public static final String DETAIL = "detail";
    public static final int LOAD_SUCCES = 101;
    public static final int LOAD_FAILED = 102;

    private TextView mTitleView;
    private TextView mContent;
    private TextView mTime;
    private ImageView mImage;
    private NewsDecriptionPojo mDetail;
    private String titleId;

    private Handler handler = new Handler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        titleId = getIntent().getStringExtra(DETAIL);
        onDescriptionView();
    }

    protected void onDescriptionView(){
        mTitleView = (TextView) findViewById(R.id.description_title);
        mContent = (TextView) findViewById(R.id.description_content);
        mImage = (ImageView) findViewById(R.id.description_image);
        mTime = (TextView) findViewById(R.id.description_time);
        mContent.setMovementMethod(LinkMovementMethod.getInstance());
        loadDetail();
    }

    private void loadDetail(){
        NewsHttpManager.getInstance(this).getNewsDetail(titleId, new NewsResultListener() {
            @Override
            public void onResult(boolean succes, Object result) {
                handler.obtainMessage(succes ? LOAD_SUCCES:LOAD_FAILED, result).sendToTarget();
            }
        });
    }

    @Override
    public boolean handleMessage(Message msg) {
       boolean handle = true;
        switch (msg.what){
            case LOAD_SUCCES:
                mDetail = (NewsDecriptionPojo) msg.obj;
                if (mDetail != null) {
                    String html = "<html><body>" + mDetail.getMessage() + "</body></html>";
                    mContent.setText(Html.fromHtml(html, null, null));
                    mTitleView.setText(mDetail.getTitle().trim());
                    mTime.setText(DataUtils.dateFormat(mDetail.getTime()));
                    NewImageDisplay.getInstance(this).loadImageUrl(mDetail.getImg(), mImage);
                }
                break;
            case LOAD_FAILED:
                break;
        }
        return handle;
    }
}
