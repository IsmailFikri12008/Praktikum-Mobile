package id.ac.unpas.mobcrafter.networks

import com.skydoves.sandwich.ApiResponse
import id.ac.unpas.mobcrafter.model.Matakuliah
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface MatakuliahApi {
    @GET("api/matakuliah")
    suspend fun all(): ApiResponse<MatakuliahGetResponse>

    @GET("api/matakuliah/{id}")
    suspend fun find(@Path("id") id: String):
            ApiResponse<MatakuliahSingleGetResponse>

    @POST("api/matakuliah")
    @Headers("Content-Type: application/json")
    suspend fun insert(@Body item: Matakuliah):
            ApiResponse<MatakuliahSingleGetResponse>

    @PUT("api/matakuliah/{id}")
    @Headers("Content-Type: application/json")
    suspend fun update(
        @Path("id") pathId: String,
        @Body item: Matakuliah
    ):
            ApiResponse<MatakuliahSingleGetResponse>

    @DELETE("api/matakuliah/{id}")
    suspend fun delete(@Path("id") id: String):
            ApiResponse<MatakuliahSingleGetResponse>
}