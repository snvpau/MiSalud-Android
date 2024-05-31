package com.healthtech.misalud.core.network.data.services

import com.google.gson.Gson
import com.healthtech.misalud.core.network.data.clients.PeopleClient
import com.healthtech.misalud.core.network.data.requests.PeopleRequests
import com.healthtech.misalud.core.network.data.responses.PeopleResponses
import com.healthtech.misalud.core.network.retrofit.RetrofitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

class PeopleService {
    private val retrofit = RetrofitHelper.getRetrofitPeople()

    suspend fun doGetUser(phoneNumber: String, accessToken: String) : PeopleResponses.GetUserData {
        return withContext(Dispatchers.IO){
            val response = retrofit.create(PeopleClient::class.java).doGetUser(accessToken = accessToken, phoneNumber = phoneNumber)

            if(!response.isSuccessful) {
                val jsonObject = JSONObject(response.errorBody()!!.charStream().readText()).toString()
                return@withContext Gson().fromJson<PeopleResponses.GetUserData?>(jsonObject, PeopleResponses.GetUserData::class.java)
            }

            return@withContext response.body()!!
        }
    }

    suspend fun doGetRecordDays(accessToken: String, uuid: String) : PeopleResponses.GetRecordDays {
        return withContext(Dispatchers.IO){
            val response = retrofit.create(PeopleClient::class.java).doGetRecordDays(accessToken, uuid)

            if(!response.isSuccessful) {
                val jsonObject = JSONObject(response.errorBody()!!.charStream().readText()).toString()
                return@withContext Gson().fromJson<PeopleResponses.GetRecordDays?>(jsonObject, PeopleResponses.GetRecordDays::class.java)
            }

            return@withContext response.body()!!
        }
    }

    suspend fun doGetMealRecords(accessToken: String, uuid: String, range: String) : PeopleResponses.GetMealRecords {
        return withContext(Dispatchers.IO){
            val response = retrofit.create(PeopleClient::class.java).doGetMealRecords(accessToken, uuid, range)

            if(!response.isSuccessful) {
                val jsonObject = JSONObject(response.errorBody()!!.charStream().readText()).toString()
                return@withContext Gson().fromJson<PeopleResponses.GetMealRecords?>(jsonObject, PeopleResponses.GetMealRecords::class.java)
            }

            return@withContext response.body()!!
        }
    }
    suspend fun doGetExerciseRecords(accessToken: String, uuid: String, range: String) : PeopleResponses.GetExerciseRecords {
        return withContext(Dispatchers.IO){
            val response = retrofit.create(PeopleClient::class.java).doGetExerciseRecords(accessToken, uuid, range)

            if(!response.isSuccessful) {
                val jsonObject = JSONObject(response.errorBody()!!.charStream().readText()).toString()
                return@withContext Gson().fromJson<PeopleResponses.GetExerciseRecords?>(jsonObject, PeopleResponses.GetExerciseRecords::class.java)
            }

            return@withContext response.body()!!
        }
    }
    suspend fun doAddMealRecord(accessToken: String, uuid: String, name: String, type: String, score: Float) : PeopleResponses.PostMealRecord {
        val mealRequest = PeopleRequests.PostMealRecord(uuid, name, type, score)

        return withContext(Dispatchers.IO){
            val response = retrofit.create(PeopleClient::class.java).doAddMealRecord(accessToken, mealRequest)

            if(!response.isSuccessful) {
                val jsonObject = JSONObject(response.errorBody()!!.charStream().readText()).toString()
                return@withContext Gson().fromJson<PeopleResponses.PostMealRecord?>(jsonObject, PeopleResponses.PostMealRecord::class.java)
            }

            return@withContext response.body()!!
        }
    }
    suspend fun doAddExerciseRecord(accessToken: String, uuid: String, name: String, duration: Int, score: Float) : PeopleResponses.PostExerciseRecord {
        val exerciseRequest = PeopleRequests.PostExerciseRecord(uuid, name, duration, score)

        return withContext(Dispatchers.IO){
            val response = retrofit.create(PeopleClient::class.java).doAddExerciseRecord(accessToken, exerciseRequest)

            if(!response.isSuccessful) {
                val jsonObject = JSONObject(response.errorBody()!!.charStream().readText()).toString()
                return@withContext Gson().fromJson<PeopleResponses.PostExerciseRecord?>(jsonObject, PeopleResponses.PostExerciseRecord::class.java)
            }

            return@withContext response.body()!!
        }
    }
}