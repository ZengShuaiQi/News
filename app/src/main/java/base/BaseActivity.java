package base;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by clever boy on 2017/6/27.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        initView();
        initListener();
        initData();
    }

    /**
     * 返回要显示的布局文件
     * @return
     */
    public abstract int getLayoutRes();
    /**
     * 查找布局中的子控件
     * @return
     */
    public abstract void initView();
    /**
     * 设置控件的监听器
     * @return
     */
    public abstract void initListener();
    /**
     * 初始化数据
     * @return
     */
    public abstract void initData();
    private Toast mToast;

    public void showToast(String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
        }
        mToast.setText(msg);
        mToast.show();
    }
}
