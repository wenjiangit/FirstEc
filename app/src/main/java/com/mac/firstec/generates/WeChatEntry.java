package com.mac.firstec.generates;

import com.mac.latte.annotations.EntryGenerator;
import com.mac.latte.core.wechat.templates.WXEntryTemplate;

/**
 * Created by douliu on 2017/8/21.
 */
@EntryGenerator(
        packageName = "com.mac.firstec",
        entryTemplate = WXEntryTemplate.class
)
public interface WeChatEntry {

}
