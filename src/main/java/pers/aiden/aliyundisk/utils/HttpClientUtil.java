package pers.aiden.aliyundisk.utils;

import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.SocketConfig;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import pers.aiden.aliyundisk.module.HttpResult;
import pers.aiden.aliyundisk.module.TaskDetail;

import java.io.*;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.CodingErrorAction;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 * @Author: 范淼
 * @Date: 2022-04-30
 * @Description: http工具
 */
public class HttpClientUtil {

    /** 从连接池中获取连接的超时时间（单位：ms） */
    private static final int CONNECTION_REQUEST_TIMEOUT = 5000;

    /** 与服务器连接的超时时间（单位：ms） */
    private static final int CONNECTION_TIMEOUT = 5000;

    /** 从服务器获取响应数据的超时时间（单位：ms） */
    private static final int SOCKET_TIMEOUT = 10000;

    /** 连接池的最大连接数 */
    private static final int MAX_CONN_TOTAL = 100;

    /** 每个路由上的最大连接数 */
    private static final int MAX_CONN_PER_ROUTE = 50;

    /** 用户代理配置 */
    private static final String USER_AGENT = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/99.0.4844.84 Safari/537.36";

    /** HttpClient对象 */
    private static CloseableHttpClient httpClient = null;

    /** Connection配置对象 */
    private static ConnectionConfig connectionConfig = null;

    /** Socket配置对象 */
    private static SocketConfig socketConfig = null;

    /** Request配置对象 */
    private static RequestConfig requestConfig = null;

    /** Cookie存储对象 */
    private static BasicCookieStore cookieStore = null;


    static {
        init();
    }

    /**
     * 全局对象初始化
     */
    private static void init() {
        // 创建Connection配置对象
        connectionConfig = ConnectionConfig.custom()
                .setMalformedInputAction(CodingErrorAction.IGNORE)
                .setUnmappableInputAction(CodingErrorAction.IGNORE)
                .setCharset(Consts.UTF_8).build();

        // 创建Socket配置对象
        socketConfig = SocketConfig.custom().setTcpNoDelay(true).build();

        // 创建Request配置对象
        requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT)
                .setConnectTimeout(CONNECTION_TIMEOUT)
                .setSocketTimeout(SOCKET_TIMEOUT)
                .build();

        // 创建Cookie存储对象（服务端返回的Cookie保存在CookieStore中，下次再访问时才会将CookieStore中的Cookie发送给服务端）
        cookieStore = new BasicCookieStore();

        // 创建HttpClient对象
        httpClient = HttpClients.custom()
                .setDefaultConnectionConfig(connectionConfig)
                .setDefaultSocketConfig(socketConfig)
                .setDefaultRequestConfig(requestConfig)
                .setDefaultCookieStore(cookieStore)
                .setUserAgent(USER_AGENT)
                .setMaxConnTotal(MAX_CONN_TOTAL)
                .setMaxConnPerRoute(MAX_CONN_PER_ROUTE)
                .build();
    }


    public static HttpResult uploadFile(String url,File file, Header[] headers){
        try {
            HttpPut httpPut = new HttpPut(url);
            FileEntity fileEntity = new FileEntity(file);
            httpPut.setEntity(fileEntity);

            CloseableHttpResponse httpResponse = httpClient.execute(httpPut);
            int statusCode= httpResponse.getStatusLine().getStatusCode();
            if(statusCode != 200){
                return new HttpResult();
            }
            if(httpResponse!=null){
                httpResponse.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new HttpResult();
    }


    public static void download(String url, File file, Header[] headers, TaskDetail taskDetail){
        InputStream inputStream = null;
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)){
            HttpGet httpGet = new HttpGet(url);
            if (headers != null) {
                httpGet.setHeaders(headers);
            }
            CloseableHttpResponse execute = httpClient.execute(httpGet);
            inputStream = execute.getEntity().getContent();
            byte[] buff = new byte[1024*1024]; //如果是稍微大的文件，这里配置的大一些
            int len = 0;
            Thread thread = Thread.currentThread();
            while((len = inputStream.read(buff)) > 0) {
                //把从服务端读取的文件流保存到ByteArrayOutputSteam中
                if (thread.isInterrupted()) {
                    taskDetail.setStatus(3);
                    Date date = new Date();
                    taskDetail.setMsg(taskDetail.getMsg() + date.toString() + "interrupted;");
                    execute.close();
                    return;
                }
                fileOutputStream.write(buff, 0, len);
                fileOutputStream.flush();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    /**
     * 发送Get请求
     * @param url 请求的地址
     * @param parameters 请求的数据
     * @param cookies 请求的Cookie信息
     * @param headers 请求的头部信息
     * @param charset 请求数据的字符编码
     */
    public static HttpResult sendGet(String url, Map<String, String> parameters, Header[] headers, Cookie[] cookies, String charset){
        HttpResult httpResult = null;
        try {
            // 创建URI并设置参数
            URIBuilder builder = new URIBuilder(url);
            if (parameters != null && !parameters.isEmpty()) {
                builder.addParameters(assemblyParameter(parameters));
            }
            if (charset != null) {
                builder.setCharset(Charset.forName(charset));
            }
            URI uri = builder.build();

            // 创建HttpGet
            HttpGet httpGet = new HttpGet(uri);

            // 设置Header
            if (headers != null) {
                /**
                 * httpGet.setHeaders(headers)，重新设置Header，前面set或者add的Header都会被去掉
                 * httpGet.setHeader(header)：如果已经有了同名的Header，则覆盖同名的Header，如果没有，则添加Header
                 * httpGet.addHeader(header)：不管是否已经有了同名的Header，都添加Header，可能会导致存在同名的Header
                 */
                httpGet.setHeaders(headers);
            }

            // 设置Cookie
            if (cookies != null) {
                /**
                 * Cookie必须通过Header来设置
                 */
                httpGet.setHeader("cookie", assemblyCookie(cookies));
            }

            // 发送Post请求并得到响应结果
            httpResult = httpClient.execute(httpGet, getResponseHandler());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return httpResult;
    }

    /**
     * 发送Post请求(Form格式的数据)
     * @param url 请求的地址
     * @param parameters 请求的Form数据
     * @param cookies 请求的Cookie信息
     * @param headers 请求的头部信息
     * @param charset 请求数据的字符编码
     */
    public static HttpResult sendPostForm(String url, Map<String, String> parameters, Header[] headers, Cookie[] cookies, String charset) {
        HttpResult httpResult = null;
        try {
            // 创建HttpPost
            HttpPost httpPost = new HttpPost(url);

            // 设置请求数据
            if (parameters != null && !parameters.isEmpty()) {
                httpPost.setEntity(assemblyFormEntity(parameters, charset));
            }

            // 设置Header
            if (headers != null) {
                /**
                 * httpGet.setHeaders(headers)，重新设置Header，前面set或者add的Header都会被去掉
                 * httpGet.setHeader(header)：如果已经有了同名的Header，则覆盖同名的Header，如果没有，则添加Header
                 * httpGet.addHeader(header)：不管是否已经有了同名的Header，都添加Header，可能会导致存在同名的Header
                 */
                httpPost.setHeaders(headers);
            }

            // 设置Cookie
            if (cookies != null) {
                /**
                 * Cookie必须通过Header来设置
                 */
                httpPost.setHeader("cookie", assemblyCookie(cookies));
            }

            // 发送Post请求并得到响应结果
            httpResult = httpClient.execute(httpPost, getResponseHandler());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return httpResult;
    }

    /**
     * 发送Post请求(Json格式的数据)
     * @param url 请求的地址
     * @param jsonData 请求的Json数据
     * @param cookies 请求的Cookie信息
     * @param headers 请求的头部信息
     */
    public static HttpResult sendPostJson(String url, String jsonData, Header[] headers, Cookie[] cookies) {
        HttpResult httpResult = null;

        try {
            // 创建HttpPost
            HttpPost httpPost = new HttpPost(url);

            // 设置请求数据
            httpPost.setEntity(assemblyStringEntity(jsonData, "UTF-8"));

            // 设置Header
            if (headers != null) {
                /**
                 * httpGet.setHeaders(headers)，重新设置Header，前面set或者add的Header都会被去掉
                 * httpGet.setHeader(header)：如果已经有了同名的Header，则覆盖同名的Header，如果没有，则添加Header
                 * httpGet.addHeader(header)：不管是否已经有了同名的Header，都添加Header，可能会导致存在同名的Header
                 */
                httpPost.setHeaders(headers);
            }

            // 设置Cookie
            if (cookies != null) {
                /**
                 * Cookie必须通过Header来设置
                 */
                httpPost.setHeader("cookie", assemblyCookie(cookies));
            }

            // 发送Post请求并得到响应结果
            httpResult = httpClient.execute(httpPost, getResponseHandler());
        } catch (Exception e) {
            httpResult.setErrorMsg(e.getMessage());
        }
        return httpResult;
    }

    /**
     * 获取响应结果处理器（把响应结果封装成HttpResult对象）
     */
    private static ResponseHandler<HttpResult> getResponseHandler() {

        ResponseHandler<HttpResult> responseHandler = new ResponseHandler<HttpResult>() {
            @Override
            public HttpResult handleResponse(HttpResponse httpResponse) throws ClientProtocolException, IOException {
                if (httpResponse == null) {
                    throw new ClientProtocolException("HttpResponse is null");
                }

                StatusLine statusLine = httpResponse.getStatusLine();

                HttpEntity httpEntity = httpResponse.getEntity();
                if (statusLine == null) {
                    throw new ClientProtocolException("HttpResponse contains no StatusLine");
                }


                HttpResult httpResult = new HttpResult();
                if (httpEntity == null) {
                    httpResult.setStatusCode(202);
                    return httpResult;
                }
                httpResult.setStatusCode(statusLine.getStatusCode());
                ContentType contentType = ContentType.getOrDefault(httpEntity);
                httpResult.setContentType(contentType.toString());
                boolean isTextType = isTextType(contentType);
                httpResult.setTextType(isTextType);
                if (isTextType) {
                    httpResult.setData(EntityUtils.toString(httpEntity,Consts.UTF_8));
                } else {
                    httpResult.setByteArrayContent(EntityUtils.toByteArray(httpEntity));
                }
                httpResult.setCookies(cookieStore.getCookies());
                httpResult.setHeaders(httpResponse.getAllHeaders());

                return httpResult;
            }
        };
        return responseHandler;
    }

    /**
     * 组装Get请求的请求参数
     * @param parameters 参数信息集合
     */
    private static List<NameValuePair> assemblyParameter(Map<String, String> parameters) {
        List<NameValuePair> allParameter = new ArrayList<>();
        if (parameters != null && !parameters.isEmpty()) {
            for (String name : parameters.keySet()) {
                NameValuePair parameter = new BasicNameValuePair(name, parameters.get(name));
                allParameter.add(parameter);
            }
        }
        return allParameter;
    }

    /**
     * 组装Post请求的Form请求体
     * @param parameters 请求的表单参数
     * @param charset 请求参数的字符编码
     */
    private static UrlEncodedFormEntity assemblyFormEntity(Map<String, String> parameters, String charset) {
        List<NameValuePair> formParameters = assemblyParameter(parameters);
        UrlEncodedFormEntity formEntity = null;
        try {
            if (charset != null) {
                formEntity = new UrlEncodedFormEntity(formParameters, charset);
            } else {
                formEntity = new UrlEncodedFormEntity(formParameters);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return formEntity;
    }

    /**
     * 组装Post请求的Json请求体
     * @param jsonData 请求的Json数据
     * @param charset 请求参数的字符编码
     */
    private static StringEntity assemblyStringEntity(String jsonData, String charset) {
        /**
         * jsonData不能为null和""，否则无法创建StringEntity。
         * Json类型的请求体，必须传一个不为null的StringEntity给服务端。
         * 如果jsonData为null或""时，则进行特殊处理。
         */
        if (jsonData == null || jsonData.equals("")) {
            jsonData = "{}";
        }
        StringEntity stringEntity = new StringEntity(jsonData, ContentType.APPLICATION_JSON);
        if (charset != null) {
            stringEntity.setContentEncoding(charset);
        }
        return stringEntity;
    }

    /**
     * 组装Cookie
     * @param cookies 所有的Cookie数据
     */
    private static String assemblyCookie(Cookie[] cookies) {
        StringBuilder sb = new StringBuilder();
        if (cookies != null) {
            for(Cookie cookie : cookies) {
                sb.append(cookie.getName()).append("=").append(cookie.getValue()).append(";");
            }
        }
        return sb.toString();
    }

    /**
     * 判断响应的内容是否是文本类型
     * @param contentType 响应内容的类型
     */
    private static boolean isTextType(ContentType contentType) {
        if (contentType == null) {
            throw new RuntimeException("ContentType is null");
        }
        if (contentType.getMimeType().startsWith("text")) {
            return true;
        } else if (contentType.getMimeType().startsWith("image")) {
            return false;
        } else if (contentType.getMimeType().startsWith("application")) {
            if (contentType.getMimeType().contains("json") || contentType.getMimeType().contains("xml")) {
                return true;
            } else {
                return false;
            }
        } else if (contentType.getMimeType().startsWith("multipart")) {
            return false;
        } else {
            return true;
        }
    }
}
