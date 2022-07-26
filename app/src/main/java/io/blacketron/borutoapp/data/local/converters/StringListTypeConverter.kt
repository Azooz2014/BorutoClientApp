package io.blacketron.borutoapp.data.local.converters

import androidx.room.TypeConverter


class StringListTypeConverter {

    private val delimiter: Char = ','

    @TypeConverter
    fun convertListToString(list: List<String>): String{

        val stringBuilder: StringBuilder = StringBuilder()

        for(item in list){
            stringBuilder.append(item).append(delimiter)
        }

        //To remove the last unneeded separator (,)
        stringBuilder.setLength(stringBuilder.length - 1)

        return stringBuilder.toString()
    }

    @TypeConverter
    fun convertStringToList(string: String): List<String>{
        return string.split(delimiter)
    }
}