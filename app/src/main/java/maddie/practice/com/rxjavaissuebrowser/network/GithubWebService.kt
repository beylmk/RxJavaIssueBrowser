package maddie.practice.com.rxjavaissuebrowser.network

import maddie.practice.com.rxjavaissuebrowser.model.comment.Comment
import maddie.practice.com.rxjavaissuebrowser.model.issue.Issue
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface GithubWebService {
    companion object {
        const val HEADER = "Accept: application/vnd.github.v3+json"
    }

    @Headers(HEADER)
    @GET("repos/{owner}/{repo}/issues")
    fun getRepoIssues(@Path("owner") owner: String = "ReactiveX", @Path("repo") repo: String = "RxJava"): Call<List<Issue>>

    @Headers(HEADER)
    @GET("repos/{owner}/{repo}/issues/{number}/comments")
    fun getIssueComments(@Path("owner") owner: String = "ReactiveX", @Path("repo") repo: String = "RxJava",
                         @Path("number") issueNumber: Int): Call<List<Comment>>
}
