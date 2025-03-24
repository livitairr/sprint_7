package models;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL) // Исключает поля со значением null из JSON
public class CourierModel {

    private String login;
    private String password;
    private String firstName;

    // Конструктор по умолчанию (необходим для сериализации/десериализации)
    public CourierModel() {
    }

    // Конструктор с параметрами
    public CourierModel(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    // Геттеры для всех полей
    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    // Сеттеры для всех полей
    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    // Переопределение метода toString() для удобного вывода информации об объекте (например, в логах)
    @Override
    public String toString() {
        return "CourierModel{" + "login='" + login + '\'' + ", password='" +
                password + '\'' + ", firstName='" + firstName + '\'' + '}';
    }
}