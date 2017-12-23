package testScripts;

import com.google.gson.Gson;
import com.jayway.restassured.response.Response;
import getstatedetails.GetStateDetail;
import getstatedetails.GetVerifiedStateDetails;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import testbase.TestBase;
import webservicemethods.Services;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sharif on 12/19/2017.
 */
public class TC001_GetStateDetails extends TestBase {

    //created list because response class will return list of object
    List<Object> list;
    List<Object> testData;
    @BeforeClass
    public void dataSetUp(){
        //for validation return is correct or not
        testData = new ArrayList<Object>();
        testData.add("Bihar");
        testData.add("UP");
        testData.add("karnataka");
        testData.add("Kerala");
        testData.add("tamil nadu");
        testData.add("MP");
    }

    @Test
    public void test001(){
        services =  new Services();
        //return all the state details
        System.out.println(services.getStateDetail().asString());

        response = services.getStateDetail();
        if(response.getStatusCode()==200){
            Gson gson = new Gson();
            GetVerifiedStateDetails getVerifiedStateDetails = gson.fromJson(response.asString(),GetVerifiedStateDetails.class);
            //response class has list that's why we created list object for that response class
            List<GetStateDetail> stateDetails = getVerifiedStateDetails.getGetStateDetails();
            //initialized an object for list
            list = new ArrayList<Object>();
            //loop will give us all the array size and values
            for(int i=0;i<stateDetails.size();i++){
                    list.add(stateDetails.get(i).getName());
                    System.out.println(stateDetails.get(i).getName());

            }
            Assert.assertEquals(list,testData);
        }else {
            Assert.assertEquals(false,response.asString());
        }
    }
}
