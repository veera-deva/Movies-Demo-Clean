package com.demo.data.repository.mapper

import com.demo.data.mapper.movies.DataMovieResponseToDomainMovieEntityMapper
import com.demo.data.mapper.movies.toDomain
import com.demo.data.model.movies.MovieResponse
import com.demo.data.repository.utils.MockedTestData
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DataToDomainMapperTest {
    private fun buildMovieResponse(): MovieResponse =
        MovieResponse(
            id = 2,
            title = "Mock title 2",
            description = "Mock Description 2",
            image = "Mock url 2",
            category = "Mock category 2"
        )

    private fun mockedMovieEntity() = MockedTestData.mockedMovieEntity()

    @Test
    fun `test DataMovieResponseToDomainMovieEntityMapper`() {
        val mapper = Mockito.mock(DataMovieResponseToDomainMovieEntityMapper::class.java)
        Mockito.`when`(mapper.map(MockedTestData.mockedMovieResponse()))
            .thenReturn(mockedMovieEntity())
        val mappingResult = mapper.map(MockedTestData.mockedMovieResponse())
        Assert.assertEquals(MockedTestData.mockedMovieEntity(), mappingResult)
    }

    @Test
    fun `map MovieResponse to MovieEntity`() {
        val mockedData = buildMovieResponse()
        val entity = mockedData.toDomain()
        Assert.assertEquals(2, entity.id)
        Assert.assertEquals("Mock title 2", entity.title)
        Assert.assertEquals("Mock Description 2", entity.description)
        Assert.assertEquals("Mock url 2", entity.image)
        Assert.assertEquals("Mock category 2", entity.category)
    }
}