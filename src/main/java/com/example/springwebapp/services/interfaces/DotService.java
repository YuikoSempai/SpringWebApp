package com.example.springwebapp.services.interfaces;

import com.example.springwebapp.models.Dot;

import java.util.List;

public interface DotService {
    void addDot(Dot dot);
    Dot getDotById(int id);
    void changeDot(int id, Dot dot);
    void deleteDot(int id);
    List<Dot> getDots();
}
