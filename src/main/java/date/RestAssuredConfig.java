package date;

import io.restassured.RestAssured;

public class RestAssuredConfig {

    // Настройка базового URL для RestAssured
    public static void setBaseURL() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/api/v1";
    }
}
