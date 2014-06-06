package com.tstine.spark.fragment;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.text.style.TextAppearanceSpan;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.tstine.spark.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 * Created by taylorstine on 6/6/14.
 */
@EFragment(R.layout.search_fragment)
public class SearchFragment extends BaseFragment {

    @ViewById  ListView search_list_categories;
    @ViewById  TextView search_field;

    @AfterViews
    public void afterViewInject(){
        SpannableString message = new SpannableString(getString(R.string.search_hint));
        message.setSpan(new TextAppearanceSpan(getActivity(), R.style.spark_text), 0, message.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        SpannableString imageSpan = new SpannableString("   ");
        imageSpan.setSpan(new ImageSpan(getActivity(), R.drawable.abc_ic_search), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        SpannableStringBuilder builder = new SpannableStringBuilder();
        builder.append(imageSpan).append(" ").append(message);

        search_field.setHint(builder);

        search_list_categories.setAdapter(new ArrayAdapter<String>(getActivity(), R.layout.search_field_text_view, getResources().getStringArray(R.array.search_categories)));
    }
}
