package com.suarezr.products_msvc.common.nats;

public record NatsResponseDto(boolean success, int statusCode, String errorMessage, String payload) {
}
