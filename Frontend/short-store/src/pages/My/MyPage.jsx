import React, { useEffect, useState } from 'react';
import { Routes, Route, Link } from "react-router-dom";
import axios from "../../api/axios";

import { Breadcrumb, Layout, Menu } from "antd";

// 테스트
import Interest from "../../components/Interest.js";

import {
  LaptopOutlined,
  NotificationOutlined,
  UserOutlined
} from "@ant-design/icons";
import styles from "./MyPage.module.css";

import 'antd/dist/antd.css';
import VirtualList from 'rc-virtual-list';

import Profile from "../../components/Profile";
import WishMarket from "../../components/WishMarket";
import WishBooth from "../../components/WishBooth";


const count = 3;
const fakeDataUrl = `https://randomuser.me/api/?results=${count}&inc=name,gender,email,nat,picture&noinfo`;

function Summary() {
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
}

function MyPage() {
  const [initLoading, setInitLoading] = useState(true);
  const [loading, setLoading] = useState(false);
  const [data, setData] = useState([]);
  const [list, setList] = useState([]);
  useEffect(() => {
    fetch(fakeDataUrl)
      .then((res) => res.json())
      .then((res) => {
        setInitLoading(false);
        setData(res.results);
        setList(res.results);
      });
  }, []);
  
  return (
    <div className="area-2">
      <>
        <div className={styles.snb_area}>
          <Link to="/my">
            <h2 className={styles.snb_main_title}>마이페이지</h2>
          </Link>
          <nav className={styles.snb}>
            <div className={styles.snb_list}>
              <strong className={styles.snb_title}>기본 사용자</strong>
              <ul className={styles.snb_menu}>
                <li className={styles.snb_item}>
                  <Link to="profile" className={styles.menu_link}>프로필 정보</Link>
                </li>
                <li className={styles.snb_item}>
                  <Link to="wishMarket" className={styles.menu_link}>관심 마켓</Link>
                </li>
                <li className={styles.snb_item}>
                  <Link to="wishBooth" className={styles.menu_link}>관심 부스</Link>
                </li>
              </ul>
            </div>
            <div className={styles.snb_list}>
              <strong className={styles.snb_title}>마켓 관계자</strong>
              <ul className={styles.snb_menu}>
                <li className={styles.snb_item}>
                  <li className={styles.snb_item}>
                    <Link to="/market/:id/manage" className={styles.menu_link}>마켓 관리하기</Link>
                  </li>
                </li>
                <li className={styles.snb_item}>
                  <li className={styles.snb_item}>
                    <Link to="/my/enroll/booth" className={styles.menu_link}>신청한 부스</Link>
                  </li>
                </li>
              </ul>
            </div>
          </nav>
        </div>
      </>
      
      <div className={styles.content_area} style={{height: "200vh", width:"700px" , backgroundColor:"blueviolet"}}>
        
        <Routes>
          <Route path="/" element={<Summary />}></Route>
          <Route path="/profile" element={<Profile />}></Route>
          <Route path="/wishBooth" element={<WishBooth />}></Route>
          <Route path="/wishMarket" element={<WishMarket />}></Route>
        </Routes>
        
      </div>
    </div>
  )
};

export default MyPage;
