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
    BACKSPACE('X', "", "BACK SPACE", 2, R.mipmap.ic_backspace_white_24dp, KeyEventType.KEY_EVENT_NORMAL),
    SPACEBAR(' ', "", "SPACE BAR", 2, R.mipmap.ic_space_bar_white_24dp, KeyEventType.KEY_EVENT_NORMAL),

    //SYMBOLS
    SECTION_MARK('\u1360', "ETHIOPIC SECTION MARK", "SECTION MARK", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    WORD_SPACE('\u1361', "ETHIOPIC WORDSPACE", "WORDSPACE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    FULL_STOP('\u1362', "ETHIOPIC FULL STOP", "FULL STOP", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    COMMA('\u1363', "ETHIOPIC COMMA", "COMMA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    SEMICOLON('\u1364', "ETHIOPIC SEMICOLON", "SEMICOLON", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    COLON('\u1365', "ETHIOPIC COLON", "COLON", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    PREFACE_COLON('\u1366', "ETHIOPIC PREFACE COLON", "PREFACE COLON", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    QUESTION_MARK('\u1367', "ETHIOPIC QUESTION MARK", "QUESTION MARK", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    PARAGRAPH_SEPARATOR('\u1367', "ETHIOPIC PARAGRAPH SEPARATOR", "PARAGRAPH SEPARATOR", 1, 0, KeyEventType.KEY_EVENT_NORMAL),


    //Amharic NUMBERS(1-10)
    ONE_AM('\u1369', "ETHIOPIC DIGIT ONE", "AND", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    TWO_AM('\u136A', "ETHIOPIC DIGIT TWO", "HULET", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    THREE_AM('\u136B', "ETHIOPIC DIGIT THREE", "SOST", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    FOUR_AM('\u136C', "ETHIOPIC DIGIT FOUR", "ARAT", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    FIVE_AM('\u136D', "ETHIOPIC DIGIT FIVE", "AMIST", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    SIX_AM('\u136E', "ETHIOPIC DIGIT SIX", "SIDIST", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    SEVEN_AM('\u136F', "ETHIOPIC DIGIT SEVEN", "SEBAT", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    EIGHT_AM('\u1370', "ETHIOPIC DIGIT EIGHT", "SIMINT", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    NINE_AM('\u1371', "ETHIOPIC DIGIT NINE", "ZETEGN", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    TEN_AM('\u1372', "ETHIOPIC NUMBER TEN", "ASIR", 1, 0, KeyEventType.KEY_EVENT_NORMAL),

    //NUMBERS(10-1000)
    TWENTY_AM('\u1373', "ETHIOPIC NUMBER TWENTY", "HAYA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    THIRTY_AM('\u1374', "ETHIOPIC NUMBER THIRTY", "SELASA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    FOURTY_AM_('\u1375', "ETHIOPIC NUMBER FORTY", "ARBA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    FIFTY_AM('\u1376', "ETHIOPIC NUMBER FIFTY", "HAMSA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    SICTY_AM('\u1377', "ETHIOPIC NUMBER SIXTY", "SILSA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    SEVENTY_AM('\u1378', "ETHIOPIC NUMBER SEVENTY", "SEBA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    EIGHTY_AM('\u1379', "ETHIOPIC NUMBER EIGHTY", "SEMANIA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    NINETY_AM('\u137A', "ETHIOPIC NUMBER NINETY", "ZETENA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    HUNDRED_AM('\u137B', "ETHIOPIC NUMBER HUNDRED", "METO", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    THOUSAND_AM('\u137C', "ETHIOPIC NUMBER TEN THOUSAND", "SHIH", 1, 0, KeyEventType.KEY_EVENT_NORMAL),

    //HA
    HA('\u1200', "ETHIOPIC SYLLABLE", "HA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    HU('\u1201', "ETHIOPIC SYLLABLE", "HU", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    HI('\u1202', "ETHIOPIC SYLLABLE", "HI", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    HAA('\u1203', "ETHIOPIC SYLLABLE", "HAA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    HEE('\u1204', "ETHIOPIC SYLLABLE", "HEE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    HE('\u1205', "ETHIOPIC SYLLABLE", "HE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    HO('\u1206', "ETHIOPIC SYLLABLE", "HO", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    HOA('\u1207', "ETHIOPIC SYLLABLE", "HOA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),

    //LE
    LA('\u1208', "ETHIOPIC SYLLABLE", "LA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    LU('\u1209', "ETHIOPIC SYLLABLE", "LU", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    LI('\u120A', "ETHIOPIC SYLLABLE", "LI", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    LAA('\u120B', "ETHIOPIC SYLLABLE", "LAA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    LEE('\u120C', "ETHIOPIC SYLLABLE", "LEE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    LE('\u120D', "ETHIOPIC SYLLABLE", "LE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    LO('\u120E', "ETHIOPIC SYLLABLE", "LO", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    LWA('\u120F', "ETHIOPIC SYLLABLE", "LWA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),

    //HA
    HHA('\u1210', "ETHIOPIC SYLLABLE", "HHA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    HHU('\u1211', "ETHIOPIC SYLLABLE", "HHU", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    HHI('\u1212', "ETHIOPIC SYLLABLE", "HHI", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    HHAA('\u1213', "ETHIOPIC SYLLABLE", "HHAA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    HHEE('\u1214', "ETHIOPIC SYLLABLE", "HHEE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    HHE('\u1215', "ETHIOPIC SYLLABLE", "HHE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    HHO('\u1216', "ETHIOPIC SYLLABLE", "HHO", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    HHWA('\u1217', "ETHIOPIC SYLLABLE", "HHWA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),


    //ME
    MA('\u1218', "ETHIOPIC SYLLABLE", "MA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    MU('\u1219', "ETHIOPIC SYLLABLE", "MU", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    MI('\u121A', "ETHIOPIC SYLLABLE", "MI", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    MAA('\u121B', "ETHIOPIC SYLLABLE", "MAA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    MEE('\u121C', "ETHIOPIC SYLLABLE", "MEE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    ME('\u121D', "ETHIOPIC SYLLABLE", "ME", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    MO('\u121E', "ETHIOPIC SYLLABLE", "MO", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    MWA('\u121F', "ETHIOPIC SYLLABLE", "MWA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),


    //SE
    SZA('\u1220', "ETHIOPIC SYLLABLE", "SZA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    SZU('\u1221', "ETHIOPIC SYLLABLE", "SZU", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    SZI('\u1222', "ETHIOPIC SYLLABLE", "SZI", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    SZAA('\u1223', "ETHIOPIC SYLLABLE", "SZAA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    SZEE('\u1224', "ETHIOPIC SYLLABLE", "SZEE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    SZE('\u1225', "ETHIOPIC SYLLABLE", "SZE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    SZO('\u1226', "ETHIOPIC SYLLABLE", "SZO", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    SZWA('\u1227', "ETHIOPIC SYLLABLE", "SZWA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),


    //RE
    RA('\u1228', "ETHIOPIC SYLLABLE", "RA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    RU('\u1229', "ETHIOPIC SYLLABLE", "RU", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    RI('\u122A', "ETHIOPIC SYLLABLE", "RI", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    RAA('\u122B', "ETHIOPIC SYLLABLE", "RAA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    REE('\u122C', "ETHIOPIC SYLLABLE", "REE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    RE('\u122D', "ETHIOPIC SYLLABLE", "RE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    RO('\u122E', "ETHIOPIC SYLLABLE", "RO", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    RWA('\u122F', "ETHIOPIC SYLLABLE", "RWA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),

    //SE
    SA('\u1230', "ETHIOPIC SYLLABLE", "SA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    SU('\u1231', "ETHIOPIC SYLLABLE", "SU", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    SI('\u1232', "ETHIOPIC SYLLABLE", "SI", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    SAA('\u1233', "ETHIOPIC SYLLABLE", "SAA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    SEE('\u1234', "ETHIOPIC SYLLABLE", "SEE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    SE('\u1235', "ETHIOPIC SYLLABLE", "SE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    SO('\u1236', "ETHIOPIC SYLLABLE", "SO", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    SWA('\u1237', "ETHIOPIC SYLLABLE", "SWA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),

    //SHE
    SHA('\u1238', "ETHIOPIC SYLLABLE", "SHA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    SHU('\u1239', "ETHIOPIC SYLLABLE", "SHU", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    SHI('\u123A', "ETHIOPIC SYLLABLE", "SHI", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    SHAA('\u123B', "ETHIOPIC SYLLABLE", "SHAA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    SHEE('\u123C', "ETHIOPIC SYLLABLE", "SHEE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    SHE('\u123D', "ETHIOPIC SYLLABLE", "SHE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    SHO('\u123E', "ETHIOPIC SYLLABLE", "SHO", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    SHWA('\u123F', "ETHIOPIC SYLLABLE", "SHWA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),

    //QE
    QA('\u1240', "ETHIOPIC SYLLABLE", "QA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    QU('\u1241', "ETHIOPIC SYLLABLE", "QU", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    QI('\u1242', "ETHIOPIC SYLLABLE", "QI", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    QAA('\u1243', "ETHIOPIC SYLLABLE", "QAA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    QEE('\u1244', "ETHIOPIC SYLLABLE", "QEE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    QE('\u1245', "ETHIOPIC SYLLABLE", "QE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    QO('\u1246', "ETHIOPIC SYLLABLE", "QO", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    QWAA('\u124B', "ETHIOPIC SYLLABLE", "QWAA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),

    //BE
    BA('\u1260', "ETHIOPIC SYLLABLE", "BA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    BU('\u1261', "ETHIOPIC SYLLABLE", "BU", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    BI('\u1262', "ETHIOPIC SYLLABLE", "BI", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    BAA('\u1263', "ETHIOPIC SYLLABLE", "BAA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    BEE('\u1264', "ETHIOPIC SYLLABLE", "BEE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    BE('\u1265', "ETHIOPIC SYLLABLE", "BE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    BO('\u1266', "ETHIOPIC SYLLABLE", "BO", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    BWA('\u1267', "ETHIOPIC SYLLABLE", "BWA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),

    //VE
    VA('\u1268', "ETHIOPIC SYLLABLE", "VA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    VU('\u1269', "ETHIOPIC SYLLABLE", "VU", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    VI('\u126A', "ETHIOPIC SYLLABLE", "VI", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    VAA('\u126B', "ETHIOPIC SYLLABLE", "VAA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    VEE('\u126C', "ETHIOPIC SYLLABLE", "VEE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    VE('\u126D', "ETHIOPIC SYLLABLE", "VE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    VO('\u126E', "ETHIOPIC SYLLABLE", "VO", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    VWA('\u126F', "ETHIOPIC SYLLABLE", "VWA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),

    //TE
    TA('\u1270', "ETHIOPIC SYLLABLE", "TA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    TU('\u1271', "ETHIOPIC SYLLABLE", "TU", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    TI('\u1272', "ETHIOPIC SYLLABLE", "TI", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    TAA('\u1273', "ETHIOPIC SYLLABLE", "TAA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    TEE('\u1274', "ETHIOPIC SYLLABLE", "TEE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    TE('\u1275', "ETHIOPIC SYLLABLE", "TE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    TO('\u1276', "ETHIOPIC SYLLABLE", "TO", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    TWA('\u1277', "ETHIOPIC SYLLABLE", "TWA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),


    //CE
    CA('\u1278', "ETHIOPIC SYLLABLE", "CA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    CU('\u1279', "ETHIOPIC SYLLABLE", "CU", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    CI('\u127A', "ETHIOPIC SYLLABLE", "CI", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    CAA('\u127B', "ETHIOPIC SYLLABLE", "CAA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    CEE('\u127C', "ETHIOPIC SYLLABLE", "CEE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    CE('\u127D', "ETHIOPIC SYLLABLE", "CE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    CO('\u127E', "ETHIOPIC SYLLABLE", "CO", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    CWA('\u127F', "ETHIOPIC SYLLABLE", "CWA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),

    //HA
    XA('\u1280', "ETHIOPIC SYLLABLE", "XA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    XU('\u1281', "ETHIOPIC SYLLABLE", "XU", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    XI('\u1282', "ETHIOPIC SYLLABLE", "XI", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    XAA('\u1283', "ETHIOPIC SYLLABLE", "XAA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    XEE('\u1284', "ETHIOPIC SYLLABLE", "XEE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    XE('\u1285', "ETHIOPIC SYLLABLE", "XE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    XO('\u1286', "ETHIOPIC SYLLABLE", "XO", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    XOA('\u1287', "ETHIOPIC SYLLABLE", "XOA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),

    //NE
    NA('\u1290', "ETHIOPIC SYLLABLE", "NA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    NU('\u1291', "ETHIOPIC SYLLABLE", "NU", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    NI('\u1292', "ETHIOPIC SYLLABLE", "NI", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    NAA('\u1293', "ETHIOPIC SYLLABLE", "NAA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    NEE('\u1294', "ETHIOPIC SYLLABLE", "NEE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    NE('\u1295', "ETHIOPIC SYLLABLE", "NE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    NO('\u1296', "ETHIOPIC SYLLABLE", "NO", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    NWA('\u1297', "ETHIOPIC SYLLABLE", "NWA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),

    //GNE
    NYA('\u1298', "ETHIOPIC SYLLABLE", "NYA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    NYU('\u1299', "ETHIOPIC SYLLABLE", "NYU", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    NYI('\u129A', "ETHIOPIC SYLLABLE", "NYI", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    NYAA('\u129B', "ETHIOPIC SYLLABLE", "NYAA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    NYEE('\u129C', "ETHIOPIC SYLLABLE", "NYEE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    NYE('\u129D', "ETHIOPIC SYLLABLE", "NYE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    NYO('\u129E', "ETHIOPIC SYLLABLE", "NYO", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    NYWA('\u129F', "ETHIOPIC SYLLABLE", "NYWA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),

    //A
    A('\u12A0', "ETHIOPIC SYLLABLE", "A", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    U('\u12A1', "ETHIOPIC SYLLABLE", "U", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    I('\u12A2', "ETHIOPIC SYLLABLE", "I", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    AA('\u12A3', "ETHIOPIC SYLLABLE", "AA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    EE('\u12A4', "ETHIOPIC SYLLABLE", "EE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    E('\u12A5', "ETHIOPIC SYLLABLE", "E", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    O('\u12A6', "ETHIOPIC SYLLABLE", "O", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    UA('\u12A7', "ETHIOPIC SYLLABLE", "UA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),

    //KE
    KA('\u12A8', "ETHIOPIC SYLLABLE", "KA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    KU('\u12A9', "ETHIOPIC SYLLABLE", "KU", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    KI('\u12AA', "ETHIOPIC SYLLABLE", "KI", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    KAA('\u12AB', "ETHIOPIC SYLLABLE", "KAA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    KEE('\u12AC', "ETHIOPIC SYLLABLE", "KEE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    KE('\u12AD', "ETHIOPIC SYLLABLE", "KE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    KO('\u12AE', "ETHIOPIC SYLLABLE", "KO", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    KWAA('\u12B3', "ETHIOPIC SYLLABLE", "KWAA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),


    //HE
    KXA('\u12B8', "ETHIOPIC SYLLABLE", "KXA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    KXU('\u12B9', "ETHIOPIC SYLLABLE", "KXU", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    KXI('\u12BA', "ETHIOPIC SYLLABLE", "KXI", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    KXAA('\u12BB', "ETHIOPIC SYLLABLE", "KXAA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    KXEE('\u12BC', "ETHIOPIC SYLLABLE", "KXEE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    KXE('\u12BD', "ETHIOPIC SYLLABLE", "KXE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    KXO('\u12BE', "ETHIOPIC SYLLABLE", "KXO", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    KXWA('\u12C0', "ETHIOPIC SYLLABLE", "KXWA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),

    //WE
    WA('\u12C8', "ETHIOPIC SYLLABLE", "WA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    WU('\u12C9', "ETHIOPIC SYLLABLE", "WU", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    WI('\u12CA', "ETHIOPIC SYLLABLE", "WI", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    WAA('\u12CB', "ETHIOPIC SYLLABLE", "WAA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    WEE('\u12CC', "ETHIOPIC SYLLABLE", "WEE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    WE('\u12CD', "ETHIOPIC SYLLABLE", "WE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    WO('\u12CE', "ETHIOPIC SYLLABLE", "WO", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    WOA('\u12CF', "ETHIOPIC SYLLABLE", "WOA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),


    //AA
    A_0('\u12D0', "ETHIOPIC SYLLABLE", "A", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    U_0('\u12D1', "ETHIOPIC SYLLABLE", "U", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    I_0('\u12D2', "ETHIOPIC SYLLABLE", "I", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    AA_0('\u12D3', "ETHIOPIC SYLLABLE", "AA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    EE_0('\u12D4', "ETHIOPIC SYLLABLE", "EE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    E_0('\u12D5', "ETHIOPIC SYLLABLE", "E", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    O_0('\u12D6', "ETHIOPIC SYLLABLE", "O", 1, 0, KeyEventType.KEY_EVENT_NORMAL),

    //ZE
    ZA('\u12D8', "ETHIOPIC SYLLABLE", "ZA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    ZU('\u12D9', "ETHIOPIC SYLLABLE", "ZU", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    ZI('\u12DA', "ETHIOPIC SYLLABLE", "ZI", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    ZAA('\u12DB', "ETHIOPIC SYLLABLE", "ZAA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    ZEE('\u12DC', "ETHIOPIC SYLLABLE", "ZEE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    ZE('\u12DD', "ETHIOPIC SYLLABLE", "ZE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    ZO('\u12DE', "ETHIOPIC SYLLABLE", "ZO", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    ZWA('\u12DF', "ETHIOPIC SYLLABLE", "ZWA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),

    //ZSE
    ZHA('\u12E0', "ETHIOPIC SYLLABLE", "ZHA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    ZHU('\u12E1', "ETHIOPIC SYLLABLE", "ZHU", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    ZHI('\u12E2', "ETHIOPIC SYLLABLE", "ZHI", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    ZHAA('\u12E3', "ETHIOPIC SYLLABLE", "ZHAA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    ZHEE('\u12E4', "ETHIOPIC SYLLABLE", "ZHEE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    ZHE('\u12E5', "ETHIOPIC SYLLABLE", "ZHE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    ZHO('\u12E6', "ETHIOPIC SYLLABLE", "ZHO", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    ZHWA('\u12E7', "ETHIOPIC SYLLABLE", "ZHWA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),

    //YE
    YA('\u12E8', "ETHIOPIC SYLLABLE", "YA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    YU('\u12E9', "ETHIOPIC SYLLABLE", "YU", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    YI('\u12EA', "ETHIOPIC SYLLABLE", "YI", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    YAA('\u12EB', "ETHIOPIC SYLLABLE", "YAA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    YEE('\u12EC', "ETHIOPIC SYLLABLE", "YEE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    YE('\u12ED', "ETHIOPIC SYLLABLE", "YE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    YO('\u12EE', "ETHIOPIC SYLLABLE", "YO", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    YOA('\u12EF', "ETHIOPIC SYLLABLE", "YOA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),


    //DE
    DA('\u12F0', "ETHIOPIC SYLLABLE", "DA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    DU('\u12F1', "ETHIOPIC SYLLABLE", "DU", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    DI('\u12F2', "ETHIOPIC SYLLABLE", "DI", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    DAA('\u12F3', "ETHIOPIC SYLLABLE", "DAA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    DEE('\u12F4', "ETHIOPIC SYLLABLE", "DEE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    DE('\u12F5', "ETHIOPIC SYLLABLE", "DE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    DO('\u12F6', "ETHIOPIC SYLLABLE", "DO", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    DWA('\u12F7', "ETHIOPIC SYLLABLE", "DWA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),

    //JE
    JA('\u1300', "ETHIOPIC SYLLABLE", "JA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    JU('\u1301', "ETHIOPIC SYLLABLE", "JU", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    JI('\u1302', "ETHIOPIC SYLLABLE", "JI", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    JAA('\u1303', "ETHIOPIC SYLLABLE", "JAA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    JEE('\u1304', "ETHIOPIC SYLLABLE", "JEE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    JE('\u1305', "ETHIOPIC SYLLABLE", "JE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    JO('\u1306', "ETHIOPIC SYLLABLE", "JO", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    JWA('\u1307', "ETHIOPIC SYLLABLE", "JWA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),

    //GE
    GA('\u1308', "ETHIOPIC SYLLABLE", "GA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    GU('\u1309', "ETHIOPIC SYLLABLE", "GU", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    GI('\u130A', "ETHIOPIC SYLLABLE", "GI", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    GAA('\u130B', "ETHIOPIC SYLLABLE", "GAA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    GEE('\u130C', "ETHIOPIC SYLLABLE", "GEE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    GE('\u130D', "ETHIOPIC SYLLABLE", "GE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    GO('\u130E', "ETHIOPIC SYLLABLE", "GO", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    GWAA('\u1313', "ETHIOPIC SYLLABLE", "GWAA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),

    //THE
    THA('\u1320', "ETHIOPIC SYLLABLE", "THA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    THU('\u1321', "ETHIOPIC SYLLABLE", "THU", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    THI('\u1322', "ETHIOPIC SYLLABLE", "THI", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    THAA('\u1323', "ETHIOPIC SYLLABLE", "THAA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    THEE('\u1324', "ETHIOPIC SYLLABLE", "THEE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    THE('\u1325', "ETHIOPIC SYLLABLE", "THE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    THO('\u1326', "ETHIOPIC SYLLABLE", "THO", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    THWA('\u1327', "ETHIOPIC SYLLABLE", "THWA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),

    //CHEE
    CHA('\u1328', "ETHIOPIC SYLLABLE", "CHA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    CHU('\u1329', "ETHIOPIC SYLLABLE", "CHU", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    CHI('\u132A', "ETHIOPIC SYLLABLE", "CHI", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    CHAA('\u132B', "ETHIOPIC SYLLABLE", "CHAA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    CHEE('\u132C', "ETHIOPIC SYLLABLE", "CHEE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    CHE('\u132D', "ETHIOPIC SYLLABLE", "CHE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    CHO('\u132E', "ETHIOPIC SYLLABLE", "CHO", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    CHWA('\u132F', "ETHIOPIC SYLLABLE", "CHWA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),


    //PEE
    PHA('\u1330', "ETHIOPIC SYLLABLE", "PHA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    PHU('\u1331', "ETHIOPIC SYLLABLE", "PHU", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    PHI('\u1332', "ETHIOPIC SYLLABLE", "PHI", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    PHAA('\u1333', "ETHIOPIC SYLLABLE", "PHAA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    PHEE('\u1334', "ETHIOPIC SYLLABLE", "PHEE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    PHE('\u1335', "ETHIOPIC SYLLABLE", "PHE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    PHO('\u1336', "ETHIOPIC SYLLABLE", "PHO", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    PHWA('\u1337', "ETHIOPIC SYLLABLE", "PHWA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),


    //TSE
    TSA('\u1338', "ETHIOPIC SYLLABLE", "TSA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    TSU('\u1339', "ETHIOPIC SYLLABLE", "TSU", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    TSI('\u133A', "ETHIOPIC SYLLABLE", "TSI", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    TSAA('\u133B', "ETHIOPIC SYLLABLE", "TSAA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    TSEE('\u133C', "ETHIOPIC SYLLABLE", "TSEE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    TSE('\u133D', "ETHIOPIC SYLLABLE", "TSE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    TSO('\u133E', "ETHIOPIC SYLLABLE", "TSO", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    TSWA('\u133F', "ETHIOPIC SYLLABLE", "TSWA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),

    //TSEE
    TZA('\u1340', "ETHIOPIC SYLLABLE", "TZA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    TZU('\u1341', "ETHIOPIC SYLLABLE", "TZU", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    TZI('\u1342', "ETHIOPIC SYLLABLE", "TZI", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    TZAA('\u1343', "ETHIOPIC SYLLABLE", "TZAA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    TZEE('\u1344', "ETHIOPIC SYLLABLE", "TZEE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    TZE('\u1345', "ETHIOPIC SYLLABLE", "TZE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    TZO('\u1346', "ETHIOPIC SYLLABLE", "TZO", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    TZOA('\u1347', "ETHIOPIC SYLLABLE", "TZOA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),

    //FE
    FA('\u1348', "ETHIOPIC SYLLABLE", "FA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    FU('\u1349', "ETHIOPIC SYLLABLE", "FU", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    FI('\u134A', "ETHIOPIC SYLLABLE", "FI", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    FAA('\u134B', "ETHIOPIC SYLLABLE", "FAA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    FEE('\u134C', "ETHIOPIC SYLLABLE", "FEE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    FE('\u134D', "ETHIOPIC SYLLABLE", "FE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    FO('\u134E', "ETHIOPIC SYLLABLE", "FO", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    FWA('\u134F', "ETHIOPIC SYLLABLE", "FWA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),

    //PE
    PA('\u1350', "ETHIOPIC SYLLABLE", "PA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    PU('\u1351', "ETHIOPIC SYLLABLE", "PU", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    PI('\u1352', "ETHIOPIC SYLLABLE", "PI", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    PAA('\u1353', "ETHIOPIC SYLLABLE", "PAA", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    PEE('\u1354', "ETHIOPIC SYLLABLE", "PEE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    PE('\u1355', "ETHIOPIC SYLLABLE", "PE", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    PO('\u1356', "ETHIOPIC SYLLABLE", "PO", 1, 0, KeyEventType.KEY_EVENT_NORMAL),
    PWA('\u1357', "ETHIOPIC SYLLABLE", "PWA", 1, 0, KeyEventType.KEY_EVENT_NORMAL);


    private final KeyEventType eventType;
    private final char fidelUnicode;
    private final String typography;
    private final String pronounciation;
    private final int columnCount;
    private final int resourceId;

    KeyTypography(char fidelUnicode, String typography,
                  String pronountiation, int columnCount, int resourceId, KeyEventType eventType) {
        this.fidelUnicode = fidelUnicode;
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
