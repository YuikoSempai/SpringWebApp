package com.example.springwebapp.controllers;

import com.example.springwebapp.PostProcessing.Audited;
import com.example.springwebapp.controllers.DTO.DotDTO;
import com.example.springwebapp.models.Dot;
import com.example.springwebapp.services.interfaces.DotService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class ApiController {

    private final DotService service;

    @Autowired
    public ApiController(DotService service) {
        this.service = service;
    }

    @GetMapping("/dot")
    public List<Dot> getDots() {
        return service.getDots();
    }

    @PostMapping("/dot")
    public String addDot(@Valid @RequestBody DotDTO dotDTO) {
        Dot dot = new Dot(dotDTO.getX(), dotDTO.getY(), dotDTO.getR(), dotDTO.getStatus());
        service.addDot(dot);
        return "New dot has been added";
    }

    @GetMapping("/dot/{id}")
    public Dot getDotById(@PathVariable int id) {
        Dot dot = service.getDotById(id);

        return service.getDotById(id);
    }


    @PutMapping("/dot/{id}")
    public String changeDot(@RequestBody DotDTO dotDTO, @PathVariable Integer id) {
        Dot dot = new Dot(dotDTO.getY(), dotDTO.getY(), dotDTO.getR(), dotDTO.getStatus());
        service.changeDot(id, dot);
        return "Dot has been changed";
    }

    @DeleteMapping("/dot/{id}")
    public String deleteDot(@PathVariable int id) {
        service.deleteDot(id);
        return "Dot has been deleted";
    }
}
