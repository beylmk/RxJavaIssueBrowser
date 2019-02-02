package maddie.practice.com.rxjavaissuebrowser.network

import maddie.practice.com.rxjavaissuebrowser.model.GithubIssue
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


//By default, all requests to https://api.github.com receive the
// v3 version of the REST API. We encourage you to explicitly
// request this version via the Accept header. Accept: application/vnd.github.v3+json
interface GithubWebService {
    @GET("repos/{owner}/{repo}/issues")
    fun getRepoIssues(@Path("owner") owner: String = "ReactiveX", @Path("repo") repo: String = "RxJava"): Call<List<GithubIssue>>
}
