package com.example.demo;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    static final class UserMapper implements RowMapper<User> {

        public User mapRow(ResultSet resultSet, int numRows) throws SQLException {
            int resultSetIndex = 0;

            final long asn = resultSet.getLong(++resultSetIndex);
            final int platformType = resultSet.getInt(++resultSetIndex);
            final String siteUserId = resultSet.getString(++resultSetIndex);
            final int userDBIndex = resultSet.getInt(++resultSetIndex);
            final int logDBIndex = resultSet.getInt(++resultSetIndex);
            final String countryCode = resultSet.getString(++resultSetIndex);
            final int marketPlatformType = resultSet.getInt(++resultSetIndex);
            final int vipLevel = resultSet.getInt(++resultSetIndex);
            final LocalDateTime lastLoginTime = resultSet.getTimestamp(++resultSetIndex).toLocalDateTime();
            final BigDecimal lastKey = resultSet.getBigDecimal(++resultSetIndex);
            final LocalDateTime lastKeyUpdateTime = resultSet.getTimestamp(++resultSetIndex).toLocalDateTime();
            final boolean hasSecondaryDownload = resultSet.getBoolean(++resultSetIndex);
            final boolean isDeleted = resultSet.getBoolean(++resultSetIndex);
            final LocalDateTime regDate = resultSet.getTimestamp(++resultSetIndex).toLocalDateTime();
            final int npCountry = resultSet.getInt(++resultSetIndex);

            return new User(asn, platformType, siteUserId, userDBIndex, logDBIndex, countryCode, marketPlatformType, vipLevel, lastLoginTime, lastKey,
                lastKeyUpdateTime, hasSecondaryDownload, isDeleted, regDate, npCountry);
        }
    }

    private JdbcTemplate jdbcTemplate;

    public UserRepositoryImpl(@Autowired @Qualifier("global") DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public User findUser(int platformType, String siteUserId) { 
        String sql = "SELECT asn, platform_type, site_user_id, userdb_server_config_idx, logdb_server_config_idx, country_code, market_platform_type, "
                        + "vip_level, last_login_time, last_key, last_key_update_time, secondary_download, is_deleted, reg_date, np_country "
                        + "FROM user_server_config "
                        + "WHERE site_user_id = ? "
                        + "AND platform_type = ?";


        return jdbcTemplate.queryForObject(sql, new Object[] { siteUserId, platformType } , new UserMapper());
    }

    public User findUser(long asn) {
        String sql = "SELECT asn, platform_type, site_user_id, userdb_server_config_idx, logdb_server_config_idx, country_code, market_platform_type, "
                        + "vip_level, last_login_time, last_key, last_key_update_time, secondary_download, is_deleted, reg_date, np_country "
                        + "FROM user_server_config "
                        + "WHERE asn = ? ";
                        
        return jdbcTemplate.queryForObject(sql, new Object[] { asn } , new UserMapper());
    }
}