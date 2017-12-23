package webservicemethods;

import com.google.gson.Gson;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import endpointsurl.URL;
import getpostresponse.GetPersonResponseDetails;
import org.json.JSONObject;
import org.testng.Assert;
import postrequest.Address;
import postrequest.RequestToCreatePerson;
import updatepersondetails.PersonInfoUpdate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sharif on 12/19/2017.
 */
public class Services {

    //we want to data as array list and convert to JsonObject
    List<JSONObject>  list;

    public Response createPersonDetails(String pName, String pSurname, String pCity, String pState, String pZipCode, String pLandMark){
        RequestToCreatePerson requestToCreatePerson = new RequestToCreatePerson();
        requestToCreatePerson.setName(pName);
        requestToCreatePerson.setSurname(pSurname);

        Address address = new Address();
        requestToCreatePerson.setAddress(address);
        address.setCity(pCity);
        address.setState(pState);
        address.setZipcode(pZipCode);
        address.setLandmark(pLandMark);

        JSONObject jsonObject = new JSONObject(requestToCreatePerson);
        //we created an object for arrayList and that data type would be as JsonObject
        list = new ArrayList<JSONObject>();
        //we added created json object in the array list
        list.add(jsonObject);
        //what data we are sending to the server that will be printed in the console
        System.out.println(list);

        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.contentType("application/json");
        requestSpecification.accept("application/json");

        //we added the list into the body parameter because that list is converted into json object
        requestSpecification.body(list.toString());

        Response response = requestSpecification.post(URL.createPerson);
        //we want to get data from the server that's why data type should be as a Response
        return response;

    }

    public static void main(String[] args) {

        Services services = new Services();

        //to get response from the server whatever data we sent and also what data we want to send to the server
        Response data = services.createPersonDetails("Jack","Adam","WashingTon","DC","11290","USA");
        System.out.println(data.asString());

        if(data.getStatusCode()==200){
            Gson gson = new Gson();
            GetPersonResponseDetails getPersonResponseDetails = gson.fromJson(data.asString(), GetPersonResponseDetails.class);
            System.out.println(getPersonResponseDetails.getResponse().get(0).getAddress().getCity());
            System.out.println(getPersonResponseDetails.getResponse().get(0).getAddress().getState());
            System.out.println(getPersonResponseDetails.getResponse().get(0).getAddress().getLandmark());
            System.out.println(getPersonResponseDetails.getResponse().get(0).getAddress().getZipcode());

            System.out.println(getPersonResponseDetails.getResponse().get(0).getName());
            System.out.println(getPersonResponseDetails.getResponse().get(0).getSurname());

            Assert.assertEquals(getPersonResponseDetails.getResponse().get(0).getAddress().getCity(),"WashingTon");
        }
    }

    public Response getStateDetail(){
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.contentType(ContentType.JSON);
        Response response = requestSpecification.get(URL.getAllDetails);
        return response;
    }

    public Response updatePersonInfo(String uPName, String uPSurname, String uPCity, String uPState, String uPLandmark, String uPZip){
        PersonInfoUpdate personInfoUpdate = new PersonInfoUpdate();
        personInfoUpdate.setName(uPName);
        personInfoUpdate.setSurname(uPSurname);

        updatepersondetails.Address address = new updatepersondetails.Address();
        personInfoUpdate.setAddress(address);
        address.setCity(uPCity);
        address.setState(uPState);
        address.setLandmark(uPLandmark);
        address.setZipcode(uPZip);

        JSONObject jsonObject = new JSONObject(personInfoUpdate);
        list = new ArrayList<JSONObject>();
        list.add(jsonObject);

        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.contentType("application/json");
        requestSpecification.accept("application/json");
        requestSpecification.body(list.toString());
        System.out.println(list.toString());

        Response response = requestSpecification.put(URL.updatePerson);

        return response;

    }
}
