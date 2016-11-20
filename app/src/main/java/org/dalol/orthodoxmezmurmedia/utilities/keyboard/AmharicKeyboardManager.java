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

import org.dalol.orthodoxmezmurmedia.R;

import java.util.ArrayList;
import java.util.List;

import static org.dalol.orthodoxmezmurmedia.utilities.keyboard.AmharicKeyboardModifiers.*;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 11/14/2016
 */
public class AmharicKeyboardManager {

    private List<String> EMPTY_LIST = new ArrayList<>();

    public List<KeyboardRow> getKeyboardStructure() {

        List<KeyboardRow> keyboardRows = new ArrayList<>();

        KeyboardRow firstRow = new KeyboardRow();

        firstRow.addKeyboardKey(new KeyboardKey(HA.getCharCode(), HA.getModifierList(), 1, KeyboardKey.KEY_EVENT_NORMAL, 0));
        firstRow.addKeyboardKey(new KeyboardKey(LA.getCharCode(), LA.getModifierList(), 1, KeyboardKey.KEY_EVENT_NORMAL, 0));
        firstRow.addKeyboardKey(new KeyboardKey(HHA.getCharCode(), HHA.getModifierList(), 1, KeyboardKey.KEY_EVENT_NORMAL, 0));
        firstRow.addKeyboardKey(new KeyboardKey(MA.getCharCode(), MA.getModifierList(), 1, KeyboardKey.KEY_EVENT_NORMAL, 0));
        firstRow.addKeyboardKey(new KeyboardKey(SZA.getCharCode(), SZA.getModifierList(), 1, KeyboardKey.KEY_EVENT_NORMAL, 0));
        firstRow.addKeyboardKey(new KeyboardKey(RA.getCharCode(), RA.getModifierList(), 1, KeyboardKey.KEY_EVENT_NORMAL, 0));
        firstRow.addKeyboardKey(new KeyboardKey(SA.getCharCode(), SA.getModifierList(), 1, KeyboardKey.KEY_EVENT_NORMAL, 0));
        firstRow.addKeyboardKey(new KeyboardKey(SHA.getCharCode(), SHA.getModifierList(), 1, KeyboardKey.KEY_EVENT_NORMAL, 0));
        firstRow.addKeyboardKey(new KeyboardKey(QA.getCharCode(), QA.getModifierList(), 1, KeyboardKey.KEY_EVENT_NORMAL, 0));
        firstRow.addKeyboardKey(new KeyboardKey(BA.getCharCode(), BA.getModifierList(), 1, KeyboardKey.KEY_EVENT_NORMAL, 0));
        firstRow.addKeyboardKey(new KeyboardKey(VA.getCharCode(), VA.getModifierList(), 1, KeyboardKey.KEY_EVENT_NORMAL, 0));

        KeyboardRow secondRow = new KeyboardRow();


        secondRow.addKeyboardKey(new KeyboardKey(TA.getCharCode(), TA.getModifierList(), 1, KeyboardKey.KEY_EVENT_NORMAL, 0));
        secondRow.addKeyboardKey(new KeyboardKey(CA.getCharCode(), CA.getModifierList(), 1, KeyboardKey.KEY_EVENT_NORMAL, 0));
        secondRow.addKeyboardKey(new KeyboardKey(XA.getCharCode(), XA.getModifierList(), 1, KeyboardKey.KEY_EVENT_NORMAL, 0));
        secondRow.addKeyboardKey(new KeyboardKey(NA.getCharCode(), NA.getModifierList(), 1, KeyboardKey.KEY_EVENT_NORMAL, 0));
        secondRow.addKeyboardKey(new KeyboardKey(NYA.getCharCode(), NYA.getModifierList(), 1, KeyboardKey.KEY_EVENT_NORMAL, 0));
        secondRow.addKeyboardKey(new KeyboardKey(A.getCharCode(), A.getModifierList(), 1, KeyboardKey.KEY_EVENT_NORMAL, 0));
        secondRow.addKeyboardKey(new KeyboardKey(KA.getCharCode(), KA.getModifierList(), 1, KeyboardKey.KEY_EVENT_NORMAL, 0));
        secondRow.addKeyboardKey(new KeyboardKey(KXA.getCharCode(), KXA.getModifierList(), 1, KeyboardKey.KEY_EVENT_NORMAL, 0));
        secondRow.addKeyboardKey(new KeyboardKey(WA.getCharCode(), WA.getModifierList(), 1, KeyboardKey.KEY_EVENT_NORMAL, 0));
        secondRow.addKeyboardKey(new KeyboardKey(A_0.getCharCode(), A_0.getModifierList(), 1, KeyboardKey.KEY_EVENT_NORMAL, 0));

        secondRow.addKeyboardKey(new KeyboardKey(ZA.getCharCode(), ZA.getModifierList(), 1, KeyboardKey.KEY_EVENT_NORMAL, 0));

        KeyboardRow thirdRow = new KeyboardRow();

        thirdRow.addKeyboardKey(new KeyboardKey(ZHA.getCharCode(), ZHA.getModifierList(), 1, KeyboardKey.KEY_EVENT_NORMAL, 0));
        thirdRow.addKeyboardKey(new KeyboardKey(YA.getCharCode(), YA.getModifierList(), 1, KeyboardKey.KEY_EVENT_NORMAL, 0));
        thirdRow.addKeyboardKey(new KeyboardKey(DA.getCharCode(), DA.getModifierList(), 1, KeyboardKey.KEY_EVENT_NORMAL, 0));
        thirdRow.addKeyboardKey(new KeyboardKey(JA.getCharCode(), JA.getModifierList(), 1, KeyboardKey.KEY_EVENT_NORMAL, 0));
        thirdRow.addKeyboardKey(new KeyboardKey(GA.getCharCode(), GA.getModifierList(), 1, KeyboardKey.KEY_EVENT_NORMAL, 0));
        thirdRow.addKeyboardKey(new KeyboardKey(THA.getCharCode(), THA.getModifierList(), 1, KeyboardKey.KEY_EVENT_NORMAL, 0));
        thirdRow.addKeyboardKey(new KeyboardKey(CHA.getCharCode(), CHA.getModifierList(), 1, KeyboardKey.KEY_EVENT_NORMAL, 0));
        thirdRow.addKeyboardKey(new KeyboardKey(PHA.getCharCode(), PHA.getModifierList(), 1, KeyboardKey.KEY_EVENT_NORMAL, 0));
        thirdRow.addKeyboardKey(new KeyboardKey(TSA.getCharCode(), TSA.getModifierList(), 1, KeyboardKey.KEY_EVENT_NORMAL, 0));
        thirdRow.addKeyboardKey(new KeyboardKey(TZA.getCharCode(), TZA.getModifierList(), 1, KeyboardKey.KEY_EVENT_NORMAL, 0));

        thirdRow.addKeyboardKey(new KeyboardKey("", EMPTY_LIST, 1, KeyboardKey.KEY_EVENT_BACKSPACE, R.mipmap.ic_backspace_white_24dp));

        KeyboardRow fourthRow = new KeyboardRow();

        fourthRow.addKeyboardKey(new KeyboardKey(FA.getCharCode(), FA.getModifierList(), 1, KeyboardKey.KEY_EVENT_NORMAL, 0));
        fourthRow.addKeyboardKey(new KeyboardKey(PA.getCharCode(), PA.getModifierList(), 1, KeyboardKey.KEY_EVENT_NORMAL, 0));
        fourthRow.addKeyboardKey(new KeyboardKey(AM_NUMBERS_ONE_TO_TEN.getCharCode(), AM_NUMBERS_ONE_TO_TEN.getModifierList(), 1, KeyboardKey.KEY_EVENT_NORMAL, 0));
        fourthRow.addKeyboardKey(new KeyboardKey(AM_NUMBERS_TWENTY_TO_TEN_THOUSAND.getCharCode(), AM_NUMBERS_TWENTY_TO_TEN_THOUSAND.getModifierList(), 1, KeyboardKey.KEY_EVENT_NORMAL, 0));
        fourthRow.addKeyboardKey(new KeyboardKey(" ", EMPTY_LIST, 3, KeyboardKey.KEY_EVENT_SPACE, R.mipmap.ic_space_bar_white_24dp));
        fourthRow.addKeyboardKey(new KeyboardKey(SYMBOLS.getCharCode(), SYMBOLS.getModifierList(), 1, KeyboardKey.KEY_EVENT_NORMAL, 0));
        fourthRow.addKeyboardKey(new KeyboardKey(EN_NUMBERS_ONE_TO_TEN.getCharCode(), EN_NUMBERS_ONE_TO_TEN.getModifierList(), 1, KeyboardKey.KEY_EVENT_NORMAL, 0));
        fourthRow.addKeyboardKey(new KeyboardKey("\n", EMPTY_LIST, 1, KeyboardKey.KEY_NEW_LINE, R.mipmap.ic_subdirectory_arrow_left_white_24dp));
        fourthRow.addKeyboardKey(new KeyboardKey("hide", EMPTY_LIST, 1, KeyboardKey.KEY_HIDE_KEYBOARD, R.mipmap.ic_keyboard_hide_white_24dp));

        keyboardRows.add(firstRow);
        keyboardRows.add(secondRow);
        keyboardRows.add(thirdRow);
        keyboardRows.add(fourthRow);
        return keyboardRows;
    }
}
