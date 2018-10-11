package grossary.cyron.com.grossary.utility;

import java.net.URL;

public class Constant {

    public static class URL
    {
        public static final String BASE_URL = "http://mobileapi.benakasoft.com/api";


    }
    public interface KEY_NAME{
        String FRAG_PARAMETER="FRAG_PARAMETER";
        String ACT_HOME_PARAMETER="ACT_HOME_PARAMETER";
        String CURRENT_FRG="CURRENT_FRG";

    }

    public interface CURRENT_STATE{
        String HOME_FRG="HOME_FRG";
        String OFFER_FRG="OFFER_FRG";
        String SELLER_FRG="SELLER_FRG";
        String CATG_LIST_FRG="CATG_LIST_FRG";
        String VIEW_CART_FRG="VIEW_CART_FRG";
        String MY_ORDER_FRG="MY_ORDER_FRG";

    }
    public interface NAV_DRAWER{
        String MY_PROFILE="MY_PROFILE";
        String MY_ORDER = "MY_ORDER";
        String LOG_OUT = "LOG_OUT";
    }

    public interface TABS{
        String HOME="HOME";
        String OFFER="OFFER";
        String SELLER="SELLER";
        String BRANDS="BRANDS";
    }

    public interface CATEGORY{
        String VIEW_CART="VIEW_CART";
        String LIST="Category_list";
        String LIST_DETAILS="Category_list_details";
        String ADD="ADD";
        String ONCLICK="ONCLICK";
        String DELETE="DELETE";
        String ORDER="ORDER";

    }

}
