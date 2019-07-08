package com.zz.garbageclassification.model.rx;

import android.text.TextUtils;
import com.zz.garbageclassification.model.http.model.LoginModel;
import com.zz.garbageclassification.util.AppUtils;
import com.zz.garbageclassification.util.ConcealUtil;
import com.zz.garbageclassification.util.SPUtil;
import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import org.reactivestreams.Publisher;
import retrofit2.HttpException;
import retrofit2.Response;

import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.List;

/**
 * <p> 文件描述 : <p>
 * <p> 作者 : zhuhewie <p>
 * <p> 创建时间 : 2019/3/12 <p>
 * <p> 更改时间 : 2019/3/12 <p>
 * <p> 版本号 : 1 <p>
 */
public class RetryLogin implements Function<Flowable<Throwable>, Publisher<?>> {

    int retryTimes = 3;
    int currentTime = 0;

    @Override
    public Publisher<?> apply(Flowable<Throwable> throwableFlowable) throws Exception {
        return throwableFlowable.flatMap(
                new Function<Throwable, Publisher<?>>() {
                    @Override
                    public Publisher<?> apply(Throwable throwable) throws Exception {
                        if (throwable instanceof HttpException && ++currentTime < retryTimes) {

                            //读取到异常数据
                            Response response = ((HttpException) throwable).response();
                            ResponseBody errBody = response.errorBody();
                            int errCode = response.code();

                            //
                            if (errBody != null) {
//                                String errString = errBody.string();
                                String errString = null;
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
                                //返回体信息不为空 并且,返回体message 包含"令牌失效"字段,重新登录
                                if (!TextUtils.isEmpty(errString) && errString.contains("令牌失效")) {
                                    List<String> userArray = AppUtils.Companion.getUserIdPwd();
                                    if (userArray == null || userArray.size() != 2) return Flowable.error(throwable);
                                    String userID = userArray.get(0);
                                    String userPassword = userArray.get(1);
                                    LoginModel loginModel = new LoginModel();
                                    return loginModel.loginByAccount(userID, userPassword);
                                }
                            }
                        }
                        return Flowable.error(throwable);

                    }
                }
        );

    }
}
