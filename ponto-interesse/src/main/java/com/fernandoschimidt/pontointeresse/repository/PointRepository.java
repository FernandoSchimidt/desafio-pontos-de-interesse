package com.fernandoschimidt.pontointeresse.repository;

import com.fernandoschimidt.pontointeresse.entity.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PointRepository extends JpaRepository<Point, Integer> {


    @Query("SELECT p FROM Point p" +
            " WHERE ( p.x >= ?1 and p.x <= ?3 and p.y >= ?2 and p.y <= ?4 )")
    List<Point> pointsInsideRectangle(int x1, int y1, int x2, int y2);
}
