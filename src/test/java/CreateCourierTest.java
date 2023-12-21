
import courier.*;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CreateCourierTest {
    private CourierGenerator courierGenerator = new CourierGenerator();
    private CourierUser courierUser;
    private Courier courier;
    private CourierStatusCode courierStatusCode;
    int idCourier;

    @Before
    @Step("Предусловия для создания теста")
    public void setUp() {
        courierUser = new CourierUser();
        courier = courierGenerator.getCourierRandom();
        courierStatusCode = new CourierStatusCode();
    }

    @Test
    @DisplayName("Создание нового курьера")
    @Description("Тест на создание нового курьера")
    public void courierCanBeCreatedWith201CodeMessageOk() {
        ValidatableResponse responseCreateCourier = courierUser.createCourier(courier);
        courierStatusCode.createCourier(responseCreateCourier);
        CourierCred credentials = CourierCred.from(courier);
        ValidatableResponse responseLoginCourier = courierUser.loginCourier(credentials);
        idCourier = responseLoginCourier.extract().path("id");
    }

    @Test
    @DisplayName("Создание курьера без логина")
    @Description("Создания нового курьера без логина")
    public void courierCanNotBeCreatedWithoutLoginField() {
        courier.setLogin(null);
        ValidatableResponse responseNullLogin = courierUser.createCourier(courier);
        courierStatusCode.createCourierFailed(responseNullLogin);
    }
    @Test
    @DisplayName("Создание курьера без пароля")
    @Description("Создание курьера без пароля") // в тесте будет ошибка тк данные в тз и фактические отличаются по статус коду
    public void courierCanNotBeCreatedWithoutPasswordField() {
        courier.setPassword(null);
        ValidatableResponse responseNullPassword = courierUser.createCourier(courier);
        courierStatusCode.createCourierFailed(responseNullPassword);
    }

    @Test
    @DisplayName("Создание курьера без логина и пароля")
    @Description("Создание нового курьера без логина и пароля") // в тесте будет ошибка тк данные в тз и фактические отличаются по тексту ответа
    public void courierCanNotBeCreatedWithoutLoginAndPasswordFields() {
        courier.setLogin(null);
        courier.setPassword(null);
        ValidatableResponse responseNullFields = courierUser.createCourier(courier);
        courierStatusCode.createCourierFailed(responseNullFields);
    }

    @Test
    @DisplayName("Создание курьера с существующими данными")
    @Description("Создание курьера с уже существующими данными") // в тесте будет ошибка тк данные в тз и фактические отличаются
    public void courierCanNotBeCreatedWithEqualData() {
        courierUser.createCourier(courier);
        ValidatableResponse responseCreateCourier = courierUser.createCourier(courier);
        courierStatusCode.createCourierEqualData(responseCreateCourier);
    }


    @After
    @Step("Удаление курьера")
    public void deleteCourier() {
        if (idCourier != 0) {
            courierUser.deleteCourier(idCourier);
        }
    }
}