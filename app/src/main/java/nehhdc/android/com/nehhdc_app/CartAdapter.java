package nehhdc.android.com.nehhdc_app;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;

/**
 * Created by soumyajit on 16/1/18.
 */

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartAdapterViewHolder> {
    private Context mContext;
    //private ArrayList<Customer> customerArrayList;

    public CartAdapter(Context context) {
        mContext = context;
        //clickItemHandler = reviewOnClickItemHandler;
    }

    @Override
    public CartAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int itemId = R.layout.cart_item;
        return new CartAdapterViewHolder(LayoutInflater.from(context).inflate(itemId, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(CartAdapterViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class CartAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.imageView_cart_item)
        ImageView imageView;
        @BindView(R.id.textView_name_cart_item)
        TextView nameTextView;
        @BindView(R.id.textView_quantity_cart_item);
        TextView cartItem;
        @BindView(R.id.textView_tax_cart_price)
        public CartAdapterViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
