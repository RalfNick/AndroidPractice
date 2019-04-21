package wang.ralf.customview_draw;

import android.arch.lifecycle.Lifecycle;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name DrawFragment
 * @email -
 * @date 2019/04/21 下午2:27
 **/
public class DrawFragment extends Fragment{

    private static final String PARAM = "layoutRes";

    public static DrawFragment getInstance(@LayoutRes int layoutRes){
        DrawFragment fragment = new DrawFragment();
        Bundle args = new Bundle();
        args.putInt(PARAM, layoutRes);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        int layoutId = 0;
        if (arguments != null) {
            layoutId = arguments.getInt(PARAM);
        }
        return inflater.inflate(layoutId,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
