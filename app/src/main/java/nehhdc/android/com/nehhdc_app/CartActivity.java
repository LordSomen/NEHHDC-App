package nehhdc.android.com.nehhdc_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CartActivity extends AppCompatActivity {

    @BindView(R.id.recyclerview_cart)
    RecyclerView recyclerView;
    @BindView(R.id.textView_subtotal_price_cart)
    TextView subtotalPriceTextView;
    @BindView(R.id.textView_price_total)
    TextView totalPrice;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
       CartAdapter mReviewAdapter = new CartAdapter( this);
        recyclerView.setAdapter(mReviewAdapter);
       // mCustomerData = new ArrayList<>();
        //attachDatabaseListener();

    }


}
