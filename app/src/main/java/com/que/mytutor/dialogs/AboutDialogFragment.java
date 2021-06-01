package com.que.mytutor.dialogs;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.danielstone.materialaboutlibrary.MaterialAboutFragment;
import com.danielstone.materialaboutlibrary.items.MaterialAboutActionItem;
import com.danielstone.materialaboutlibrary.items.MaterialAboutTitleItem;
import com.danielstone.materialaboutlibrary.model.MaterialAboutCard;
import com.danielstone.materialaboutlibrary.model.MaterialAboutList;
import com.que.mytutor.R;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

import static androidx.fragment.app.DialogFragment.STYLE_NO_FRAME;


public class AboutDialogFragment extends DialogFragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_FRAME, R.style.FullScreenDialogStyle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Element adsElement = new Element();
        adsElement.setTitle("Advertise with us");

        return new AboutPage(getContext())
                .isRTL(false)
                .enableDarkMode(false)
                .setDescription("Thima")
                //  .setImage(R.drawable.dummy_image)
                .addItem(new Element().setTitle("Version 0.0.1"))
                .addGroup("Connect with us")
                .addEmail("thimakulani@gmail.com")
                .addWebsite("thimakulani.github.io")
                .addFacebook("thima.sigauque")
                .addGitHub("thimakulani")
                .addItem(getCopyRightsElement())
                .create();
    }


    Element getCopyRightsElement() {
        Element copyRightsElement = new Element();
        final String copyrights = "Thapelo";
        copyRightsElement.setTitle(copyrights);
        copyRightsElement.setIconDrawable(R.drawable.tenacity);
        copyRightsElement.setAutoApplyIconTint(true);
        copyRightsElement.setIconTint(mehdi.sakout.aboutpage.R.color.about_item_icon_color);
        copyRightsElement.setIconNightTint(android.R.color.white);
        copyRightsElement.setGravity(Gravity.CENTER);
        copyRightsElement.setOnClickListener(v -> Toast.makeText(getContext(), copyrights, Toast.LENGTH_SHORT).show());
        return copyRightsElement;
    }

}