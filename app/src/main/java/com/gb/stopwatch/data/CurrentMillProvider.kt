package com.gb.stopwatch.data

import com.gb.stopwatch.domain.TimestampProvider

class CurrentMillProvider : TimestampProvider {
    override fun getMilliseconds(): Long {
        return System.currentTimeMillis()
    }
}