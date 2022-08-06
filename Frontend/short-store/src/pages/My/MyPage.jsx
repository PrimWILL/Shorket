import React from "react";
import { Link } from "react-router-dom";

import styles from "./MyPage.module.css";
import Interest from "../../components/Interest.js";
import {
  LaptopOutlined,
  NotificationOutlined,
  UserOutlined
} from "@ant-design/icons";
import type { MenuProps } from "antd";
import { Breadcrumb, Layout, Menu } from "antd";
const { Header, Content, Sider } = Layout;


function MyPage() {
  return (
    
    <div className={styles.area}>
      <div className={styles.snb_snb}>
        <Link to="/my">
          <h2>마이페이지</h2>
        </Link>
        <nav className={styles.snb}>
          <div className={styles.snb_list}>
            <strong className={styles.snb_title}>기본 사용자</strong>
            <ul className={styles.snb_menu}>
              <li className={styles.snb_item}>
                <Link to="/my/profile">프로필 정보</Link>
              </li>
              <li className={styles.snb_item}>
                <Link to="/my/wish/market">관심 마켓</Link>
              </li>
              <li className={styles.snb_item}>
                <Link to="/my/wish/booth">관심 부스</Link>
              </li>
            </ul>
          </div>
          <div className={styles.snb_list}>
            <strong className={styles.snb_title}>마켓 관리자</strong>
            <ul className={styles.snb_menu}>
              <li className={styles.snb_item}>
                <a href="">마켓 관리하기</a>
              </li>
              <li className={styles.snb_item}>
                <a href="">신청한 부스</a>
              </li>
            </ul>
          </div>
        </nav>
      </div>
      <div></div>
      <Interest />
    </div>
  );
}

export default MyPage;
