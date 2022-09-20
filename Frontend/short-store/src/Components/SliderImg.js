import React from "react";

// * Swiper 라이브러리 이용하기

// Import Swiper React components
import { Swiper, SwiperSlide } from "swiper/react";

// Import Swiper styles
import "swiper/css";
import "swiper/css/pagination";
import "swiper/css/navigation";

// import required modules
import { Autoplay, Navigation, Pagination, Mousewheel, Keyboard } from "swiper";

function SliderImg({ list, imgList }) {
    const slides = list.map((item, index) => (
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

    const imgSlides = imgList.map((item, index) => (
        <SwiperSlide
            key={index}
            style={{
                display: "flex",
                alignItems: "center",
                justifyContent: "center",
            }}
        >
            <img src={item} />
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
            {imgSlides.length === 0 ? slides : imgSlides}
        </Swiper>
    );
}

SliderImg.defaultProps = {
    list: [],
    imgList: [],
};

export default SliderImg;
