package com.demo.data.mapper

fun interface ListMapper<in I, out O> {
    fun map(input: List<I>): List<O>
}