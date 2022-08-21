import React from "react";
// import { bannerData } from "../../constants/data";

// Import Swiper React components
import { Swiper, SwiperSlide } from "swiper/react";

// Import Swiper styles
import "swiper/css";
import "swiper/css/pagination";
import "swiper/css/navigation";

// import required modules
import { Navigation, Pagination, Mousewheel, Keyboard } from "swiper";

function Banner() {
  const bgColor = [
    "#3498db",
    "#27ae60",
    "#e74c3c",
    "#f1c40f",
    "#9834db",
    "#ae2760",
    "#e73c4c"
  ];

  const slides = bgColor.map((item, index) => (
    <SwiperSlide key={index} style={{ backgroundColor: item }}>
      Slide{index}
    </SwiperSlide>
  ));

  return (
    <Swiper
      style={{ marginTop: "100px", height: "480px" }}
      cssMode={true}
      navigation={true}
      pagination={{
        clickable: true
      }}
      mousewheel={true}
      keyboard={true}
      modules={[Navigation, Pagination, Mousewheel, Keyboard]}
      className="mySwiper"
      slidesPerView={1}
      spaceBetween={30}
      loop={true}
      autoplay={{
        delay: 2000
      }}>
      {slides}
    </Swiper>
  );
}

export default Banner;
