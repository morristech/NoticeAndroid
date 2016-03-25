package lin.jiang.notice;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
//        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
//        collapsingToolbarLayout.setStatusBarScrimColor(Color.YELLOW);
//        collapsingToolbarLayout.setExpandedTitleMargin(0,0,0,0);
//        collapsingToolbarLayout.setTitle("撒欸和地方撒回复啊是飞机撒地方奥iuoiwewejfk为附件文件发来我");

        Retrofit retrofit = new Retrofit.Builder()
                // 可选：对Rxjava的适配
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .callbackExecutor()
//                .callFactory()
//                .client()
//                .validateEagerly()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("")
                .build();
        ApiService service = retrofit.create(ApiService.class);
        OBservice oBservice = retrofit.create(OBservice.class);
        Call<User> call = service.load();
//        call.execute();// 同步
//        call.enqueue(callback);// 异步
        Observable<User> observable = oBservice.load();
//        observable.observeOn().compose().map().ambWith().subscribe();// rx链
    }
    public interface ApiService {
        @POST(User.TAG)
        Call<User> load();
        @GET("")
        Call<User> get();
    }
    public interface OBservice {
        Observable<User> load();
    }
    public class User{
        public static final String TAG = "";
        public String name;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
