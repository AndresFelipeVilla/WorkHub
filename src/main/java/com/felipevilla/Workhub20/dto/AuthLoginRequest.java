package com.felipevilla.Workhub20.dto;

import lombok.NonNull;

public record AuthLoginRequest(@NonNull String username, @NonNull String password) {

}
