package com.demo.data.repository.mapper

import com.demo.data.mapper.movies.DataMovieResponseToDomainMovieEntityMapper
import com.demo.data.mapper.movies.toDomain
import com.demo.data.model.movies.MovieResponse
import com.demo.data.repository.utils.MockedTestData
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

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

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `test DataMovieResponseToDomainMovieEntityMapper`() {
        val mapper = mockk<DataMovieResponseToDomainMovieEntityMapper>()
        every { mapper.map(MockedTestData.mockedMovieResponse()) } returns mockedMovieEntity()
        val mappingResult = mapper.map(MockedTestData.mockedMovieResponse())
        expectThat(MockedTestData.mockedMovieEntity()).isEqualTo(mappingResult)
    }

    @Test
    fun `map MovieResponse to MovieEntity`() {
        val mockedData = buildMovieResponse()
        val entity = mockedData.toDomain()
        expectThat(entity).and { get { entity.id }.isEqualTo(2) }.and {
            get { entity.title }.isEqualTo("Mock title 2")
        }.and { get { entity.description }.isEqualTo("Mock Description 2") }.and {
            get { entity.image }.isEqualTo("Mock url 2")
        }.get {
            entity.category
        }.isEqualTo("Mock category 2")
    }
}