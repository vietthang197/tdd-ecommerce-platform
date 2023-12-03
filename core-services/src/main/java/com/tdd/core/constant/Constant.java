package com.tdd.core.constant;

public interface Constant {

    String STR_Y = "Y";
    String STR_N = "N";

    interface TABLE {
        String PRODUCT_TBL = "TDD_PRODUCT";
        String CATEGORY_TBL = "TDD_CATEGORY";
        String VARIANT_TBL = "TDD_VARIANT";
        String DATA_DRIVEN_ENUM_TBL = "TDD_DATA_DRIVEN_ENUM";
        String PRODUCT_ASSET_TBL = "TDD_PRODUCT_ASSET";
        String PRODUCT_ASSET_TAG_TBL = "TDD_PRODUCT_ASSET_TAG";
        String CATEGORY_PRODUCT_TBL = "TDD_CATEGORY_PRODUCT";
        String CUSTOMER_TBL = "TDD_CUSTOMER";
    }

    interface PRODUCT {

        /* cấu hình xem có check xem còn hàng trong kho khi KH thêm hàng vào giỏ hay không */
        interface INVENTORY_CHECK_STRATEGY {
            String  NEVER = "NEVER";
            String  ADD_TO_CART = "ADD_TO_CART";
        }

        /* Cấu hình thời điểm giữ chỗ sản phẩm
        (Như kiểu đặt vé xem phim thì sẽ giữ chỗ ngồi cho tới khi thanh toán, để tránh khách hàng đang đặt thì thằng khác mua mất
        */
        interface INVENTORY_RESERVATION_STRATEGY {
            String ADD_TO_CART = "ADD_TO_CART";
            String NEVER = "NEVER";
            String SUBMIT_ORDER = "SUBMIT_ORDER";
        }

        /* Kho hàng nà là kho hàng ảo hay là kho hàng có thật */
        interface INVENTORY_TYPE {
            String PHYSICAL = "PHYSICAL";
            String VIRTUAL = "VIRTUAL";
        }

        interface PRODUCT_TYPE {
            // tạo product kiểu chuẩn, product đơn giản ko có nhiều thông số
            String STANDARD = "STANDARD";
            /* tạo product có mở rộng thuộc tính, kiểu như bán máy
            tính thì có thông số cpu, ram
             */
            String VARIANT_BASED = "VARIANT_BASED";
            // tạo combo sản phẩm, người mua phải mua cả combo
            String BUNDLE = "BUNDLE";
            /* Loại select or nà thì chưa biết*/
            String SELECTOR = "SELECTOR";
            /* tạo combo sản phẩm nhưng khi mua thì có thể mua 1 trong
            list combo chứ không phải mua cả combo như bundle */
            String MERCHANDISING = "MERCHANDISING";
        }
    }
}
