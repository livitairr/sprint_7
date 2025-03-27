
package test;

import date.CourierTestDate;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.CourierModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import steps.CourierSteps;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.hamcrest.CoreMatchers.equalTo;

@Epic("Параметризованные тесты: Создание курьера")
@RunWith(Parameterized.class)
public class ParameterizedCourierCreationTest {

    private final CourierModel courier; // Модель курьера для теста
    private CourierSteps courierSteps;

    public ParameterizedCourierCreationTest(CourierModel courier) {
        this.courier = courier;
    }

    @Parameterized.Parameters(name = "Test {index}: {0}")
    public static Collection<CourierModel> testData() {
        // Генерация тестовых данных
        List<CourierModel> testData = new ArrayList<>();
        testData.addAll(CourierTestDate.getInvalidCourierBodies()); // Невалидные данные
        testData.addAll(CourierTestDate.getMissingRequiredFields()); // Отсутствующие обязательные поля
        return testData;
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = CourierTestDate.BASE_URL; // Установка базового URL
        courierSteps = new CourierSteps(); // Инициализация шагов для работы с курьерами
    }

    @Test
    @Description("Проверка создания курьера с различными комбинациями обязательных полей.")
    public void validateCourierFieldTest() {
        Response response = courierSteps.createCourier(courier); // Создание курьера
        response.then()
                .log().all()
                .statusCode(SC_BAD_REQUEST)
                .body("message", equalTo("Недостаточно данных для создания учетной записи")); // Проверка ошибки

    }
}