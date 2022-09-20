import React, { useEffect, useState } from "react";
import styles from "./My.module.css";

import { Routes, Route } from "react-router-dom";

import axios from "../api/axios";

import "antd/dist/antd.css";

// Components
import Sidebar from "../components/Sidebar";
import Profile from "../components/Profile";
import WishMarket from "../components/WishMarket";
import WishBooth from "../components/WishBooth";

//
// 테스트
import { Breadcrumb, Layout, Menu } from "antd";
import Interest from "../components/Interest.js";
import {
    LaptopOutlined,
    NotificationOutlined,
    UserOutlined,
} from "@ant-design/icons";

const count = 3;
const fakeDataUrl = `https://randomuser.me/api/?results=${count}&inc=name,gender,email,nat,picture&noinfo`;

export const Summary = () => {
    return (
        <>
            <Profile />
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
    const [data, setData] = useState([]);

    // const [initLoading, setInitLoading] = useState(true);
    // const [loading, setLoading] = useState(false);
    // const [list, setList] = useState([]);

    // axios로 유저정보 가져오기
    useEffect(() => {
        // fetch(fakeDataUrl)
        //     .then((res) => res.json())
        //     .then((res) => {
        //         setInitLoading(false);
        //         setData(res.results);
        //         setList(res.results);
        //     });
    }, []);

    return (
        <div className="area-2">
            <Sidebar />
            <div className="content_area">
                <Routes>
                    <Route path="/" element={<Summary />}></Route>
                    <Route path="/profile" element={<Profile />}></Route>
                    <Route path="/wishBooth" element={<WishBooth />}></Route>
                    <Route path="/wishMarket" element={<WishMarket />}></Route>
                </Routes>
            </div>
        </div>
    );
};

export default MyPage;
