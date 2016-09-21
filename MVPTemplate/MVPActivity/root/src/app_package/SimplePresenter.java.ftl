package ${packageName};

public class ${mainName}Presenter extends BasePresenter<I${mainName}View> {
    protected DataManager dataManager;

    @Inject
    public ${mainName}Presenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }
}
