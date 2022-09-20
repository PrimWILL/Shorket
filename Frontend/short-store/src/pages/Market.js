import React, { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";

import axios from "../api/axios";

import styles from "./Market.module.css";

import Slider from "../components/SliderImg.js";

function Market() {
    const { id } = useParams();
    const navigate = useNavigate();
    const [data, setData] = useState([]);
    const [isLoad, setIsLoad] = useState(false);

    useEffect(() => {
        console.log(id);
        axios
            .get(`/markets/${id}`)
            .then((res) => {
                setData(res.data);
                console.log(res);
                setIsLoad(true);
            })
            .catch(function (error) {
                console.log(error);
                alert("존재하지 않는 페이지 입니다.");
                navigate("/", { replace: true });
            });
    }, []);

    return (
        <div className="area-3">
            <h1>마켓상세페이지</h1>
            <section>
                <div className={styles.column_wrap}>
                    <div className={styles.column_is_fixed}>
                        {isLoad === false ? (
                            <p>로딩중...</p>
                        ) : (
                            <Slider imgList={data.images} />
                        )}
                        <h1>마켓 사진들</h1>
                    </div>
                    <div className={styles.column}>
                        <h1>플리마켓 정보들</h1>
                        {isLoad === false ? (
                            <p>로딩중...</p>
                        ) : (
                            <section>
                                <h2>{data.name}</h2>
                                <h2>
                                    지역: {data.address.sido}{" "}
                                    {data.address.sigungu}
                                </h2>
                                <h2>상세위치: {data.address.detailAddress}</h2>
                                <h2>
                                    시간: {data.openTime.slice(0, 5)} ~
                                    {data.closeTime.slice(0, 5)}
                                </h2>

                                <h2>
                                    운영기간:
                                    {new Date(data.startDate).getFullYear()}.
                                    {new Date(data.startDate).getMonth() + 1}.
                                    {new Date(data.startDate).getDate()} ~
                                    {new Date(data.endDate).getFullYear()}.
                                    {new Date(data.endDate).getMonth() + 1}.
                                    {new Date(data.endDate).getDate()}
                                </h2>
                                <div>관심마켓 {data.interestCount}</div>
                            </section>
                        )}
                        <h1>부스 목록</h1>
                        {isLoad === false ? (
                            <p>로딩중...</p>
                        ) : (
                            <section></section>
                        )}
                    </div>
                </div>
            </section>
            <section className={styles.map_area}>
                <h1>마켓 지도 팜플렛</h1>
                <img
                    src={`${data.mapImage}`}
                    className="marketMap"
                    alt="Market Map Image"
                />
            </section>
            <section>
                <div className={styles.others_area}>
                    <h1>ㅇㅇ 지역의 다른 마켓 둘러보기</h1>
                </div>
            </section>
        </div>
    );
}

export default Market;
