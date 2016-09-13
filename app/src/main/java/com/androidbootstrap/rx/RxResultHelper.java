package com.androidbootstrap.rx;

import com.androidbootstrap.rx.error.NetworkConnectionException;
import com.androidbootstrap.rx.error.ServerException;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * 在subscribe之前对数据进行处理，让观察者拿到加工完成的数据源
 */
public class RxResultHelper {

    public static <T> Observable.Transformer<ApiResponse<T>, T> handleResult() {
        return new Observable.Transformer<ApiResponse<T>, T>() {
            @Override
            public Observable<T> call(Observable<ApiResponse<T>> apiResponseObservable) {
                return apiResponseObservable.flatMap(
                        new Func1<ApiResponse<T>, Observable<T>>() {
                            @Override
                            public Observable<T> call(ApiResponse<T> apiResponse) {

                                if (apiResponse == null) {
                                    return Observable.error(new NetworkConnectionException());
                                } else if (apiResponse.isSuccess()) {
                                    return createData(apiResponse.result());
                                } else {
                                    return Observable.error(new ServerException(apiResponse.code(), apiResponse.msg()));
                                }
                            }
                        }
                );
            }
        };
    }


    public static <T> Observable<T> createData(final T t) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                try {
                    subscriber.onNext(t);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }
}

