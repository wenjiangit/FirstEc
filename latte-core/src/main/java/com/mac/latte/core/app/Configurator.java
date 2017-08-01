package com.mac.latte.core.app;

import com.joanzapata.android.iconify.IconFontDescriptor;
import com.joanzapata.android.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.WeakHashMap;

/**
 * 配置器，配置一些全局通用的东西
 * Created by mac on 2017/7/31.
 */

public class Configurator {

    private static final HashMap<String, Object> LATTE_CONFIGS = new HashMap<>();

    private static final List<IconFontDescriptor> ICONS = new ArrayList<>();

    private Configurator() {
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), false);
    }

    public HashMap<String, Object> getLatteConfigs() {
        return LATTE_CONFIGS;
    }

    public void configure() {
        initIcons();
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), true);
    }

    public Configurator withApiHost(String host) {
        LATTE_CONFIGS.put(ConfigType.API_HOST.name(), host);
        return this;
    }

    /**
     * 初始化矢量图标
     */
    private static void initIcons() {
        if (ICONS.size() > 0) {
            for (IconFontDescriptor descriptor : ICONS) {
                Iconify.with(descriptor);
            }
        }
    }

    public Configurator withIcon(IconFontDescriptor descriptor) {
        ICONS.add(descriptor);
        return this;
    }

    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder{
        private static final Configurator INSTANCE = new Configurator();
    }

    @SuppressWarnings("unchecked")
    public static <T> T getConfiguration(ConfigType type) {
        checkConfigReady();
        return (T) LATTE_CONFIGS.get(type.name());
    }

    private static void checkConfigReady() {
        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigType.CONFIG_READY.name());
        if (!isReady) {
            throw new RuntimeException("Configuration is not ready,please call configure");
        }
    }
}
