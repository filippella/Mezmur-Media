package org.dalol.model.books;

import java.util.List;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 10/23/2016
 */
public class HolyContentBook {

    private String book_name;
    private String version;
    private List<HolyContent> contents;

    public String getBookName() {
        return book_name;
    }

    public String getVersion() {
        return version;
    }

    public List<HolyContent> getContents() {
        return contents;
    }
}
