package org.dalol.model.books;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 10/23/2016
 */
public class HolyContent {

    private int id;
    private String title;
    private String header;
    private String audioURL;
    private String videoURL;
    private String body;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getHeader() {
        return header;
    }

    public String getBody() {
        return body;
    }

    public String getAudioURL() {
        return audioURL;
    }

    public String getVideoURL() {
        return videoURL;
    }
}
