package com.mac.firstec.generates;

import com.mac.latte.annotations.PayEntryGenerator;
import com.mac.latte.core.wechat.templates.PayEntryTemplate;

/**
 *
 * Created by douliu on 2017/8/23.
 */
@PayEntryGenerator(
        packageName = "com.mac.firstec",
        entryTemplate = PayEntryTemplate.class
)
public interface WeChatPayEntry {
}
