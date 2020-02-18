package ru.naumow.tree;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum NodeType {
    @JsonProperty("country") COUNTRY,
    @JsonProperty("region") REGION,
    @JsonProperty("district") DISTRICT,
    @JsonProperty("city") CITY,
    @JsonProperty("street") STREET,
    @JsonProperty("house") HOUSE
}
