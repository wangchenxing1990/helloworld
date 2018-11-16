package com.technology.waangyukui.autofill;

import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;

/**
 * Created by lenvo on 2018/6/1.
 */

public abstract class BaseMainFragment extends Fragment {
    public abstract  @StringRes int getPagerTitleId();
}
