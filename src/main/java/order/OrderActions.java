package order;
import static io.restassured.RestAssured.given;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import client.Client;
public class OrderActions extends Client {
    public static final String ORDER_PATH = "/api/v1/orders";
    public static final String CANCEL_ORDER_PATH = "/api/v1/orders/cancel";

    @Step("Создание нового заказа")
    public ValidatableResponse createNewOrder(Order order) {
        return given().log().all()
                .spec(getSpec())
                .body(order)
                .when()
                .post(ORDER_PATH)
                .then();

    }

    @Step("Получение списка заказов")
    public ValidatableResponse getOrderList() {
        return given().log().all()
                .spec(getSpec())
                .when()
                .get(ORDER_PATH)
                .then();
    }

    @Step("Удалить существующий заказ по трек-номеру")
    public ValidatableResponse cancelOrder(int track) {
        return given().log().all()
                .spec(getSpec())
                .body(track)
                .when()
                .put(CANCEL_ORDER_PATH)
                .then();
    }
}
