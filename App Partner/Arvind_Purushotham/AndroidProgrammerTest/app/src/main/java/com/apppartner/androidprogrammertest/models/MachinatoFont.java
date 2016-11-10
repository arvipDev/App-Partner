package com.apppartner.androidprogrammertest.models;

import android.content.res.AssetManager;
import android.graphics.Typeface;


public class MachinatoFont
{
    private static Typeface font = null;

    public static Typeface getBold(AssetManager manager)
    {
        font = Typeface.createFromAsset(manager, "fonts/bold.ttf");
        return font;
    }

    public static Typeface getExtraLight(AssetManager manager)
    {
        font = Typeface.createFromAsset(manager, "fonts/extralight.ttf");
        return font;
    }

    public static Typeface getRegular(AssetManager manager)
    {
        font = Typeface.createFromAsset(manager, "fonts/machinato.ttf");
        return font;
    }

    public static Typeface getLight(AssetManager manager)
    {
        font = Typeface.createFromAsset(manager, "fonts/light.ttf");
        return font;
    }

    public static Typeface getSemiBoldItalic(AssetManager manager)
    {
        font = Typeface.createFromAsset(manager, "fonts/semibolditalic.ttf");
        return font;
    }

    public static Typeface getItalic(AssetManager manager)
    {
        font = Typeface.createFromAsset(manager, "fonts/italic.ttf");
        return font;
    }

    public static Typeface getBoldItalic(AssetManager manager)
    {
        font = Typeface.createFromAsset(manager, "fonts/bolditalic.ttf");
        return font;
    }

    public static Typeface getSemiBold(AssetManager manager)
    {
        font = Typeface.createFromAsset(manager, "fonts/semibold.ttf");
        return font;
    }
}
