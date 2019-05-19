package getRequestTests;

import commonUtils.Base;
import commonUtils.ReadProperties;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import static org.hamcrest.CoreMatchers.equalTo;


public class getDeliveryPrice extends Base {

    ReadProperties readProperties = new ReadProperties();
    String length,width, height, weight;
    String from, to ,code;
    String expectedPrice;
    String path;
    String authKey;

    @BeforeClass
    public void setReadProperties(){
        //Setting all the property values
        path = readProperties.getPropertyValue("path");
        authKey = readProperties.getPropertyValue("authKey");
        length = readProperties.getPropertyValue("LENGTH");
        width = readProperties.getPropertyValue("WIDTH");
        height = readProperties.getPropertyValue("HEIGHT");
        weight = readProperties.getPropertyValue("WEIGHT");
        code = readProperties.getPropertyValue("CODE");
        from = readProperties.getPropertyValue("FROM");
        to = readProperties.getPropertyValue("TO");
        expectedPrice = readProperties.getPropertyValue("expectedPrice");


    }

    @Test
    public void testResponse()
    {
        //Read the path and replace the stop which we want to run api
        path = path.replace("LENGTH", length).replace("WIDTH", width).replace("HEIGHT",height).replace("WEIGHT",weight).replace("FROM",from).replace("TO",to).replace("CODE",code);
                     RestAssured.given()
                                       .header("User-Agent","Curl")
                                       .header("AUTH-KEY",authKey)
                                       .when()
                                       .get(path)
                                       .then()
                                       .statusCode(200)
                                       .body("postage_result.total_cost",equalTo(expectedPrice));






    }

}