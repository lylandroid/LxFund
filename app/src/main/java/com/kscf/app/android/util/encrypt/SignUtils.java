package com.kscf.app.android.util.encrypt;

import com.kscf.app.android.app.LxConstants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SignUtils {

    private static final String CHARSET = "UTF-8";

    public static boolean getSignVerify(Map<String, String> Params, String sign) {
        // 过滤空值、sign参数
        Map<String, String> sParaNew = paraFilter(Params);
        // 获取待签名字符串
        String preSignStr = createLinkString(sParaNew);
        // 获得签名验证结果
        boolean isSign = MD5.verify(preSignStr, sign, LxConstants.HTTP.VSTONE_MD5, CHARSET);

        return isSign;
    }

    public static Map<String, String> buildRequestPara(Map<String, String> sParaTemp) {
        // 除去数组中的空值和签名参数
        Map<String, String> sPara = paraFilter(sParaTemp);
        // 生成签名结果
        String mysign = buildRequestMysign(sPara);

        // 签名结果与签名方式加入请求提交参数组中
        sPara.put("sign", mysign);

        return sPara;
    }

    /**
     * 除去数组中的空值和签名参数
     *
     * @param sArray 签名参数组
     * @return 去掉空值与签名参数后的新签名参数组
     */
    private static Map<String, String> paraFilter(Map<String, String> sArray) {

        Map<String, String> result = new HashMap<String, String>();

        if (sArray == null || sArray.size() <= 0) {
            return result;
        }

        for (String key : sArray.keySet()) {
            String value = String.valueOf(sArray.get(key));
            if (value == null || value.equals("") || key.equalsIgnoreCase("sign")) {
                continue;
            }
            result.put(key, value);
        }

        return result;
    }

    /**
     * 生成签名结果
     *
     * @param sPara 要签名的数组
     * @return 签名结果字符串
     */
    private static String buildRequestMysign(Map<String, String> sPara) {
        String prestr = createLinkString(sPara); // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
        String mysign = MD5.sign(prestr, LxConstants.HTTP.VSTONE_MD5, CHARSET);
        return mysign;
    }

    /**
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     *
     * @param params 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    private static String createLinkString(Map<String, String> params) {

        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        String prestr = "";

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);

            if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }

        return prestr;
    }

    public static void main(String[] args) throws Exception {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("appid", "888");
        params.put("appv", "1.0");

        Map<String, String> signedParams = buildRequestPara(params);
        System.out.println(signedParams.get("sign"));

        System.out.println(getSignVerify(params, signedParams.get("sign")));

    }

}
