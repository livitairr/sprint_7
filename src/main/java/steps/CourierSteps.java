package steps;

import io.qameta.allure.Step;
import models.CourierModel;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CourierSteps {

    private static final String COURIER_ENDPOINT = "/api/v1/courier"; // Эндпоинт для работы с курьерами

    // Метод для создания курьера с использованием POST-запроса
    @Step("Создание курьера с параметрами: {courier}")
    public Response createCourier(CourierModel courier) {
        return given()
                .header("Content-type", "application/json")
                .body(courier)
                .when()
                .post(COURIER_ENDPOINT);
    }

    // Метод для авторизации курьера с использованием POST-запроса
    @Step("Авторизация курьера")
    public static Response loginCourier(CourierModel courier) {
        return given()
                .header("Content-type", "application/json")
                .body(courier)
                .when()
                .post(COURIER_ENDPOINT + "/login");
    }

    // Извлечение ID курьера из ответа
    @Step("Получение ID курьера из ответа на авторизацию")
    public Integer getCourierId(Response loginResponse) {
        return loginResponse
                .then()
                .statusCode(200)
                .extract()
                .path("id");
    }

    // Метод для удаления курьера с использованием DELETE-запроса
    @Step("Удаление курьера с ID: {courierId}")
    public void deleteCourier(Integer courierId) {
        given()
                .header("Content-type", "application/json")
                .when()
                .delete(COURIER_ENDPOINT + "/" + courierId)
                .then()
                .statusCode(200)
                .body("ok", equalTo(true));
    }
}