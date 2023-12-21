import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import order.Order;
import order.OrderActions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;


    @RunWith(Parameterized.class)
    public class CreateOrderTest {
        private OrderActions orderActions;
        private final String[] color;
        int track;
        public CreateOrderTest(String[] color) {
            this.color = color;
        }

        @Before
        public void setUp() {
            orderActions = new OrderActions();
        }

        @Parameterized.Parameters(name = "Цвет самоката: {0}")
        public static Object[][] getData() {
            return new Object[][]{
                    {new String[]{"BLACK"}},
                    {new String[]{"GRAY"}},
                    {new String[]{"BLACK", "GRAY"}},
                    {new String[]{}}
            };
        }

        @Test
        @DisplayName("Создание заказа с разным цветом")
        @Description("Создание заказа с разными цветами. Проверка кода состояния")
        public void createOrderWithDifferentDataColor() {
            Order order = new Order(color);
            ValidatableResponse responseCreateOrder = orderActions.createNewOrder(order);
            track = responseCreateOrder.extract().path("track");
            responseCreateOrder.assertThat()
                    .statusCode(201)
                    .body("track", is(notNullValue()));
        }

        @After
        @Step("Отмена заказа")
        public void deleteTestOrder() {
            orderActions.cancelOrder(track);
        }

    }

