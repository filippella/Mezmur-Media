package org.dalol.orthodoxmezmurmedia.basic.binders;

import android.support.annotation.DrawableRes;

import org.dalol.orthodoxmezmurmedia.R;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 10/30/2016
 */
public enum MezmurCategoryInfo {

    MEZMUR_CATEGORY_1(34, "የሰንበት ት/ቤት መዝሙር", 1, R.drawable.saint_gebriel),
    MEZMUR_CATEGORY_2(35, "መዝሙር በዘማሪያን", 2, R.drawable.saint_gebriel),
    MEZMUR_CATEGORY_3(36, "የእናንተው መዝሙር ስብስብ", 3, R.drawable.saint_gebriel),
    MEZMUR_CATEGORY_4(1, "የዐውድ ዓመት መዝሙራት", 1, R.drawable.ic_new_year),
    MEZMUR_CATEGORY_5(2, "የመስቀል መዝሙራት", 2, R.drawable.ic_mesqel),
    MEZMUR_CATEGORY_6(3, "የመስቀል መዝሙራት በኦሮምኛ", 1, R.drawable.ic_meskel),
    MEZMUR_CATEGORY_7(4, "የወርኃ ጽጌ መዝሙራት", 1, R.drawable.ic_tsige),
    MEZMUR_CATEGORY_8(5, "የመድኃዓለም(አማኑኤል) መዝሙራት", 1, R.drawable.ic_medhanialem),
    MEZMUR_CATEGORY_9(6, "የቅዱስ ሚካኤል መዝሙራት", 1, R.drawable.ic_saint_michael),
    MEZMUR_CATEGORY_10(7, "የኀዳር ጽዮን መዝሙራት", 1, R.drawable.ic_tsion_mariam),
    MEZMUR_CATEGORY_11(8, "የልደት እና የጥምቀት", 1, R.drawable.ic_timket),
    MEZMUR_CATEGORY_12(9, "የሠርግ መዝሙራት", 1, R.drawable.ic_wedding),
    MEZMUR_CATEGORY_13(10, "የአስትርእዮ ማርያም መዝሙራት", 1, R.drawable.ic_stmary_two),
    MEZMUR_CATEGORY_14(11, "የኪዳነ ምሕረት የካቲት መዝሙራት", 1, R.drawable.ic_kidnamehert),
    MEZMUR_CATEGORY_15(12, "የሰንበት ት/ቤት መዝሙር", 1, R.drawable.yared),
    MEZMUR_CATEGORY_16(13, "የንስሓ መዝሙራት", 1, R.drawable.ic_neseha),
    MEZMUR_CATEGORY_17(14, "የሆሣዕና መዝሙራት", 1, R.drawable.ic_hosaena),
    MEZMUR_CATEGORY_18(15, "የትንሣኤ መዝሙራት", 1, R.drawable.ic_tinsae),
    MEZMUR_CATEGORY_19(16, "የዕርገት እና የጵራቅሊጦስ መዝሙራት", 1, R.drawable.ic_erget),
    MEZMUR_CATEGORY_20(17, "የግንቦት ልደታ መዝሙራት", 1, R.drawable.ic_stmary_three),
    MEZMUR_CATEGORY_21(18, "የመላእክት መዝሙራት", 1, R.drawable.ic_angels),
    MEZMUR_CATEGORY_22(19, "የቅዱሳን ጻድቃን መዝሙራት", 1, R.drawable.ic_saints),
    MEZMUR_CATEGORY_23(20, "የሰማዕታት መዝሙራት", 1, R.drawable.ic_semaetat),
    MEZMUR_CATEGORY_24(21, "ለቤተ ክርስቲያን እና ለአባቶቸ የሚዘመሩ መዝሙራት", 1, R.drawable.ic_church),
    MEZMUR_CATEGORY_25(22, "የምስጋና መዝሙራት", 1, R.drawable.kebero),
    MEZMUR_CATEGORY_26(23, "የሕጻናት መዝሙራት", 1, R.drawable.ic_styared),
    MEZMUR_CATEGORY_27(24, "የደብረ ታቦር መዝሙራት", 1, R.drawable.ic_debretabor),
    MEZMUR_CATEGORY_28(25, "የእመቤታችን መዝሙራት", 1, R.drawable.mariam),
    MEZMUR_CATEGORY_29(26, "የቅዱስ ዑራኤል መዝሙራት", 1, R.drawable.ic_urael),
    MEZMUR_CATEGORY_30(27, "የቅዱስ ገብርኤል መዝሙራት", 1, R.drawable.saint_gebriel),
    MEZMUR_CATEGORY_31(28, "የቅዱስ ሩፋኤል መዝሙራት", 1, R.drawable.ic_rufael),
    MEZMUR_CATEGORY_32(29, "የቅዱስ ራጉኤል መዝሙራት", 1, R.drawable.ic_raguel),
    MEZMUR_CATEGORY_33(30, "ወረብ", 1, R.drawable.ic_wereb_two),
    MEZMUR_CATEGORY_34(31, "ግዕዝ መዝሙሮች", 1, R.drawable.ic_geez);

    private final int mCategoryId;
    private final String mCategoryTitle;
    private final int mCategoryOrder;
    private final int mResId;

    MezmurCategoryInfo(int categoryId, String categoryTitle, int categoryOrder, @DrawableRes int resId) {
        mCategoryId = categoryId;
        mCategoryTitle = categoryTitle;
        mCategoryOrder = categoryOrder;
        mResId = resId;
    }

    public int getCategoryId() {
        return mCategoryId;
    }

    public int getCategoryOrder() {
        return mCategoryOrder;
    }

    public int getResId() {
        return mResId;
    }

    public String getCategoryTitle() {
        return mCategoryTitle;
    }

    public static MezmurCategoryInfo getByCategoryId(int id) {
        MezmurCategoryInfo categoryInfo = null;
        for (MezmurCategoryInfo info : values()) {
            if (info.getCategoryId() == id) {
                categoryInfo = info;
                break;
            }
        }
        return categoryInfo;
    }
}
