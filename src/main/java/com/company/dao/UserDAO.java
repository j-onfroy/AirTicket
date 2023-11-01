package com.company.dao;

import com.company.dao.rowmapper.UserMapper;
import com.company.model.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserDAO {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserDAO(NamedParameterJdbcTemplate jdbcTemplate, BCryptPasswordEncoder passwordEncoder) {
        this.jdbcTemplate = jdbcTemplate;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> findByEmail(String email) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("email", email);
        User user = null;
        try {
            user = jdbcTemplate.queryForObject("""
                        SELECT  u.id AS u_id,
                                u.name AS u_name,
                                u.email AS u_email,
                                u.password AS u_password,
                                u.verified AS u_verified,
                                u.admin_role AS u_admin_role
                        FROM users u
                                WHERE u.email = :email
                        """, parameterSource,
                new UserMapper());
            assert user != null;
            return Optional.of(user);
        }catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

        public Integer save(User user) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("email", user.getEmail())
                .addValue("name", user.getName())
                .addValue("password", passwordEncoder.encode(user.getPassword()));
        return jdbcTemplate.queryForObject(
                """ 
                         INSERT INTO users(name,email,password) VALUES (:name,:email,:password) returning id
                         """, parameterSource, Integer.class);
    }

    public void setVerified(Integer id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        jdbcTemplate.update("""
                UPDATE users set verified = true where id = :id
                """, parameterSource);
    }
    public String getEmailById(Integer id){
        SqlParameterSource parameterSource = new MapSqlParameterSource("id",id);
        return jdbcTemplate.queryForObject("""
                SELECT email FROM users WHERE id = :id
                """,parameterSource,String.class);
    }
}
