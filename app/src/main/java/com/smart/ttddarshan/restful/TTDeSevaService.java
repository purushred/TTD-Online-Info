package com.smart.ttddarshan.restful;

/**
 * Created by purushoy on 4/23/2015.
 */

import com.smart.ttddarshan.vo.CalendarVO;
import com.smart.ttddarshan.vo.DateVO;
import com.smart.ttddarshan.vo.PostDataVO;

import java.util.List;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;

public interface TTDeSevaService {

    @GET("/apsrtc/dates")
    public DateVO getInitialData();

    @POST("/apsrtc/calendar")
    public void postData(@Body PostDataVO postVO, retrofit.Callback<CalendarVO> callback);
}
