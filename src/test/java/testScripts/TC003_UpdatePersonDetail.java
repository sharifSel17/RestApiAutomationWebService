package testScripts;

import com.google.gson.Gson;
import com.jayway.restassured.response.Response;
import getupdatepersondetail.GetUpdatePersonInfo;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import testbase.TestBase;
import updatepersondetails.PersonInfoUpdate;
import webservicemethods.Services;

/**
 * Created by Sharif on 12/21/2017.
 */
public class TC003_UpdatePersonDetail extends TestBase{

    String pName;
    String pSurname;
    String pCity;
    String pState;
    String pZipCode;
    String pLandMark;

    @BeforeClass
    public void dataSet(){
        pName="David";
        pSurname="Cameron";
        pCity="Brooklyn";
        pState="NY";
        pZipCode="11290";
        pLandMark="USA";
    }

    @Test
    public void updatePerson(){
        services = new Services();
        response = services.updatePersonInfo(pName,pSurname,pCity,pState,pLandMark,pZipCode);
        System.out.println(response.asString());

        if (response.getStatusCode()==200) {
            Gson gson = new Gson();
            GetUpdatePersonInfo getUpdatePersonInfo = gson.fromJson(response.asString(), GetUpdatePersonInfo.class);

            System.out.println(getUpdatePersonInfo.getResponse().get(0).getAddress().getCity());
            System.out.println(getUpdatePersonInfo.getResponse().get(0).getAddress().getState());
            System.out.println(getUpdatePersonInfo.getResponse().get(0).getAddress().getLandmark());
            System.out.println(getUpdatePersonInfo.getResponse().get(0).getAddress().getZipcode());
            System.out.println(getUpdatePersonInfo.getResponse().get(0).getName());
            System.out.println(getUpdatePersonInfo.getResponse().get(0).getSurname());


            Assert.assertEquals(getUpdatePersonInfo.getResponse().get(0).getAddress().getCity(),"Brooklyn");

        }else {
            Assert.assertEquals(false,response.toString());
        }
    }
}
