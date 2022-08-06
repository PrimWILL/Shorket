package com.solmi.shorket.booth.repository;

import com.solmi.shorket.booth.domain.Booth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoothRepository extends JpaRepository<Booth, Integer> {
    // 부스 전체 조회
    List<Booth> findAllBy();

    // 부스 상세 조회
    Optional<Booth> findById(Integer idX);
}
