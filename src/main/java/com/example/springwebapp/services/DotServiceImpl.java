package com.example.springwebapp.services;

import com.example.springwebapp.PostProcessing.Audited;
import com.example.springwebapp.exceptionsData.exceptions.DotException;
import com.example.springwebapp.models.Dot;
import com.example.springwebapp.repository.DotRepository;
import com.example.springwebapp.services.interfaces.DotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DotServiceImpl implements DotService {
    private final DotRepository dotRepository;

    @Autowired
    public DotServiceImpl(DotRepository dotRepository) {
        this.dotRepository = dotRepository;
    }

    @Audited
    public void addDot(Dot dot) {
        dot.setStatus(check(dot.getX(), dot.getY(), dot.getR()).toString());
        if(validateDot(dot.getX(),dot.getY(),dot.getR())){
            dotRepository.save(dot);
        }
    }

    public Dot getDotById(int id) {
        Optional<Dot> optionalDot = dotRepository.findById(id);
        Dot dot = optionalDot.orElse(null);
        if (Objects.equals(null, dot)) {
            throw new DotException("There is no dot with id " + id);
        }
        return dot;
    }

    public void changeDot(int id, Dot dot) {
        Optional<Dot> optionalDot = dotRepository.findById(id);
        if (optionalDot.isPresent()) {
            dot.setId(optionalDot.get().getId());
            dotRepository.save(dot);
        } else {
            throw new DotException("There is no dot with id " + id);
        }
    }

    public void deleteDot(int id) {
        if (dotRepository.existsById(id)) {
            dotRepository.deleteById(id);
        } else {
            throw new DotException("here is no dot with id " + id);
        }
    }

    public List<Dot> getDots() {
        return dotRepository.findAll();
    }

    private Boolean validateDot(Double x, Double y, Integer r){
        return x <= 4 && x >= -4 && y >= -3 && y <= 3 && r >= -4 && r <= 4;
    }

    private Boolean check(Double x, Double y, Integer r) {
        if (x == 0 && y == 0) {
            return true;
        }
        if (x < 0 && y > 0) {
            return y < r && x > (-1 * r / 2.0);
        }
        if (x > 0 && y > 0) {
            return x * x + y * y <= (r * r) / 4.0;
        }
        if (x < 0 && y < 0) {
            double x1, y1, x2, y2, x3, y3;
            x1 = 0;
            y1 = 0;
            x2 = -1 * r / 2.0;
            y2 = 0;
            x3 = 0;
            y3 = -1 * r / 2.0;
            double var1 = (x1 - x) * (y2 - y1) - (x2 - x1) * (y1 - y);
            double var2 = (x2 - x) * (y3 - y2) - (x3 - x2) * (y2 - y);
            double var3 = (x3 - x) * (y1 - y3) - (x1 - x3) * (y3 - y);
            return (var1 >= 0 && var2 >= 0 && var3 >= 0) || (var1 <= 0 && var2 <= 0 && var3 <= 0);
        }
        return false;
    }

}
