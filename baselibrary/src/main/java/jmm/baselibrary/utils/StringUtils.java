package jmm.baselibrary.utils;


import android.util.Base64;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    public static String getSignature(Map<String, String> map, String timestamp) {
        ArrayList<Map.Entry<String, String>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, (lhs, rhs) -> lhs.getKey().compareTo(rhs.getKey()));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < map.size(); i++) {
            Map.Entry<String, String> entry = list.get(i);
            sb.append(entry.getKey());
            sb.append("=");
            sb.append(entry.getValue());
            if (i < list.size() - 1) {
                sb.append("&");
            }
        }
        sb.append("123456").append(timestamp);
        String content = sb.toString();
        System.out.println(content);
        return md5(content);
    }

    public static String getSignature(String key, String timestamp) {
        return md5Encode(key + timestamp);
    }

    public static String getSignature(String key, String timestamp, String base64) {
        return md5Encode(base64 + key + timestamp);
    }

    public static String getJson(Map<String, String> map) {
        return new Gson().toJson(map);
    }

    public static String getBase64(String str) {
        byte[] bytes = null;
        String result = null;
        try {
            bytes = str.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (bytes != null) {
            result = Base64.encodeToString(bytes, Base64.DEFAULT);
        }
        return result.replaceAll("[\\s*\t\n\r]", "");
    }

    public static String mapToString(Map<String, String> map) {
        ArrayList<Map.Entry<String, String>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, (lhs, rhs) -> lhs.getKey().compareTo(rhs.getKey()));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < map.size(); i++) {
            Map.Entry<String, String> entry = list.get(i);
            sb.append(entry.getKey());
            sb.append("=");
            sb.append(entry.getValue());
            if (i < list.size() - 1) {
                sb.append("&");
            }
        }
        return sb.toString();
    }

    public static String md5Encode(String text) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(text.getBytes("UTF-8"));
            StringBuilder hex = new StringBuilder(hash.length * 2);
            for (byte b : hash) {
                if ((b & 0xFF) < 0x10)
                    hex.append("0");
                hex.append(Integer.toHexString(b & 0xFF));
            }
            return hex.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String md5(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            str = buf.toString();
        } catch (Exception e) {
            e.printStackTrace();

        }
        return str;
    }

    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    public static List<String> getPhoneNumbers(String content) {
        List<String> digitList = new ArrayList<>();
        Pattern p = Pattern.compile("(\\d[0-9]{7,11})");
        Matcher m = p.matcher(content);
        while (m.find()) {
            String find = m.group(1);
            digitList.add(find);
        }
        return digitList;
    }


    public static String getJsonFromLocal(InputStream is) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            byte[] buffer = new byte[1024];
            int len;
            while ((len = is.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
            return bos.toString();
        } catch (IOException e) {
//            IOUtils.close(is);
//            IOUtils.close(bos);
            e.printStackTrace();
            return null;
        }
    }

    public static String convertUnicode(String ori) {
        char aChar;
        int len = ori.length();
        StringBuilder outBuffer = new StringBuilder(len);
        for (int x = 0; x < len; ) {
            aChar = ori.charAt(x++);
            if (aChar == '\\') {
                aChar = ori.charAt(x++);
                if (aChar == 'u') {
                    // Read the xxxx
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = ori.charAt(x++);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException(
                                        "Malformed   \\uxxxx   encoding.");
                        }
                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't')
                        aChar = '\t';
                    else if (aChar == 'r')
                        aChar = '\r';
                    else if (aChar == 'n')
                        aChar = '\n';
                    else if (aChar == 'f')
                        aChar = '\f';
                    outBuffer.append(aChar);
                }
            } else
                outBuffer.append(aChar);
        }
        return outBuffer.toString();
    }

    public static String formatJson(String json) {
        try {
            if (json.startsWith("{")) {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(json);
                    json = jsonObject.toString(4);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else if (json.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(json);
                json = jsonArray.toString(4);
            }
        } catch (JSONException e) {
        }
        return json;
    }

    public static boolean xx(String xx, String text) {
        Pattern p = Pattern.compile(xx);
        Matcher m = p.matcher(text);
        return m.matches();
    }
}
