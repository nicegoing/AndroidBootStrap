package presenter;

import com.androidbootstrap.constant.Constants;
import com.androidbootstrap.ui.activity.i.IMainView;

import presenter.base.BasePresenter;

/**
 * @author puhanhui
 * @version 1.0
 * @date 2016/9/3
 * @since 1.0
 */
public class MainPresenter extends BasePresenter {
    private IMainView iMainView;

    public MainPresenter(IMainView iMainView) {
        this.iMainView = iMainView;
    }

    void writeEmail(String email) {
        getDataManager().getSharePreferece().save(Constants.SP_KEY.EMAIL, email);
    }

    public String readEmail() {
        return getDataManager().getSharePreferece().getString(Constants.SP_KEY.EMAIL);
    }
}
