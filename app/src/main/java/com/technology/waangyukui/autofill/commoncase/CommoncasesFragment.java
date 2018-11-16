package com.technology.waangyukui.autofill.commoncase;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.technology.waangyukui.autofill.BaseMainFragment;
import com.technology.waangyukui.mycyclerapp.R;

/**
 * Created by lenvo on 2018/6/1.
 */

public class CommoncasesFragment extends BaseMainFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_common_case,container,false);
    }

    @Override
    public int getPagerTitleId() {
        return R.string.common_case_page_title;
    }
}
