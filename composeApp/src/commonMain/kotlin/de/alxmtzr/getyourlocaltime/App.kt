package de.alxmtzr.getyourlocaltime

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import getyourlocaltime.composeapp.generated.resources.Res
import getyourlocaltime.composeapp.generated.resources.compose_multiplatform
import kotlinx.datetime.Clock
import kotlinx.datetime.IllegalTimeZoneException
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@Composable
@Preview
fun App() {
    MaterialTheme {
        var showCountries by remember { mutableStateOf(false) }
        var timeAtLocation by remember { mutableStateOf("No location selected") }

        Column(modifier = Modifier.padding(20.dp)) {
            Text(
                timeAtLocation,
                style = TextStyle(fontSize = 20.sp),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally)
            )
            Row(modifier = Modifier.padding(start = 20.dp, top = 10.dp)) {
                DropdownMenu(
                    expanded = showCountries,
                    onDismissRequest = { showCountries = false }
                ) {
                    countries().forEach { (name, zone) ->
                        DropdownMenuItem(onClick = {
                            timeAtLocation = currentTimeAt(zone.id)?: "Invalid Location"
                            showCountries = false
                        }) {
                            Text(text = name)
                        }
                    }
                }
            }
            Button(
                onClick = { showCountries = !showCountries },
                modifier = Modifier.padding(top = 10.dp)
            ) {
                Text("Select Location")
            }
        }
    }
}

data class Country(val name: String, val zone: TimeZone)

fun currentTimeAt(location: String): String? {
    fun LocalTime.formatted() = "$hour:$minute:$second"

    return try {
        val time = Clock.System.now()
        val zone = TimeZone.of(location)
        val localTime = time.toLocalDateTime(zone).time
        "The time in $location is ${localTime.formatted()}"
    }catch (ex: IllegalTimeZoneException) {
        null
    }
}

fun countries() = listOf(
    Country("Japan", TimeZone.of("Asia/Tokyo")),
    Country("France", TimeZone.of("Europe/Paris")),
    Country("Germany", TimeZone.of("Europe/Berlin")),
    Country("United States", TimeZone.of("America/New_York")),
    Country("India", TimeZone.of("Asia/Kolkata")),
    Country("Russia", TimeZone.of("Europe/Moscow")),
    Country("United Kingdom", TimeZone.of("Europe/London")),
    Country("Canada", TimeZone.of("America/Toronto")),
    Country("Australia", TimeZone.of("Australia/Sydney")),
    Country("China", TimeZone.of("Asia/Shanghai")),
    Country("Brazil", TimeZone.of("America/Sao_Paulo")),
    Country("Argentina", TimeZone.of("America/Buenos_Aires")),
    Country("Mexico", TimeZone.of("America/Mexico_City")),
    Country("South Africa", TimeZone.of("Africa/Johannesburg")),
    Country("Egypt", TimeZone.of("Africa/Cairo")),
    Country("South Korea", TimeZone.of("Asia/Seoul")),
    Country("Turkey", TimeZone.of("Europe/Istanbul")),
    Country("Italy", TimeZone.of("Europe/Rome")),
    Country("Spain", TimeZone.of("Europe/Madrid")),
    Country("Netherlands", TimeZone.of("Europe/Amsterdam")),
    Country("Sweden", TimeZone.of("Europe/Stockholm")),
    Country("Switzerland", TimeZone.of("Europe/Zurich")),
    Country("Austria", TimeZone.of("Europe/Vienna")),
    Country("Belgium", TimeZone.of("Europe/Brussels")),
    Country("Ireland", TimeZone.of("Europe/Dublin")),
    Country("Portugal", TimeZone.of("Europe/Lisbon")),
    Country("Denmark", TimeZone.of("Europe/Copenhagen")),
    Country("Norway", TimeZone.of("Europe/Oslo")),
    Country("Finland", TimeZone.of("Europe/Helsinki")),
    Country("Greece", TimeZone.of("Europe/Athens")),
    Country("Iceland", TimeZone.of("Atlantic/Reykjavik")),
    Country("Greenland", TimeZone.of("America/Godthab")),
    Country("United Arab Emirates", TimeZone.of("Asia/Dubai")),
).sortedBy { it.name }



