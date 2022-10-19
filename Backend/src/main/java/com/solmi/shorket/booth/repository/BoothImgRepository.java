package com.solmi.shorket.booth.repository;

import com.solmi.shorket.booth.domain.Booth;
import com.solmi.shorket.booth.domain.BoothImg;
import com.solmi.shorket.market.domain.Market;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoothImgRepository extends JpaRepository<BoothImg, Integer> {

    List<BoothImg> findByBoothIdx(Integer boothIdx);

}
