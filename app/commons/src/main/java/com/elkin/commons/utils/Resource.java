package com.elkin.commons.utils;

public interface Resource<T> {
    void OnError(String message);
    void OnSuccess(T data);
    void OnLoading(Boolean isLoading);
}