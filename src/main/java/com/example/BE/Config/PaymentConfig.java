package com.example.BE.Config;

import java.time.LocalDateTime;

public class PaymentConfig {

    public static final String CUSTOMER_NUMBER = "customerNumber";

    public static final String PARTNER_REF_ID = "partnerRefId";

    public static final String PARTNER_TRANS_ID = "partnerTransId";

    public static final String USERNAME = "userName";

    public static final String PARTNER_NAME = "partnerName";

    public static final String STORE_ID = "storeId";

    public static final String STORE_NAME = "storeName";

    public static String ENDPOINT = "https://test-payment.momo.vn/gw_payment/transactionProcessor";

    public static String HOSTNAME = "https://test-payment.momo.vn";

    public static String PATH = "/gw_payment/transactionProcessor";

    public static String PARTNERCODE = "MOMOFUL620210807";

    public static String ACCESSKEY = "FRCfxu0SDLAEpnIP";

    public static String SECRETKEY = "qy8y8cxlvs3coVGodv4eqKclCdxezB24";

    public static String ORDERINFO = "payWithMoMoATM";

    public static String RETURNURL = "https://www.momo.vn/returnUrl-";

    public static String NOTIFYURL = "https://www.momo.vn/notifyUrl-";

    public static String REQUESTTYPE = "transactionStatus";

}
