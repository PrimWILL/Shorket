package com.solmi.shorket.booth.repository;

import com.solmi.shorket.booth.domain.Booth;
import com.solmi.shorket.market.domain.Market;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoothRepository extends JpaRepository<Booth, Integer> {

    Page<Booth> findAll(Pageable pageable);

    Page<Booth> findByMarket(Pageable pageable, Market market);

    Optional<Booth> findById(Integer idx);
}
