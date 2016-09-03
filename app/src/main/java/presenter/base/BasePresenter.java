package presenter.base;

import com.androidbootstrap.data.DataManager;
import com.androidbootstrap.inject.component.DaggerDataManagerComponent;

import javax.inject.Inject;

/**
 * @author puhanhui
 * @version 1.0
 * @date 2016/9/3
 * @since 1.0
 */
public  class BasePresenter {
    @Inject
    DataManager dataManager;

    public BasePresenter() {
        DaggerDataManagerComponent.builder().build();
    }

    public DataManager getDataManager() {
        return dataManager;
    }
}
