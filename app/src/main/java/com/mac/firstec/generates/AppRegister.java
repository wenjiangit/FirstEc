package com.mac.firstec.generates;

import com.mac.latte.annotations.AppRegisterGenerator;
import com.mac.latte.core.wechat.templates.AppRegisterTemplate;

/**
 * Created by douliu on 2017/8/23.
 */
@AppRegisterGenerator(
        packageName = "com.mac.firstec",
        registerTemplate = AppRegisterTemplate.class
)
public interface AppRegister {

}
