package date;

import models.OrderModel;

import java.util.Arrays;
import java.util.List;

public class OrderTestDate {

    // Настроим RestAssured для использования базового URL
    static {
        RestAssuredConfig.setBaseURL(); // Устанавливаем базовый URL
    }


    // Основные данные для заказа
    public static final String FIRST_NAME = "Рукия";
    public static final String LAST_NAME = "Кучики";
    public static final String ADDRESS = "Гатей 13";
    public static final int METRO_STATION = 4;
    public static final String PHONE = "343455673535";
    public static final int RENT_TIME = 3; // Время аренды
    public static final String DELIVERY_DATE = "2025-02-20";
    public static final String COMMENT = "Первый танец - Хакурен.";
    public static final String[] COLOR = {"BLACK", "GREY"};

    // Метод, который возвращает объект заказа с валидными данными
    public static OrderModel getValidOrder() {
        return new OrderModel(FIRST_NAME, LAST_NAME, ADDRESS, METRO_STATION, PHONE, RENT_TIME, DELIVERY_DATE, COMMENT, Arrays.asList(COLOR));
    }

    // Метод, который возвращает список заказов с разными комбинациями цветов самоката
    public static List<OrderModel> getOrdersWithOptionalColors() {
        return Arrays.asList(
                new OrderModel(FIRST_NAME, LAST_NAME, ADDRESS, METRO_STATION, PHONE, RENT_TIME, DELIVERY_DATE, COMMENT, Arrays.asList(COLOR[0])), // Только черный цвет
                new OrderModel(FIRST_NAME, LAST_NAME, ADDRESS, METRO_STATION, PHONE, RENT_TIME, DELIVERY_DATE, COMMENT, Arrays.asList(COLOR[1])), // Только серый цвет
                new OrderModel(FIRST_NAME, LAST_NAME, ADDRESS, METRO_STATION, PHONE, RENT_TIME, DELIVERY_DATE, COMMENT, Arrays.asList(COLOR)), // Оба цвета
                new OrderModel(FIRST_NAME, LAST_NAME, ADDRESS, METRO_STATION, PHONE, RENT_TIME, DELIVERY_DATE, COMMENT, null) // Без указания цвета
        );
    }

}