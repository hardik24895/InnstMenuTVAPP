package com.eisuchi.extention

import android.text.TextUtils
import android.util.Patterns
import java.util.regex.Matcher
import java.util.regex.Pattern


fun isValidName(text: String): Boolean {
    val expression = "^[a-z A-Z]{1,}$"
    val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
    val matcher = pattern.matcher(text)
    return matcher.matches()
}

fun isValidEmail(target: CharSequence): Boolean {
    return if (TextUtils.isEmpty(target)) {
        false
    } else {
        Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }
}

fun isValidPassword(text: String): Boolean {
    val expression = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@\$%^&*-]).{8,}\$"
    val pattern = Pattern.compile(expression)
    val matcher = pattern.matcher(text)
    return matcher.matches()
}

var EMAIL_PATTERN =
    "^[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-z0-9])?\\.)+[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?$"

fun validateEmail(target: String?): Boolean {
    return if (target != null && target.length > 1) {
        val pattern = Pattern.compile(EMAIL_PATTERN)
        val matcher: Matcher = pattern.matcher(target)
        matcher.matches()
    } else if (target!!.length == 0) {
        false
    } else {
        false
    }
}