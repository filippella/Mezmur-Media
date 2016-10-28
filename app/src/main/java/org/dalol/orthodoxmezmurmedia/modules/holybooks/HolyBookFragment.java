package org.dalol.orthodoxmezmurmedia.modules.holybooks;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.util.TypedValue;
import android.view.View;

import org.dalol.model.callback.OnZoomActionListener;
import org.dalol.orthodoxmezmurmedia.R;
import org.dalol.orthodoxmezmurmedia.basic.base.BaseFragment;
import org.dalol.orthodoxmezmurmedia.utilities.custom.AmharicTextView;

import butterknife.BindView;

import static org.dalol.model.mezmur.MezumrConstants.KEY_HOLY_BOOK_BODY;
import static org.dalol.model.mezmur.MezumrConstants.KEY_HOLY_BOOK_HEADER;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 10/23/2016
 */
public class HolyBookFragment extends BaseFragment implements OnZoomActionListener {

    @BindView(R.id.holy_book_header_textview)
    protected AmharicTextView mHeaderTextView;
    @BindView(R.id.holy_book_body_textview)
    protected AmharicTextView mBodyTextView;

    private String mHolyBookBody;
    private String mHolyBookHeader;

    public static HolyBookFragment newInstance(String header, String body) {
        Bundle args = new Bundle();
        args.putString(KEY_HOLY_BOOK_HEADER, header);
        args.putString(KEY_HOLY_BOOK_BODY, body);
        HolyBookFragment fragment = new HolyBookFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        mHolyBookHeader = arguments.getString(KEY_HOLY_BOOK_HEADER);
        mHolyBookBody = arguments.getString(KEY_HOLY_BOOK_BODY);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context != null) {
            HolyBooksActivity activity = (HolyBooksActivity) getActivity();
            activity.addZoomListener(this);
        }
    }

    @Override
    protected void bindView(View view) {
        super.bindView(view);
        mHeaderTextView.setTextSize(DEFAULT_TEXT_SIZE + 5f);
        mHeaderTextView.setText(mHolyBookHeader);
        mBodyTextView.setTextSize(DEFAULT_TEXT_SIZE);
        mBodyTextView.setText(Html.fromHtml(mHolyBookBody));
    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_holy_book;
    }

    @Override
    public void onZoomIn() {
        float bodyTextSize = mBodyTextView.getTextSize() + 2f;
        float headerTextSize = mHeaderTextView.getTextSize() + 2f;
        mBodyTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, bodyTextSize);
        mHeaderTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, headerTextSize);
    }

    @Override
    public void onZoomOut() {
        float bodyTextSize = mBodyTextView.getTextSize() - 2f;
        float headerTextSize = mHeaderTextView.getTextSize() - 2f;
        mBodyTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, bodyTextSize);
        mHeaderTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, headerTextSize);
    }
}
