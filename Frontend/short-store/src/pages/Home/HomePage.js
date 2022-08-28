import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

import styles from "./Home.module.css";

import Button from "@mui/material/Button";
import MarketList from "../../components/MarketList.js";
import Banner from "../../components/Banner.js";

import { useFetch } from "../../hooks/useFetch";
const baseUrl = "http://52.79.146.185:8080/api/";

// - 더보기 -> 4개씩 더 로드 하는걸로 변경

function Home() {
  const [loading, setLoading] = useState(true);
  const [feeds, setFeeds] = useState([]);

  const [data, setData] = useState([]); // get으로 가져온 데이터

  const getClick = () => {
    axios
      .get(`${baseUrl}/markets/4`)
      .then((res) => setData(res.data))
      .catch(function (error) {
        console.log(error);
      });
  };
  const postClick = () => {
    axios
      .post("http://52.79.146.185:8080/api/users/email", {
        email: "temp000@naver.com",
      })
      .then((res) => console.log(res.data))
      .catch(function (error) {
        console.log(error);
      });
  };

  const navigate = useNavigate();

  useEffect(() => {
    // console.log("homepage loaded...");
    // - 마켓 리스트 조회 추가 지금 진행중인 마켓 여기가 맞나?
    // marketlist prop 개수 카운트
  }, []);

  function handleClick() {
    navigate("/search");
  }

  const getMarketsAsync = async () => {
    try {
      const response = await axios.get(
        "https://jsonplaceholder.typicode.com/posts"
      );
      setData(response.data);
      console.log(response.data);
    } catch (err) {
      console.log("Error >>", err);
    }
  };

  return (
    <div className="area">
      <Banner />
      <h1>진행중인 마켓</h1>
      <MarketList />
      <Button
        className={styles.button}
        variant="outlined"
        onClick={handleClick}
      >
        더보기
      </Button>
      <button onClick={getClick}>get</button>
      <button onClick={postClick}>post</button>
    </div>
  );
}

export default Home;
