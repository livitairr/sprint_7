package test;

import date.CourierTest;
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
        RestAssured.baseURI = CourierTest.BASE_URL; // Установка базового URL
        courierSteps = new CourierSteps(); // Инициализация шагов для работы с курьерами
    }

    @Test
    @DisplayName("Курьер может быть создан")
    @Description("Проверка возможности создания курьера с валидными данными.")
    public void courierCanBeCreated() {
        CourierModel courier = CourierTest.getValidCourier();
        courierSteps.createCourier(courier)
                .then()
                .statusCode(SC_CREATED)
                .body("ok", equalTo(true));
    }

    @Test
    @DisplayName("Невозможно создать дубликат курьера")
    @Description("Проверка невозможности создания двух курьеров с одинаковыми данными." +
            "При попытке создать второго курьера возвращается ожидаемый код ошибки.")
    public void courierCanNotBeCreatedTwice() {
        CourierModel courier = CourierTest.getValidCourier();
        courierSteps.createCourier(courier); // Создание первого курьера
        courierSteps.createCourier(courier)  // Попытка создать второго курьера с теми же данными
                .then()
                .statusCode(SC_CONFLICT)
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }

    // Удаление курьера после теста
    @After
    public void tearDown() {
        CourierModel loginCourier = CourierTest.getValidLoginBody();
        Response loginResponse = CourierSteps.loginCourier(loginCourier);
        Integer courierId = courierSteps.getCourierId(loginResponse);
        courierSteps.deleteCourier(courierId);

    }


}