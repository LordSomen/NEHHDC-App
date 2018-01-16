package nehhdc.android.com.nehhdc_app.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by rohit_roychowdhury on 16/01/18.
 */

public class ProductItem implements Parcelable {
    public final String mItemName;
    public final boolean mLiked;
    public final boolean mAddedToCart;
    public final int mPrice;
    public static final String[] bagListNames= {"bag1.jpg","bag2.jpg","bag3.jpg","bag4.jpg","bag5.jpg","bag6.jpg"};
    public static final String[] furnitureListNames={"hcf0001.jpg","hcf0003.jpg","hcf0008.jpg","hcf0011.jpg","hcf0012.jpg","hcf0014.jpg"};
    public static final String[] jewelleryListNames={"orn001.jpg","orn003.jpg","orn006.jpg","orn008.jpg","orn009.jpg","orn011.jpg"};
    public static final String[] othersListNames={"hcp0001.jpg"," hcp0002.jpg","hcp0006.jpg","hcp0011.jpg","hcp0020.jpg","hcp0039.jpg"};
    public static final String[] pillowListNames={"dec0001.jpg","dec0002.jpg","dec0003.jpg"," dec0004.jpg","dec0005.jpg","dec0006.jpg"};
    public static final String[] sareeListNames={"hl0007.jpg"," hl0008.jpg","hl0010.jpg","hl0012.jpg","hl0013.jpg","hl0020.jpg"};
    public static final String[] shawlListNames={"hm0010.jpg","hm0013.jpg","hm0015.jpg","hm0023.jpg","hm0026.jpg","hm0029.jpg"};

    public ProductItem(String itemName, Boolean liked, boolean addedToCart, int imageId) {
        mItemName = itemName;
        mLiked = liked;
        mAddedToCart = addedToCart;
        mPrice = imageId;
    }

    private ProductItem(Parcel parcel) {
        mItemName = parcel.readString();
        mLiked = parcel.readByte()!=0;
        mAddedToCart = parcel.readByte()!=0;
        mPrice = parcel.readInt();
    }

    public int getImageId() {
        return mPrice;
    }

    public String getItemName() {
        return mItemName;
    }
    public boolean getLiked() {
        return mLiked;
    }
    public boolean getAddedToCart(){
        return mAddedToCart;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mItemName);
        parcel.writeByte((byte) (mLiked?1:0));
        parcel.writeByte((byte) (mAddedToCart?1:0));
        parcel.writeInt(mPrice);
    }

    @Override
    public int describeContents() {
        return hashCode();
    }

    public static final Parcelable.Creator<ProductItem> CREATOR = new Parcelable.Creator<ProductItem>() {
        @Override
        public ProductItem createFromParcel(Parcel parcel) {
            return new ProductItem(parcel);
        }

        @Override
        public ProductItem[] newArray(int i) {
            return new ProductItem[0];
        }
    };
}
