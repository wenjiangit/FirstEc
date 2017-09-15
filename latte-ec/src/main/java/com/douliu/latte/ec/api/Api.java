package com.douliu.latte.ec.api;

import com.mac.latte.core.app.ConfigKey;
import com.mac.latte.core.app.Latte;

/**
 *
 * Created by douliu on 2017/8/14.
 */

public interface Api {

    String BASE_URL = Latte.getConfiguration(ConfigKey.API_HOST)+"/RestServer/data/";

    String USER_PROFILE = BASE_URL + "user_profile.json";

    String ABOUT = BASE_URL + "about.json";

    String INFO = BASE_URL + "info.json";

    String ADD_SHOP_CART = BASE_URL + "add_shop_cart.json";

    String ADDRESS = BASE_URL + "address.json";

    String CLASSIFY_CONTENT = BASE_URL + "classify_content.json";

    String GOODS_DETAILS_DATA_1 = BASE_URL + "goods_details_data_1.json";

    String GOODS_DETAILS_DATA_2 = BASE_URL + "goods_details_data_2.json";

    String INDEX_2_DATA = BASE_URL + "index_2_data.json";

    String INDEX_DATA = BASE_URL + "index_data.json";

    String ORDER_LIST = BASE_URL + "order_list.json";

    String SORT_CONTENT_DATA_1 = BASE_URL + "sort_content_data_1.json";

    String SORT_CONTENT_DATA_2 = BASE_URL + "sort_content_data_2.json";

    String SORT_LIST_DATA = BASE_URL + "sort_list_data.json";
}
