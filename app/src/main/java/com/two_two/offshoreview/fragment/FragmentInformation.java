package com.two_two.offshoreview.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.two_two.offshoreview.R;

public class FragmentInformation extends Fragment{

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.name_fragment_info);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_layout_info, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.infoContent);
        textView.setText(Html.fromHtml(getString(R.string.content_info_blog)));
        Button btn = (Button) rootView.findViewById(R.id.btnGoToSite);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://offshoreview.eu"));
                startActivity(intent);
            }
        });
        return rootView;
    }
}
