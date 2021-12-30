package com.example.data_remote.mapper.user

abstract class DataRemoteMapper<in R, out D> {
    abstract fun toDomain(data: R): D
}