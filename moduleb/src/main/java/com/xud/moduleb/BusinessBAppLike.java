package com.xud.moduleb;

import com.xud.base.di.AppComponent;
import com.xud.base.modulekit.AppModuleComponentDelegate;
import com.xud.base.modulekit.BaseModuleKit;
import com.xud.base.modulekit.BusinessBModuleKit;
import com.xud.componentlib.applicationlike.IApplicationLike;
import com.xud.componentlib.router.Router;
import com.xud.componentservice.moduleb.BusinessBService;
import com.xud.moduleb.di.BusinessBAppComponent;
import com.xud.moduleb.di.DaggerBusinessBAppComponent;
import com.xud.moduleb.serviceimpl.BusinessBServiceImpl;

/**
 * Created by xud on 2018/4/7.
 */

public class BusinessBAppLike implements IApplicationLike {

    private AppModuleComponentDelegate componentDelegate = new AppModuleComponentDelegate() {
        @Override
        public AppComponent initAppComponent() {
            BusinessBAppComponent appComponent = DaggerBusinessBAppComponent.builder()
                    .baseAppComponent(BaseModuleKit.getInstance().getComponent())
                    .build();
            return appComponent;
        }
    };

    @Override
    public void onCreate() {
        Router.getInstance().addService(BusinessBService.class.getSimpleName(), new BusinessBServiceImpl());
        BusinessBModuleKit.getInstance().init(componentDelegate);
    }

    @Override
    public void onStop() {
        Router.getInstance().removeService(BusinessBService.class.getSimpleName());
    }
}
