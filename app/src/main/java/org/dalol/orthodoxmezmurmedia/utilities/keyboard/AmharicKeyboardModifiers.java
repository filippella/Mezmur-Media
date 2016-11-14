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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 11/14/2016
 */
public enum AmharicKeyboardModifiers {

    AM_NUMBERS_ONE_TO_TEN("\u1369", "\u136A", "\u136B", "\u136C", "\u136D", "\u136E", "\u136F", "\u1370", "\u1371", "\u1372"),
    EN_NUMBERS_ONE_TO_TEN("0", "1", "2", "3", "4", "5", "6", "7", "8", "9"),
    AM_NUMBERS_TWENTY_TO_TEN_THOUSAND("\u1373", "\u1374", "\u1375", "\u1376", "\u1377", "\u1378", "\u1379", "\u137A", "\u137B", "\u137C"),
    SYMBOLS("\u1360", "\u1361", "\u1362", "\u1363", "\u1364", "\u1365", "\u1366", "\u1367", "\u1367"),

    //HA
    HA("\u1200", "\u1201", "\u1202", "\u1203", "\u1204", "\u1205", "\u1206", "\u1207"),
    //LE
    LA("\u1208", "\u1209", "\u120A", "\u120B", "\u120C", "\u120D", "\u120E", "\u120F"),
    //HA
    HHA("\u1210", "\u1211", "\u1212", "\u1213", "\u1214", "\u1215", "\u1216", "\u1217"),
    //ME
    MA("\u1218", "\u1219", "\u121A", "\u121B", "\u121C", "\u121D", "\u121E", "\u121F"),
    //SE
    SZA("\u1220", "\u1221", "\u1222", "\u1223", "\u1224", "\u1225", "\u1226", "\u1227"),
    //RE
    RA("\u1228", "\u1229", "\u122A", "\u122B", "\u122C", "\u122D", "\u122E", "\u122F"),
    //SE
    SA("\u1230", "\u1231", "\u1232", "\u1233", "\u1234", "\u1235", "\u1236", "\u1237"),
    //SHE
    SHA("\u1238", "\u1239", "\u123A", "\u123B", "\u123C", "\u123D", "\u123E", "\u123F"),
    //QE
    QA("\u1240", "\u1241", "\u1242", "\u1243", "\u1244", "\u1245", "\u1246", "\u124B"),
    //BE
    BA("\u1260", "\u1261", "\u1262", "\u1263", "\u1264", "\u1265", "\u1266", "\u1267"),

    //----------------------------
    //*** END OF THE FIRST ROW ***
    //----------------------------

    //VE
    VA("\u1268", "\u1269", "\u126A", "\u126B", "\u126C", "\u126D", "\u126E", "\u126F"),

    //TE
    TA("\u1270", "\u1271", "\u1272", "\u1273", "\u1274", "\u1275", "\u1276", "\u1277"),
    //CE
    CA("\u1278", "\u1279", "\u127A", "\u127B", "\u127C", "\u127D", "\u127E", "\u127F"),

    //HA
    XA("\u1280", "\u1281", "\u1282", "\u1283", "\u1284", "\u1285", "\u1286", "\u1287"),
    //NE
    NA("\u1290", "\u1291", "\u1292", "\u1293", "\u1294", "\u1295", "\u1296", "\u1297"),
    //GNE
    NYA("\u1298", "\u1299", "\u129A", "\u129B", "\u129C", "\u129D", "\u129E", "\u129F"),
    //A
    A("\u12A0", "\u12A1", "\u12A2", "\u12A3", "\u12A4", "\u12A5", "\u12A6", "\u12A7"),
    //KE
    KA("\u12A8", "\u12A9", "\u12AA", "\u12AB", "\u12AC", "\u12AD", "\u12AE", "\u12B3"),
    //HE
    KXA("\u12B8", "\u12B9", "\u12BA", "\u12BB", "\u12BC", "\u12BD", "\u12BE", "\u12C0"),
    //WE
    WA("\u12C8", "\u12C9", "\u12CA", "\u12CB", "\u12CC", "\u12CD", "\u12CE", "\u12CF"),
    //AA
    A_0("\u12D0", "\u12D1", "\u12D2", "\u12D3", "\u12D4", "\u12D5", "\u12D6"),

    //ZE
    ZA("\u12D8", "\u12D9", "\u12DA", "\u12DB", "\u12DC", "\u12DD", "\u12DE", "\u12DF"),
    //ZSE
    ZHA("\u12E0", "\u12E1", "\u12E2", "\u12E3", "\u12E4", "\u12E5", "\u12E6", "\u12E7"),
    //YE
    YA("\u12E8", "\u12E9", "\u12EA", "\u12EB", "\u12EC", "\u12ED", "\u12EE", "\u12EF"),
    //DE
    DA("\u12F0", "\u12F1", "\u12F2", "\u12F3", "\u12F4", "\u12F5", "\u12F6", "\u12F7"),
    //JE
    JA("\u1300", "\u1301", "\u1302", "\u1303", "\u1304", "\u1305", "\u1306", "\u1307"),
    //GE
    GA("\u1308", "\u1309", "\u130A", "\u130B", "\u130C", "\u130D", "\u130E", "\u1313"),
    //THE
    THA("\u1320", "\u1321", "\u1322", "\u1323", "\u1324", "\u1325", "\u1326", "\u1327"),
    //CHEE
    CHA("\u1328", "\u1329", "\u132A", "\u132B", "\u132C", "\u132D", "\u132E", "\u132F"),
    //PEE
    PHA("\u1330", "\u1331", "\u1332", "\u1333", "\u1334", "\u1335", "\u1336", "\u1337"),
    //TSE
    TSA("\u1338", "\u1339", "\u133A", "\u133B", "\u133C", "\u133D", "\u133E", "\u133F"),
    //TSEE
    TZA("\u1340", "\u1341", "\u1342", "\u1343", "\u1344", "\u1345", "\u1346", "\u1347"),
    //FE
    FA("\u1348", "\u1349", "\u134A", "\u134B", "\u134C", "\u134D", "\u134E", "\u134F"),
    //PE
    PA("\u1350", "\u1351", "\u1352", "\u1353", "\u1354", "\u1355", "\u1356", "\u1357");

    private List<String> mModifierList;
    private String charCode;

    AmharicKeyboardModifiers(String... properties) {
        charCode = properties[0];
        mModifierList = new ArrayList<>(Arrays.asList(properties));
    }

    public String getCharCode() {
        return charCode;
    }

    public List<String> getModifierList() {
        return mModifierList;
    }
}
