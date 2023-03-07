package com.elkin.challengeappgate.utils;

import org.json.JSONObject;

public interface UiEvent {
    void OnError(String message);
    void OnSuccess(JSONObject data);
    void OnLoading(Boolean isLoading);
}
