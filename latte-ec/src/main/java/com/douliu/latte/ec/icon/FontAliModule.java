package com.douliu.latte.ec.icon;

import com.joanzapata.iconify.Icon;
import com.joanzapata.iconify.IconFontDescriptor;

/**
 *
 * Created by douliu on 2017/8/2.
 */

public class FontAliModule implements IconFontDescriptor{

    public FontAliModule() {
    }

    @Override
    public String ttfFileName() {
        return "iconfont.ttf";
    }

    @Override
    public Icon[] characters() {
        return FontAliIcons.values();
    }

}
