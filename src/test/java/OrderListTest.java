import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import order.OrderActions;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.notNullValue;

public class OrderListTest {
    @Test
    @DisplayName("Получение списка заказов")
    @Description("Проверка успешного получения списка заказов с проверкой кода состояния")
    public void getOrderListIsOk() {
        OrderActions orderActions = new OrderActions();
        ValidatableResponse responseOrderList = orderActions.getOrderList();
        responseOrderList.assertThat()
                .statusCode(200)
                .body("orders", notNullValue());
    }
}
