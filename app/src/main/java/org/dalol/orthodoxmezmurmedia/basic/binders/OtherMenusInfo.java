package org.dalol.orthodoxmezmurmedia.basic.binders;

import android.support.annotation.IntDef;

import org.dalol.orthodoxmezmurmedia.modules.churches.ChurchListActivity;
import org.dalol.orthodoxmezmurmedia.modules.holybooks.HolyBooksActivity;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static org.dalol.model.mezmur.MezumrConstants.MENU_ID_AMADE_MISTIRAT;
import static org.dalol.model.mezmur.MezumrConstants.MENU_ID_ANQUETSE_BIRHAN;
import static org.dalol.model.mezmur.MezumrConstants.MENU_ID_CHURCH_INFO;
import static org.dalol.model.mezmur.MezumrConstants.MENU_ID_DIRSANE_MICHAEL;
import static org.dalol.model.mezmur.MezumrConstants.MENU_ID_MELKEA_IYESUS;
import static org.dalol.model.mezmur.MezumrConstants.MENU_ID_MELKEA_MARIAM;
import static org.dalol.model.mezmur.MezumrConstants.MENU_ID_TSELOT_METSHAF;
import static org.dalol.model.mezmur.MezumrConstants.MENU_ID_WUDASSIE_AMLAK;
import static org.dalol.model.mezmur.MezumrConstants.MENU_ID_WUDASSIE_MARIAM;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 10/29/2016
 */
public enum OtherMenusInfo {

    AMADE_MISTIRAT(MENU_ID_AMADE_MISTIRAT, "amade_mistirat.json", "AMADE_MISTIRAT", HolyBooksActivity.class),
    ANQUETSE_BIRHAN(MENU_ID_ANQUETSE_BIRHAN, "anquetse_birhan.json", "ANQUETSE_BIRHAN", HolyBooksActivity.class),
    CHURCH_LIST(MENU_ID_CHURCH_INFO, "churches.json", "Church List", ChurchListActivity.class),
    DIRSANE_MICHAEL(MENU_ID_DIRSANE_MICHAEL, "dirsane_michael.json", "DIRSANE_MICHAEL", HolyBooksActivity.class),
    MELKEA_IYESUS(MENU_ID_MELKEA_IYESUS, "melkea_iyesus.json", "MELKEA_IYESUS", HolyBooksActivity.class),
    MELKEA_MARIAM(MENU_ID_MELKEA_MARIAM, "melkea_mariyam.json", "MELKEA_MARIAM", HolyBooksActivity.class),
    WUDASSIE_AMLAK(MENU_ID_WUDASSIE_AMLAK, "wuddasie_amlak.json", "WUDASSIE_AMLAK", HolyBooksActivity.class),
    WUDASSIE_MARIAM(MENU_ID_WUDASSIE_MARIAM, "wuddasie_mariam.json", "WUDASSIE_MARIAM", HolyBooksActivity.class);

    private final int mId;
    private final String mDataSource;
    private final String mTitle;
    private final Class<?> mClazz;

    OtherMenusInfo(@OtherMenuDefinitions int id, String dataSource, String title, Class<?> clazz) {
        mId = id;
        mDataSource = dataSource;
        mTitle = title;
        mClazz = clazz;
    }

    @OtherMenuDefinitions
    public int getId() {
        return mId;
    }

    public String getDataSource() {
        return mDataSource;
    }

    public String getTitle() {
        return mTitle;
    }

    public Class<?> getClazz() {
        return mClazz;
    }

    public static OtherMenusInfo getById(int id) {
        OtherMenusInfo menu = null;
        for (OtherMenusInfo menusBinder : values()) {
            if (menusBinder.getId() == id) {
                menu = menusBinder;
                break;
            }
        }
        return menu;
    }

    @IntDef({
            MENU_ID_CHURCH_INFO,
            MENU_ID_TSELOT_METSHAF,
            MENU_ID_AMADE_MISTIRAT,
            MENU_ID_ANQUETSE_BIRHAN,
            MENU_ID_DIRSANE_MICHAEL,
            MENU_ID_MELKEA_IYESUS,
            MENU_ID_MELKEA_MARIAM,
            MENU_ID_WUDASSIE_AMLAK,
            MENU_ID_WUDASSIE_MARIAM
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface OtherMenuDefinitions {
    }
}
