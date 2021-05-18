package ru.svolf.melissa.util;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;

import androidx.annotation.AttrRes;
import androidx.annotation.ColorInt;

public class Swatcher {
    /**
     * Получение цвета из attr. Пригодится, когда тебе нужно будет изменить цвет какого-то
     * элемента из кода, но при этом требуется, чтобы он соответствовал текущей
     * теме приложения.
     * Этот метод очень сильно зависит от контекста, который ты передашь, поэтому ЖЕЛАТЕЛЬНО
     * использовать контекст объекта, а не контекст приложения.
     * <p>
     * Например, для Activity: <b>this</b>
     * Для фрагмента: Fragment.getContext()
     * Для View: {@link android.view.View#getContext()}
     *
     * @param context контекст текущего обьекта
     * @param attr    ресурс, из которого нужно извлечь цвет
     * @return цвет или {@link Color#RED} если контекст темы не содержит данного атрибута
     */
    @ColorInt
    public static int getColorFromAttr(Context context, @AttrRes int attr) {
        TypedValue typedValue = new TypedValue();
        if (context != null && context.getTheme().resolveAttribute(attr, typedValue, true))
            return typedValue.data;
        else
            return Color.RED;
    }

}
