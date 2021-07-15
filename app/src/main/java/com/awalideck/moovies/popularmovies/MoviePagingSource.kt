package com.awalideck.moovies.popularmovies

//class MoviePagingSource(
//    private val backend: MovieApiService
//) : PagingSource<Int, Movie>() {
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
//        return try {
//            val nextPageNumber = params.key ?: 1
//            val response = backend.getPopularMovies(nextPageNumber)
//            LoadResult.Page(
//                data = response.results,
//                prevKey = null,
//                nextKey = response.page + 1
//            )
//        } catch (e: IOException) {
//            LoadResult.Error(e)
//        } catch (e: HttpException) {
//            LoadResult.Error(e)
//        }
//    }
//
//    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
//        return state.anchorPosition?.let {anchorPosition->
//            val anchorPage = state.closestPageToPosition(anchorPosition)
//            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
//        }
//    }
//}