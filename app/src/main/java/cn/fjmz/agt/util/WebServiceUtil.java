package cn.fjmz.agt.util;

import android.util.Log;

import com.alibaba.fastjson.JSONObject;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.MarshalFloat;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class WebServiceUtil {

    private static final String HUI_WEI_URL = "http://huiwei.contingencymanagement.cn/";
    private static final String WISE_BUS_URL = "http://nhuiwei.contingencymanagement.cn/";
    public static final String HUI_WEI_NAMESPACE = "http://www.huiweioa.com/";
    public static final String WISEBUS_NAMESPACE = "http://wisebus.com/";
    public static final String HUI_WEI_5VC = HUI_WEI_URL + "5VCommon.asmx";
    public static final String HUI_WEI_5VT = HUI_WEI_URL + "5VTaskFollow.asmx";
    public static final String HUI_WEI_5VI = HUI_WEI_URL + "5VInformPublish.asmx";
    public static final String HUI_WEI_5VP = HUI_WEI_URL + "5VProjectManager.asmx";
    public static final String HUI_WEI_5VS = HUI_WEI_URL + "5VSafetyProduction.asmx";
    public static final String WISE_BUS_5VS = WISE_BUS_URL + "5VSafetyProduction.asmx";
    public static final String HUI_WEI_5VH = HUI_WEI_URL + "5VHumanResource.asmx";
    public static final String SAFE_URL = "http://www.wisebus.com/5VSafetyProduction.asmx";
    public static final String WEBSERVICE_NAMESPACE = "http://wisebus.com/";
    public static final String PART_DUTY_URL = "http://www.wisebus.com/5VHumanResource.asmx";
    public static final String IMAGE_URL_PATH = "http://huiweioa.chinasafety.org/";


    public static String putWebServiceMsg(String[] keys, Object[] values,
                                          String methodName, String url, String nameSpace) throws Exception {
        String actionUrl = nameSpace + methodName;
        SoapObject so = new SoapObject(nameSpace, methodName);
        if (keys != null) {
            for (int i = 0; i < keys.length; i++) {
                so.addProperty(keys[i], values[i]);
            }
        }
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.bodyOut = so;
        envelope.encodingStyle = "UTF-8";
        envelope.dotNet = true;
        HttpTransportSE ht = new HttpTransportSE(url, 5000);
        ht.call(actionUrl, envelope);
        SoapObject result;
        try {
            result = (SoapObject) envelope.bodyIn;
            String resultStr = result.toString();
            int start = resultStr.indexOf("e{UploadFileResult=")
                    + "e{UploadFileResult=".length();
            int end = resultStr.indexOf(";");
            resultStr = resultStr.substring(start, end);
            System.out.println("result:" + resultStr);
            return resultStr;
        } catch (Exception e) {
            return "";
        }
    }

    public static ArrayList<HashMap<String, Object>> getWebServiceMsg(
            String[] keys, Object[] values, String methodName) throws Exception {
        String actionUrl = HUI_WEI_NAMESPACE + methodName;
        SoapObject so = new SoapObject(HUI_WEI_NAMESPACE, methodName);
        if (keys != null) {
            for (int i = 0; i < keys.length; i++) {
                so.addProperty(keys[i], values[i]);
            }
        }
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        MarshalFloat marshalFloat = new MarshalFloat();
        marshalFloat.register(envelope);
        envelope.bodyOut = so;
        envelope.encodingStyle = "UTF-8";
        envelope.dotNet = true;
        HttpTransportSE ht = new HttpTransportSE(HUI_WEI_5VC, 5000);
        ht.call(actionUrl, envelope);
        SoapObject result = null;
        try {
            result = (SoapObject) envelope.bodyIn;
        } catch (Exception e) {
            SoapFault soaF = (SoapFault) envelope.bodyIn;
            System.out.println("FaultString:" + methodName + "----" + soaF.faultstring);
        }
        ArrayList<HashMap<String, Object>> datas = new ArrayList<>();
        if (result != null) {
            String resultStr = "";
            try {
                resultStr = result.toString();
                System.out.println("result:" + resultStr);
                int endInt = resultStr.lastIndexOf("};");
                resultStr = resultStr.substring(endInt + 2,
                        resultStr.length() - 2);
            } catch (Exception e) {
                e.printStackTrace();
            }
            SoapObject detail = (SoapObject) result.getProperty(0);
            SoapObject mstr = (SoapObject) detail.getProperty(1);
            if (mstr.getPropertyCount() > 0) {
                SoapObject mstr2 = (SoapObject) mstr.getProperty(0);
                for (int i = 0; i < mstr2.getPropertyCount(); i++) {
                    String str_result = mstr2.getProperty(i).toString();
                    HashMap<String, Object> map = parseData(str_result,
                            resultStr);
                    if (map != null) {
                        datas.add(map);
                    }
                }
            } else {
                HashMap<String, Object> map = parseData("", resultStr);
                if (map != null) {
                    datas.add(map);
                }
            }
        }
        String s = JSONObject.toJSONString(datas);
        Log.e("ljj", s);
        return datas;
    }

    public static ArrayList<HashMap<String, Object>> getWebServiceMsg(
            Map<String, Object> requestData, String methodName, String url, String nameSpace)
            throws Exception {
        String actionUrl = nameSpace + methodName;
        SoapObject so = new SoapObject(nameSpace, methodName);
        if (requestData != null) {
            Set<Map.Entry<String, Object>> requestEntry = requestData.entrySet();
            for (Map.Entry<String, Object> entry : requestEntry) {
                so.addProperty(entry.getKey(), entry.getValue());
            }
        }
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        MarshalFloat marshaldDouble = new MarshalFloat();
        marshaldDouble.register(envelope);
        envelope.bodyOut = so;
        envelope.encodingStyle = "UTF-8";
        envelope.dotNet = true;
        HttpTransportSE ht = new HttpTransportSE(url, 5000);
        ht.call(actionUrl, envelope);
        SoapObject result = (SoapObject) envelope.bodyIn;
        ArrayList<HashMap<String, Object>> datas;
        datas = new ArrayList<>();
        if (result != null) {
            String resultStr = result.toString();
            System.out.println("result:" + resultStr);
            int endInt = resultStr.lastIndexOf("};");
            resultStr = resultStr.substring(endInt + 2,
                    resultStr.length() - 2);
            SoapObject detail = (SoapObject) result.getProperty(0);
            SoapObject mstr = (SoapObject) detail.getProperty(1);
            if (mstr.getPropertyCount() > 0) {
                SoapObject mstr2 = (SoapObject) mstr.getProperty(0);
                for (int i = 0; i < mstr2.getPropertyCount(); i++) {
                    String str_result = mstr2.getProperty(i).toString();
                    HashMap<String, Object> map = parseData(str_result,
                            resultStr);
                    if (map != null) {
                        datas.add(map);
                    }
                }
            } else {
                HashMap<String, Object> map = parseData("", resultStr);
                if (map != null) {
                    datas.add(map);
                }
            }
        }
        return datas;
    }

    public static String getWebServiceMsgList(Map<String, Object> requestData, String methodName,
                                              String url, String nameSpace) throws Exception {
        SoapObject so = new SoapObject(nameSpace, methodName);
        if (requestData != null) {
            Set<Map.Entry<String, Object>> requestEntry = requestData.entrySet();
            for (Map.Entry<String, Object> entry : requestEntry) {
                so.addProperty(entry.getKey(), entry.getValue());
            }
        }
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        MarshalFloat marshalFloat = new MarshalFloat();
        marshalFloat.register(envelope);
        envelope.bodyOut = so;
        envelope.encodingStyle = "UTF-8";
        envelope.dotNet = true;
        HttpTransportSE ht = new HttpTransportSE(url, 5000);
        ht.call(nameSpace + methodName, envelope);
        SoapObject result = (SoapObject) envelope.bodyIn;
        ArrayList<HashMap<String, Object>> maps = new ArrayList<>();
        if (result != null) {
            String resultStr = result.toString();
            Log.e("ljj", methodName + ":" + resultStr);
            int endInt = resultStr.lastIndexOf("};");
            resultStr = resultStr.substring(endInt + 2, resultStr.length() - 2);
            SoapObject detail = (SoapObject) result.getProperty(0);
            SoapObject detailProperty = (SoapObject) detail.getProperty(1);
            if (detailProperty.getPropertyCount() > 0) {
                SoapObject property = (SoapObject) detailProperty.getProperty(0);
                for (int i = 0; i < property.getPropertyCount(); i++) {
                    String str_result = property.getProperty(i).toString();
                    HashMap<String, Object> map = parseData(str_result, resultStr);
                    if (map != null) {
                        maps.add(map);
                    }
                }
            } else {
                HashMap<String, Object> map = parseData("", resultStr);
                if (map != null) {
                    maps.add(map);
                }
            }

        }
        String s = JSONObject.toJSONString(maps);
        Log.e("ljj", methodName + ":" + s);
        return s;
    }

    public static String getWebServiceMsgStr(Map<String, Object> requestData, String methodName,
                                             String url, String nameSpace) throws Exception {
        SoapObject so = new SoapObject(nameSpace, methodName);
        if (requestData != null) {
            Set<Map.Entry<String, Object>> requestEntry = requestData.entrySet();
            for (Map.Entry<String, Object> entry : requestEntry) {
                so.addProperty(entry.getKey(), entry.getValue());
            }
        }
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        MarshalFloat marshalFloat = new MarshalFloat();
        marshalFloat.register(envelope);
        envelope.bodyOut = so;
        envelope.encodingStyle = "UTF-8";
        envelope.dotNet = true;
        HttpTransportSE ht = new HttpTransportSE(url, 5000);
        ht.call(nameSpace + methodName, envelope);
        SoapObject result = (SoapObject) envelope.bodyIn;
        ArrayList<HashMap<String, Object>> maps = new ArrayList<>();
        if (result != null) {
            String resultStr = result.toString();
            HashMap<String, Object> map = parseData(resultStr, "");
            if (map != null && map.isEmpty()) {
                return "";
            }
            maps.add(map);
        }
        String s = JSONObject.toJSONString(maps);
        Log.e("ljj", methodName + ":" + s);
        return s;
    }

    public static ArrayList<HashMap<String, Object>> getWebServiceMsg(
            String[] keys, Object[] values, String methodName, String url, String nameSpace)
            throws Exception {
        String actionUrl = nameSpace + methodName;
        SoapObject so = new SoapObject(nameSpace, methodName);
        if (keys != null) {
            for (int i = 0; i < keys.length; i++) {
                so.addProperty(keys[i], values[i]);
            }
        }
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        MarshalFloat marshaldDouble = new MarshalFloat();
        marshaldDouble.register(envelope);
        envelope.bodyOut = so;
        envelope.encodingStyle = "UTF-8";
        envelope.dotNet = true;
        HttpTransportSE ht = new HttpTransportSE(url, 5000);
        ht.call(actionUrl, envelope);
        SoapObject result = null;
        try {
            result = (SoapObject) envelope.bodyIn;
        } catch (Exception e) {
            SoapFault soaF = (SoapFault) envelope.bodyIn;
            System.out.println("FaultString:" + methodName + "----" + soaF.faultstring);
        }
        ArrayList<HashMap<String, Object>> datas = new ArrayList<HashMap<String, Object>>();
        if (result != null) {
            String resultStr = "";
            try {
                resultStr = result.toString();
                System.out.println("result:" + resultStr);
                int endInt = resultStr.lastIndexOf("};");
                resultStr = resultStr.substring(endInt + 2,
                        resultStr.length() - 2);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                SoapObject detail = (SoapObject) result.getProperty(0);
                SoapObject mstr = (SoapObject) detail.getProperty(1);
                if (mstr.getPropertyCount() > 0) {
                    SoapObject mstr2 = (SoapObject) mstr.getProperty(0);
                    for (int i = 0; i < mstr2.getPropertyCount(); i++) {
                        String str_result = mstr2.getProperty(i).toString();
                        HashMap<String, Object> map = parseData(str_result,
                                resultStr);
                        if (map != null) {
                            datas.add(map);
                        }
                    }
                } else {
                    HashMap<String, Object> map = parseData("", resultStr);
                    if (map != null) {
                        datas.add(map);
                    }
                }
            } catch (Exception e) {
                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("result", "ok");
                datas.add(map);
            }
        }
        return datas;
    }


    public static ArrayList<HashMap<String, Object>> getWebServiceMsg(
            String[] keys, Object[] values, String methodName,
            String[] getParams_Keys) throws Exception {
        String actionUrl = HUI_WEI_NAMESPACE + methodName;
        SoapObject so = new SoapObject(HUI_WEI_NAMESPACE, methodName);
        if (keys != null) {
            for (int i = 0; i < keys.length; i++) {
                so.addProperty(keys[i], values[i]);
            }
        }
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.bodyOut = so;
        envelope.encodingStyle = "UTF-8";
        envelope.dotNet = true;
        HttpTransportSE ht = new HttpTransportSE(HUI_WEI_5VC, 5000);
        ht.call(actionUrl, envelope);
        SoapObject result = null;
        try {
            result = (SoapObject) envelope.bodyIn;
        } catch (Exception e) {
            SoapFault soaF = (SoapFault) envelope.bodyIn;
            System.out.println("FaultString:" + methodName + "----" + soaF.faultstring);
        }
        ArrayList<HashMap<String, Object>> datas = new ArrayList<>();
        if (result != null) {
            String resultStr;
            try {
                resultStr = result.toString();
                System.out.println("result:" + resultStr);
                int endInt = resultStr.lastIndexOf("};");
                resultStr = resultStr.substring(endInt + 2,
                        resultStr.length() - 2);
            } catch (Exception e) {
                e.printStackTrace();
            }
            SoapObject detail = (SoapObject) result.getProperty(0);
            SoapObject mstr = (SoapObject) detail.getProperty(1);
            if (mstr.getPropertyCount() > 0) {
                SoapObject mstr2 = (SoapObject) mstr.getProperty(0);
                for (int i = 0; i < mstr2.getPropertyCount(); i++) {
                    SoapObject items = (SoapObject) mstr2.getProperty(i);
                    HashMap<String, Object> maps = new HashMap<String, Object>();
                    for (int j = 0; j < getParams_Keys.length; j++) {
                        try {
                            maps.put(getParams_Keys[j],
                                    items.getProperty(getParams_Keys[j])
                                            .toString());
                        } catch (Exception e) {
                            continue;
                        }
                    }
                    datas.add(maps);
                }
            }
        }
        return datas;
    }

    public static ArrayList<HashMap<String, Object>> getWebServiceMsg(
            String[] keys, Object[] values, String methodName,
            String[] getParams_Keys, String url) throws Exception {
        String actionUrl = WEBSERVICE_NAMESPACE + methodName;
        SoapObject so = new SoapObject(WEBSERVICE_NAMESPACE, methodName);
        if (keys != null) {
            for (int i = 0; i < keys.length; i++) {
                so.addProperty(keys[i], values[i]);
            }
        }
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        MarshalFloat marshaldDouble = new MarshalFloat();
        marshaldDouble.register(envelope);
        envelope.bodyOut = so;
        envelope.encodingStyle = "UTF-8";
        envelope.dotNet = true;
        HttpTransportSE ht = new HttpTransportSE(url, 5000);
        ht.call(actionUrl, envelope);
        SoapObject result = null;
        try {
            result = (SoapObject) envelope.bodyIn;
        } catch (Exception e) {
            SoapFault soaF = (SoapFault) envelope.bodyIn;
            System.out.println("FaultString:" + methodName + "----" + soaF.faultstring);
        }
        ArrayList<HashMap<String, Object>> datas = new ArrayList<HashMap<String, Object>>();
        if (result != null) {
            String resultStr = "";
            try {
                resultStr = result.toString();
                System.out.println("result:" + resultStr);
                int endInt = resultStr.lastIndexOf("};");
                resultStr = resultStr.substring(endInt + 2,
                        resultStr.length() - 2);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                SoapObject detail = (SoapObject) result.getProperty(0);
                SoapObject mstr = (SoapObject) detail.getProperty(1);
                if (mstr.getPropertyCount() > 0) {
                    SoapObject mstr2 = (SoapObject) mstr.getProperty(0);
                    for (int i = 0; i < mstr2.getPropertyCount(); i++) {
                        SoapObject items = (SoapObject) mstr2.getProperty(i);
                        HashMap<String, Object> maps = new HashMap<String, Object>();
                        for (int j = 0; j < getParams_Keys.length; j++) {
                            try {
                                maps.put(getParams_Keys[j],
                                        items.getProperty(getParams_Keys[j])
                                                .toString());
                            } catch (Exception e) {
                                continue;
                            }
                        }
                        datas.add(maps);
                    }
                }
            } catch (Exception e) {
            }
        }
        return datas;
    }

    public static ArrayList<HashMap<String, Object>> getWebServiceMsg(
            String[] keys, Object[] values, String methodName,
            String[] getParams_Keys, String url, String nameSpace) throws Exception {
        String actionUrl = nameSpace + methodName;
        SoapObject so = new SoapObject(nameSpace, methodName);
        if (keys != null) {
            for (int i = 0; i < keys.length; i++) {
                so.addProperty(keys[i], values[i]);
            }
        }
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        MarshalFloat marshaldDouble = new MarshalFloat();
        marshaldDouble.register(envelope);
        envelope.bodyOut = so;
        envelope.encodingStyle = "UTF-8";
        envelope.dotNet = true;
        HttpTransportSE ht = new HttpTransportSE(url, 5000);
        ht.call(actionUrl, envelope);
        SoapObject result = null;
        try {
            result = (SoapObject) envelope.bodyIn;
        } catch (Exception e) {
            SoapFault soaF = (SoapFault) envelope.bodyIn;
            System.out.println("FaultString:" + methodName + "----" + soaF.faultstring);
        }
        ArrayList<HashMap<String, Object>> datas = new ArrayList<HashMap<String, Object>>();
        if (result != null) {
            String resultStr = "";
            try {
                resultStr = result.toString();
                System.out.println("result:" + resultStr);
                int endInt = resultStr.lastIndexOf("};");
                resultStr = resultStr.substring(endInt + 2,
                        resultStr.length() - 2);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                SoapObject detail = (SoapObject) result.getProperty(0);
                SoapObject mstr = (SoapObject) detail.getProperty(1);
                if (mstr.getPropertyCount() > 0) {
                    SoapObject mstr2 = (SoapObject) mstr.getProperty(0);
                    for (int i = 0; i < mstr2.getPropertyCount(); i++) {
                        SoapObject items = (SoapObject) mstr2.getProperty(i);
                        HashMap<String, Object> maps = new HashMap<String, Object>();
                        for (int j = 0; j < getParams_Keys.length; j++) {
                            try {
                                maps.put(getParams_Keys[j],
                                        items.getProperty(getParams_Keys[j])
                                                .toString());
                            } catch (Exception e) {
                                continue;
                            }
                        }
                        datas.add(maps);
                    }
                }
            } catch (Exception e) {
            }
        }
        return datas;
    }

    private static HashMap<String, Object> parseData(String str_result, String outStr) {
        HashMap<String, Object> data = new HashMap<>();
        if (StringUtil.isEmpty(str_result)) {
            if (StringUtil.isEmpty(outStr)) {
                return null;
            }
            str_result = outStr;
        } else {
            int start = str_result.indexOf("{");
            int end = str_result.lastIndexOf("}");
            str_result = str_result.substring(start + 1, end);
            str_result = str_result + outStr;
        }

        String[] temps = str_result.split(";");
        for (String temp : temps) {
            String res = temp.trim();
            if (StringUtil.isNotEmpty(res)) {
                String[] keyvalues = res.split("=");
                if (keyvalues.length > 1) {
                    if ((keyvalues[1].contains("{"))) {
                        keyvalues[1] = keyvalues[1].replace("{", "#");
                        keyvalues[1] = keyvalues[1].replace("}", "$");
                    }
                    data.put(keyvalues[0].trim(), keyvalues[1].trim());
                }
            }
        }
        return data;
    }
}
