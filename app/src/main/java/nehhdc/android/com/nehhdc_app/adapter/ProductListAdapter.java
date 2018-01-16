package nehhdc.android.com.nehhdc_app.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import nehhdc.android.com.nehhdc_app.ProductGalleryActivity;
import nehhdc.android.com.nehhdc_app.R;
import nehhdc.android.com.nehhdc_app.data.ProductItem;

/**
 * Created by rohit_roychowdhury on 15/01/18.
 */

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductItemViewHolder> {
    private ArrayList<ProductItem> mProductArrayList;
    private Context mContext;
    private ProductItemOnClickHandler mClickHandler;
    private StorageReference storageReference;

    public interface ProductItemOnClickHandler {
        void onClick(ProductItem productItem);
    }
    public ProductListAdapter(ProductGalleryActivity activity) {
        mContext = activity.getContext();
        mClickHandler = activity;
        storageReference=activity.storageReference;
    }
    class ProductItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.product_fav_ib)
        ImageButton productFavImageButton;
        @BindView(R.id.product_add_cart_ib)
        ImageButton productAddCartImageButton;
        @BindView(R.id.product_item_iv)
        ImageView productImageView;
        @BindView(R.id.product_name_tv)
        TextView productTextView;
        private ProductItemViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }

        @Override
        public void onClick(View view) {
            int adapterPostion = getAdapterPosition();
            ProductItem item = mProductArrayList.get(adapterPostion);

            mClickHandler.onClick(item);
        }
    }

    @Override
    public ProductItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForGridItem = R.layout.product_item_layout;
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutIdForGridItem, parent, false);
        return new ProductItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductItemViewHolder holder, int position) {
        ProductItem productItem = mProductArrayList.get(position);
        storageReference = storageReference.child(productItem.getItemName());
        Glide.with(mContext)
                .using(new FirebaseImageLoader())
                .load(storageReference)
                .into(holder.productImageView);
    }

    @Override
    public int getItemCount() {
        if(mProductArrayList==null) {return 0;}
        return mProductArrayList.size();
    }
    public void setProductArrayList(ArrayList<ProductItem> productArrayList){
        mProductArrayList=productArrayList;
        notifyDataSetChanged();
    }
}
