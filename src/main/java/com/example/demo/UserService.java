package com.example.demo;

public interface UserService {
    public User getUser(int platformType, String siteUserId);
    public User getUser(long asn);
}