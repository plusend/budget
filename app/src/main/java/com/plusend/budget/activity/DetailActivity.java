package com.plusend.budget.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.plusend.budget.R;
import com.plusend.budget.detail.JsonDetailUtil;
import com.plusend.budget.model.Detail;

import java.util.Calendar;

public class DetailActivity extends AppCompatActivity {
    private static final String TAG = "DetailActivity";
    public static final String DETAIL = "detail";
    private EditText numET;
    private Spinner nameV1Spin, nameV2Spin;
    private DatePicker datePicker;
    private EditText remarkET;

    private String mDetailId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        numET = (EditText) findViewById(R.id.num_et);
        nameV1Spin = (Spinner) findViewById(R.id.name_v1_spinner);
        nameV2Spin = (Spinner) findViewById(R.id.name_v2_spinner);
        datePicker = (DatePicker) findViewById(R.id.datePicker);
        remarkET = (EditText) findViewById(R.id.remark_et);

        Detail detail = getIntent().getParcelableExtra(DETAIL);
        if (detail != null) {
            mDetailId = detail.getId();
            showDetail(detail);
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
                saveDetail();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showDetail(Detail detail) {
        numET.setText(String.valueOf(detail.num));
        //TODO name date
        remarkET.setText(detail.remark);
    }

    private void saveDetail() {
        //TODO 判断各参数合理性
        int num = Integer.parseInt(numET.getText().toString());
//        String nameV1 = nameV1Spin.getSelectedItem().toString();
//        String nameV2 = nameV2Spin.getSelectedItem().toString();
        String nameV1 = "nameV1";
        String nameV2 = "nameV2";

        Calendar calendar = Calendar.getInstance();
        calendar.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
        long date = calendar.getTimeInMillis();
        String remark = remarkET.getText().toString();
        Detail detail = mDetailId == null ? new Detail(num, nameV1 + nameV2, date, remark) : new Detail(mDetailId, num, nameV1 + nameV2, date, remark);
        JsonDetailUtil.getInstance(this).saveDetail(detail);
    }
}
