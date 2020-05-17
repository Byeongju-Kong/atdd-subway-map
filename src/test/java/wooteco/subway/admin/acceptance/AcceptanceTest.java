package wooteco.subway.admin.acceptance;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import java.time.LocalTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import wooteco.subway.admin.dto.LineRequest;
import wooteco.subway.admin.dto.StationCreateRequest;
import wooteco.subway.admin.dto.StationResponse;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql("/truncate.sql")
public class AcceptanceTest {
    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    public static RequestSpecification given() {
        return RestAssured.given().log().all();
    }

    public void createLine(String name) {
        LineRequest lineRequest = new LineRequest(name, "bg-red-300",
                LocalTime.of(5, 30), LocalTime.of(23, 30), 10);

        given().
                body(lineRequest).
                contentType(MediaType.APPLICATION_JSON_VALUE).
                accept(MediaType.APPLICATION_JSON_VALUE).
        when().
                post("/lines").
        then().
                log().all().
                statusCode(HttpStatus.CREATED.value());
    }

    public void createStation(String name) {
        StationCreateRequest stationCreateRequest = new StationCreateRequest(name);

        given().
                body(stationCreateRequest).
                contentType(MediaType.APPLICATION_JSON_VALUE).
                accept(MediaType.APPLICATION_JSON_VALUE).
        when().
                post("/stations").
        then().
                log().all().
                statusCode(HttpStatus.CREATED.value());
    }

    public List<StationResponse> getStations() {
        return given().
                when().
                    get("/stations").
                then().
                    log().all().
                    extract().
                    jsonPath().getList(".", StationResponse.class);
    }
}