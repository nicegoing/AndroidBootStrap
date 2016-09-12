package com.androidbootstrap.rx;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * compose()接收一个Transformer对象，Transformer继承自Func1<Observable<T>, Observable<R>>,
 * 可以通过它将一种类型的Observable转换成另一种类型的Observable
 */
public class RxSchedulersHelper {


    private final static Observable.Transformer ioTransformer = new Observable.Transformer() {
        @Override
        public Object call(Object o) {
            return ((Observable)o).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        }
    };

    public static <T> Observable.Transformer<T, T> applyIoSchedulers() {
        return (Observable.Transformer<T, T>) ioTransformer;
    }
}
