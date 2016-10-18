package cn.edu.gdmec.s07150836.work3;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private TextView tv1;
    private Button but;
    private EditText edi;
    private CheckBox che1,che2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        but= (Button) findViewById(R.id.calculator);
        edi= (EditText) findViewById(R.id.weight);
        che1= (CheckBox) findViewById(R.id.man);
        che2= (CheckBox) findViewById(R.id.woman);
        tv1= (TextView) findViewById(R.id.tv1);
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerEvent();
    }

    private void registerEvent() {
    but.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
             if(!edi.getText().toString().trim().equals("")){
                 if(che1.isChecked()||che2.isChecked()){

                     Double weight=Double.parseDouble(edi.getText().toString());
                     StringBuffer sb=new StringBuffer();
                     sb.append("------评估结果是:-------\n");

                     if(che1.isChecked()){
                         sb.append("男性标准身高是:");
                         double result=evaluateHight(weight,"男");
                         sb.append((int)result+"(厘米)");
                     }

                     if(che2.isChecked()){
                         sb.append("女性标准身高是:");
                         double result=evaluateHight(weight,"女");
                         sb.append((int)result+"(厘米)");
                     }
                        tv1.setText(sb.toString());
                 }else {
                     showMessage("请选择性别！");
                 }
             }else{
                 showMessage("请输入体重！");
             }
        }
    });
    }

    private void showMessage(String s) {
        AlertDialog alert=new AlertDialog.Builder(this).create();
        alert.setTitle("系统信息");
        alert.setMessage(s);
        alert.setButton("确定",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alert.show();
    }

    private double evaluateHight(double weight, String sex) {
        double height;
        if(sex=="男"){
            height=170-(62-weight)/0.6;
        }else{
            height=158-(52-weight)/0.5;
        }
        return height;
    }
    @Override
    public boolean onCreatePanelMenu(int featureId, Menu menu) {
        menu.add(Menu.NONE,1,Menu.NONE,"退出");
        return super.onCreatePanelMenu(featureId, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 1:finish();break;
        }
        return super.onOptionsItemSelected(item);
    }
}
