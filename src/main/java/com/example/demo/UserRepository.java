package com.example.demo;

public interface UserRepository {
    User findUser(int platformType, String siteUserId);
    User findUser(long asn);
}