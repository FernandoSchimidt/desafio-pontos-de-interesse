package com.fernandoschimidt.pontointeresse.service;

import com.fernandoschimidt.pontointeresse.dto.PointDTO;
import com.fernandoschimidt.pontointeresse.entity.Point;
import com.fernandoschimidt.pontointeresse.repository.PointRepository;
import com.fernandoschimidt.pontointeresse.service.exceptions.IllegalValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PointService {

    @Autowired
    private PointRepository repository;

    public Point save(Point point) {
        return repository.save(point);
    }

    public List<Point> findAll() {
        return repository.findAll();
    }

    public Point fromDTO(PointDTO dto) {
        if (dto.getX() < 0 || dto.getY() < 0) {
            throw new IllegalValueException("point coordinate must be positive");
        }
        return new Point(null, dto.getName(), dto.getX(), dto.getY());
    }

    public List<Point> pointsNear(int x, int y, int dmax) {
        if (dmax < 0) {
            throw new IllegalValueException("Distance must be positive");
        }

        int dmaxsquared = dmax * dmax;
        List<Point> possiblePoints = repository.pointsInsideRectangle(x - dmax, y - dmax, x + dmax, y + dmax);

        return possiblePoints.stream()
                .filter(p -> distanceSquared(p.getX(), p.getY(), x, y) <= dmaxsquared)
                .collect(Collectors.toList());
    }

    private int distanceSquared(int x1, int y1, int x2, int y2) {
        return (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1);
    }
}
