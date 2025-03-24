package tests;

import date.OrderTest;
import date.OrderTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import steps.OrderSteps;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.empty;

@Epic("Управление заказами")
public class GetOrderListTest {

    private OrderSteps orderSteps;

    @Before
    public void setUp() {
        RestAssured.baseURI = OrderTest.BASE_URL; // Установка базового URL
        orderSteps = new OrderSteps(); // Инициализация шагов для работы с заказами
    }

    @Test
    @DisplayName("Проверка получения списка заказов")
    @Description("Проверка, что ответ содержит корректный список заказов. " +
            "Ожидается, что поле, содержащее заказы, присутствует и содержит данные в правильном формате.")
    public void getOrdersList() {
        orderSteps.getOrdersList()
                .then()
                .statusCode(SC_OK)
                .body("orders", not(empty()))
                .body("orders.id", everyItem(instanceOf(Integer.class)));
    }
}