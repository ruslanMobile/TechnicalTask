package com.example.technical_task2.models

import androidx.annotation.NonNull
import androidx.annotation.Nullable

class ModelWrapResult<T>(@NonNull var status: Status,@Nullable var data: T?,@Nullable var message: String?) {
    companion object {
        fun <T> success(@NonNull data: T): ModelWrapResult<T> {
            return ModelWrapResult(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String?, @Nullable data: T?): ModelWrapResult<T> {
            return ModelWrapResult(Status.ERROR, data, msg)
        }
    }

    enum class Status {
        SUCCESS, ERROR
    }
}