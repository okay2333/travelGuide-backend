package com.huang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huang.model.entity.CountSource;
import com.huang.model.entity.CountTicket;
import com.huang.model.entity.CountTrend;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 帖子数据库操作
 *
 * 
 * 
 */
public interface CountMapper extends BaseMapper<CountTrend> {

    @Select("SELECT " +
            "DATE_FORMAT(d.date, '%m-%d') AS date, " +
            "COALESCE(r.reservations, 0) AS reservationCount, " +
            "COALESCE(o.purchases, 0) AS purchaseCount, " +
            "COALESCE(v.actual_visits, 0) AS actualVisits " +
            "FROM " +
            "(" +
            "    SELECT DATE_SUB(CURDATE(), INTERVAL n DAY) AS date " +
            "    FROM (" +
            "        SELECT 0 AS n UNION ALL SELECT 1 UNION ALL SELECT 2 " +
            "        UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 " +
            "        UNION ALL SELECT 6" +
            "    ) AS numbers" +
            ") AS d " +
            "LEFT JOIN " +
            "(" +
            "    SELECT DATE(createTime) AS date, COUNT(*) AS reservations " +
            "    FROM reservations " +
            "    WHERE createTime >= DATE_SUB(CURDATE(), INTERVAL 6 DAY) " +
            "    GROUP BY DATE(createTime)" +
            ") AS r ON d.date = r.date " +
            "LEFT JOIN " +
            "(" +
            "    SELECT DATE(purchaseDate) AS date, COUNT(*) AS purchases " +
            "    FROM orders " +
            "    WHERE purchaseDate >= DATE_SUB(CURDATE(), INTERVAL 6 DAY) " +
            "    GROUP BY DATE(purchaseDate)" +
            ") AS o ON d.date = o.date " +
            "LEFT JOIN " +
            "(" +
            "    SELECT DATE(verificationDate) AS date, COUNT(*) AS actual_visits " +
            "    FROM qrcodeverification " +
            "    WHERE verificationDate >= DATE_SUB(CURDATE(), INTERVAL 6 DAY) " +
            "    GROUP BY DATE(verificationDate)" +
            ") AS v ON d.date = v.date " +
            "ORDER BY d.date")
    @Results({
            @Result(property = "date", column = "date"),
            @Result(property = "reservationCount", column = "reservationCount"),
            @Result(property = "purchaseCount", column = "purchaseCount"),
            @Result(property = "actualVisits", column = "actualVisits")
    })
    List<CountTrend> getVisitTrend();




    @Select("SELECT " +
            "s.scenicName AS name, " +
            "COALESCE(t.total_stock, 0) - COALESCE(o.total_orders, 0) AS value " +
            "FROM scenic s " +
            "LEFT JOIN ( " +
            "    SELECT scenicId, SUM(stock) AS total_stock " +
            "    FROM ticket " +
            "    GROUP BY scenicId " +
            ") t ON s.id = t.scenicId " +
            "LEFT JOIN ( " +
            "    SELECT t.scenicId, SUM(o.quantity) AS total_orders " +
            "    FROM orders o " +
            "    JOIN ticket t ON o.ticketId = t.id " +
            "    GROUP BY t.scenicId " +
            ") o ON s.id = o.scenicId " +
            "WHERE s.type = 1 " +
            "ORDER BY s.scenicName")
    @Results({
            @Result(property = "name", column = "name"),
            @Result(property = "value", column = "value")
    })
    List<CountTicket> getScenicTicketDistribution();




    @Select({
            "SELECT 'Reservation Count' AS name, COUNT(*) AS value",
            "FROM reservations",
            "WHERE isDelete = 0",
            "UNION ALL",
            "SELECT 'Ticket Scenic Count' AS name, COUNT(*) AS value",
            "FROM scenic",
            "WHERE type = 1 ",
            "UNION ALL",
            "SELECT 'Platform User Count' AS name, COUNT(*) AS value",
            "FROM user",
            "WHERE userRole = 'user'",
            "UNION ALL",
            "SELECT 'Administrators' AS name, COUNT(*) AS value",
            "FROM user",
            "WHERE userRole = 'admin' "
    })
    @Results({
            @Result(property = "name", column = "name"),
            @Result(property = "value", column = "value")
    })
    List<CountSource> getUserDistribution();
}




