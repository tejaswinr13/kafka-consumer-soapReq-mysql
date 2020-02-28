package com.kafkaconsumer.cosumesoapcall.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class UserDetailsRepository {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private static final String SQL_INSERT = "INSERT INTO dbo.user_details (patientid,rxNumber,storenum,daysSupply) values(:patientid,:rxNumber,:storenum,:daysSupply)";

    public String save (String patientid,String rxNumber,String storenum,String daysSupply) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("patientid", patientid);
        mapSqlParameterSource.addValue("rxNumber", rxNumber);
        mapSqlParameterSource.addValue("storenum", storenum);
        mapSqlParameterSource.addValue("daysSupply", daysSupply);
        SqlParameterSource paramSource = mapSqlParameterSource;
        namedParameterJdbcTemplate.update(SQL_INSERT, paramSource);

       return "Inserted to DB";
    }
}
