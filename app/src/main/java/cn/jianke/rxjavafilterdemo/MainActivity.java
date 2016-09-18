package cn.jianke.rxjavafilterdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.Observer;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * @className: MainActivity
 * @classDescription: Rxjava（过滤篇）
 * @author: leibing
 * @createTime: 2016/09/18
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // onClick
        findViewById(R.id.btn_filter).setOnClickListener(this);
        findViewById(R.id.btn_take).setOnClickListener(this);
        findViewById(R.id.btn_take_last).setOnClickListener(this);
        findViewById(R.id.btn_distinct).setOnClickListener(this);
        findViewById(R.id.btn_skip).setOnClickListener(this);
        findViewById(R.id.btn_skip_last).setOnClickListener(this);
        findViewById(R.id.btn_timeout).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_filter:
                // 过滤内容
                filterObservable();
                break;
            case R.id.btn_take:
                // 筛选出原始数据中前n个数据
                takeObservable();
                break;
            case R.id.btn_take_last:
                // 筛选出原始数据中从倒数前n个数据
                takeLastObservable();
                break;
            case R.id.btn_distinct:
                // 去掉数据中重复值
                distinctObservable();
                break;
            case R.id.btn_skip:
                // 筛选出原始数据中前n个数据跳过不发射
                skipObservable();
                break;
            case R.id.btn_skip_last:
                // 筛选出原始数据中从倒数前n个数据跳过不发射
                skipLastObservable();
                break;
            case R.id.btn_timeout:
                // 在指定的时间间隔内Observable不发射值的话,监听的原始的Observable时就会触发onError()函数
                timeoutObservable();
                break;
            default:
                break;
        }
    }

    /**
     * 筛选符合条件的内容
     * @author leibing
     * @createTime 2016/09/18
     * @lastModify 2016/09/18
     * @param
     * @return
     */
    private void filterObservable(){
        // 使用filter方法过滤内容中不需要的值
        Observable.just("A1","B1","A2","C3").filter(new Func1<String, Boolean>() {
            @Override
            public Boolean call(String s) {
                // 条件筛选以“A”开始的内容
                return s.startsWith("A");
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                // 打印结果
                System.out.println("ddddddddddddddddddd filter s = " + s);
            }
        });
    }

    /**
     * 筛选出原始数据中前n个数据发射
     * @author leibing
     * @createTime 2016/09/18
     * @lastModify 2016/09/18
     * @param
     * @return
     */
    private void takeObservable(){
        // take(n) 提取原数据前n个数据发射
        Observable.just("1","2","3","4","5").take(4).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                // 打印结果
                System.out.println("ddddddddddddddd take s = " + s);
            }
        });
    }

    /**
     * 筛选出原始数据中从倒数前n个数据发射
     * @author leibing
     * @createTime 2016/09/18
     * @lastModify 2016/09/18
     * @param
     * @return
     */
    private void takeLastObservable(){
        // takeLast(n) 提取原数据倒数前n个数据发射
        Observable.just("1","2","3","4","5").takeLast(2).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                // 打印结果
                System.out.println("dddddddddddddddddd takeLast s = " + s);
            }
        });
    }

    /**
     * 去掉数据中重复值
     * @author leibing
     * @createTime 2016/09/18
     * @lastModify 2016/09/18
     * @param
     * @return
     */
    private void distinctObservable(){
        // 将发射数据重复五遍再去重复
        Observable.just("A1","A1","B1","C1","D1","B1","A1").repeat(5).distinct().subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                // 打印结果
                System.out.println("ddddddddddddddddd distinct s = " + s);
            }
        });
    }

    /**
     * 筛选出原始数据中前n个数据跳过不发射
     * @author leibing
     * @createTime 2016/09/18
     * @lastModify 2016/09/18
     * @param
     * @return
     */
    private void skipObservable(){
        // skip(n) 提取原数据前n个数据跳过不发射
        Observable.just("1","2","3","4","5").skip(4).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                // 打印结果
                System.out.println("ddddddddddddddd skip s = " + s);
            }
        });
    }

    /**
     * 筛选出原始数据中从倒数前n个数据跳过不发射
     * @author leibing
     * @createTime 2016/09/18
     * @lastModify 2016/09/18
     * @param
     * @return
     */
    private void skipLastObservable(){
        // takeLast(n) 提取原数据倒数前n个数据跳过不发射
        Observable.just("1","2","3","4","5").skipLast(2).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                // 打印结果
                System.out.println("dddddddddddddddddd skipLast s = " + s);
            }
        });
    }

    /**
     * 在指定的时间间隔内Observable不发射值的话,监听的原始的Observable时就会触发onError()函数
     * @author leibing
     * @createTime 2016/09/18
     * @lastModify 2016/09/18
     * @param
     * @return
     */
    private void timeoutObservable(){
        // 指定4秒后再发射，再指定3秒后如果未发射就触发onError函数
        Observable.just("1","2").timer(4, TimeUnit.SECONDS).timeout(3, TimeUnit.SECONDS).subscribe(
                new Observer<Long>() {
            @Override
            public void onCompleted() {
                System.out.println("dddddddddddddddd timeout onCompleted = ");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("dddddddddddddddd timeout e = " + e.getMessage());
            }

            @Override
            public void onNext(Long aLong) {
                System.out.println("dddddddddddddddd timeout along = " + aLong);
            }
        });
    }
}
