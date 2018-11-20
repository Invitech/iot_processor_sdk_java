package hu.invitech.insight.processor.example.api;

import hu.invitech.insight.processor.sdk.v1.data.IncomingData;
import hu.invitech.insight.processor.sdk.v1.server.Status;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.POST;

public interface PlatformApi {
    @POST("/v1")
    Call<Void> start(@Body Status status);

    @POST("/data/v1")
    Call<Void> sendData(@Body IncomingData data);

    @DELETE("/v1")
    Call<Void> stop();
}
