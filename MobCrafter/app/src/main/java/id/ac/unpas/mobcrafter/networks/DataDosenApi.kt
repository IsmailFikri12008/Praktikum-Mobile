package id.ac.unpas.mobcrafter.networks


import com.skydoves.sandwich.ApiResponse
import id.ac.unpas.mobcrafter.model.DataDosen
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface DataDosenApi {
    @GET("api/dosen")
    suspend fun all(): ApiResponse<DataDosenGetResponse>

    @GET("api/dosen/{id}")
    suspend fun find(@Path("id") id: String):
            ApiResponse<DataDosenSingleGetResponse>

    @POST("api/dosen")
    @Headers("Content-Type: application/json")
    suspend fun insert(@Body item: DataDosen):
            ApiResponse<DataDosenSingleGetResponse>

    @PUT("api/dosen/{id}")
    @Headers("Content-Type: application/json")
    suspend fun update(
        @Path("id") pathId: String,
        @Body item: DataDosen
    ):
            ApiResponse<DataDosenSingleGetResponse>

    @DELETE("api/dosen/{id}")
    suspend fun delete(@Path("id") id: String):
            ApiResponse<DataDosenSingleGetResponse>
}