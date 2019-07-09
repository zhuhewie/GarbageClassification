package com.zz.garbageclassification.util;

import android.text.TextUtils;
import android.util.Log;
import com.zz.garbageclassification.model.http.ApiException;
import com.google.gson.JsonObject;
import io.reactivex.*;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Response;
import retrofit2.adapter.rxjava2.HttpException;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.concurrent.Callable;

/**
 * Created by codeest on 2016/8/3.
 */
public class RxUtil {

    /**
     * 统一线程处理
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<T, T> rxSchedulerHelper() {    //compose简化线程
        return new FlowableTransformer<T, T>() {
            @Override
            public Flowable<T> apply(Flowable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 统一返回结果处理
     * @param <T>
     * @return
     */
//    public static <T> FlowableTransformer<GankHttpResponse<T>, T> handleResult() {   //compose判断结果
//        return new FlowableTransformer<GankHttpResponse<T>, T>() {
//            @Override
//            public Flowable<T> apply(Flowable<GankHttpResponse<T>> httpResponseFlowable) {
//                return httpResponseFlowable.flatMap(new Function<GankHttpResponse<T>, Flowable<T>>() {
//                    @Override
//                    public Flowable<T> apply(GankHttpResponse<T> tGankHttpResponse) {
//                        if(!tGankHttpResponse.getError()) {
//                            return createData(tGankHttpResponse.getResults());
//                        } else {
//                            return Flowable.error(new ApiException("服务器返回error"));
//                        }
//                    }
//                });
//            }
//        };
//    }
//
//    /**
//     * 统一返回结果处理
//     * @param <T>
//     * @return
//     */
//    public static <T> FlowableTransformer<WXHttpResponse<T>, T> handleWXResult() {   //compose判断结果
//        return new FlowableTransformer<WXHttpResponse<T>, T>() {
//            @Override
//            public Flowable<T> apply(Flowable<WXHttpResponse<T>> httpResponseFlowable) {
//                return httpResponseFlowable.flatMap(new Function<WXHttpResponse<T>, Flowable<T>>() {
//                    @Override
//                    public Flowable<T> apply(WXHttpResponse<T> tWXHttpResponse) {
//                        if(tWXHttpResponse.getCode() == 200) {
//                            return createData(tWXHttpResponse.getNewslist());
//                        } else {
//                            return Flowable.error(new ApiException(tWXHttpResponse.getMsg(), tWXHttpResponse.getCode()));
//                        }
//                    }
//                });
//            }
//        };
//    }
//
//    /**
//     * 统一返回结果处理
//     * @param <T>
//     * @return
//     */
//    public static <T> FlowableTransformer<MyHttpResponse<T>, T> handleMyResult() {   //compose判断结果
//        return new FlowableTransformer<MyHttpResponse<T>, T>() {
//            @Override
//            public Flowable<T> apply(Flowable<MyHttpResponse<T>> httpResponseFlowable) {
//                return httpResponseFlowable.flatMap(new Function<MyHttpResponse<T>, Flowable<T>>() {
//                    @Override
//                    public Flowable<T> apply(MyHttpResponse<T> tMyHttpResponse) {
//                        if(tMyHttpResponse.getCode() == 200) {
//                            return createData(tMyHttpResponse.getQueData());
//                        } else {
//                            return Flowable.error(new ApiException(tMyHttpResponse.getMessage(), tMyHttpResponse.getCode()));
//                        }
//                    }
//                });
//            }
//        };
//    }
//
//    /**
//     * 统一返回结果处理
//     * @param <T>
//     * @return
//     */
//    public static <T> FlowableTransformer<GoldHttpResponse<T>, T> handleGoldResult() {   //compose判断结果
//        return new FlowableTransformer<GoldHttpResponse<T>, T>() {
//            @Override
//            public Flowable<T> apply(Flowable<GoldHttpResponse<T>> httpResponseFlowable) {
//                return httpResponseFlowable.flatMap(new Function<GoldHttpResponse<T>, Flowable<T>>() {
//                    @Override
//                    public Flowable<T> apply(GoldHttpResponse<T> tGoldHttpResponse) {
//                        if(tGoldHttpResponse.getResults() != null) {
//                            return createData(tGoldHttpResponse.getResults());
//                        } else {
//                            return Flowable.error(new ApiException("服务器返回error"));
//                        }
//                    }
//                });
//            }
//        };
//    }


    /**
     * 请求错误的统一处理
     *
     * @param throwable
     * @return
     */
    public static String handleResult(Throwable throwable) {
        try {
            if (throwable instanceof ApiException) {
                return httpErrorCodeMess(((ApiException) throwable).getCode(), ((ApiException) throwable).getMessage());
            }
            if (throwable instanceof HttpException) {

                ResponseBody errBody = ((HttpException) throwable).response().errorBody();
                Response response = ((HttpException) throwable).response();
                int errCode = response.code();

                if (errBody != null) {
                    try {
                        String errString = errBody.string();

                        //errString有时可能会为空,错误信息在source
                        if (StringUtil.INSTANCE.isEmpty(errString)) {
                            BufferedSource source = errBody.source();
                            if (source != null) {
                                source.request(Long.MAX_VALUE); // Buffer the entire body.
                                Buffer buffer = source.buffer();
                                Charset charset = Charset.forName("UTF-8");
                                MediaType contentType = errBody.contentType();
                                if (contentType != null) {
                                    try {
                                        charset = contentType.charset(Charset.forName("UTF-8"));
                                        errString = buffer.clone().readString(charset);
                                    } catch (UnsupportedCharsetException e) {
                                    }
                                }

                            }

                        }
//                        String bytesString = new String(errBody.bytes());
//                        Log.e("RxUtil","errString = "+errString
//                                + ";;;;;errBody.bytes"+new String(errBody.bytes())
//                                + ";;;;;errBody.charStream"+errBody.charStream().toString()
//                                + ";;;;;errBody.source"+errBody.source().toString()
//                                + ";;;;errBody.byteStream=" + errBody.byteStream().toString());
                        return httpErrorCodeMess(errCode, errString);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return "错误信息无法显示";
                    }
                } else {
                    return httpErrorCodeMess(errCode, "错误信息为空");
                }
            } else if (throwable instanceof JSONException) {
                return "数据解析异常";
            } else if (throwable instanceof SocketTimeoutException){
                return "请求超时";
            }
            else {
                return "请求出现异常";
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "请求出现异常";
        }
    }

    private static String getErrString(String errString) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(errString);
            return jsonObject.optString("message","未知错误原因");
        } catch (JSONException e) {
            e.printStackTrace();
            //非json格式的返回,直接显示
            return errString;
        }
    }

    /**
     * 根据网络请求状态码,转换为提示用户的信息
     * @param errorCode
     * @param errString
     * @return
     */
    public static String httpErrorCodeMess(int errorCode,String errString){
        if (300 <= errorCode && errorCode <= 399) {
            return "业务异常";
        } else if (400 <= errorCode && errorCode <= 499){
            if (TextUtils.isEmpty(errString)){
                return "业务异常";

            } else if (errString.contains("html")){
                return "请检查网络";
            } else {
                //服务器返回信息
                return getErrString(errString);
            }
        } else if (500 <= errorCode && errorCode <= 599){
            return "服务器异常";
        } else {
            return "请求失败";
        }

    }

    /**
     * 生成Flowable
     * @param <T>
     * @return
     */
    public static <T> Flowable<T> createData(final T t) {
        return Flowable.create(new FlowableOnSubscribe<T>() {
            @Override
            public void subscribe(FlowableEmitter<T> emitter) throws Exception {
                try {
                    emitter.onNext(t);
                    emitter.onComplete();
                } catch (Exception e) {
                    emitter.onError(e);
                }
            }
        }, BackpressureStrategy.BUFFER);
    }


    /**
     * 获取缓存token 返回flowable
     * @return
     */
    public static Callable<Flowable<String>> getToken(){
        return new Callable<Flowable<String>>() {
            @Override
            public Flowable<String> call() throws Exception {
                return Flowable.just("");
            }
        };
    }
}
