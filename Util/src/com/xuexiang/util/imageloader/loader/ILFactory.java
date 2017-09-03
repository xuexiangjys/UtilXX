package com.xuexiang.util.imageloader.loader;

/**
 * Created by wanglei on 2016/11/28.
 */
public class ILFactory {

    private static ILoader loader;
    public static final int IL_LOADING_RES = ILoader.Options.RES_NONE;
    public static final int IL_ERROR_RES = ILoader.Options.RES_NONE;

    public static ILoader getLoader() {
        if (loader == null) {
            synchronized (ILFactory.class) {
                if (loader == null) {
                    loader = new GlideLoader();
                }
            }
        }
        return loader;
    }


}
