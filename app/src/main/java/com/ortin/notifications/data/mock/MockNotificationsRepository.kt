package com.ortin.notifications.data.mock

import com.ortin.notifications.data.models.Notification
import com.ortin.notifications.domain.repository.NotificationRepository
import kotlinx.coroutines.delay
import kotlin.random.Random

internal class MockNotificationRepositor(private val count: Int = 15) : NotificationRepository {
    private val incidentPairs = listOf(
        "https://24.kg/files/media/387/387445.jpg" to "Наводнение",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ5IS5C3lzkZBiK8oOLN0J1PdezbzGvO7tm5A&s" to "Извержение вулкана",
        "https://0d314c86-f76b-45cc-874e-45816116a667.selcdn.net/4c3fd175-aef5-479e-b726-6ef4a34d9c40.jpg" to "Землетрясение",
        "https://kokl.ua/wp-content/uploads/2025/06/najbilshe-czunami-v-sviti.jpg" to "Цунами",
        "https://global.unitednations.entermediadb.net/assets/mediadb/services/module/asset/downloads/preset/Libraries/Production+Library/06-11-2020_UNAMID_sand-storm.jpg/image1170x530cropped.jpg" to "Песчаная буря",
        "https://static.ukrinform.com/photos/2019_09/thumb_files/630_360_1568041576-670.jpg" to "Тайфун",
        "https://dlpinfo.org/wp-content/uploads/2022/11/003.jpg" to "Лесной пожар",
        "https://thumbs.dreamstime.com/b/%D1%81%D0%BD%D0%B5%D0%B6%D0%BD%D0%B0%D1%8F-%D0%B1%D1%83%D1%80%D1%8F-%D0%BB%D0%B5%D1%82%D0%B0%D1%8E%D1%89%D0%B0%D1%8F-%D0%B2%D0%BE-%D0%B2%D1%81%D0%B5%D1%85-%D0%BD%D0%B0%D0%BF%D1%80%D0%B0%D0%B2%D0%BB%D0%B5%D0%BD%D0%B8%D1%8F%D1%85-%D1%81%D0%BE%D0%B7%D0%B4%D0%B0%D0%BD%D0%BD%D0%B0%D1%8F-%D1%81-%D0%BF%D0%BE%D0%BC%D0%BE%D1%89%D1%8C%D1%8E-275634113.jpg" to "Снежная буря",
        "https://xacavurt.ru/uploads/posts/2021-08/1628777091_scale_1200.jpg" to "Селевой поток"
    )

    private fun createNotification(index: Int): Notification {
        val (imageUrl, incidentType) = incidentPairs.random()
        val now = System.currentTimeMillis()
        val oneYearMillis = 365L * 24 * 60 * 60 * 1000
        val randomDate = now - Random.nextLong(0, oneYearMillis)

        val titles = listOf(
            "Срочное предупреждение!",
            "Внимание, чрезвычайная ситуация",
            "Оповещение службы МЧС",
            "Прогноз опасных условий",
            "Экстренное уведомление"
        )

        val descriptions = listOf(
            "Пожалуйста, оставайтесь дома и следите за обновлениями.",
            "Эвакуация возможна в ближайшие часы. Подготовьтесь заранее.",
            "Синоптики прогнозируют ухудшение погодных условий.",
            "Просьба не покидать безопасные зоны.",
            "Соблюдайте меры предосторожности и следите за официальной информацией."
        )

        return Notification(
            id = index.toString(),
            title = titles.random(),
            description =
                """
                    Произошло серьёзное опасное явление - $incidentType.
                    В целях вашей безопасности сохраняйте спокойствие.
                    ${descriptions.random()}
                """.trimIndent(),
            imageUrl = imageUrl,
            dateTime = randomDate,
            isRead = Random.nextBoolean(),
            incidentType = incidentType
        )
    }

    override suspend fun getNotifications(id: String): List<Notification> {
        delay(500)
        return List(count) { createNotification(it + 1) }
    }
}
