package com.example.gadsleaderboardmobileapp.retro;

import com.example.gadsleaderboardmobileapp.retro.NetworkSubmit;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIService {
/*
gads form filed
Base url - https://docs.google.com/forms/d/e/

Form ID-1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse
FullURL- https://docs.google.com/forms/d/e/1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse

Entries IDs:

Email Address = entry.1824927963
Name = entry.1877115667
Last Name = entry.2006916086
Link to project = entry.284483984
 */
@POST("1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
@FormUrlEncoded
Call<Void> savePost(
        @Field("entry.1824927963") String email,
        @Field("entry.1877115667") String firstName,
        @Field("entry.2006916086") String lastName,
        /// @Field("entry.839337160") String track,
        @Field("entry.284483984")  String githubLink
/*
personal form fileds for testing
entry.1045781291: gg@g.com  //  email
entry.2005620554: Gbenge  //firstname
entry.808418281: Raphael  //last name
entry.839337160: Add        //track
entry.1065046570: sog  //git link


https://docs.google.com/forms/u/0/d/e/1FAIpQLScmsi0l4q66caxFYYta7tRsSVR5_EZ_dBsvtvFhb6yco7Sqkg/formResponse
Request Method: POST
Status Code: 200
Remote Address: 216.58.223.206:443
Referrer Policy: strict-origin-when-cross-origin




 */
                        );

}
