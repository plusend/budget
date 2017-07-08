package com.plusend.budget.activity;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.plusend.budget.R;
import com.plusend.budget.detail.JsonDetailUtil;
import com.plusend.budget.fragment.DatePickerFragment;
import com.plusend.budget.model.Detail;
import com.plusend.budget.util.DateUtil;

import java.util.Calendar;

public class DetailActivity extends AppCompatActivity implements DatePickerFragment.OnFragmentInteractionListener {
    private static final String TAG = "DetailActivity";
    public static final String DETAIL = "detail";
    private TextInputLayout numTIL;
    private TextInputLayout nameTIL;
    private TextInputLayout dateTIL;
    private TextInputLayout remarkTIL;

    private String mDetailId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        numTIL = (TextInputLayout) findViewById(R.id.num_til);
        nameTIL = (TextInputLayout) findViewById(R.id.name_til);
        dateTIL = (TextInputLayout) findViewById(R.id.date_til);
        remarkTIL = (TextInputLayout) findViewById(R.id.remark_til);

        dateTIL.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dateString = dateTIL.getEditText().getText().toString();
                long dateLong = DateUtil.stringToDate(dateString);
                DialogFragment newFragment = DatePickerFragment.newInstance(dateLong);
                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });


        Detail detail = getIntent().getParcelableExtra(DETAIL);
        if (detail != null) {
            mDetailId = detail.getId();
            showDetail(detail);
        } else {
            Calendar calendar = Calendar.getInstance();
            String dateString = DateUtil.dateToString(calendar.getTimeInMillis());
            dateTIL.getEditText().setText(dateString);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.detail, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save:
                if (TextUtils.isEmpty(mDetailId)) {
                    saveDetail(getDetail());
                } else {
                    updateDetail(getDetail());
                }
                break;
            case R.id.delete:
                removeDetail(getDetail());
                break;
            default:
                break;
        }
        finish();
        return super.onOptionsItemSelected(item);
    }

    private void showDetail(Detail detail) {
        numTIL.getEditText().setText(String.valueOf(detail.num));
        String dateString = DateUtil.dateToString(detail.date);
        dateTIL.getEditText().setText(dateString);
        remarkTIL.getEditText().setText(detail.remark);
    }

    private Detail getDetail() {
        //TODO 判断各参数合理性
        String nameV1 = "nameV1";
        String nameV2 = "nameV2";

        String remark = remarkTIL.getEditText().getText().toString();
        int num = Integer.parseInt(numTIL.getEditText().getText().toString());
        String dateString = dateTIL.getEditText().getText().toString();
        long date = DateUtil.stringToDate(dateString);

        Detail detail = mDetailId == null ? new Detail(num, nameV1 + nameV2, date, remark) : new Detail(mDetailId, num, nameV1 + nameV2, date, remark);
        Log.d(TAG, "getDetail: " + detail);
        return detail;
    }

    private void saveDetail(Detail detail) {
        JsonDetailUtil.getInstance(this).saveDetail(detail);
    }

    private void updateDetail(Detail detail) {
        JsonDetailUtil.getInstance(this).updateDetail(detail);
    }

    private void removeDetail(Detail detail) {
        JsonDetailUtil.getInstance(this).removeDetail(detail);
    }

    @Override
    public void onFragmentInteraction(long date) {
        String dateString = DateUtil.dateToString(date);
        dateTIL.getEditText().setText(dateString);
    }
}
