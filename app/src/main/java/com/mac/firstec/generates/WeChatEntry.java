package com.mac.firstec.generates;

import com.mac.latte.annotations.EntryGenerator;
import com.mac.latte.core.wechat.templates.WxEntryTemplate;

/**
 * Created by douliu on 2017/8/21.
 */
@EntryGenerator(
        packageName = "com.mac.firstec",
        entryTemplate = WxEntryTemplate.class
)
public interface WeChatEntry {

}
