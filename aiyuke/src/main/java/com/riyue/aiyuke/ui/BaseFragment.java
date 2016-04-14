package com.riyue.aiyuke.ui;

import android.net.Uri;
import android.support.v4.app.Fragment;

/**
 * @author mtf
 * @date 2016/4/2
 */
public class BaseFragment extends Fragment{
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
