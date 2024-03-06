package com.kt.edu.thirdproject.jwt;

import java.util.Set;

record ProfileResponse(String username, Set<String> roles) {
}
