
package tests;

import io.qameta.allure.Description;
import date.CourierTest;
import io.qameta.allure.Epic;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.CourierModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import steps.CourierSteps;
import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import java.util.Collection;
import static org.hamcrest.CoreMatchers.equalTo;

@Epic("Параметризованные тесты: Авторизация с невалидными данными")
@RunWith(Parameterized.class)
public class ParameterizedLoginInvalidDataTest {

    private final CourierModel invalidCourier; // Модель курьера с невалидными данными
    private CourierSteps courierSteps;

    public ParameterizedLoginInvalidDataTest(CourierModel invalidCourier) {
        this.invalidCourier = invalidCourier;
    }

    @Parameterized.Parameters(name = "Test {index}: {0}")
    public static Collection<CourierModel> testData() {
        return CourierTest.getInvalidCourierBodies(); // Получение тестовых данных
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = CourierTest.BASE_URL; // Установка базового URL
        courierSteps = new CourierSteps(); // Инициализация шагов для работы с курьерами
        CourierModel courier = CourierTest.getValidCourier(); // Получение валидного курьера
        courierSteps.createCourier(courier); // Создание курьера перед тестами
    }

    @Test
    @Description("Проверка авторизации курьера с невалидными данными.")
    public void validateCourierFields() {
        Response response = courierSteps.loginCourier(invalidCourier); // Авторизация курьера
        response.then()
                .log().all()
                .statusCode(SC_BAD_REQUEST)
                .body("message", equalTo("Недостаточно данных для входа")); // Проверка ошибки

    }

    @After
    public void tearDown() {
        // Удаление курьера после теста
        CourierModel loginCourier = CourierTest.getValidLoginBody();
        Response loginResponse = CourierSteps.loginCourier(loginCourier);
        Integer courierId = courierSteps.getCourierId(loginResponse);
        courierSteps.deleteCourier(courierId);
    }
}