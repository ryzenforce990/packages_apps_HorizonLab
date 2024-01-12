/*
 * Copyright (C) 2024 HorizonDroid
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

import android.content.*;
import android.view.View;
import android.widget.*;

import androidx.preference.PreferenceScreen;

import com.android.settings.core.BasePreferenceController;

import com.android.settingslib.widget.LayoutPreference;

import com.google.android.material.card.MaterialCardView;

import com.android.settings.R;

public class HorizonHeaderController extends BasePreferenceController implements View.OnClickListener {
	
	private LayoutPreference mPreference;
	private MaterialCardView aboutCard;
	private LinearLayout quickSettingsCard, statusBarCard;
	
	public HorizonHeaderController(Context context, String key) {
		super(context, key);
	}
	
	@Override
	public int getAvailabilityStatus() {
	    return AVAILABLE;
	}
	
	@Override
	public void displayPreference(PreferenceScreen screen) {
		super.displayPreference(screen);
		mPreference = screen.findPreference("hzn_header");
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
		intent.setComponent(new ComponentName("com.android.settings", "com.android.settings.Settings$" + activity));
		mContext.startActivity(intent);
	}
	
}