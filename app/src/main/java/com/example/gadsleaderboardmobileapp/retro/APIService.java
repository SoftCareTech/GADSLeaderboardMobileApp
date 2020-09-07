package com.example.gadsleaderboardmobileapp.retro;

import com.example.gadsleaderboardmobileapp.retro.NetworkSubmit;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIService {
/*
Base url - https://docs.google.com/forms/d/e/

Form ID-1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse
FullURL- https://docs.google.com/forms/d/e/1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse

Entries IDs:

Email Address = entry.1824927963
Name = entry.1877115667
Last Name = entry.2006916086
Link to project = entry.284483984
 */
    @POST("1FAIpQLScmsi0l4q66caxFYYta7tRsSVR5_EZ_dBsvtvFhb6yco7Sqkg/formResponse")
    @FormUrlEncoded
    Call<NetworkSubmit> savePost(
                        @Field("entry.1045781291") String email,
                           @Field("entry.2005620554") String firstName,
                           @Field("entry.808418281") String lastName,
                       /// @Field("entry.839337160") String track,
                        @Field("entry.1065046570")  String githubLink


                        /* ,@Field("fvv") String fvv,
                        @Field("draftResponse") String draftResponse,
                        @Field("token") String token,
                        @Field("fbzx")  String fbzx */
                        );

}

/*


entry.1045781291: gg@g.com  //  email
entry.2005620554: Gbenge  //firstname
entry.808418281: Raphael  //last name
entry.839337160: Add        //track
entry.1065046570: sog  //git link
fvv: 1


fvv: 1
draftResponse: [null,null,"8764658540020751943"]
pageHistory: 0
token: o6TmWnQBAAA.E_sq1z7WS3kJcOBt7lnBsA.OEh2ixm-6YIz5g-gAD7uLg
fbzx: 8764658540020751943

 */