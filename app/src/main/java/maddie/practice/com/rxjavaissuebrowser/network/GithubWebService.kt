package maddie.practice.com.rxjavaissuebrowser.network

import maddie.practice.com.rxjavaissuebrowser.model.issue.GithubIssue
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface GithubWebService {
    @Headers("Accept: application/vnd.github.v3+json")
    @GET("repos/{owner}/{repo}/issues")
    fun getRepoIssues(@Path("owner") owner: String = "ReactiveX", @Path("repo") repo: String = "RxJava"): Call<List<GithubIssue>>

    @GET("repos/{owner}/{repo}/issues/{id}/comments")
    fun getIssueComments(@Path("owner") owner: String = "ReactiveX", @Path("repo") repo: String = "RxJava",
                         @Path("id") issueId: Long)
}
