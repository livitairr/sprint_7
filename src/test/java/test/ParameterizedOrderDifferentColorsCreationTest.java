package tests;

import io.qameta.allure.Description;
import date.OrderTest;
import io.qameta.allure.Epic;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.OrderModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import steps.OrderSteps;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.hamcrest.CoreMatchers.instanceOf;
import java.util.Collection;

@Epic("Параметризованные тесты: Создание заказов с разными цветами")
@RunWith(Parameterized.class)
public class ParameterizedOrderDifferentColorsCreationTest {

    private final OrderModel order; // Модель заказа
    private OrderSteps orderSteps;
    private Integer orderId;

    public ParameterizedOrderDifferentColorsCreationTest(OrderModel order) {
        this.order = order;
    }

    @Parameterized.Parameters(name = "Test {index}: {0}")
    public static Collection<OrderModel> testData() {
        return OrderTest.getOrdersWithOptionalColors(); // Получение тестовых данных
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = OrderTest.BASE_URL; // Установка базового URL
        orderSteps = new OrderSteps(); // Инициализация шагов для работы с заказами
    }

    @Test
    @Description("Проверка создания заказов с разными цветами.")
    public void shouldCreateOrderWithDifferentColors() {
        Response response = orderSteps.createOrder(order); // Создание заказа
        response.then()
                .log().all()
                .statusCode(SC_CREATED)
                .body("track", instanceOf(Integer.class)); // Проверка успешного создания

        orderId = orderSteps.getOrderId(response); // Получение ID заказа
    }

    @After
    public void tearDown() {
        orderSteps.cancelOrder(orderId); // Отмена заказа после теста
    }
}
