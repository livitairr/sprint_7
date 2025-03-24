
package steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.OrderModel;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class OrderSteps {

    private static final String ORDERS_ENDPOINT = "/orders"; // Константа, содержащая эндпоинт для работы с заказами

    // Метод для создания заказа с использованием POST-запроса
    @Step("Создание заказа с заданным объектом OrderModel")
    public Response createOrder(OrderModel order) {
        return given()
                .header("Content-type", "application/json")
                .body(order)
                .when()
                .post(ORDERS_ENDPOINT);
    }

    // Метод для получения списка всех заказов с использованием GET-запроса
    @Step("Получение списка всех заказов")
    public Response getOrdersList() {
        return given()
                .header("Content-type", "application/json")
                .when()
                .get(ORDERS_ENDPOINT);
    }

    // Метод для извлечения ID заказа из ответа
    @Step("Получение ID заказа из ответа")
    public Integer getOrderId(Response response) {
        return response
                .then()
                .statusCode(201)
                .extract()
                .path("track"); // Извлечение ID заказа из ответа
    }

    // Метод для отмены заказа по его ID
    @Step("Отмена заказа с номером: {track}")
    public void cancelOrder(Integer track) {
        if (track == null) {
            throw new IllegalArgumentException("Track ID не может быть null"); // Проверка на null
        }
        given()
                .header("Content-type", "application/json")
                .queryParam("track", track)
                .when()
                .put(ORDERS_ENDPOINT + "/cancel")
                .then().statusCode(200)
                .body("ok", equalTo(true)); // Отправка PUT-запроса для отмены заказа
    }


}