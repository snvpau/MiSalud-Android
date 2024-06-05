package com.healthtech.misalud.core.network.data.clients

import com.healthtech.misalud.core.network.data.requests.PeopleRequests
import com.healthtech.misalud.core.network.data.responses.PeopleResponses
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface PeopleClient {
    @GET("/api/v1/people/user")
    suspend fun doGetUser(
        @Header("Authorization") accessToken: String,
        @Query("phoneNumber") phoneNumber: String
    ) : Response<PeopleResponses.GetUserData>

    @GET("/api/v1/people/getRecordDays")
    suspend fun doGetRecordDays(
        @Header("Authorization") accessToken: String,
        @Query("uuid") uuid: String
    ) : Response<PeopleResponses.GetRecordDays>

    @GET("/api/v1/people/meals")
    suspend fun doGetMealRecords(
        @Header("Authorization") accessToken: String,
        @Query("uuid") uuid: String,
        @Query("range") range: String
    ) : Response<PeopleResponses.GetMealRecords>

    @GET("/api/v1/people/exercises")
    suspend fun doGetExerciseRecords(
        @Header("Authorization") accessToken: String,
        @Query("uuid") uuid: String,
        @Query("range") range: String
    ) : Response<PeopleResponses.GetExerciseRecords>

    @GET("/api/v1/people/ui/data/app?")
    suspend fun doGetHomeScreenData(
        @Header("Authorization") accessToken: String,
        @Query("type") type: String,
        @Query("uuid") uuid: String
    ) : Response<PeopleResponses.GetHomeScreenData>

    @POST("/api/v1/people/meals")
    suspend fun doAddMealRecord(
        @Header("Authorization") accessToken: String,
        @Body addRecordRequest: PeopleRequests.PostMealRecord
    ) : Response<PeopleResponses.PostMealRecord>

    @POST("/api/v1/people/exercises")
    suspend fun doAddExerciseRecord(
        @Header("Authorization") accessToken: String,
        @Body addRecordRequest: PeopleRequests.PostExerciseRecord
    ) : Response<PeopleResponses.PostExerciseRecord>
}
