package date;

import models.CourierModel;

import java.util.Arrays;
import java.util.List;

public class CourierTestData {

    // Основной URL API
    public static final String BASE_URL = "https://qa-scooter.praktikum-services.ru";

    // Основные данные для курьера
    public static final String LOGIN = "kurosaki@ymail.com";
    public static final String PASSWORD = "1234";
    public static final String FIRST_NAME = "Ichigo";

    // Метод, который возвращает объект курьера с валидными данными
    public static CourierModel getValidCourier() {
        return new CourierModel(LOGIN, PASSWORD, FIRST_NAME); // Возвращает курьера с валидными данными
    }

    // Метод, который возвращает список курьеров с невалидными (пустыми) значениями полей
    public static List<CourierModel> getInvalidCourierBodies() {
        return Arrays.asList(
                new CourierModel("", PASSWORD, FIRST_NAME), // Пустой логин
                new CourierModel(LOGIN, "", FIRST_NAME), // Пустой пароль
                new CourierModel("", "", "") // Все поля пустые
        );
    }

    // Метод, который возвращает объект курьера с валидными данными для авторизации
    public static CourierModel getValidLoginBody() {
        return new CourierModel(LOGIN, PASSWORD, null); // Имя не требуется для авторизации
    }

    // Метод, который возвращает список курьеров с отсутствующими обязательными полями
    public static List<CourierModel> getMissingRequiredFields() {
        return Arrays.asList(
                new CourierModel(null, PASSWORD, FIRST_NAME), // Логин отсутствует
                new CourierModel(LOGIN, null, FIRST_NAME), // Пароль отсутствует
                new CourierModel(null, null, null) // Все обязательные поля отсутствуют
        );
    }

    // Метод, который возвращает список курьеров с повторяющимся логином, но разными паролями и именами
    public static List<CourierModel> getDuplicateLoginCouriers() {
        return Arrays.asList(
                new CourierModel(LOGIN, "12345", "Test1"), // Логин одинаковый, пароль и имя разные
                new CourierModel(LOGIN, "123456", "Test2"), // Логин одинаковый, пароль и имя разные
                new CourierModel(LOGIN, PASSWORD, "Test3") // Логин и пароль одинаковые, имя другое
        );
    }

    // Метод, который возвращает объект курьера с невалидным логином
    public static CourierModel getCourierWithInvalidLogin() {
        return new CourierModel("InvalidLogin", PASSWORD, FIRST_NAME); // Неправильный логин
    }

    // Метод, который возвращает объект курьера с невалидным паролем
    public static CourierModel getCourierWithInvalidPassword() {
        return new CourierModel(LOGIN, "InvalidPassword", FIRST_NAME); // Неправильный пароль
    }


}