package com.example.technical_task2.models

import java.io.Serializable

data class ModelResult(
    val status:String,
    val data: ModelDataResult
) : Serializable
