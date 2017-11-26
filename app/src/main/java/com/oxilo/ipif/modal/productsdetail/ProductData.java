package com.oxilo.ipif.modal.productsdetail;

/**
 * Created by nikk on 22/11/17.
 */

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "item_code",
        "name",
        "short_description",
        "long_description",
        "sprice",
        "gender",
        "price",
        "p_condition",
        "product_type",
        "addition_image",
        "size_type",
        "label",
        "gtin",
        "category_name",
        "brand_name",
        "color",
        "image_url"
})
public class ProductData implements Parcelable
{

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
    @JsonProperty("sprice")
    private String sprice;
    @JsonProperty("gender")
    private String gender;
    @JsonProperty("price")
    private String price;
    @JsonProperty("p_condition")
    private String pCondition;
    @JsonProperty("product_type")
    private String productType;
    @JsonProperty("addition_image")
    private List<AdditionImage> additionImage = null;
    @JsonProperty("size_type")
    private String sizeType;
    @JsonProperty("label")
    private String label;
    @JsonProperty("gtin")
    private String gtin;
    @JsonProperty("category_name")
    private String categoryName;
    @JsonProperty("brand_name")
    private String brandName;
    @JsonProperty("color")
    private List<String> color = null;
    @JsonProperty("image_url")
    private String imageUrl;
    public final static Parcelable.Creator<ProductData> CREATOR = new Creator<ProductData>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ProductData createFromParcel(Parcel in) {
            return new ProductData(in);
        }

        public ProductData[] newArray(int size) {
            return (new ProductData[size]);
        }

    }
            ;

    protected ProductData(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.itemCode = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.shortDescription = ((String) in.readValue((String.class.getClassLoader())));
        this.longDescription = ((String) in.readValue((String.class.getClassLoader())));
        this.sprice = ((String) in.readValue((String.class.getClassLoader())));
        this.gender = ((String) in.readValue((String.class.getClassLoader())));
        this.price = ((String) in.readValue((String.class.getClassLoader())));
        this.pCondition = ((String) in.readValue((String.class.getClassLoader())));
        this.productType = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.additionImage, (AdditionImage.class.getClassLoader()));
        this.sizeType = ((String) in.readValue((String.class.getClassLoader())));
        this.label = ((String) in.readValue((String.class.getClassLoader())));
        this.gtin = ((String) in.readValue((String.class.getClassLoader())));
        this.categoryName = ((String) in.readValue((String.class.getClassLoader())));
        this.brandName = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.color, (java.lang.String.class.getClassLoader()));
        this.imageUrl = ((String) in.readValue((String.class.getClassLoader())));
    }

    public ProductData() {
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

    @JsonProperty("sprice")
    public String getSprice() {
        return sprice;
    }

    @JsonProperty("sprice")
    public void setSprice(String sprice) {
        this.sprice = sprice;
    }

    @JsonProperty("gender")
    public String getGender() {
        return gender;
    }

    @JsonProperty("gender")
    public void setGender(String gender) {
        this.gender = gender;
    }

    @JsonProperty("price")
    public String getPrice() {
        return price;
    }

    @JsonProperty("price")
    public void setPrice(String price) {
        this.price = price;
    }

    @JsonProperty("p_condition")
    public String getPCondition() {
        return pCondition;
    }

    @JsonProperty("p_condition")
    public void setPCondition(String pCondition) {
        this.pCondition = pCondition;
    }

    @JsonProperty("product_type")
    public String getProductType() {
        return productType;
    }

    @JsonProperty("product_type")
    public void setProductType(String productType) {
        this.productType = productType;
    }

    @JsonProperty("addition_image")
    public List<AdditionImage> getAdditionImage() {
        return additionImage;
    }

    @JsonProperty("addition_image")
    public void setAdditionImage(List<AdditionImage> additionImage) {
        this.additionImage = additionImage;
    }

    @JsonProperty("size_type")
    public String getSizeType() {
        return sizeType;
    }

    @JsonProperty("size_type")
    public void setSizeType(String sizeType) {
        this.sizeType = sizeType;
    }

    @JsonProperty("label")
    public String getLabel() {
        return label;
    }

    @JsonProperty("label")
    public void setLabel(String label) {
        this.label = label;
    }

    @JsonProperty("gtin")
    public String getGtin() {
        return gtin;
    }

    @JsonProperty("gtin")
    public void setGtin(String gtin) {
        this.gtin = gtin;
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

    @JsonProperty("color")
    public List<String> getColor() {
        return color;
    }

    @JsonProperty("color")
    public void setColor(List<String> color) {
        this.color = color;
    }

    @JsonProperty("image_url")
    public String getImageUrl() {
        return imageUrl;
    }

    @JsonProperty("image_url")
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(itemCode);
        dest.writeValue(name);
        dest.writeValue(shortDescription);
        dest.writeValue(longDescription);
        dest.writeValue(sprice);
        dest.writeValue(gender);
        dest.writeValue(price);
        dest.writeValue(pCondition);
        dest.writeValue(productType);
        dest.writeList(additionImage);
        dest.writeValue(sizeType);
        dest.writeValue(label);
        dest.writeValue(gtin);
        dest.writeValue(categoryName);
        dest.writeValue(brandName);
        dest.writeList(color);
        dest.writeValue(imageUrl);
    }

    public int describeContents() {
        return 0;
    }

}