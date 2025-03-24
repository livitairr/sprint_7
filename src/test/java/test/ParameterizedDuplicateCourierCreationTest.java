
package tests;

import static org.apache.http.HttpStatus.SC_CONFLICT;
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
import io.qameta.allure.Description;
import java.util.Collection;
import static org.hamcrest.CoreMatchers.equalTo;

@Epic("Параметризованные тесты: Дублирующий логин")
@RunWith(Parameterized.class)
public class ParameterizedDuplicateCourierCreationTest {

    private final CourierModel duplicateCourier; // Модель курьера с дублирующим логином
    private CourierSteps courierSteps;

    public ParameterizedDuplicateCourierCreationTest(CourierModel duplicateCourier) {
        this.duplicateCourier = duplicateCourier;
    }

    @Parameterized.Parameters(name = "Test {index}: {0}")
    public static Collection<CourierModel> testData() {
        return CourierTest.getDuplicateLoginCouriers(); // Получение тестовых данных
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = CourierTest.BASE_URL; // Установка базового URL
        courierSteps = new CourierSteps(); // Инициализация шагов для работы с курьерами
        CourierModel courier = CourierTest.getValidCourier(); // Получение валидного курьера
        courierSteps.createCourier(courier); // Создание курьера перед тестами
    }

    @Test
    @Description("Проверка невозможности создания курьера с дублирующим логином.")
    public void validateDuplicateLogin() {
        courierSteps.createCourier(duplicateCourier)
                .then()
                .log().all()
                .statusCode(SC_CONFLICT)
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой.")); // Проверка ошибки
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
