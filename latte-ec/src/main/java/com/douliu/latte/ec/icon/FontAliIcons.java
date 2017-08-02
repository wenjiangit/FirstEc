package com.douliu.latte.ec.icon;

import com.joanzapata.iconify.Icon;

/**
 *
 * Created by douliu on 2017/8/2.
 */

public enum FontAliIcons implements Icon{

    icon_ali_pay('\ue6b1'),
    icon_scan('\ue605');


    char character;

    private FontAliIcons(char character) {
        this.character = character;
    }

    public String key() {
        return this.name().replace('_', '-');
    }

    public char character() {
        return this.character;
    }


}
