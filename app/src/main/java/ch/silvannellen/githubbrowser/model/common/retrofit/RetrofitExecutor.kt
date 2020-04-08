package ch.silvannellen.githubbrowser.model.common.retrofit

import ch.silvannellen.githubbrowser.model.common.Error
import ch.silvannellen.githubbrowser.model.common.Result
import retrofit2.Call
import retrofit2.Response
import timber.log.Timber

object RetrofitExecutor {

    /**
     * Executes the call provided by the given call provider.
     *
     * @param callProvider provide the call to execute
     * @param successResponseConverter convert the result of the call to a successful response
     * @param errorResponseConverter optional: override the default error handling
     */
    fun <Dto, Domain> execute(
        callProvider: (() -> Call<Dto>),
        successResponseConverter: ((Dto) -> Result<Domain>),
        errorResponseConverter: ((Response<Dto>) -> Result<Domain>)? = null
    ): Result<Domain> = try {
        val response = callProvider().execute()
        if (response.isSuccessful) {
            response.body()?.let(successResponseConverter) ?: Result<Domain>(null, Error.GenericError)
        } else {
            errorResponseConverter?.invoke(response)
                ?: getDefaultErrorResponse(response)
        }
    } catch (e: Throwable) {
        Timber.w(e)
        Result(null, Error.GenericError)
    }

    /**
     * Get the default error response. Useful if the client code only wants to handle some errors
     * and return the default response for all other errors.
     */
    fun <Dto, Domain> getDefaultErrorResponse(response: Response<Dto>): Result<Domain> {
        // In a real world application, error handling is probably more sophisticated...
        return if (response.code() == 401) {
            Result(null, Error.Unauthorized)
        } else {
            Result(null, Error.GenericError)
        }
    }
}