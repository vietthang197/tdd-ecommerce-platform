package com.tdd.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tdd.core.constant.Constant;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

/*Thuộc tính chung của sản phẩm, sản phẩm này không được hiển thị để bán hoặc là thêm vào giỏ hàng, thêm vào giỏ hàng là sku
* Tham chiếu: https://developer.broadleafcommerce.com/services/catalog-services/data-model/product
* Chi tiết xem resources/json/product.json
* */
@Entity
@Table(name = Constant.TABLE.PRODUCT_TBL, indexes = {
        @Index(name = "TDD_PRODUCT_URI_INDEX", columnList = "URI"),
        @Index(name = "TDD_PRODUCT_CURRENCY_INDEX", columnList = "CURRENCY"),
        @Index(name = "TDD_PRODUCT_NAME_INDEX", columnList = "NAME"),
        @Index(name = "TDD_PRODUCT_ACTIVE_START_DATE_INDEX", columnList = "ACTIVE_START_DATE"),
        @Index(name = "TDD_PRODUCT_ACTIVE_END_DATE_INDEX", columnList = "ACTIVE_END_DATE"),
        @Index(name = "TDD_PRODUCT_PRODUCT_TYPE_INDEX", columnList = "PRODUCT_TYPE"),
        @Index(name = "TDD_PRODUCT_DATA_DRIVEN_ENUM_ID_INDEX", columnList = "DATA_DRIVEN_ENUM_ID"),
})
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product extends SuperEntity implements Serializable {

    @Id
    @Column(name = "PRODUCT_ID")
    @UuidGenerator
    private String productId;

    @Column(name = "ACTIVE_START_DATE", nullable = false)
    private LocalDateTime activeStartDate;

    @Column(name = "ACTIVE_END_DATE")
    private LocalDateTime activeEndDate;

    // Dynamic attribute là một phần của product, admin thêm thông tin phụ lưu trên sản phẩm
    // [{"name":"hotRange", "label":"Độ cay", "isArray": false, "value":"fuckk"}, {"name":"hotRange2", "label":"Độ cay", "isArray": true, "value":"['fuck', 'fuck2']"}]
    @Column(name = "ATTRIBUTES") // format JSON
    private String attributes;

    // Sản phẩm có sẵn cho cửa hàng trực tuyến hay không Y/N
    @Column(name = "AVAILABLE_ONLINE", nullable = false)
    @ColumnDefault("'"+ Constant.STR_Y +"'")
    private String availableOnline;

    // Chi phí sản xuất hoặc chi phí nhập hàng, dùng để tính số tiền lãi
    @Column(name = "COST")
    private BigDecimal cost;

    // Currency of all prices on the product (Kiểu product có giá bán lẻ , giá bán buôn, giá nhập -> dùng chung currency)
    @Column(name = "CURRENCY", nullable = false)
    private String currency;

    // Một product có nhiều sku, mỗi sku nếu không set price thì nó sẽ lấy default_price của product
    @Column(name = "DEFAULT_PRICE")
    private BigDecimal defaultPrice;

    // Giá bán lẻ sản phẩm, cột này chỉ dùng để hiển thị, không nên gắn thêm logic nào với nó
    @Column(name = "MSRP")
    private BigDecimal msrp;

    // cho biết sản phẩm này đang được giảm giá và đang ở mức nào. Nếu salePrice < defaultPrice thì sẽ lấy salePrice
    @Column(name = "SALE_PRICE")
    private BigDecimal salePrice;

    // Mô tả sản phẩm, có thể chứa mã HTMl để display cho KH xem
    @Column(name = "DESCRIPTION")
    @Lob
    private String description;

    // Kích thước chiều sâu của sản phẩm 3 chiều, có liên hệ với dimension unit
    @Column(name = "DEPTH")
    private Double depth;

    // Kích thước chiều cao của sản phẩm 3 chiều
    @Column(name = "HEIGHT")
    private Double height;

    // Kích thước chiều dài của sản phẩm
    @Column(name = "WIDTH")
    private Double width;

    // Đơn vị đo cho 3 loại kích thước trên
    @Column(name = "DIMENSION_UNITS")
    private String dimensionUnits;

    // Cân nặng của sản phẩm
    @Column(name = "WEIGHT")
    private Double weight;

    // đơn vị đo của
    @Column(name = "WEIGHT_UNITS")
    private String weightUnits;

    // sản phẩm có được giảm giá hay không Y/N
    @Column(name = "DISCOUNTABLE", nullable = false)
    @ColumnDefault ("'N'")
    private String discountable;

    // Sản phẩm có thể nhận tại cửa hàng hay địa điểm thực tế thay vì vận chuyển hay không Y/N
    @Column(name = "ELIGIBLE_FOR_PICKUP", nullable = false)
    @ColumnDefault ("'Y'")
    private String eligibleForPickup;

    //một json map data về mức giá tương ứng theo shipping method, kiểu ship standard 7 ngày thì rẻ hơn ship express nhanh 1 ngày
    @Column(name = "FULFILLMENT_FLAT_RATES")
    private String fulfillmentFlatRates;

    // Trường này dành riêng cho bundled product, theo tìm hiểu qua loa thì nó lưu danh sách thông tin các product bundle item json
    @Column(name = "INCLUDED_PRODUCTS")
    @Lob
    private String includedProducts;

    /* Nếu product hoặc bất kỳ biến th của nó có thể được bán riêng lẻ trong cửa hàng
    * hoặc chúng có phải tách rời khỏi sản phẩm như một tiện ích bổ sung không
    * Thông thường nếu product not individually thì nó không được show ở search results
    * */
    @Column(name = "INDIVIDUALLY_SOLD")
    private String individuallySold;

    // Xác định thời điểm cần kiểm tra còn hàng trong kho của product NEVER / ADD_TO_CART
    @Column(name = "INVENTORY_CHECK_STRATEGY", nullable = false)
    @ColumnDefault ("'"+ Constant.PRODUCT.INVENTORY_CHECK_STRATEGY.ADD_TO_CART +"'")
    private String inventoryCheckStrategy;

    // Xác định thời điểm nào thì sản phẩm của KH đặt được giữ chỗ, để tránh người khác mua hết sản phẩm KH đặt NEVER, ADD_TO_CART, and SUBMIT_ORDER
    @Column(name = "INVENTORY_RESERVATION_STRATEGY", nullable = false)
    @ColumnDefault("'"+ Constant.PRODUCT.INVENTORY_RESERVATION_STRATEGY.SUBMIT_ORDER +"'")
    private String inventoryReservationStrategy;

    // Loại kho hàng là kho hàng ảo hay là kho thật PHYSICAL / VIRTUAL
    @Column(name = "INVENTORY_TYPE", nullable = false)
    @ColumnDefault("'" + Constant.PRODUCT.INVENTORY_TYPE.PHYSICAL + "'")
    private String inventoryType;

    // <meta name="keywords" /> tag
    @Column(name = "KEYWORDS")
    private String keywords;

    // Tóm lại nó cũng là một kiểu product mà nó có nhiều product con trong đó (Không phải là biến thể giống như variant product (SML red, white,..)).
    // Nó chỉ gọi là gom 2 hoặc nhiều sản phẩm có sẵn vào bán
    // Cột này đuợc đánh dấu có phải merchandising product hay không
    @Column(name = "MERCHANDISING_PRODUCT", nullable = false)
    private String merchandisingProduct;

    // Khi product là Merchandising thì người dùng thêm sản phẩm vào giỏ hàng lần 1, nếu thêm vào giỏ hàng lần 2 thì nó display là 2 sản phẩm riêng biệt hay là một sản phẩm
    // COMBINE - gộp lại thành 1, SEPARATE  hiển thị thành 2 sản phẩm riêng biệt ở card, REJECT_OR_IGNORE - Báo lỗi khi KH cố add sản phẩm đã có sẵn trong card
    @Column(name = "MERGING_TYPE", nullable = false)
    private String mergingType;

    // Hiển thị ở thẻ meta tag
    @Column(name = "META_DESCRIPTION", length = 500)
    private String metaDescription;

    // hiển thị ở thẻ meta tag
    @Column(name = "META_TITLE", length = 500)
    private String metaTitle;

    // Tên sản phẩm
    @Column(name = "NAME", nullable = false)
    private String name;

    // sản phẩm mà ko online thì ko hiển thị ở store front và không tìm kiếm hoặc bán được Y/N
    @Column(name = "IS_ONLINE", nullable = false)
    private String isOnline;

    // string json quy định các options của sản phẩm như kiểu Size, Color, ....
    @Column(name = "OPTIONS")
    @Lob
    private String options;

    // đơn giản hoá việc ghi lại số lượng reviews của một sản phẩm, cột này ghi lại tổng số review của KH
    @Column(name = "REVIEWS_NUMBER_OF_REVIEWS")
    private String reviewsNumberOfReviews;

    // overall rating (điểm rating trung bình)
    @Column(name = "REVIEWS_RATING")
    private Double reviewsRating;

    /* đơn vị đo rating là start hay là thumbs */
    @Column(name = "REVIEWS_RATING_UNITS")
    private String reviewsRatingUnits;

    /*Xác định xem sản phẩm có hiển thị trong kết quả search của KH hay không*/
    @Column(name = "SEARCHABLE", nullable = false)
    @ColumnDefault("'"+ Constant.STR_Y +"'")
    private String searchable;

    /* Là mã định danh sản phẩm, khi sản phẩm có nhiều biến thể như màu sắc, kích cỡ thì mỗi biến thể có sku khác nhau*/
    @Column(name = "SKU", nullable = false, unique = true)
    private String sku;

    /* Mã vạch của sản phẩm */
    @Column(name = "UPC")
    private String upc;

    /*SEO URI thân thiện vs product thay vì productId, nó thường được dùng để generate theo kiểu
    * /category-uri/product-name ...
    * */
    @Column(name = "URI", nullable = false, length = 800)
    private String uri;

    /* Mã số thuế của sản phẩm */
    @Column(name = "TAX_CODE", length = 200)
    private String taxCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="DATA_DRIVEN_ENUM_ID", nullable=false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private DataDrivenEnum merchandisingType;

    // Số lượng sản phẩm tối thiểu mà phải add vào cart
    @Column(name = "MIN_THRESHOLD")
    private Integer minThreshold;

    // Số lượng sản phẩm tối đa được add vào cart
    @Column(name = "MAX_THRESHOLD")
    private Integer maxThreshold;

    /*Loại sản phẩm, hiện tại đang hỗ trợ common 5 loại product type:
    * STANDARD,  VARIANT_BASED, BUNDLE, SELECTOR, MERCHANDISING
    *  */
    @Column(name = "PRODUCT_TYPE")
    private String productType;

    /*Tính năng này tạm thời chưa phát triển, người dùng có thể customize
    * lại product type của riêng mình, base từ 5 loại ở bên trên
    * */
    @Column(name = "BUSINESS_TYPE")
    private String businessType;

    /* Một số các nhãn đơn giản để phân loại sản phẩm*/
    @Column(name = "TAGS", length = 500)
    private String tags;

    @OneToMany(cascade= CascadeType.ALL, mappedBy="product", fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    public Set<Variant> variants;

    @OneToMany(cascade= CascadeType.ALL, mappedBy="product", fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    public Set<ProductAsset> productAssets;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product", fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Set<CategoryProduct> productCategories;
}
