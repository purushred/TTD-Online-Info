package com.smart.ttddarshan.restful;

import com.smart.ttddarshan.vo.DarshanDetailsVO;
import com.smart.ttddarshan.vo.DarshanSlotVO;
import com.smart.ttddarshan.vo.SevaAvailabilityVO;
import com.smart.ttddarshan.vo.SevaDetailsVO;
import com.smart.ttddarshan.vo.TTDVO;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Query;

public interface TTDService {

    @GET("/")
    public TTDVO getAvailabilityMonths(@Query("index") int index, @Query("eventTarget") String eventTarget,
                                       @Query("eventArg") String eventArg, @Query("viewStateGenerator") String viewStateGenerator,
                                       @Query("cmbDate") String cmbDate, @Query("cmbSeva") String cmbSeva,
                                       @Query("lastFocus") String lastFocus, @Query("eventValidation") String eventValidation,
                                       @Query("viewState") String viewState);

    @GET("/sevaavailability/")
    public SevaAvailabilityVO getSevaAvailability(@Query("sevaId") String sevaId);

    @GET("/sevadetails/")
    public List<SevaDetailsVO> getSevaDetails(@Query("sevaId") String sevaId, @Query("sevaDate") String sevaDate);

    @GET("/darshanavailability/")
    public SevaAvailabilityVO getDarshanAvailability();

    @GET("/darshandetails/")
    public DarshanDetailsVO getDarshanDetails(@Query("darshanDate") String darshanDate);
}
