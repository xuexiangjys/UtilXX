package com.beardedhen.androidbootstrap.api.attributes;

import java.io.Serializable;

import android.content.Context;

/**
 * A Bootstrap Brand is a color which is used universally across many Bootstrap Views. An example is
 * the 'Info' Brand which colors views light blue.
 */
public interface BootstrapBrand extends Serializable {

    String KEY = "BootstrapBrand";

    /**
     * Retrieves the color that should be used for the default fill state
     *
     * @param context the current context
     * @return the color for the current brand
     */
    int defaultFill(Context context);

    /**
     * Retrieves the color that should be used for the default border state
     *
     * @param context the current context
     * @return the color for the current brand
     */
    int defaultEdge(Context context);

    /**
     * Retrieves the text color that should be used for the default state
     *
     * @param context the current context
     * @return the color for the current brand
     */
    int defaultTextColor(Context context);

    /**
     * Retrieves the color that should be used for the active fill state
     *
     * @param context the current context
     * @return the color for the current brand
     */
    int activeFill(Context context);

    /**
     * Retrieves the color that should be used for the active border state
     *
     * @param context the current context
     * @return the color for the current brand
     */
    int activeEdge(Context context);

    /**
     * Retrieves the text color that should be used for the active state
     *
     * @param context the current context
     * @return the color for the current brand
     */
    int activeTextColor(Context context);

    /**
     * Retrieves the color that should be used for the disabled fill state
     *
     * @param context the current context
     * @return the color for the current brand
     */
    int disabledFill(Context context);

    /**
     * Retrieves the color that should be used for the disabled border state
     *
     * @param context the current context
     * @return the color for the current brand
     */
    int disabledEdge(Context context);

    /**
     * Retrieves the text color that should be used for the disabled state
     *
     * @param context the current context
     * @return the color for the current brand
     */
    int disabledTextColor(Context context);

    int getColor();
}
