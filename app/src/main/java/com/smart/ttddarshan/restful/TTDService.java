package com.smart.ttddarshan.restful;

import com.smart.ttddarshan.vo.AccommodationDetailsVO;
import com.smart.ttddarshan.vo.DarshanDetailsVO;
import com.smart.ttddarshan.vo.SevaAvailabilityVO;
import com.smart.ttddarshan.vo.SevaDetailsVO;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Query;

public interface TTDService {


    @GET("/sevaavailability/")
    public SevaAvailabilityVO getSevaAvailability(@Query("sevaId") String sevaId);

    @GET("/sevadetails/")
    public List<SevaDetailsVO> getSevaDetails(@Query("sevaId") String sevaId, @Query("sevaDate") String sevaDate);

    @GET("/darshanavailability/")
    public SevaAvailabilityVO getDarshanAvailability();

    @GET("/darshandetails/")
    public DarshanDetailsVO getDarshanDetails(@Query("darshanDate") String darshanDate);

    @GET("/accommodationavailability/")
    public SevaAvailabilityVO getAccommodationAvailability(@Query("accomId") String accomId);

    @GET("/accommodationdetails/")
    public List<AccommodationDetailsVO> getAccommodationDetails(@Query("accomDate") String accomDate, @Query("location") String location);
}
