package com.oxilo.ipif.modal.products;

/**
 * Created by nikk on 19/11/17.
 */

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "vendor_id",
        "id",
        "item_code",
        "name",
        "short_description",
        "long_description",
        "sale_price",
        "price",
        "image_url",
        "category_name",
        "brand_name"
})
public class ProductListings implements Parcelable
{

    @JsonProperty("vendor_id")
    private String vendorId;
    @JsonProperty("id")
    private String id;
    @JsonProperty("item_code")
    private String itemCode;
    @JsonProperty("name")
    private String name;
    @JsonProperty("short_description")
    private String shortDescription;
    @JsonProperty("long_description")
    private String longDescription;
    @JsonProperty("sale_price")
    private String salePrice;
    @JsonProperty("price")
    private String price;
    @JsonProperty("image_url")
    private String imageUrl;
    @JsonProperty("category_name")
    private String categoryName;
    @JsonProperty("brand_name")
    private String brandName;
    public final static Parcelable.Creator<ProductListings> CREATOR = new Creator<ProductListings>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ProductListings createFromParcel(Parcel in) {
            return new ProductListings(in);
        }

        public ProductListings[] newArray(int size) {
            return (new ProductListings[size]);
        }

    }
            ;

    protected ProductListings(Parcel in) {
        this.vendorId = ((String) in.readValue((String.class.getClassLoader())));
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.itemCode = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.shortDescription = ((String) in.readValue((String.class.getClassLoader())));
        this.longDescription = ((String) in.readValue((String.class.getClassLoader())));
        this.salePrice = ((String) in.readValue((String.class.getClassLoader())));
        this.price = ((String) in.readValue((String.class.getClassLoader())));
        this.imageUrl = ((String) in.readValue((String.class.getClassLoader())));
        this.categoryName = ((String) in.readValue((String.class.getClassLoader())));
        this.brandName = ((String) in.readValue((String.class.getClassLoader())));
    }

    public ProductListings() {
    }

    @JsonProperty("vendor_id")
    public String getVendorId() {
        return vendorId;
    }

    @JsonProperty("vendor_id")
    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("item_code")
    public String getItemCode() {
        return itemCode;
    }

    @JsonProperty("item_code")
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("short_description")
    public String getShortDescription() {
        return shortDescription;
    }

    @JsonProperty("short_description")
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    @JsonProperty("long_description")
    public String getLongDescription() {
        return longDescription;
    }

    @JsonProperty("long_description")
    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    @JsonProperty("sale_price")
    public String getSalePrice() {
        return salePrice;
    }

    @JsonProperty("sale_price")
    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    @JsonProperty("price")
    public String getPrice() {
        return price;
    }

    @JsonProperty("price")
    public void setPrice(String price) {
        this.price = price;
    }

    @JsonProperty("image_url")
    public String getImageUrl() {
        return imageUrl;
    }

    @JsonProperty("image_url")
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @JsonProperty("category_name")
    public String getCategoryName() {
        return categoryName;
    }

    @JsonProperty("category_name")
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @JsonProperty("brand_name")
    public String getBrandName() {
        return brandName;
    }

    @JsonProperty("brand_name")
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(vendorId);
        dest.writeValue(id);
        dest.writeValue(itemCode);
        dest.writeValue(name);
        dest.writeValue(shortDescription);
        dest.writeValue(longDescription);
        dest.writeValue(salePrice);
        dest.writeValue(price);
        dest.writeValue(imageUrl);
        dest.writeValue(categoryName);
        dest.writeValue(brandName);
    }

    public int describeContents() {
        return 0;
    }

}
