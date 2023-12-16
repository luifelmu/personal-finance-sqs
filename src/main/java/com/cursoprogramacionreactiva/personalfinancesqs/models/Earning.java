package com.cursoprogramacionreactiva.personalfinancesqs.models;

public record Earning(
    int id,
    String name,
    Float amount,
    String date,
    int account,
    int category) {
}