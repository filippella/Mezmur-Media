package org.dalol.model.mezmur;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 10/9/2016
 */
public class MezmurListItem implements Serializable {

    private String cid;
    private String name;
    private String morder;
    private List<String> mezmurIdList = new ArrayList<>();

    public MezmurListItem(String cid, String name, String morder) {
        this.cid = cid;
        this.name = name;
        this.morder = morder;
    }

    public String getCid() {
        return cid;
    }

    public String getName() {
        return name;
    }

    public String getMorder() {
        return morder;
    }

    public List<String> getMezmurIdList() {
        return mezmurIdList;
    }

    public void addMezmurId(String mid) {
        mezmurIdList.add(mid);
    }
}
