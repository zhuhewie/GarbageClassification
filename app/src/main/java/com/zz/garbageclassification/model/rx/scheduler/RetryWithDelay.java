package com.zz.garbageclassification.model.rx.scheduler;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

/**
 * <p> 文件描述 : <p>
 * <p> 作者 : zhuhewie <p>
 * <p> 创建时间 : 2019/3/12 <p>
 * <p> 更改时间 : 2019/3/12 <p>
 * <p> 版本号 : 1 <p>
 */
public class RetryWithDelay implements Function<Observable<? extends Throwable>,Observable<?>> {

    @Override
    public Observable<?> apply(Observable<? extends Throwable> observable) throws Exception {
        return observable
                .flatMap(new Function<Throwable, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Throwable throwable) throws Exception {

                        return Observable.error(throwable);
                    }
                });
    }
}
