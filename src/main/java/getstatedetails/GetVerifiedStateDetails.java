package getstatedetails;

/**
 * Created by Sharif on 12/21/2017.
 */
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetVerifiedStateDetails{

    @SerializedName("getStateDetails")
    @Expose
    private List<GetStateDetail> getStateDetails = null;

    public List<GetStateDetail> getGetStateDetails() {
        return getStateDetails;
    }

    public void setGetStateDetails(List<GetStateDetail> getStateDetails) {
        this.getStateDetails = getStateDetails;
    }

}