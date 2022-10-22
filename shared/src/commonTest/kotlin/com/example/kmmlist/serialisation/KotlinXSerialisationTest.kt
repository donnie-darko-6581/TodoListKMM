package com.example.kmmlist.serialisation

import com.example.api.models.PhotosResponseItem
import kotlin.test.Test
import kotlinx.serialization.*
import kotlinx.serialization.json.*

class KotlinXSerialisationTest {

    @Test
    fun `validate data class fields`() {
        val data = PhotosResponseItem(
            author = "author",
            download_url = "download_url",
            height = 3,
            id = "id",
            url = "url",
            width = 4,
        )

        val string = Json.encodeToString(data)
        println(string)
        // Deserializing back into objects
        val obj = Json.decodeFromString<PhotosResponseItem>(string)
        println(obj)
    }

    @Test
    fun `validate nulls and non nulls parsing correctly`() {
        val data = TestClass.sampleData()

        val string = Json.encodeToString(data)
        println(string)
        // Deserializing back into objects
        val obj = Json.decodeFromString<TestClass>(string)
        println(obj)

        val dataNext = TestClass.sampleDataNext()

        val stringTwo = Json.encodeToString(dataNext)
        println(string)
        // Deserializing back into objects
        val objTwo = Json.decodeFromString<TestClass>(stringTwo)
        println(objTwo)
    }
}

@Serializable
data class TestClass(
    val nullable: String?,
    val nonNullable: String,
    val inner: TestInnerClass
) {
    companion object {

        fun sampleData() = TestClass(
            nullable = null,
            nonNullable = "nonNullable",
            inner = TestInnerClass(
                innerNullable = null
            )
        )

        fun sampleDataNext() = TestClass(
            nullable = "nullable",
            nonNullable = "nonNullable",
            inner = TestInnerClass(
                innerNullable = null
            )
        )

    }

    @Serializable
    data class TestInnerClass(
        val innerNullable: String? = "sampleData"
    )
}