package wang.ralf.customview_draw;

import android.arch.lifecycle.Lifecycle;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name DrawFragment
 * @email -
 * @date 2019/04/21 下午2:27
 **/
public class DrawFragment extends Fragment {

    private static final String PARAM = "layoutRes";
    private ServicePopupWindow mPopupWindow;

    public static DrawFragment getInstance(@LayoutRes int layoutRes) {
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
        return inflater.inflate(layoutId, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final Button button = view.findViewById(R.id.pop_btn);
        if (button != null) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    View inflate = LayoutInflater.from(getContext()).inflate(R.layout.popup_window_option_lalyout, null);
                    mPopupWindow = new ServicePopupWindow.Builder(getContext())
                            .size(400,900)
                            .focusable(true)
                            .view(inflate)
                            .outsideTouchable(true)
                            .dismissListener(new PopupWindow.OnDismissListener() {
                                @Override
                                public void onDismiss() {
                                    Toast.makeText(getContext(), "关闭", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .itemClickListener(new ServicePopupWindow.ItemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                    mPopupWindow.dismiss();
                                    Toast.makeText(getContext(), "item", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .build();
                    mPopupWindow.showAsDropDown(button);
                }
            });
        }
    }
}
