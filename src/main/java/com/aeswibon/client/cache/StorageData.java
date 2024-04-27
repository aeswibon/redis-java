package com.aeswibon.client.cache;

import java.time.Instant;

public record StorageData(String value, Instant expirationTime) {
}
