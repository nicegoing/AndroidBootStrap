package ${packageName};

public class ${activityClass} extends BaseActivity<${mainName}Presenter> implements I${mainName}View {


    @Override
    protected int getLayoutId() {
        return R.layout.${layoutName};
    }

    @Override
    protected void setActivityComponent(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

}
