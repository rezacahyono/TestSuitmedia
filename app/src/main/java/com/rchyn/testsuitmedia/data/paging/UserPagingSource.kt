package com.rchyn.testsuitmedia.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rchyn.testsuitmedia.data.mapper.toUser
import com.rchyn.testsuitmedia.data.retrofit.ReqresApi
import com.rchyn.testsuitmedia.model.User

class UserPagingSource(
    private val reqresApi: ReqresApi
) : PagingSource<Int, User>() {

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        return try {
            val position = params.key ?: INITIAL_PAGE_INDEX
            val response = reqresApi.getAllUser(position).data.map {
                it.toUser()
            }

            LoadResult.Page(
                data = response,
                prevKey = if (position == INITIAL_PAGE_INDEX) null else position - 1,
                nextKey = if (response.isEmpty()) null else position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }
}