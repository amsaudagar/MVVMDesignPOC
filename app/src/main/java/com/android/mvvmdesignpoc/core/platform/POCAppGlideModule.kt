package com.android.mvvmdesignpoc.core.platform

import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@GlideModule
@Module
@InstallIn(SingletonComponent::class)
class POCAppGlideModule : AppGlideModule()