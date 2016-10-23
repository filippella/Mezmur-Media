package org.dalol.model.callback;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 10/23/2016
 */
public interface OnZoomActionListener {

    float DEFAULT_TEXT_SIZE = 15f;

    /**
     * This method will be used to manage the size of the text
     */
    void onZoomIn();

    /**
     * This method will be used to manage the size of the text
     */
    void onZoomOut();
}
