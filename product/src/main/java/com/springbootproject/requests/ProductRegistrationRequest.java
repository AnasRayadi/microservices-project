package com.springbootproject.requests;

import java.time.LocalDate;


public record ProductRegistrationRequest(String title, String image, String description, Double price, Integer quantity) {

}
