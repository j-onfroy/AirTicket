package com.company.dao.rowmapper;

import com.company.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return User.builder()
                .id(rs.getInt("u_id"))
                .name(rs.getString("u_name"))
                .email(rs.getString("u_email"))
                .password(rs.getString("u_password"))
                .verified(rs.getBoolean("u_verified"))
                .admin(rs.getBoolean("u_admin_role"))
                .build();
    }
}
