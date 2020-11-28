package com.enc.utils.http;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;


public class HttpRequest {

    /***
     * 向指定URL发送GET方法的请求
     * @param url
     * @param map
     * @param charset
     * @return
     */
    public static String sendGet(String url, Map<String, Object> map, String charset) {
        StringBuffer sb = new StringBuffer();
        //构建请求参数
        if (map != null && map.size() > 0) {
            Iterator it = map.entrySet().iterator(); //定义迭代器
            while (it.hasNext()) {
                Entry er = (Entry) it.next();
                sb.append(er.getKey());
                sb.append("=");
                sb.append(er.getValue());
                sb.append("&");
            }
        }
        return sendGet(url, sb.toString(), charset);
    }

    /***
     * 向指定URL发送POST方法的请求
     * @param url
     * @param json
     * @param charset
     * @return
     */
    public static String sendPost(String url, JSONObject json, String charset) {
        StringBuffer sb = new StringBuffer();
        return sendPost(url, json.toString(), charset);
    }

    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url     发送请求的URL
     * @param param   请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @param charset 发送和接收的格式
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param, String charset) {
        String result = "";
        String line;
        StringBuffer sb = new StringBuffer();
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性 设置请求格式
            conn.setRequestProperty("contentType", charset);
            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            //设置超时时间
            conn.setConnectTimeout(60);
            conn.setReadTimeout(60);
            // 建立实际的连接
            conn.connect();
            // 定义 BufferedReader输入流来读取URL的响应,设置接收格式
            in = new BufferedReader(new InputStreamReader(
                    conn.getInputStream(), charset));
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
            result = sb.toString();
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 向指定URL发送GET方法的请求(人脸请求方法)
     *
     * @param url
     *            发送请求的URL
     * @param paramFace
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL
     *            所代表远程资源的响应结果
     */
    public static String sendGetface(String url, String paramFace) {
        String result = "";
        String line;
        StringBuffer sb=new StringBuffer();
        BufferedReader in = null;
        try {
            String urlNameString = url + paramFace;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性 设置请求格式
            conn.setRequestProperty("contentType", "application/json");
            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            //设置超时时间
            conn.setConnectTimeout(60);
            conn.setReadTimeout(60);
            // 建立实际的连接
            conn.connect();
            // 定义 BufferedReader输入流来读取URL的响应,设置接收格式
            in = new BufferedReader(new InputStreamReader(
                    conn.getInputStream(),"utf-8"));
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
            result=sb.toString();
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
    /**
     * 向指定URL发送GET方法的请求(人脸请求方法)
     *
     * @param url
     *            发送请求的URL
     * @param paramFace
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL
     *            所代表远程资源的响应结果
     */
    public static String sendDeleteface(String url, String paramFace) {
        String result = "";
        String line;
        StringBuffer sb=new StringBuffer();
        BufferedReader in = null;
        try {
            String urlNameString = url + paramFace;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            HttpURLConnection httpURLConnection = (HttpURLConnection)conn;
            httpURLConnection.setRequestMethod("DELETE");
            // 设置通用的请求属性 设置请求格式
            conn.setRequestProperty("contentType", "application/json");
            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            //设置超时时间
            conn.setConnectTimeout(60);
            conn.setReadTimeout(60);
            // 建立实际的连接
            conn.connect();
            // 定义 BufferedReader输入流来读取URL的响应,设置接收格式
            in = new BufferedReader(new InputStreamReader(
                    conn.getInputStream(),"utf-8"));
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
            result=sb.toString();
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url     发送请求的 URL
     * @param param   请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @param charset 发送和接收的格式
     * @return 所代表远程资源的响应结果
     */
    public static String sendPostPut(String url, String param, String charset) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        String line;
        StringBuffer sb = new StringBuffer();
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            HttpURLConnection httpURLConnection = (HttpURLConnection)conn;
            httpURLConnection.setRequestMethod("PUT");
            // 设置通用的请求属性 设置请求格式
            conn.setRequestProperty("contentType", charset);
            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            //设置超时时间
            conn.setConnectTimeout(3000);
            conn.setReadTimeout(3000);
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应    设置接收格式
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), charset));
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
            result = sb.toString();
        } catch (Exception e) {
            System.out.println("发送 POST请求出现异常!" + e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }
    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url     发送请求的 URL
     * @param param   请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @param charset 发送和接收的格式
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param, String charset) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        String line;
        StringBuffer sb = new StringBuffer();
        try {
            URL realUrl = new URL(url);
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性 设置请求格式
            conn.setRequestProperty("contentType", charset);
            conn.setRequestProperty("content-type", "application/json");
            //设置超时时间
            conn.setConnectTimeout(100000);
            conn.setReadTimeout(100000);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            out.print(JSONObject.parseObject(param));
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应    设置接收格式
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), charset));
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
            result = sb.toString();
        } catch (Exception e) {
            System.out.println("发送 POST请求出现异常!" + e);

            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    /***
     * 带图片参数POST请求
     * @param httpPost
     * @param reqEntity
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static String sentYtstImgPost(HttpPost httpPost, HttpEntity reqEntity) throws ClientProtocolException, IOException {
        httpPost.setEntity(reqEntity);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(httpPost);
        HttpEntity resultEntity = response.getEntity();
        String responseMessage = "";
        try {
            if (resultEntity != null) {
                InputStream is = resultEntity.getContent();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                StringBuffer sb = new StringBuffer();
                String line = "";
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                responseMessage = sb.toString();
            }
            EntityUtils.consume(resultEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if (null != response) {
                response.close();
            }
        }
        return responseMessage;
    }


    public static void main(String[] args) {

        String base64 = "";

        String postUrl = "http://10.19.151.224:9905/";

        String getUrl = "http://10.19.151.224:8081/client/getallinfo.php";
        StringBuffer param = new StringBuffer();
        param.append("id=995869358074249383");

        System.out.println(HttpRequest.sendGet(getUrl,param.toString(),"UTF-8").toString());
    }

    private static String getImgStr(String imgFile) {
        InputStream in = null;
        byte[] data = null;
        //读取图片字节数组
        try {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(Base64.encodeBase64(data));
    }

}

















