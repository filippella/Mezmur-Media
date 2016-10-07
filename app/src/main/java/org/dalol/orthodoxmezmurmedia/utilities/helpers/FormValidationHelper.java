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

package org.dalol.orthodoxmezmurmedia.utilities.helpers;

import android.text.TextUtils;
import android.widget.EditText;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 9/11/2016
 */
public class FormValidationHelper {

    public static boolean validateField(String field) {
        return TextUtils.isEmpty(field);
    }

    /**
     * This method is used to validate an array of editTexts
     *
     * @param editTexts
     * @return
     */
    public static boolean validateFields(EditText... editTexts) {
        boolean result = true;
        for (EditText editText : editTexts) {
            if (TextUtils.isEmpty(editText.getText().toString())) {
                editText.setError("Required");
                result = false;
            } else {
                editText.setError(null);
            }
        }
        return result;
    }
}
