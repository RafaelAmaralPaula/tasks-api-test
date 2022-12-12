package br.ce.wc.aquibo.tasks.apitest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.CoreMatchers;
import org.junit.BeforeClass;
import org.junit.Test;

public class APITest {

    @BeforeClass
    public static void setup(){
        RestAssured.baseURI = "http://localhost:8001/tasks-backend";
    }


    @Test
    public void deveRetornarTarefas(){
        RestAssured
                .given()
                .when()
                   .get("/todo")
                .then()
                    .statusCode(200)
                ;
    }

    @Test
    public void deveAdicionarTarefaComSucesso(){
        RestAssured
                .given()
                    .body("{\"task\":\"Example nine\",\"dueDate\":\"2022-12-30\"}")
                    .contentType(ContentType.JSON)
                .when()
                    .post("/todo")
                .then()
                    .statusCode(201)
                ;
    }

    @Test
    public void naoDeveAdicionarTarefaInvalida(){
        RestAssured
                .given()
                    .body("{\"task\":\"Example nine\",\"dueDate\":\"2022-12-01\"}")
                .contentType(ContentType.JSON)
                .when()
                    .post("/todo")
                .then()
                    .statusCode(400)
                    .body("message" , CoreMatchers.is("Due date must not be in past"))
        ;

    }
}

