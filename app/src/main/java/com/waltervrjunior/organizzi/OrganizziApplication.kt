package com.waltervrjunior.organizzi

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate

class OrganizziApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    }
}