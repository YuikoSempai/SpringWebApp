package com.example.springwebapp.repository;


import com.example.springwebapp.models.Dot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DotRepository extends JpaRepository<Dot, Integer> {
}
