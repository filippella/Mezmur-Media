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

package org.dalol.orthodoxmezmurmedia.utilities.helpers;

import org.dalol.orthodoxmezmurmedia.R;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 11/10/2016
 */
public enum KeyTypography implements ITypography {

    /**
     * Special key characters(Clear, backspace, enter...
     */
    BACKSPACE(' ', ' ', "", "BACK SPACE", 2, R.mipmap.ic_backspace_white_24dp, KeyEventType.KEY_EVENT_NORMAL),
    SPACEBAR(' ', ' ', "", "SPACE BAR", 2, R.mipmap.ic_space_bar_white_24dp, KeyEventType.KEY_EVENT_NORMAL),

    //SYMBOLS
    SECTION_MARK('\u1360', '፠', "ETHIOPIC SECTION MARK", "SECTION MARK", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    WORD_SPACE('\u1361', '፡', "ETHIOPIC WORDSPACE", "WORDSPACE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    FULL_STOP('\u1362', '።', "ETHIOPIC FULL STOP", "FULL STOP", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    COMMA('\u1363', '፣', "ETHIOPIC COMMA", "COMMA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    SEMICOLON('\u1364', '፤', "ETHIOPIC SEMICOLON", "SEMICOLON", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    COLON('\u1365', '፥', "ETHIOPIC COLON", "COLON", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    PREFACE_COLON('\u1366', '፦', "ETHIOPIC PREFACE COLON", "PREFACE COLON", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    QUESTION_MARK('\u1367', '፧', "ETHIOPIC QUESTION MARK", "QUESTION MARK", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    PARAGRAPH_SEPARATOR('\u1367', '፨', "ETHIOPIC PARAGRAPH SEPARATOR", "PARAGRAPH SEPARATOR", 1, 0, KeyEventType.KEY_EVENT_NORMAL),


    //NUMBERS(1-10)
    ONE_AM('\u1369', '፩', "ETHIOPIC DIGIT ONE", "AND", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    TWO_AM('\u136A', '፪', "ETHIOPIC DIGIT TWO", "HULET", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    THREE_AM('\u136B', '፫', "ETHIOPIC DIGIT THREE", "SOST", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    FOUR_AM('\u136C', '፬', "ETHIOPIC DIGIT FOUR", "ARAT", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    FIVE_AM('\u136D', '፭', "ETHIOPIC DIGIT FIVE", "AMIST", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    SIX_AM('\u136E', '፮', "ETHIOPIC DIGIT SIX", "SIDIST", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    SEVEN_AM('\u136F', '፯', "ETHIOPIC DIGIT SEVEN", "SEBAT", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    EIGHT_AM('\u1370', '፰', "ETHIOPIC DIGIT EIGHT", "SIMINT", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    NINE_AM('\u1371', '፱', "ETHIOPIC DIGIT NINE", "ZETEGN", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    TEN_AM('\u1372', '፲', "ETHIOPIC NUMBER TEN", "ASIR", 1, 0, KeyEventType.KEY_EVENT_NORMAL),

    //NUMBERS(10-1000)
    TWENTY_AM('\u1373', '፳', "ETHIOPIC NUMBER TWENTY", "HAYA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    THIRTY_AM('\u1374', '፴', "ETHIOPIC NUMBER THIRTY", "SELASA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    FOURTY_AM_('\u1375', '፵', "ETHIOPIC NUMBER FORTY", "ARBA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    FIFTY_AM('\u1376', '፶', "ETHIOPIC NUMBER FIFTY", "HAMSA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    SICTY_AM('\u1377', '፷', "ETHIOPIC NUMBER SIXTY", "SILSA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    SEVENTY_AM('\u1378', '፸', "ETHIOPIC NUMBER SEVENTY", "SEBA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    EIGHTY_AM('\u1379', '፹', "ETHIOPIC NUMBER EIGHTY", "SEMANIA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    NINETY_AM('\u137A', '፺', "ETHIOPIC NUMBER NINETY", "ZETENA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    HUNDRED_AM('\u137B', '፻', "ETHIOPIC NUMBER HUNDRED", "METO", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    THOUSAND_AM('\u137C', '፼', "ETHIOPIC NUMBER TEN THOUSAND", "SHIH", 1, 0, KeyEventType.KEY_EVENT_NORMAL),

    //HA
    HA('\u1200', 'ሀ', "ETHIOPIC SYLLABLE", "HA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    HU('\u1201', 'ሁ', "ETHIOPIC SYLLABLE", "HU", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    HI('\u1202', 'ሂ', "ETHIOPIC SYLLABLE", "HI", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    HAA('\u1203', 'ሃ', "ETHIOPIC SYLLABLE", "HAA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    HEE('\u1204', 'ሄ', "ETHIOPIC SYLLABLE", "HEE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    HE('\u1205', 'ህ', "ETHIOPIC SYLLABLE", "HE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    HO('\u1206', 'ሆ', "ETHIOPIC SYLLABLE", "HO", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    HOA('\u1207', 'ሇ', "ETHIOPIC SYLLABLE", "HOA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),

    //LE
    LA('\u1208', 'ለ', "ETHIOPIC SYLLABLE", "LA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    LU('\u1209', 'ሉ', "ETHIOPIC SYLLABLE", "LU", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    LI('\u120A', 'ሊ', "ETHIOPIC SYLLABLE", "LI", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    LAA('\u120B', 'ላ', "ETHIOPIC SYLLABLE", "LAA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    LEE('\u120C', 'ሌ', "ETHIOPIC SYLLABLE", "LEE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    LE('\u120D', 'ል', "ETHIOPIC SYLLABLE", "LE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    LO('\u120E', 'ሎ', "ETHIOPIC SYLLABLE", "LO", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    LWA('\u120F', 'ሏ', "ETHIOPIC SYLLABLE", "LWA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),

    //HA
    HHA('\u1210', 'ሐ', "ETHIOPIC SYLLABLE", "HHA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    HHU('\u1211', 'ሑ', "ETHIOPIC SYLLABLE", "HHU", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    HHI('\u1212', 'ሒ', "ETHIOPIC SYLLABLE", "HHI", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    HHAA('\u1213', 'ሓ', "ETHIOPIC SYLLABLE", "HHAA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    HHEE('\u1214', 'ሔ', "ETHIOPIC SYLLABLE", "HHEE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    HHE('\u1215', 'ሕ', "ETHIOPIC SYLLABLE", "HHE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    HHO('\u1216', 'ሖ', "ETHIOPIC SYLLABLE", "HHO", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    HHWA('\u1217', 'ሗ', "ETHIOPIC SYLLABLE", "HHWA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),


    //ME
    MA('\u1218', 'መ', "ETHIOPIC SYLLABLE", "MA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    MU('\u1219', 'ሙ', "ETHIOPIC SYLLABLE", "MU", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    MI('\u121A', 'ሚ', "ETHIOPIC SYLLABLE", "MI", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    MAA('\u121B', 'ማ', "ETHIOPIC SYLLABLE", "MAA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    MEE('\u121C', 'ሜ', "ETHIOPIC SYLLABLE", "MEE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    ME('\u121D', 'ም', "ETHIOPIC SYLLABLE", "ME", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    MO('\u121E', 'ሞ', "ETHIOPIC SYLLABLE", "MO", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    MWA('\u121F', 'ሟ', "ETHIOPIC SYLLABLE", "MWA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),


    //SE
    SZA('\u1220', 'ሠ', "ETHIOPIC SYLLABLE", "SZA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    SZU('\u1221', 'ሡ', "ETHIOPIC SYLLABLE", "SZU", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    SZI('\u1222', 'ሢ', "ETHIOPIC SYLLABLE", "SZI", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    SZAA('\u1223', 'ሣ', "ETHIOPIC SYLLABLE", "SZAA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    SZEE('\u1224', 'ሤ', "ETHIOPIC SYLLABLE", "SZEE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    SZE('\u1225', 'ሥ', "ETHIOPIC SYLLABLE", "SZE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    SZO('\u1226', 'ሦ', "ETHIOPIC SYLLABLE", "SZO", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    SZWA('\u1227', 'ሧ', "ETHIOPIC SYLLABLE", "SZWA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),


    //RE
    RA('\u1228', 'ረ', "ETHIOPIC SYLLABLE", "RA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    RU('\u1229', 'ሩ', "ETHIOPIC SYLLABLE", "RU", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    RI('\u122A', 'ሪ', "ETHIOPIC SYLLABLE", "RI", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    RAA('\u122B', 'ራ', "ETHIOPIC SYLLABLE", "RAA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    REE('\u122C', 'ሬ', "ETHIOPIC SYLLABLE", "REE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    RE('\u122D', 'ር', "ETHIOPIC SYLLABLE", "RE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    RO('\u122E', 'ሮ', "ETHIOPIC SYLLABLE", "RO", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    RWA('\u122F', 'ሯ', "ETHIOPIC SYLLABLE", "RWA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),

    //SE
    SA('\u1230', 'ሰ', "ETHIOPIC SYLLABLE", "SA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    SU('\u1231', 'ሱ', "ETHIOPIC SYLLABLE", "SU", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    SI('\u1232', 'ሲ', "ETHIOPIC SYLLABLE", "SI", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    SAA('\u1233', 'ሳ', "ETHIOPIC SYLLABLE", "SAA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    SEE('\u1234', 'ሴ', "ETHIOPIC SYLLABLE", "SEE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    SE('\u1235', 'ስ', "ETHIOPIC SYLLABLE", "SE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    SO('\u1236', 'ሶ', "ETHIOPIC SYLLABLE", "SO", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    SWA('\u1237', 'ሷ', "ETHIOPIC SYLLABLE", "SWA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),

    //SHE
    SHA('\u1238', 'ሸ', "ETHIOPIC SYLLABLE", "SHA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    SHU('\u1239', 'ሹ', "ETHIOPIC SYLLABLE", "SHU", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    SHI('\u123A', 'ሺ', "ETHIOPIC SYLLABLE", "SHI", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    SHAA('\u123B', 'ሻ', "ETHIOPIC SYLLABLE", "SHAA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    SHEE('\u123C', 'ሼ', "ETHIOPIC SYLLABLE", "SHEE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    SHE('\u123D', 'ሽ', "ETHIOPIC SYLLABLE", "SHE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    SHO('\u123E', 'ሾ', "ETHIOPIC SYLLABLE", "SHO", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    SHWA('\u123F', 'ሿ', "ETHIOPIC SYLLABLE", "SHWA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),

    //QE
    QA('\u1240', 'ቀ', "ETHIOPIC SYLLABLE", "QA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    QU('\u1241', 'ቁ', "ETHIOPIC SYLLABLE", "QU", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    QI('\u1242', 'ቂ', "ETHIOPIC SYLLABLE", "QI", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    QAA('\u1243', 'ቃ', "ETHIOPIC SYLLABLE", "QAA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    QEE('\u1244', 'ቄ', "ETHIOPIC SYLLABLE", "QEE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    QE('\u1245', 'ቅ', "ETHIOPIC SYLLABLE", "QE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    QO('\u1246', 'ቆ', "ETHIOPIC SYLLABLE", "QO", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    QWAA('\u124B', 'ቋ', "ETHIOPIC SYLLABLE", "QWAA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),

    //BE
    BA('\u1260', 'በ', "ETHIOPIC SYLLABLE", "BA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    BU('\u1261', 'ቡ', "ETHIOPIC SYLLABLE", "BU", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    BI('\u1262', 'ቢ', "ETHIOPIC SYLLABLE", "BI", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    BAA('\u1263', 'ባ', "ETHIOPIC SYLLABLE", "BAA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    BEE('\u1264', 'ቤ', "ETHIOPIC SYLLABLE", "BEE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    BE('\u1265', 'ብ', "ETHIOPIC SYLLABLE", "BE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    BO('\u1266', 'ቦ', "ETHIOPIC SYLLABLE", "BO", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    BWA('\u1267', 'ቧ', "ETHIOPIC SYLLABLE", "BWA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),

    //VE
    VA('\u1268', 'ቨ', "ETHIOPIC SYLLABLE", "VA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    VU('\u1269', 'ቩ', "ETHIOPIC SYLLABLE", "VU", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    VI('\u126A', 'ቪ', "ETHIOPIC SYLLABLE", "VI", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    VAA('\u126B', 'ቫ', "ETHIOPIC SYLLABLE", "VAA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    VEE('\u126C', 'ቬ', "ETHIOPIC SYLLABLE", "VEE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    VE('\u126D', 'ቭ', "ETHIOPIC SYLLABLE", "VE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    VO('\u126E', 'ቮ', "ETHIOPIC SYLLABLE", "VO", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    VWA('\u126F', 'ቯ', "ETHIOPIC SYLLABLE", "VWA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),

    //TE
    TA('\u1270', 'ተ', "ETHIOPIC SYLLABLE", "TA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    TU('\u1271', 'ቱ', "ETHIOPIC SYLLABLE", "TU", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    TI('\u1272', 'ቲ', "ETHIOPIC SYLLABLE", "TI", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    TAA('\u1273', 'ታ', "ETHIOPIC SYLLABLE", "TAA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    TEE('\u1274', 'ቴ', "ETHIOPIC SYLLABLE", "TEE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    TE('\u1275', 'ት', "ETHIOPIC SYLLABLE", "TE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    TO('\u1276', 'ቶ', "ETHIOPIC SYLLABLE", "TO", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    TWA('\u1277', 'ቷ', "ETHIOPIC SYLLABLE", "TWA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),


    //CE
    CA('\u1278', 'ቸ', "ETHIOPIC SYLLABLE", "CA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    CU('\u1279', 'ቹ', "ETHIOPIC SYLLABLE", "CU", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    CI('\u127A', 'ቺ', "ETHIOPIC SYLLABLE", "CI", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    CAA('\u127B', 'ቻ', "ETHIOPIC SYLLABLE", "CAA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    CEE('\u127C', 'ቼ', "ETHIOPIC SYLLABLE", "CEE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    CE('\u127D', 'ች', "ETHIOPIC SYLLABLE", "CE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    CO('\u127E', 'ቾ', "ETHIOPIC SYLLABLE", "CO", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    CWA('\u127F', 'ቿ', "ETHIOPIC SYLLABLE", "CWA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),

    //HA
    XA('\u1280', 'ኀ', "ETHIOPIC SYLLABLE", "XA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    XU('\u1281', 'ኁ', "ETHIOPIC SYLLABLE", "XU", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    XI('\u1282', 'ኂ', "ETHIOPIC SYLLABLE", "XI", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    XAA('\u1283', 'ኃ', "ETHIOPIC SYLLABLE", "XAA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    XEE('\u1284', 'ኄ', "ETHIOPIC SYLLABLE", "XEE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    XE('\u1285', 'ኅ', "ETHIOPIC SYLLABLE", "XE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    XO('\u1286', 'ኆ', "ETHIOPIC SYLLABLE", "XO", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    XOA('\u1287', 'ኇ', "ETHIOPIC SYLLABLE", "XOA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),

    //NE
    NA('\u1290', 'ነ', "ETHIOPIC SYLLABLE", "NA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    NU('\u1291', 'ኑ', "ETHIOPIC SYLLABLE", "NU", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    NI('\u1292', 'ኒ', "ETHIOPIC SYLLABLE", "NI", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    NAA('\u1293', 'ና', "ETHIOPIC SYLLABLE", "NAA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    NEE('\u1294', 'ኔ', "ETHIOPIC SYLLABLE", "NEE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    NE('\u1295', 'ን', "ETHIOPIC SYLLABLE", "NE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    NO('\u1296', 'ኖ', "ETHIOPIC SYLLABLE", "NO", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    NWA('\u1297', 'ኗ', "ETHIOPIC SYLLABLE", "NWA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),

    //GNE
    NYA('\u1298', 'ኘ', "ETHIOPIC SYLLABLE", "NYA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    NYU('\u1299', 'ኙ', "ETHIOPIC SYLLABLE", "NYU", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    NYI('\u129A', 'ኚ', "ETHIOPIC SYLLABLE", "NYI", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    NYAA('\u129B', 'ኛ', "ETHIOPIC SYLLABLE", "NYAA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    NYEE('\u129C', 'ኜ', "ETHIOPIC SYLLABLE", "NYEE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    NYE('\u129D', 'ኝ', "ETHIOPIC SYLLABLE", "NYE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    NYO('\u129E', 'ኞ', "ETHIOPIC SYLLABLE", "NYO", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    NYWA('\u129F', 'ኟ', "ETHIOPIC SYLLABLE", "NYWA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),

    //A
    A('\u12A0', 'አ', "ETHIOPIC SYLLABLE", "A", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    U('\u12A1', 'ኡ', "ETHIOPIC SYLLABLE", "U", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    I('\u12A2', 'ኢ', "ETHIOPIC SYLLABLE", "I", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    AA('\u12A3', 'ኣ', "ETHIOPIC SYLLABLE", "AA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    EE('\u12A4', 'ኤ', "ETHIOPIC SYLLABLE", "EE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    E('\u12A5', 'እ', "ETHIOPIC SYLLABLE", "E", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    O('\u12A6', 'ኦ', "ETHIOPIC SYLLABLE", "O", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    UA('\u12A7', 'ኧ', "ETHIOPIC SYLLABLE", "UA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),

    //KE
    KA('\u12A8', 'ከ', "ETHIOPIC SYLLABLE", "KA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    KU('\u12A9', 'ኩ', "ETHIOPIC SYLLABLE", "KU", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    KI('\u12AA', 'ኪ', "ETHIOPIC SYLLABLE", "KI", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    KAA('\u12AB', 'ካ', "ETHIOPIC SYLLABLE", "KAA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    KEE('\u12AC', 'ኬ', "ETHIOPIC SYLLABLE", "KEE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    KE('\u12AD', 'ክ', "ETHIOPIC SYLLABLE", "KE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    KO('\u12AE', 'ኮ', "ETHIOPIC SYLLABLE", "KO", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    KWAA('\u12B3', 'ኳ', "ETHIOPIC SYLLABLE", "KWAA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),


    //HE
    KXA('\u12B8', 'ኸ', "ETHIOPIC SYLLABLE", "KXA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    KXU('\u12B9', 'ኹ', "ETHIOPIC SYLLABLE", "KXU", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    KXI('\u12BA', 'ኺ', "ETHIOPIC SYLLABLE", "KXI", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    KXAA('\u12BB', 'ኻ', "ETHIOPIC SYLLABLE", "KXAA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    KXEE('\u12BC', 'ኼ', "ETHIOPIC SYLLABLE", "KXEE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    KXE('\u12BD', 'ኽ', "ETHIOPIC SYLLABLE", "KXE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    KXO('\u12BE', 'ኾ', "ETHIOPIC SYLLABLE", "KXO", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    KXWA('\u12C0', 'ዀ', "ETHIOPIC SYLLABLE", "KXWA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),

    //WE
    WA('\u12C8', 'ወ', "ETHIOPIC SYLLABLE", "WA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    WU('\u12C9', 'ዉ', "ETHIOPIC SYLLABLE", "WU", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    WI('\u12CA', 'ዊ', "ETHIOPIC SYLLABLE", "WI", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    WAA('\u12CB', 'ዋ', "ETHIOPIC SYLLABLE", "WAA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    WEE('\u12CC', 'ዌ', "ETHIOPIC SYLLABLE", "WEE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    WE('\u12CD', 'ው', "ETHIOPIC SYLLABLE", "WE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    WO('\u12CE', 'ዎ', "ETHIOPIC SYLLABLE", "WO", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    WOA('\u12CF', 'ዏ', "ETHIOPIC SYLLABLE", "WOA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),


    //AA
    A_0('\u12D0', 'ዐ', "ETHIOPIC SYLLABLE", "A", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    U_0('\u12D1', 'ዑ', "ETHIOPIC SYLLABLE", "U", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    I_0('\u12D2', 'ዒ', "ETHIOPIC SYLLABLE", "I", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    AA_0('\u12D3', 'ዓ', "ETHIOPIC SYLLABLE", "AA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    EE_0('\u12D4', 'ዔ', "ETHIOPIC SYLLABLE", "EE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    E_0('\u12D5', 'ዕ', "ETHIOPIC SYLLABLE", "E", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    O_0('\u12D6', 'ዖ', "ETHIOPIC SYLLABLE", "O", 1, 0, KeyEventType.KEY_EVENT_NORMAL),

    //ZE
    ZA('\u12D8', 'ዘ', "ETHIOPIC SYLLABLE", "ZA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    ZU('\u12D9', 'ዙ', "ETHIOPIC SYLLABLE", "ZU", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    ZI('\u12DA', 'ዚ', "ETHIOPIC SYLLABLE", "ZI", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    ZAA('\u12DB', 'ዛ', "ETHIOPIC SYLLABLE", "ZAA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    ZEE('\u12DC', 'ዜ', "ETHIOPIC SYLLABLE", "ZEE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    ZE('\u12DD', 'ዝ', "ETHIOPIC SYLLABLE", "ZE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    ZO('\u12DE', 'ዞ', "ETHIOPIC SYLLABLE", "ZO", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    ZWA('\u12DF', 'ዟ', "ETHIOPIC SYLLABLE", "ZWA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),

    //ZSE
    ZHA('\u12E0', 'ዠ', "ETHIOPIC SYLLABLE", "ZHA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    ZHU('\u12E1', 'ዡ', "ETHIOPIC SYLLABLE", "ZHU", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    ZHI('\u12E2', 'ዢ', "ETHIOPIC SYLLABLE", "ZHI", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    ZHAA('\u12E3', 'ዣ', "ETHIOPIC SYLLABLE", "ZHAA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    ZHEE('\u12E4', 'ዤ', "ETHIOPIC SYLLABLE", "ZHEE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    ZHE('\u12E5', 'ዥ', "ETHIOPIC SYLLABLE", "ZHE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    ZHO('\u12E6', 'ዦ', "ETHIOPIC SYLLABLE", "ZHO", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    ZHWA('\u12E7', 'ዧ', "ETHIOPIC SYLLABLE", "ZHWA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),

    //YE
    YA('\u12E8', 'የ', "ETHIOPIC SYLLABLE", "YA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    YU('\u12E9', 'ዩ', "ETHIOPIC SYLLABLE", "YU", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    YI('\u12EA', 'ዪ', "ETHIOPIC SYLLABLE", "YI", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    YAA('\u12EB', 'ያ', "ETHIOPIC SYLLABLE", "YAA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    YEE('\u12EC', 'ዬ', "ETHIOPIC SYLLABLE", "YEE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    YE('\u12ED', 'ይ', "ETHIOPIC SYLLABLE", "YE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    YO('\u12EE', 'ዮ', "ETHIOPIC SYLLABLE", "YO", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    YOA('\u12EF', 'ዯ', "ETHIOPIC SYLLABLE", "YOA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),


    //DE
    DA('\u12F0', 'ደ', "ETHIOPIC SYLLABLE", "DA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    DU('\u12F1', 'ዱ', "ETHIOPIC SYLLABLE", "DU", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    DI('\u12F2', 'ዲ', "ETHIOPIC SYLLABLE", "DI", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    DAA('\u12F3', 'ዳ', "ETHIOPIC SYLLABLE", "DAA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    DEE('\u12F4', 'ዴ', "ETHIOPIC SYLLABLE", "DEE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    DE('\u12F5', 'ድ', "ETHIOPIC SYLLABLE", "DE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    DO('\u12F6', 'ዶ', "ETHIOPIC SYLLABLE", "DO", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    DWA('\u12F7', 'ዷ', "ETHIOPIC SYLLABLE", "DWA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),

    //JE
    JA('\u1300', 'ጀ', "ETHIOPIC SYLLABLE", "JA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    JU('\u1301', 'ጁ', "ETHIOPIC SYLLABLE", "JU", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    JI('\u1302', 'ጂ', "ETHIOPIC SYLLABLE", "JI", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    JAA('\u1303', 'ጃ', "ETHIOPIC SYLLABLE", "JAA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    JEE('\u1304', 'ጄ', "ETHIOPIC SYLLABLE", "JEE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    JE('\u1305', 'ጅ', "ETHIOPIC SYLLABLE", "JE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    JO('\u1306', 'ጆ', "ETHIOPIC SYLLABLE", "JO", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    JWA('\u1307', 'ጇ', "ETHIOPIC SYLLABLE", "JWA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),

    //GE
    GA('\u1308', 'ገ', "ETHIOPIC SYLLABLE", "GA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    GU('\u1309', 'ጉ', "ETHIOPIC SYLLABLE", "GU", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    GI('\u130A', 'ጊ', "ETHIOPIC SYLLABLE", "GI", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    GAA('\u130B', 'ጋ', "ETHIOPIC SYLLABLE", "GAA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    GEE('\u130C', 'ጌ', "ETHIOPIC SYLLABLE", "GEE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    GE('\u130D', 'ግ', "ETHIOPIC SYLLABLE", "GE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    GO('\u130E', 'ጎ', "ETHIOPIC SYLLABLE", "GO", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    GWAA('\u1313', 'ጓ', "ETHIOPIC SYLLABLE", "GWAA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),

    //THE
    THA('\u1320', 'ጠ', "ETHIOPIC SYLLABLE", "THA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    THU('\u1321', 'ጡ', "ETHIOPIC SYLLABLE", "THU", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    THI('\u1322', 'ጢ', "ETHIOPIC SYLLABLE", "THI", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    THAA('\u1323', 'ጣ', "ETHIOPIC SYLLABLE", "THAA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    THEE('\u1324', 'ጤ', "ETHIOPIC SYLLABLE", "THEE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    THE('\u1325', 'ጥ', "ETHIOPIC SYLLABLE", "THE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    THO('\u1326', 'ጦ', "ETHIOPIC SYLLABLE", "THO", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    THWA('\u1327', 'ጧ', "ETHIOPIC SYLLABLE", "THWA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),

    //CHEE
    CHA('\u1328', 'ጨ', "ETHIOPIC SYLLABLE", "CHA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    CHU('\u1329', 'ጩ', "ETHIOPIC SYLLABLE", "CHU", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    CHI('\u132A', 'ጪ', "ETHIOPIC SYLLABLE", "CHI", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    CHAA('\u132B', 'ጫ', "ETHIOPIC SYLLABLE", "CHAA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    CHEE('\u132C', 'ጬ', "ETHIOPIC SYLLABLE", "CHEE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    CHE('\u132D', 'ጭ', "ETHIOPIC SYLLABLE", "CHE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    CHO('\u132E', 'ጮ', "ETHIOPIC SYLLABLE", "CHO", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    CHWA('\u132F', 'ጯ', "ETHIOPIC SYLLABLE", "CHWA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),


    //PEE
    PHA('\u1330', 'ጰ', "ETHIOPIC SYLLABLE", "PHA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    PHU('\u1331', 'ጱ', "ETHIOPIC SYLLABLE", "PHU", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    PHI('\u1332', 'ጲ', "ETHIOPIC SYLLABLE", "PHI", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    PHAA('\u1333', 'ጳ', "ETHIOPIC SYLLABLE", "PHAA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    PHEE('\u1334', 'ጴ', "ETHIOPIC SYLLABLE", "PHEE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    PHE('\u1335', 'ጵ', "ETHIOPIC SYLLABLE", "PHE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    PHO('\u1336', 'ጶ', "ETHIOPIC SYLLABLE", "PHO", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    PHWA('\u1337', 'ጷ', "ETHIOPIC SYLLABLE", "PHWA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),


    //TSE
    TSA('\u1338', 'ጸ', "ETHIOPIC SYLLABLE", "TSA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    TSU('\u1339', 'ጹ', "ETHIOPIC SYLLABLE", "TSU", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    TSI('\u133A', 'ጺ', "ETHIOPIC SYLLABLE", "TSI", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    TSAA('\u133B', 'ጻ', "ETHIOPIC SYLLABLE", "TSAA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    TSEE('\u133C', 'ጼ', "ETHIOPIC SYLLABLE", "TSEE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    TSE('\u133D', 'ጽ', "ETHIOPIC SYLLABLE", "TSE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    TSO('\u133E', 'ጾ', "ETHIOPIC SYLLABLE", "TSO", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    TSWA('\u133F', 'ጿ', "ETHIOPIC SYLLABLE", "TSWA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),

    //TSEE
    TZA('\u1340', 'ፀ', "ETHIOPIC SYLLABLE", "TZA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    TZU('\u1341', 'ፁ', "ETHIOPIC SYLLABLE", "TZU", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    TZI('\u1342', 'ፂ', "ETHIOPIC SYLLABLE", "TZI", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    TZAA('\u1343', 'ፃ', "ETHIOPIC SYLLABLE", "TZAA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    TZEE('\u1344', 'ፄ', "ETHIOPIC SYLLABLE", "TZEE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    TZE('\u1345', 'ፅ', "ETHIOPIC SYLLABLE", "TZE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    TZO('\u1346', 'ፆ', "ETHIOPIC SYLLABLE", "TZO", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    TZOA('\u1347', 'ፇ', "ETHIOPIC SYLLABLE", "TZOA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),

    //FE
    FA('\u1348', 'ፈ', "ETHIOPIC SYLLABLE", "FA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    FU('\u1349', 'ፉ', "ETHIOPIC SYLLABLE", "FU", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    FI('\u134A', 'ፊ', "ETHIOPIC SYLLABLE", "FI", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    FAA('\u134B', 'ፋ', "ETHIOPIC SYLLABLE", "FAA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    FEE('\u134C', 'ፌ', "ETHIOPIC SYLLABLE", "FEE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    FE('\u134D', 'ፍ', "ETHIOPIC SYLLABLE", "FE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    FO('\u134E', 'ፎ', "ETHIOPIC SYLLABLE", "FO", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    FWA('\u134F', 'ፏ', "ETHIOPIC SYLLABLE", "FWA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),

    //PE
    PA('\u1350', 'ፐ', "ETHIOPIC SYLLABLE", "PA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    PU('\u1351', 'ፑ', "ETHIOPIC SYLLABLE", "PU", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    PI('\u1352', 'ፒ', "ETHIOPIC SYLLABLE", "PI", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    PAA('\u1353', 'ፓ', "ETHIOPIC SYLLABLE", "PAA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    PEE('\u1354', 'ፔ', "ETHIOPIC SYLLABLE", "PEE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    PE('\u1355', 'ፕ', "ETHIOPIC SYLLABLE", "PE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    PO('\u1356', 'ፖ', "ETHIOPIC SYLLABLE", "PO", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    PWA('\u1357', 'ፗ', "ETHIOPIC SYLLABLE", "PWA", 1, 0, KeyEventType.KEY_EVENT_NORMAL);


    private final KeyEventType eventType;
    private final char fidelUnicode;
    private final char fidel;
    private final String typography;
    private final String pronounciation;
    private final int columnCount;
    private final int resourceId;

    KeyTypography(char fidelUnicode, char fidel, String typography,
                  String pronountiation, int columnCount, int resourceId, KeyEventType eventType) {
        this.fidelUnicode = fidelUnicode;
        this.fidel = fidel;
        this.typography = typography;
        this.pronounciation = pronountiation;
        this.columnCount = columnCount;
        this.resourceId = resourceId;
        this.eventType = eventType;
    }

    @Override
    public String getTypography() {
        return this.typography;
    }

    @Override
    public String getPronounciation() {
        return this.pronounciation;
    }

    @Override
    public char getFidel() {
        return this.fidel;
    }

    @Override
    public char getFidelUnicode() {
        return this.fidelUnicode;
    }

    @Override
    public int getColumnCount() {
        return this.columnCount;
    }

    @Override
    public int getResourceId() {
        return this.resourceId;
    }

    @Override
    public KeyEventType getKeyEventType() {
        return this.eventType;
    }
//    ('\u1247', 'ቇ', "ETHIOPIC SYLLABLE", "QOA"),
//    ('\u1248', 'ቈ', "ETHIOPIC SYLLABLE", "QWA"),
//    ('\u124A', 'ቊ', "ETHIOPIC SYLLABLE", "QWI"),
//    ('\u124B', 'ቋ', "ETHIOPIC SYLLABLE", "QWAA"),
//    ('\u124C', 'ቌ', "ETHIOPIC SYLLABLE", "QWEE"),
//    ('\u124D', 'ቍ', "ETHIOPIC SYLLABLE", "QWE"),
//    ('\u1250', 'ቐ', "ETHIOPIC SYLLABLE", "QHA"),
//    ('\u1251', 'ቑ', "ETHIOPIC SYLLABLE", "QHU"),
//    ('\u1252', 'ቒ', "ETHIOPIC SYLLABLE", "QHI"),
//    ('\u1253', 'ቓ', "ETHIOPIC SYLLABLE", "QHAA"),
//    ('\u1254', 'ቔ', "ETHIOPIC SYLLABLE", "QHEE"),
//    ('\u1255', 'ቕ', "ETHIOPIC SYLLABLE", "QHE"),
//    ('\u1256', 'ቖ', "ETHIOPIC SYLLABLE", "QHO"),
//    ('\u1258', 'ቘ', "ETHIOPIC SYLLABLE", "QHWA"),
//    ('\u125A', 'ቚ', "ETHIOPIC SYLLABLE", "QHWI"),
//    ('\u125B', 'ቛ', "ETHIOPIC SYLLABLE", "QHWAA"),
//    ('\u125C', 'ቜ', "ETHIOPIC SYLLABLE", "QHWEE"),
//    ('\u125D', 'ቝ', "ETHIOPIC SYLLABLE", "QHWE"),
//    ('\u12C2', 'ዂ', "ETHIOPIC SYLLABLE", "KXWI"),
//    ('\u12C3', 'ዃ', "ETHIOPIC SYLLABLE", "KXWAA"),
//    ('\u12C4', 'ዄ', "ETHIOPIC SYLLABLE", "KXWEE"),
//    ('\u12C5', 'ዅ', "ETHIOPIC SYLLABLE", "KXWE"),
//    ('\u1288', 'ኈ', "ETHIOPIC SYLLABLE", "XWA"),
//    ('\u128A', 'ኊ', "ETHIOPIC SYLLABLE", "XWI"),
//    ('\u128B', 'ኋ', "ETHIOPIC SYLLABLE", "XWAA"),
//    ('\u128C', 'ኌ', "ETHIOPIC SYLLABLE", "XWEE"),
//    ('\u128D', 'ኍ', "ETHIOPIC SYLLABLE", "XWE"),
//    ('\u12AF', 'ኯ', "ETHIOPIC SYLLABLE", "KOA"),
//    ('\u12B0', 'ኰ', "ETHIOPIC SYLLABLE", "KWA"),
//    ('\u12B2', 'ኲ', "ETHIOPIC SYLLABLE", "KWI"),
//    ('\u12B3', 'ኳ', "ETHIOPIC SYLLABLE", "KWAA"),
//    ('\u12B4', 'ኴ', "ETHIOPIC SYLLABLE", "KWEE"),
//    ('\u12B5', 'ኵ', "ETHIOPIC SYLLABLE", "KWE"),
//    ('\u1358', 'ፘ', "ETHIOPIC SYLLABLE", "RYA"),
//    ('\u1359', 'ፙ', "ETHIOPIC SYLLABLE", "MYA"),
//    ('\u135A', 'ፚ', "ETHIOPIC SYLLABLE", "FYA"),
//    ('\u12F6', 'ዶ', "ETHIOPIC SYLLABLE", "DO"),
//    ('\u12F7', 'ዷ', "ETHIOPIC SYLLABLE", "DWA"),
//    ('\u12F8', 'ዸ', "ETHIOPIC SYLLABLE", "DDA"),
//    ('\u12F9', 'ዹ', "ETHIOPIC SYLLABLE", "DDU"),
//    ('\u12FA', 'ዺ', "ETHIOPIC SYLLABLE", "DDI"),
//    ('\u12FB', 'ዻ', "ETHIOPIC SYLLABLE", "DDAA"),
//    ('\u12FC', 'ዼ', "ETHIOPIC SYLLABLE", "DDEE"),
//    ('\u12FD', 'ዽ', "ETHIOPIC SYLLABLE", "DDE"),
//    ('\u12FE', 'ዾ', "ETHIOPIC SYLLABLE", "DDO"),
//    ('\u12FF', 'ዿ', "ETHIOPIC SYLLABLE", "DDWA"),
//    ('\u130F', 'ጏ', "ETHIOPIC SYLLABLE", "GOA"),
//    ('\u1310', 'ጐ', "ETHIOPIC SYLLABLE", "GWA"),
//    ('\u1312', 'ጒ', "ETHIOPIC SYLLABLE", "GWI"),
//    ('\u1313', 'ጓ', "ETHIOPIC SYLLABLE", "GWAA"),
//    ('\u1314', 'ጔ', "ETHIOPIC SYLLABLE", "GWEE"),
//    ('\u1315', 'ጕ', "ETHIOPIC SYLLABLE", "GWE"),
//    ('\u1318', 'ጘ', "ETHIOPIC SYLLABLE", "GGA"),
//    ('\u1319', 'ጙ', "ETHIOPIC SYLLABLE", "GGU"),
//    ('\u131A', 'ጚ', "ETHIOPIC SYLLABLE", "GGI"),
//    ('\u131B', 'ጛ', "ETHIOPIC SYLLABLE", "GGAA"),
//    ('\u131C', 'ጜ', "ETHIOPIC SYLLABLE", "GGEE"),
//    ('\u131D', 'ጝ', "ETHIOPIC SYLLABLE", "GGE"),
//    ('\u131E', 'ጞ', "ETHIOPIC SYLLABLE", "GGO"),
//    ('\u131F', 'ጟ', "ETHIOPIC SYLLABLE", "GGWAA"),
}
