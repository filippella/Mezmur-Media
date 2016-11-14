/*
 * Copyright (c) 2016 Orthodox Mezmur Media
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.dalol.orthodoxmezmurmedia.utilities.keyboard;

import android.support.annotation.DrawableRes;
import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 11/14/2016
 */
public class KeyboardKey {

    @Retention(RetentionPolicy.RUNTIME)
    @IntDef({
            KEY_EVENT_NORMAL,
            KEY_EVENT_DELETE,
            KEY_EVENT_CANCEL,
            KEY_EVENT_ENTER,
            KEY_EVENT_CHANGE_KEYBOARD,
            KEY_EVENT_BACKSPACE,
            KEY_EVENT_SPACE,
            KEY_NEW_LINE,
            KEY_HIDE_KEYBOARD
    })
    public @interface KeyboardCommand {}

    public final static int KEY_EVENT_NORMAL = 0;
    public final static int KEY_EVENT_DELETE = 1;
    public final static int KEY_EVENT_CANCEL = 2;
    public final static int KEY_EVENT_ENTER = 3;
    public final static int KEY_EVENT_CHANGE_KEYBOARD = 4;
    public final static int KEY_EVENT_BACKSPACE = 5;
    public final static int KEY_EVENT_SPACE = 6;
    public final static int KEY_NEW_LINE = 7;
    public final static int KEY_HIDE_KEYBOARD = 8;

    private String charCode;
    private int columnCount;
    private int keyCommand;
    private int commandImage;
    private List<String> keyModifiers;

    public KeyboardKey(String charCode, List<String> keyModifiers, int columnCount, @KeyboardCommand int keyCommand,
                       @DrawableRes int commandImage) {
        this.charCode = charCode;
        this.keyModifiers = keyModifiers;
        this.columnCount = columnCount;
        this.keyCommand = keyCommand;
        this.commandImage = commandImage;
    }

    public String getCharCode() {
        return charCode;
    }

    public int getColumnCount() {
        return columnCount;
    }

    public int getKeyCommand() {
        return keyCommand;
    }

    public int getCommandImage() {
        return commandImage;
    }

    public List<String> getKeyModifiers() {
        return keyModifiers;
    }
}
