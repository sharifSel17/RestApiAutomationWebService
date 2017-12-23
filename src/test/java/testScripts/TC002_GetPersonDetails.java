package testScripts;

import com.google.gson.Gson;
import getpostresponse.GetPersonResponseDetails;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import testbase.TestBase;
import webservicemethods.Services;

/**
 * Created by Sharif on 12/21/2017.
 */
public class TC002_GetPersonDetails extends TestBase {


    String pName;
    String pSurname;
    String pCity;
    String pState;
    String pZipCode;
    String pLandMark;

    @BeforeClass
    public void dataSetUp(){
        pName = "Jack";
        pSurname = "Adam";
        pCity = "WashingTon";
        pState = "DC";
        pZipCode = "11290";
        pLandMark = "USA";


    }

    @Test
    public void getDetails(){
        services = new Services();
        response = services.createPersonDetails(pName,pSurname,pCity,pState,pZipCode,pLandMark);
        System.out.println(response.asString());

        if (response.getStatusCode()==200){

            Gson gson = new Gson();
            //we created a reference to get response from the server
            GetPersonResponseDetails getPersonResponseDetails = gson.fromJson(response.asString(), GetPersonResponseDetails.class);
            System.out.println(getPersonResponseDetails.getResponse().get(0).getAddress().getCity());
            System.out.println(getPersonResponseDetails.getResponse().get(0).getAddress().getState());
            System.out.println(getPersonResponseDetails.getResponse().get(0).getAddress().getLandmark());
            System.out.println(getPersonResponseDetails.getResponse().get(0).getAddress().getZipcode());

            System.out.println(getPersonResponseDetails.getResponse().get(0).getName());
            System.out.println(getPersonResponseDetails.getResponse().get(0).getSurname());

            Assert.assertEquals(getPersonResponseDetails.getResponse().get(0).getAddress().getCity(),"WashingTon");
        }else {
            Assert.assertEquals(false,response.asString());
        }
    }
}
