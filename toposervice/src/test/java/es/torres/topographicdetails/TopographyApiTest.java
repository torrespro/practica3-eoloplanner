package es.torres.topographicdetails;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Disabled
public class TopographyApiTest {

    @LocalServerPort
    int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    public void createNewTopographyTest() {

        given().
            request()
            .body("{ \"id\" : \"Madrid\", \"topography\" : \"flat\" }")
            .contentType(ContentType.JSON).
            when().
            post("/api/v1/topographicdetails/").
            then().
            statusCode(201).
            body("id", notNullValue());

    }

    @Test
    public void getById() {

        given().
            request()
            .body("{ \"id\" : \"Madrid\", \"topography\" : \"flat\" }")
            .contentType(ContentType.JSON).
            when().
            post("/api/v1/topographicdetails/").
            then().
            statusCode(201).
            body("id", notNullValue());

        given().
            request().
            when().
            get("/api/v1/topographicdetails/Madrid").
            then().
            statusCode(200).
            body("id", is("Madrid"));
    }

    @Test
    public void getByIdNofFound() {

        given().
            request().
            when().
            get("/api/v1/topographicdetails/Venecia").
            then().
            statusCode(404);
    }

    @Test
    public void deleteTopography() {

        given().
            request()
            .body("{ \"id\" : \"Madrid\", \"topography\" : \"flat\" }")
            .contentType(ContentType.JSON).
            when().
            post("/api/v1/topographicdetails/").
            then().
            statusCode(201).
            body("id", notNullValue());

        given().
            request().
            when().
            delete("/api/v1/topographicdetails/Madrid").
            then().
            statusCode(200).
            body("id", is("Madrid"));

        given().
            request().
            when().
            get("/api/v1/topographicdetails/Madrid").
            then().
            statusCode(404);

    }

}
