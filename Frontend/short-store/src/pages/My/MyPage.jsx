import React, { useEffect, useState } from 'react';
import { Link } from "react-router-dom";

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

const count = 3;
const fakeDataUrl = `https://randomuser.me/api/?results=${count}&inc=name,gender,email,nat,picture&noinfo`;

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
                  <Link to="/my/profile" className={styles.menu_link}>프로필 정보</Link>
                </li>
                <li className={styles.snb_item}>
                  <Link to="/my/wish/market" className={styles.menu_link}>관심 마켓</Link>
                </li>
                <li className={styles.snb_item}>
                  <Link to="/my/wish/booth" className={styles.menu_link}>관심 부스</Link>
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
                    <Link to="/my/wish/booth" className={styles.menu_link}>신청한 부스</Link>
                  </li>
                </li>
              </ul>
            </div>
          </nav>
        </div>
      </>
      
      <div className={styles.content_area} style={{height: "200vh", width:"700px" , backgroundColor:"blueviolet"}}>
        123
        <div></div>
        <div></div>
        <div></div>
  
      </div>
    </div>
  )
};

export default MyPage;
