package com.zz.garbageclassification.model.rx;

import com.zz.garbageclassification.model.bean.ResponseBodyBean;
import com.zz.garbageclassification.model.http.ApiException;
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

/**
 * <p> 文件描述 : <p>
 * <p> 作者 : zhuhewie <p>
 * <p> 创建时间 : 2019/3/12 <p>
 * <p> 更改时间 : 2019/3/12 <p>
 * <p> 版本号 : 1 <p>
 */
public class ErrorResponse<T> implements Function<ResponseBodyBean<T>, Publisher<T>> {



    @Override
    public Publisher<T> apply(ResponseBodyBean<T> responseBodyBean) throws Exception {
        if (responseBodyBean!= null && responseBodyBean.getCode()==0) {
            return Flowable.error(new ApiException(responseBodyBean.getCode(),responseBodyBean.getMsg()));
        } else  {
            return Flowable.just(responseBodyBean.getData());
        }
    }
}
