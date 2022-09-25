import React, { useState, useEffect } from "react";
import { Link, Routes, Route } from "react-router-dom";

import axios from "axios";

function BoothEnroll() {
  const [inputBoothName, setInputBoothName] = useState("");
  const [inputDate, setInputDate] = useState("");
  const [inputDate2, setInputDate2] = useState("");
  const [inputAbout, setInputAbout] = useState("");
  const [inputSeller, setInputSeller] = useState("");
  const [inputProduct, setInputProduct] = useState("");
  const [inputOpen, setInputOpen] = useState("");
  const [inputClose, setInputClose] = useState("");

  // input data 의 변화가 있을 때마다 value 값을 변경해서 useState 해준다
  const handleInputBoothName = (e) => {
    setInputBoothName(e.target.value);
  };

  const handleInputDate = (e) => {
    setInputDate(e.target.value);
  };

  const handleInputDate2 = (e) => {
    setInputDate2(e.target.value);
  };

  const handleInputAbout = (e) => {
    setInputAbout(e.target.value);
  };

  const handleInputSeller = (e) => {
    setInputSeller(e.target.value);
  };

  const handleInputProduct = (e) => {
    setInputProduct(e.target.value);
  };

  const handleInputOpen = (e) => {
    setInputOpen(e.target.value);
  };

  const handleInputClose = (e) => {
    setInputClose(e.target.value);
  };

  // login 버튼 클릭 이벤트
  const onClickBoothEnroll = () => {
    console.log("click BoothEnroll");
  };

  // 페이지 렌더링 후 가장 처음 호출되는 함수
  useEffect(
    () => {
      axios
        .get("/user_inform/login")
        .then((res) => console.log(res))
        .catch();
    },
    // 페이지 호출 후 처음 한번만 호출될 수 있도록 [] 추가
    []
  );

  return (
    <div>
      <h2>부스 신청</h2>
      <div>
        <label htmlFor="input_name">부스 이름 : </label>
        <input
          type="text"
          name="input_name"
          value={inputBoothName}
          onChange={handleInputBoothName}
        />
      </div>
      <div>
        <label htmlFor="input_date">부스 기간 : </label>
        <input
          type="date"
          name="input_date1"
          value={inputDate}
          onChange={handleInputDate}
        />
        <input
          type="date"
          name="input_date2"
          value={inputDate2}
          onChange={handleInputDate2}
        />
      </div>
      <div>
        <label htmlFor="input_about">판매자 정보(ex: 이름/전화번호) : </label>
        <input
          type="text"
          name="input_seller"
          value={inputSeller}
          onChange={handleInputSeller}
        />
      </div>
      <div>
        <label htmlFor="input_about">판매상품목록 : </label>
        <textarea
          type="text"
          name="input_seller"
          value={inputProduct}
          onChange={handleInputProduct}
        />
      </div>
      <div>
        <label htmlFor="input_date">운영 시간 : </label>
        <input
          type="time"
          name="input_open"
          value={inputOpen}
          onChange={handleInputOpen}
        />
        ~
        <input
          type="time"
          name="input_close"
          value={inputClose}
          onChange={handleInputClose}
        />
      </div>
      <div>
        <label htmlFor="input_about">부스 설명 : </label>
        <textarea
          type="text"
          name="input_about"
          value={inputAbout}
          onChange={handleInputAbout}
        />
      </div>
      <div>
        <button type="button" onClick={onClickBoothEnroll}>
          부스 목록 보기
        </button>
        <button type="button" onClick={onClickBoothEnroll}>
          부스 등록하기
        </button>
      </div>
      {/* <Link to="/register">회원가입</Link> */}
    </div>
  );
}

export default BoothEnroll;
