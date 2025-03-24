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
import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;

@Epic("Авторизация курьера")
public class LoginCourierTest {

    private CourierSteps courierSteps;
    private CourierModel courier;

    @Before
    public void setUp() {
        RestAssured.baseURI = CourierTest.BASE_URL; // Установка базового URL
        courierSteps = new CourierSteps(); // Инициализация шагов для работы с курьерами
        courier = CourierTest.getValidCourier(); // Получение валидного курьера
        courierSteps.createCourier(courier); // Создание курьера перед тестами
    }

    @Test
    @DisplayName("Курьер может авторизоваться")
    @Description("Проверка возможности авторизации курьера с валидными данными.")
    public void courierCanLogin() {
        CourierSteps.loginCourier(courier)
                .then()
                .statusCode(SC_OK)
                .body("id", instanceOf(Integer.class)); // Проверка успешной авторизации
    }

    @Test
    @DisplayName("При входе под несуществующим пользователем запрос возвращает ошибку.")
    @Description("Проверка ошибки при авторизации с несуществующими данными.")
    public void courierCanNotLoginWithoutRegistration() {
        CourierModel notRegisteredCourier = CourierTest.getCourierWithInvalidLogin();
        CourierSteps.loginCourier(notRegisteredCourier)
                .then()
                .statusCode(SC_NOT_FOUND)
                .body("message", equalTo("Учетная запись не найдена")); // Проверка ошибки
    }

    @Test
    @DisplayName("При входе с неверным паролем запрос возвращает ошибку")
    @Description("Проверка ошибки при авторизации с неверным паролем.")
    public void courierCanNotLoginWithInvalidPassword() {
        CourierModel courierWithInvalidPassword = CourierTest.getCourierWithInvalidPassword(); // Получение курьера с невалидным паролем
        CourierSteps.loginCourier(courierWithInvalidPassword)
                .then()
                .statusCode(SC_NOT_FOUND)
                .body("message", equalTo("Учетная запись не найдена")); // Проверка ошибки
    }

    // Удаление курьера после выполнения теста
    @After
    public void tearDown() {
        CourierModel loginCourier = CourierTest.getValidLoginBody();
        Response loginResponse = CourierSteps.loginCourier(loginCourier);
        Integer courierId = courierSteps.getCourierId(loginResponse);
        courierSteps.deleteCourier(courierId);
    }
}