package models;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonInclude;

import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL) // Исключает поля со значением null из JSON
public class OrderModel {

    private String firstName;
    private String lastName;
    private String address;
    private Integer metroStation;
    private String phone;
    private Integer rentTime;
    private String deliveryDate;
    private String comment;
    private List<String> color;

    // Конструктор по умолчанию (необходим для сериализации/десериализации)
    public OrderModel() {
    }

    // Конструктор с параметрами
    public OrderModel(String firstName, String lastName, String address, Integer metroStation, String phone, Integer rentTime, String deliveryDate, String comment, List<String> color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }
    // Геттеры для всех полей
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public Integer getMetroStation() {
        return metroStation;
    }

    public String getPhone() {
        return phone;
    }

    public Integer getRentTime() {
        return rentTime;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public String getComment() {
        return comment;
    }

    public List<String> getColor() {
        return color;
    }

    // Сеттеры для всех полей
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setMetroStation(Integer metroStation) {
        this.metroStation = metroStation;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setRentTime(Integer rentTime) {
        this.rentTime = rentTime;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setColor(List<String> color) {
        this.color = color;
    }

    // Переопределение метода toString для удобного вывода информации об объекте (например, в логах)
    @Override
    public String toString() {
        return "OrderModel{" + "firstName='" + firstName + '\'' + ", lastName='" +
                lastName + '\'' + ", address='" + address + '\'' + ", metroStation=" +
                metroStation + ", phone='" + phone + '\'' + ", rentTime=" + rentTime + ", deliveryDate='" +
                deliveryDate + '\'' + ", comment='" + comment + '\'' + ", color=" + color + '}';
    }
}