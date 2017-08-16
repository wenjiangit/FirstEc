package com.douliu.latte.ec.api;

/**
 *
 * Created by douliu on 2017/8/14.
 */

public interface Api {

    String BASE_URL = "http://10.71.73.138:8080";

    String USER_PROFILE = BASE_URL + "/data/user_profile.json";

    String ABOUT = BASE_URL + "/data/about.json";

    String INFO = BASE_URL + "/data/info.json";

    String ADD_SHOP_CART = BASE_URL + "/data/add_shop_cart.json";

    String ADDRESS = BASE_URL + "/data/address.json";

    String CLASSIFY_CONTENT = BASE_URL + "/data/classify_content.json";

    String GOODS_DETAILS_DATA_1 = BASE_URL + "/data/goods_details_data_1.json";

    String GOODS_DETAILS_DATA_2 = BASE_URL + "/data/goods_details_data_2.json";

    String INDEX_2_DATA = BASE_URL + "/data/index_2_data.json";

    String INDEX_DATA = BASE_URL + "/data/index_data.json";

    String ORDER_LIST = BASE_URL + "/data/order_list.json";

    String SORT_CONTENT_DATA_1 = BASE_URL + "/data/sort_content_data_1.json";

    String SORT_CONTENT_DATA_2 = BASE_URL + "/data/sort_content_data_2.json";

    String SORT_LIST_DATA = BASE_URL + "/data/sort_list_data.json";
}
