/*
 * Copyright (C) 2020 Project-Awaken
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.horizon.lab;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.provider.SearchIndexableResource;

import androidx.preference.*;
import androidx.recyclerview.widget.*;

import com.android.settings.R;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settingslib.search.SearchIndexable;
import com.android.settingslib.widget.LayoutPreference;
import com.google.android.material.card.MaterialCardView;

import com.android.internal.logging.nano.MetricsProto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HorizonDashboard extends SettingsPreferenceFragment implements View.OnClickListener {
	
	private LayoutPreference mPreference;
	private MaterialCardView aboutCard;
	private LinearLayout quickSettingsCard, statusBarCard;
	
	@Override
	public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        addPreferencesFromResource(R.xml.horizon_dashboard);
        
        mPreference = findPreference("hzn_header");
		aboutCard = mPreference.findViewById(R.id.hzn_about_card);
		quickSettingsCard = mPreference.findViewById(R.id.hzn_qspanel_card);
		statusBarCard = mPreference.findViewById(R.id.hzn_statusbar_card);
		
		aboutCard.setOnClickListener(this);
		quickSettingsCard.setOnClickListener(this);
		statusBarCard.setOnClickListener(this);
    }
    
    @Override
	public void onClick(View v) {
		if (v == aboutCard) {
			startActivity("AboutTeamActivity");
		} else if (v == quickSettingsCard) {
			startActivity("QuickSettingsActivity");
		} else if (v == statusBarCard) {
			startActivity("StatusBarActivity");
		}
	}
	
	private void startActivity(String activity) {
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.setClassName("com.android.settings", "com.android.settings.Settings$" + activity);
		getContext().startActivity(intent);
	}
	
	@Override
	public int getMetricsCategory() {
        return MetricsProto.MetricsEvent.HORIZON;
    }
	
	@Override
	public RecyclerView onCreateRecyclerView(LayoutInflater inflater, ViewGroup container, Bundle icicle) {
		RecyclerView rcv = super.onCreateRecyclerView(inflater, container, icicle);
		GridLayoutManager layoutG = new GridLayoutManager(getActivity(), 2);
		layoutG.setSpanSizeLookup(new SpanSizeLookupG());
		rcv.setLayoutManager(layoutG);
		return rcv;
	}
	
	class SpanSizeLookupG extends GridLayoutManager.SpanSizeLookup {
		@Override
		public int getSpanSize(int position) {
		    if (position == 0 || position == 1) {
				return 2;
			} else {
				return 1;
			}
		}
	}
	
	public static final BaseSearchIndexProvider SEARCH_INDEX_DATA_PROVIDER =
            new BaseSearchIndexProvider() {
                @Override
                public List<SearchIndexableResource> getXmlResourcesToIndex(
                        Context context, boolean enabled) {
                    final SearchIndexableResource sir = new SearchIndexableResource(context);
                    sir.xmlResId = R.xml.horizon_dashboard;
                    return Arrays.asList(sir);
                }

                @Override
                public List<String> getNonIndexableKeys(Context context) {
                    final List<String> keys = super.getNonIndexableKeys(context);
                    return keys;
                }
            };
	
}
