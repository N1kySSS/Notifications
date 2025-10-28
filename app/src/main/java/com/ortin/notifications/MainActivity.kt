package com.ortin.notifications

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import com.ortin.notifications.presentation.detail.DetailScreen
import com.ortin.notifications.presentation.detail.DetailScreenViewModel
import com.ortin.notifications.ui.theme.NotificationsTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val detailsViewModel: DetailScreenViewModel by viewModel()

    private val title = "Наводнение"

    private val description =
        """    При получении предупреждения об угрозе наводнения сообщите об этом близким и соседям, помогите престарелым и больным. 
    Внимательно слушайте радио, не выключайте радиоточки в ночное время — там сообщат о времени и границах затопления, рекомендациях по поведению и порядке эвакуации.
    Перед эвакуацией отключите воду, газ, электричество, погасите огонь в печах. Перенесите на верхние этажи или чердак ценные вещи, продовольствие, одежду и обувь.
    Закройте окна и двери, при необходимости обейте их досками или фанерой. Выпустите животных из помещений, отвяжите собак. 
    Из подвалов уберите всё, что может испортиться от воды.
    При сигнале об эвакуации быстро соберите:
        - документы в герметичной упаковке;
        - ценности и деньги;
        - аптечку первой помощи и привычные лекарства;
        - комплект одежды и обуви по сезону;
        - трёхдневный запас продуктов и воды (общий вес — не более 50кг);
        - туалетные принадлежности и постельное бельё."""


    private val image = "https://poltavskoe-r52.gosweb.gosuslugi.ru/netcat_files/413/2265/i_2_.jpg"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // TODO: replace with navigation in the future
            NotificationsTheme {
                Scaffold { paddingValues ->
                    DetailScreen(
                        title = title,
                        description = description,
                        image = image,
                        onClick = detailsViewModel::reportAction,
                        modifier = Modifier.padding(paddingValues)
                    )
                }
            }
        }
    }
}
