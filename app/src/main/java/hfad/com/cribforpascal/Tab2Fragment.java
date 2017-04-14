package hfad.com.cribforpascal;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Mephisto on 3/22/2017.
 */
public class Tab2Fragment extends Fragment {
    private TextView mStepByStepTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_two, container, false);
        mStepByStepTextView = (TextView)rootView.findViewById(R.id.tv_step_by_step);
        mStepByStepTextView.setText("TEST");
        return rootView;
    }
}
