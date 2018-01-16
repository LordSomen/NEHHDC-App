package nehhdc.android.com.nehhdc_app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.ToggleButton;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import nehhdc.android.com.nehhdc_app.adapter.ProductListAdapter;
import nehhdc.android.com.nehhdc_app.data.ProductItem;

public class ProductGalleryActivity extends Fragment implements ProductListAdapter.ProductItemOnClickHandler {
    private static final int SPAN_COUNT = 2;
    @BindView(R.id.product_list_rv)
    public RecyclerView productListRecyclerView;
    @BindView(R.id.product_list_progressbar)
    public ProgressBar productListProgressBar;
    private ProductListAdapter mProductListAdapter;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener mChildEventListener;
    private FirebaseStorage mStorage;
    public StorageReference storageReference;
    private ArrayList<String[]> mProductCategoryList;
    private ArrayList<ProductItem> mProductList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                R.layout.product_gallery_layout, container, false);
        ButterKnife.bind(this, rootView);
        mProductCategoryList = new ArrayList<>();
        mProductList = new ArrayList<>();
        mProductListAdapter = new ProductListAdapter(this);

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), SPAN_COUNT);
        productListRecyclerView.setLayoutManager(layoutManager);
        productListRecyclerView.setHasFixedSize(true);
        productListRecyclerView.setAdapter(mProductListAdapter);
        mProductListAdapter.setProductArrayList(mProductList);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference();
        mStorage = FirebaseStorage.getInstance();
        storageReference = mStorage.getReference();

        attachDatabaseListener();
        return rootView;
    }

    private void attachDatabaseListener() {
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
                                                     @Override
                                                     public void onDataChange(DataSnapshot dataSnapshot) {
                                                         productListProgressBar.setVisibility(View.VISIBLE);
                                                         for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                                             for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                                                                 ProductItem productItem = new ProductItem(snapshot1.child("imageURL").toString(), false,
                                                                         false, Integer.parseInt(snapshot1.child("price").toString()));
                                                                 mProductList.add(productItem);
                                                             }
                                                         }
                                                         mProductListAdapter.setProductArrayList(mProductList);
                                                     }


                                                     @Override
                                                     public void onCancelled(DatabaseError databaseError) {

                                                     }
                                                 }
        );
    }

    private void detachDatabaseListener() {
        if (mChildEventListener != null) {
            mDatabaseReference.removeEventListener(mChildEventListener);
            mChildEventListener = null;
        }
    }

    @Override
    public void onClick(ProductItem productItem) {

    }

    @OnClick({R.id.toggle_button_all, R.id.toggle_button_bag, R.id.toggle_button_furniture, R.id.toggle_button_jewellery,
            R.id.toggle_button_others, R.id.toggle_button_pillow, R.id.toggle_button_saree, R.id.toggle_button_shawl})
    public void onClickProductCategory(ToggleButton toggleButton) {
        switch (toggleButton.getId()) {
            case R.id.toggle_button_all:
                if (!mProductCategoryList.contains(ProductItem.bagListNames))
                    mProductCategoryList.add(ProductItem.bagListNames);
                if (!mProductCategoryList.contains(ProductItem.furnitureListNames))
                    mProductCategoryList.add(ProductItem.furnitureListNames);
                if (!mProductCategoryList.contains(ProductItem.jewelleryListNames))
                    mProductCategoryList.add(ProductItem.jewelleryListNames);
                if (!mProductCategoryList.contains(ProductItem.othersListNames))
                    mProductCategoryList.add(ProductItem.othersListNames);
                if (!mProductCategoryList.contains(ProductItem.pillowListNames))
                    mProductCategoryList.add(ProductItem.pillowListNames);
                if (!mProductCategoryList.contains(ProductItem.sareeListNames))
                    mProductCategoryList.add(ProductItem.sareeListNames);
                if (!mProductCategoryList.contains(ProductItem.shawlListNames))
                    mProductCategoryList.add(ProductItem.shawlListNames);
                break;
            case R.id.toggle_button_bag:
                mProductCategoryList.clear();
                mProductCategoryList.add(ProductItem.bagListNames);
                break;
            case R.id.toggle_button_furniture:
                mProductCategoryList.clear();
                mProductCategoryList.add(ProductItem.furnitureListNames);
                break;
            case R.id.toggle_button_jewellery:
                mProductCategoryList.clear();
                mProductCategoryList.add(ProductItem.jewelleryListNames);
                break;
            case R.id.toggle_button_others:
                mProductCategoryList.clear();
                mProductCategoryList.add(ProductItem.othersListNames);
                break;
            case R.id.toggle_button_pillow:
                mProductCategoryList.clear();
                mProductCategoryList.add(ProductItem.pillowListNames);
                break;
            case R.id.toggle_button_saree:
                mProductCategoryList.clear();
                mProductCategoryList.add(ProductItem.sareeListNames);
                break;
            case R.id.toggle_button_shawl:
                mProductCategoryList.clear();
                mProductCategoryList.add(ProductItem.shawlListNames);
                break;
        }
    }
}
