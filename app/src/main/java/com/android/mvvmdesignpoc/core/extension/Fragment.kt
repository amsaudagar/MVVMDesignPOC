package com.android.mvvmdesignpoc.core.extension

inline fun androidx.fragment.app.FragmentManager.inTransaction(func: androidx.fragment.app.FragmentTransaction.() -> androidx.fragment.app.FragmentTransaction) =
        beginTransaction().func().commit()