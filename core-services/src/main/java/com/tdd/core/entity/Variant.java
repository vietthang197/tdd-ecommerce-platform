package com.tdd.core.entity;

import com.tdd.core.constant.Constant;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

/*
* Bảng này để lưu các biến thể của product.
* Biến thể là các phiên bản cụ thể của sản phẩm có chứa sku riêng
* Biến thể không đuợc bán riêng, nó là tuỳ chọn khi người dùng click vào sản phẩm cha rồi chọn theo giá tiền
* Giả sử áo có màu đỏ sẽ sinh ra một variant khác và có sku khác
* tham chiếu: https://developer.broadleafcommerce.com/services/catalog-services/data-model/produc
*
* */
@Entity
@Table(name = Constant.TABLE.VARIANT_TBL, indexes = {
        @Index(name = "TDD_VARIANT_PRODUCT_ID_INDEX", columnList = "PRODUCT_ID")
})
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Variant extends SuperEntity {

    @Id
    @Column(name = "VARIANT_ID")
    @UuidGenerator
    private String variantId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="PRODUCT_ID", nullable=false)
    private Product product;

    @Column(name = "ACTIVE_START_DATE")
    private LocalDateTime activeStartDate;

    @Column(name = "ACTIVE_END_DATE")
    private LocalDateTime activeEndDate;

    // Thuộc tính động của biến thể
    @Column(name = "ATTRIBUTES")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, String> attributes;

    // giá nhập
    @Column(name = "COST")
    private BigDecimal cost;

    // giá bán lẻ, nó bị thay thế bởi salePrice nếu có confign sale_price
    @Column(name = "DEFAULT_PRICE")
    private BigDecimal defaultPrice;

    // Giá bán khuyến mại
    @Column(name = "SALE_PRICE")
    private BigDecimal salePrice;

    // mô tả sản phẩm
    @Column(name = "DESCRIPTION")
    @Lob
    private String description;

    // Kích thước chiều sâu của sản phẩm 3 chiều, có liên hệ với dimension unit
    private String depth;

    // Kích thước chiều cao của sản phẩm 3 chiều
    @Column(name = "HEIGHT")
    private Double height;

    // Kích thước chiều dài của sản phẩm
    @Column(name = "WIDTH")
    private Double width;

    // Đơn vị đo cho 3 loại kích thước trên
    @Column(name = "DIMENSIONAL_UNITS")
    private String dimensionalUnits;

    @Column(name = "WEIGHT")
    private Double weight;

    @Column(name = "WEIGHT_UNITS")
    private Double weightUnits;

    // Sản phẩm có được phép giảm giá hay không?
    @Column(name = "DISCOUNTABLE")
    private String discountable;

    //một json map data về mức giá tương ứng theo shipping method, kiểu ship standard 7 ngày thì rẻ hơn ship express nhanh 1 ngày
    @Column(name = "FULFILLMENT_FLAT_RATES")
    private String fulfillmentFlatRates;

    // Xác định thời điểm cần kiểm tra còn hàng trong kho của product NEVER / ADD_TO_CART
    @Column(name = "INVENTORY_CHECK_STRATEGY", nullable = false)
    @ColumnDefault("'"+ Constant.PRODUCT.INVENTORY_CHECK_STRATEGY.ADD_TO_CART +"'")
    private String inventoryCheckStrategy;

    // Xác định thời điểm nào thì sản phẩm của KH đặt được giữ chỗ, để tránh người khác mua hết sản phẩm KH đặt NEVER, ADD_TO_CART, and SUBMIT_ORDER
    @Column(name = "INVENTORY_RESERVATION_STRATEGY", nullable = false)
    @ColumnDefault("'"+ Constant.PRODUCT.INVENTORY_RESERVATION_STRATEGY.SUBMIT_ORDER +"'")
    private String inventoryReservationStrategy;

    // Loại kho hàng là kho hàng ảo hay là kho thật PHYSICAL / VIRTUAL
    @Column(name = "INVENTORY_TYPE", nullable = false)
    @ColumnDefault("'" + Constant.PRODUCT.INVENTORY_TYPE.PHYSICAL + "'")
    private String inventoryType;

    // Tên sản phẩm
    @Column(name = "NAME", nullable = false)
    private String name;

    // sản phẩm mà ko online thì ko hiển thị ở store front và không tìm kiếm hoặc bán được Y/N
    @Column(name = "IS_ONLINE", nullable = false)
    private String isOnline;

    // Giá trị các option của biến thể
    @Column(name = "OPTIONS_VALUES")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, String> optionsValues;

    @Column(name = "REVIEWS_NUMBER_OF_REVIEWS")
    private Integer reviewsNumberOfReviews;

    // overall rating (điểm rating trung bình)
    @Column(name = "REVIEWS_RATING")
    private Double reviewsRating;

    /* đơn vị đo rating là start hay là thumbs */
    @Column(name = "REVIEWS_RATING_UNITS")
    private String reviewsRatingUnits;

    /* Là mã định danh sản phẩm, khi sản phẩm có nhiều biến thể như màu sắc, kích cỡ thì mỗi biến thể có sku khác nhau*/
    @Column(name = "SKU", nullable = false, unique = true)
    private String sku;

    /* Mã vạch của sản phẩm */
    @Column(name = "UPC")
    private String upc;
}
