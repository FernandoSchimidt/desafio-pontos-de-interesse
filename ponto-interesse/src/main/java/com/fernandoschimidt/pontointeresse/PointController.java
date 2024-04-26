package com.fernandoschimidt.pontointeresse;

import com.fernandoschimidt.pontointeresse.dto.PointDTO;
import com.fernandoschimidt.pontointeresse.entity.Point;
import com.fernandoschimidt.pontointeresse.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/points")
public class PointController {
    @Autowired
    private PointService service;

    @GetMapping
    public ResponseEntity<List<PointDTO>> findAll() {
        List<PointDTO> resp = listToDTO(service.findAll());
        return ResponseEntity.ok().body(resp);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody PointDTO dto) {
        Point obj = service.fromDTO(dto);
        obj = service.save(obj);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri)
                .build();
    }

    @GetMapping(value = "/near")
    public ResponseEntity<List<PointDTO>> pointsNear(@RequestParam(value = "x") int x,
                                                     @RequestParam(value = "y") int y,
                                                     @RequestParam(value = "dmax") int dmax) {

        List<PointDTO> resp = listToDTO(service.pointsNear(x, y, dmax));

        return ResponseEntity.ok().body(resp);
    }

    private List<PointDTO> listToDTO(List<Point> list) {
        return list.stream().map(p -> new PointDTO(p)).collect(Collectors.toList());
    }
}
