package test;

import date.CourierTestDate;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.CourierModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import steps.CourierSteps;
import static org.apache.http.HttpStatus.SC_CONFLICT;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.hamcrest.CoreMatchers.equalTo;

@Epic("Управление курьерами")
public class CreationCourierTest {

    private CourierSteps courierSteps;

    @Before
    public void setUp() {
        RestAssured.baseURI = CourierTestDate.BASE_URL; // Установка базового URL
        courierSteps = new CourierSteps(); // Инициализация шагов для работы с курьерами
    }

    @Test
    @DisplayName("Курьер может быть создан без поля firstName")
    @Description("Проверка возможности создания курьера без необязательного поля firstName.")
    public void courierCanBeCreatedWithoutFirstNameTest() {
        // Создаем курьера без поля firstName
        CourierModel courierWithoutFirstName = new CourierModel(
                CourierTestDate.LOGIN,
                CourierTestDate.PASSWORD,
                null // Отсутствует firstName
        );

        // Отправляем запрос на создание курьера
        courierSteps.createCourier(courierWithoutFirstName)
                .then()
                .statusCode(SC_CREATED) // Ожидаем код 201 для успешного создания
                .body("ok", equalTo(true)); // Проверяем, что ответ содержит "ok": true
    }

    @Test
    @DisplayName("Курьер может быть создан")
    @Description("Проверка возможности создания курьера с валидными данными.")
    public void courierCanBeCreatedTest() {
        CourierModel courier = CourierTestDate.getValidCourier();
        courierSteps.createCourier(courier)
                .then()
                .statusCode(SC_CREATED)
                .body("ok", equalTo(true));
    }


    // Удаление курьера после теста
    @After
    public void tearDown() {
        CourierModel loginCourier = CourierTestDate.getValidLoginBody();
        Response loginResponse = CourierSteps.loginCourier(loginCourier);
        Integer courierId = courierSteps.getCourierId(loginResponse);
        courierSteps.deleteCourier(courierId);

    }


}