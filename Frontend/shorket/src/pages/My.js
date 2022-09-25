import React, { useEffect, useState } from "react";
import styles from "./My.module.css";

import { Routes, Route, useNavigate } from "react-router-dom";

import axios from "../api/axios";
import useAuth from "../hooks/useAuth";
import useRefreshToken from "../hooks/useRefreshToken";
import useAxiosPrivate from "../hooks/useAxiosPrivate";

// Components
import Sidebar from "../components/Sidebar";
import Profile from "../components/Profile";
import WishMarket from "../components/WishMarket";
import WishBooth from "../components/WishBooth";

//
// 테스트
import { Breadcrumb, Layout, Menu } from "antd";
import Interest from "../components/Interest.js";

import "antd/dist/antd.css";
import {
    LaptopOutlined,
    NotificationOutlined,
    UserOutlined,
} from "@ant-design/icons";

const count = 3;
const fakeDataUrl = `https://randomuser.me/api/?results=${count}&inc=name,gender,email,nat,picture&noinfo`;

export const Summary = ({ userData }) => {
    return (
        <>
            <Profile userData={userData} />
            <WishMarket />
            <WishBooth />
            <div>
                <h1>마켓 관리하기</h1>
            </div>
            <div>
                <h1>신청한 부스</h1>
            </div>
        </>
    );
};

const MyPage = () => {
    const { auth } = useAuth();
    const navigate = useNavigate();

    const [myData, setMyData] = useState([]);
    const [wishBooths, setWishBooths] = useState([]);
    const [wishMarkets, setWishMarkets] = useState([]);
    const refresh = useRefreshToken();
    const axiosPrivate = useAxiosPrivate();

    // axios로 유저정보 가져오기
    useEffect(() => {
        const getUser = async () => {
            try {
                const response = await axiosPrivate.get("/users/");
                console.log(response);
                setMyData(response.data);
            } catch (err) {
                console.log(err?.response);
            }
        };

        getUser();
    }, []);

    const testGet = () => {
        console.log("!!!");
        const getUser = async () => {
            try {
                const response = await axios.get("/users/", {
                    headers: {
                        // Authorization: `Bearer ${auth.accessToken}`,
                        "X-Auth-Token": `${auth.accessToken}`,
                    },
                });

                console.log(response);
            } catch (err) {
                console.log(err?.response);
            }
        };

        getUser();
    };

    return (
        <div className="area-2">
            <Sidebar />
            <div className="content_area">
                <Routes>
                    <Route
                        path="/"
                        element={<Summary userData={myData} />}
                    ></Route>
                    <Route path="/profile" element={<Profile />}></Route>
                    <Route path="/wishBooth" element={<WishBooth />}></Route>
                    <Route path="/wishMarket" element={<WishMarket />}></Route>
                </Routes>
            </div>
            <button onClick={() => refresh()}>Refresh</button>
            <button onClick={() => testGet()}>test</button>
        </div>
    );
};

export default MyPage;
