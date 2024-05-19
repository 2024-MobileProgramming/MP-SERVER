package com.MobileProgramming.dto.request;

public record GetDailyProofsRequest(
        int userId,
        int year,
        int month
) {
}