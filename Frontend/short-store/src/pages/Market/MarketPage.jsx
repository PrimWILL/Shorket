import React from "react";
import styles from "./MarketPage.module.css";
import Slider from "../../components/Banner.js";

function MarketPage() {
  
  
  return (
    <div className="area-3">
      <h1>마켓상세페이지</h1>
      <div className={styles.column_wrap}>
        <div className={styles.column_is_fixed}>
          <h1>마켓 사진들</h1>
          <Slider />
        </div>
        <div className={styles.column}>
          <h1>마켓 세부 정보들</h1>

        </div>
      </div>
      <div className={styles.map_area} >
        <h1>마켓 지도 팜플렛</h1>

      </div>
      <div className={styles.others_area}>
        <h1>ㅇㅇ 지역의 다른 마켓 둘러보기</h1>
      </div>
    </div>
  );
}

export default MarketPage;
