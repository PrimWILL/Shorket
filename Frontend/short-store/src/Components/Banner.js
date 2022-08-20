import React from "react";
import { bannerBgColor } from "../constants/data";

// * Swiper 라이브러리 이용하기

// Import Swiper React components
import { Swiper, SwiperSlide } from "swiper/react";

// Import Swiper styles
import "swiper/css";
import "swiper/css/pagination";
import "swiper/css/navigation";

// import required modules
import { Autoplay, Navigation, Pagination, Mousewheel, Keyboard } from "swiper";

function Banner() {
  const bgColor = bannerBgColor; // import from data.js

  const slides = bgColor.map((item, index) => (
    <SwiperSlide
      key={index}
      style={{
        backgroundColor: item,
        display: "flex",
        alignItems: "center",
        justifyContent: "center",
      }}
    >
      <h1>슬라이드 {index + 1}</h1>
    </SwiperSlide>
  ));

  return (
    <Swiper
      style={{ height: "480px" }}
      className="mySwiper"
      cssMode={true}
      navigation={true}
      pagination={{
        clickable: true,
      }}
      mousewheel={true}
      keyboard={true}
      modules={[Autoplay, Navigation, Pagination, Mousewheel, Keyboard]}
      slidesPerView={1}
      // onSlideChange={() => console.log("slide change")}
      spaceBetween={30}
      loop={true}
      autoplay={{
        delay: 5000,
        disableOnInteraction: false,
      }}
    >
      {slides}
    </Swiper>
  );
}

export default Banner;
