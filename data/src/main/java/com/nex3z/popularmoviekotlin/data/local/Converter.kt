package com.nex3z.popularmoviekotlin.data.local

import android.arch.persistence.room.TypeConverter

class Converter {

    @TypeConverter
    fun stringToIntegerList(str: String): List<Int> {
        val items = str.split("\\s*,\\s*".toRegex())
        return items.map { Integer.parseInt(it) }
    }

    @TypeConverter
    fun integerListToString(list: List<Int>): String {
        val sb = StringBuilder()
        for (item in list) {
            sb.append(item)
            sb.append(",")
        }
        return sb.toString()
    }

}