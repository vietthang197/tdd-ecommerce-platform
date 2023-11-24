package com.tdd.core.entity;

import com.tdd.core.constant.Constant;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.ws.rs.DefaultValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
/*Thuộc tính chung của sản phẩm, sản phẩm này không được hiển thị để bán hoặc là thêm vào giỏ hàng, thêm vào giỏ hàng là sku*/
@Entity
@Table(name = Constant.TABLE.PRODUCT_TBL, indexes = {
        @Index(name = "tdd_product_default_category_id_index", columnList = "default_category_id"),
        @Index(name = "tdd_product_url_url_key_index", columnList = "url, url_key"),
        @Index(name = "tdd_product_url_key_index", columnList = "url_key")
})
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product implements Serializable {

    @Id
    @Column(name = "PRODUCT_ID")
    @UuidGenerator
    private String productId;

    @Column(name = "ACTIVE_START_DATE", nullable = false)
    private LocalDateTime activeStartDate;

    @Column(name = "ACTIVE_END_DATE")
    private LocalDateTime activeEndDate;

    // Dynamic attribute là một phần của product, admin thêm thông tin phụ lưu trên sản phẩm
    @Column(name = "ATTRIBUTES") // format JSON
    private String attributes;


    // Sản phẩm có sẵn cho cửa hàng trực tuyến hay không Y/N
    @Column(name = "AVAILABLE_ONLINE", nullable = false)
    @DefaultValue("'Y'")
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
    @DefaultValue("'N'")
    private String discountable;

    // Sản phẩm có thể nhận tại cửa hàng hay địa điểm thực tế thay vì vận chuyển hay không Y/N
    @Column(name = "ELIGIBLE_FOR_PICKUP", nullable = false)
    @DefaultValue("'Y'")
    private String eligibleForPickup;

    //một json map data về mức giá tương ứng theo shipping method, kiểu ship standard 7 ngày thì rẻ hơn ship express nhanh 1 ngày
    @Column(name = "FULFILLMENT_FLAT_RATES")
    @Lob
    private String fulfillmentFlatRates;
}
