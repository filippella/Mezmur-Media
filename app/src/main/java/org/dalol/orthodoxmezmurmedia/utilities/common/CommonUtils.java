/*
 * Copyright (c) 2016 Amharic Mezmur Lyrics
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.dalol.orthodoxmezmurmedia.utilities.common;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 9/10/2016
 */
public class CommonUtils {

    /**
     * This method is used to compute a typed value
     * It takes the following parameters and gives a value based on the aspect ration/resolution of the screen
     *
     * @param context the context
     * @param unit the unit is DP, SP, DIP, PX
     * @param value the desired value
     * @return an exact value
     */
    public static int computeTypedValue(Context context, int unit, float value) {
        return Math.round(TypedValue.applyDimension(unit, value, context.getResources().getDisplayMetrics()));
    }

    /**
     * This method returns the screen width in density pixel
     *
     * @param context
     * @return
     */
    public static float getScreenWidthInDensityPixel(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return metrics.widthPixels / metrics.density;
    }

    /**
     * This method returns the screen height in density pixel
     *
     * @param context
     * @return
     */
    public static float getScreenHeightInDensityPixel(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return metrics.heightPixels / metrics.density;
    }
}
