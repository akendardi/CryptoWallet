package com.akendardi.cryptowallet.presentation.coin_info_screen.information.utils

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

class TimeConverter {

    companion object {
        fun selectSixDates(data: List<List<String>>): List<List<String>> {
            if (data.size <= 6) return data

            val step = (data.size - 1) / 5
            return List(6) { i -> data[i * step] }
        }

        fun selectSixHours(data: List<List<String>>): List<List<String>> {
            if (data.size <= 6) return data

            val step = (data.size - 1) / 5
            return List(6) { i ->
                if (i == 5) data.last() else data[i * step]
            }
        }

        fun clearHoursDates(hours: List<List<String>>): List<List<String>> {
            val days = mutableSetOf<String>()
            val list = mutableListOf<List<String>>()
            for (hour in hours) {
                if (hour[1] !in days) {
                    list.add(hour)
                    days.add(hour[1])
                } else {
                    list.add(listOf(hour[0], "", ""))
                }
            }
            return list
        }


        fun formatUnixTimestampsForDays(timestamps: List<Long>): List<List<String>> {
            val formatter = DateTimeFormatter.ofPattern("dd MMM E")
            val zoneId = ZoneId.systemDefault()

            return timestamps.map { timestamp ->
                val dateTime = Instant.ofEpochSecond(timestamp).atZone(zoneId)
                val formattedDate = dateTime.format(formatter)

                formattedDate.split(" ").map {

                    it.replaceFirstChar { char ->
                        if (char.isLowerCase()) char.titlecase(
                            Locale.ROOT
                        ) else char.toString()
                    }
                }
            }
        }

        fun formatUnixTimestampsForHours(timestamps: List<Long>): List<List<String>> {
            val formatter = DateTimeFormatter.ofPattern("HH dd MMM")
            val zoneId = ZoneId.systemDefault()

            return timestamps.map { timestamp ->
                val dateTime = Instant.ofEpochSecond(timestamp).atZone(zoneId)
                val formattedDateTime = dateTime.format(formatter)

                formattedDateTime.split(" ").map {
                    it.replaceFirstChar { char ->
                        if (char.isLowerCase()) char.titlecase(Locale.ROOT) else char.toString()
                    }
                }
            }
        }

        fun formatUnixTimeToHoursAndMinutes(timestamp: Long): String {
            val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
            val dateTime = Instant.ofEpochSecond(timestamp)
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime()
            return dateTime.format(formatter)
        }
    }
}