package courier;
import io.restassured.response.ValidatableResponse;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

public class CourierStatusCode {

    public void createCourier(ValidatableResponse response) {
        response.assertThat()
                .statusCode(201)
                .body("ok", is(true));
    }
    public void createCourierFailed(ValidatableResponse response) {
        response.assertThat()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для входа")); // в тесте будет ошибка тк данные в тз и фактические отличаются

    }
    public void createCourierEqualData(ValidatableResponse response) {
        response.assertThat()
                .statusCode(409)
                .body("message" , equalTo("Этот логин уже используется")); // в тесте будет ошибка тк данные в тз и фактические отличаются
    }
    public static void LoginCourierSuccess(ValidatableResponse response) {
        response.assertThat()
                .statusCode(200)
                .body("id", greaterThan(0));
    }

}
