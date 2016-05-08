package lin.jiang.notice.presentation.drawer;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import lin.jiang.notice.R;
import lin.jiang.notice.presentation.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DrawerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DrawerFragment extends BaseFragment {


    public DrawerFragment() {
        // Required empty public constructor
    }
    public static DrawerFragment newInstance() {
        DrawerFragment fragment = new DrawerFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_drawer, container, false);
    }

    @Override
    protected void InitData() {

    }
}
