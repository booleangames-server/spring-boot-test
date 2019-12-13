package com.example.demo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    private long asn;
    private int platformType;
    private String siteUserId;
    private int userDBIndex;
    private int logDBIndex;
    private String countryCode;
    private int marketPlatformType;
    private int vipLevel;
    private LocalDateTime lastLoginTime;
    private BigDecimal lastKey;
    private LocalDateTime lastKeyUpdateTime; 
    private boolean secondaryDownload;
    private boolean isDeleted;
    private LocalDateTime regDate;
    private int npCountry;

    public User(long asn, int platformType, String siteUserId, int userDBIndex, int logDBIndex, String countryCode, int marketPlatformType, int vipLevel,
     LocalDateTime lastLoginTime, BigDecimal lastKey, LocalDateTime lastKeyUpdateTime, boolean secondaryDownload, boolean isDeleted, LocalDateTime regDate,
     int npCountry) {
         this.asn = asn;
         this.platformType = platformType;
         this.siteUserId = siteUserId;
         this.userDBIndex = userDBIndex;
         this.logDBIndex = logDBIndex;
         this.countryCode = countryCode;
         this.marketPlatformType = marketPlatformType;
         this.vipLevel = vipLevel;
         this.lastLoginTime = lastLoginTime;
         this.lastKey = lastKey;
         this.lastKeyUpdateTime = lastKeyUpdateTime;
         this.secondaryDownload = secondaryDownload;
         this.isDeleted = isDeleted;
         this.regDate = regDate;
         this.npCountry = npCountry;
    }

    public long getAsn() {
        return asn;
    }

    public int getPlatformType() {
        return platformType;
    }

    public String getSiteUserId() {
        return siteUserId;
    }

    public int getUserDBIndex() {
        return userDBIndex;
    }

    public int getLogDBIndex() {
        return logDBIndex;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public int getMarketPlatformType() {
        return marketPlatformType;
    }

    public int getVIPLevel() {
        return vipLevel;
    }

    public LocalDateTime getLastLoginTime() {
        return lastLoginTime;
    }

    public BigDecimal getLastKey() {
        return lastKey;
    }

    public LocalDateTime getLastKeyUpdateTime() {
        return lastKeyUpdateTime;
    }

    @JsonProperty("secondaryDownload")
    public boolean hasSecondaryDownload() {
        return secondaryDownload;
    }

    @JsonProperty("isDeleted")
    public boolean isDeleted() {
        return isDeleted;
    }

    public LocalDateTime getRegDate() {
        return regDate;
    }

    public int getNPCountry() {
        return npCountry;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (null == object) {
            return false;
        }
        
        if (this.getClass() != object.getClass()) {
            return false;
        }

        User other = (User) object;

        return asn == other.asn
            && platformType == other.platformType
            && siteUserId.equals(other.siteUserId)
            && userDBIndex == other.userDBIndex
            && logDBIndex == other.logDBIndex
            && countryCode.equals(other.countryCode)
            && marketPlatformType == other.marketPlatformType
            && vipLevel == other.vipLevel
            && lastLoginTime.equals(other.lastLoginTime)
            && lastKey.equals(other.lastKey)
            && lastKeyUpdateTime.equals(other.lastKeyUpdateTime)
            && secondaryDownload == other.secondaryDownload
            && isDeleted == other.isDeleted
            && regDate.equals(other.regDate)
            && npCountry == other.npCountry;
    }
}
