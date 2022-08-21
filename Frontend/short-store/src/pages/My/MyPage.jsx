import React from "react";
import { Link } from "react-router-dom";

import { Breadcrumb, Layout, Menu } from "antd";

// 테스트
import styles from "./MyPage.module.css";
import Interest from "../../components/Interest.js";

import {
  LaptopOutlined,
  NotificationOutlined,
  UserOutlined
} from "@ant-design/icons";
import styles from "./MyPage.module.css";


const { Header, Content, Sider } = Layout;
const items1 = ["1", "2", "3"].map((key) => ({
  key,
  label: `nav ${key}`
}));
const items2 = [UserOutlined, LaptopOutlined, NotificationOutlined].map(
  (icon, index) => {
    const key = String(index + 1);
    return {
      key: `sub${key}`,
      icon: React.createElement(icon),
      label: `subnav ${key}`,
      children: new Array(4).fill(null).map((_, j) => {
        const subKey = index * 4 + j + 1;
        return {
          key: subKey,
          label: `option${subKey}`
        };
      })
    };
  }
);

const MyPage = () => (
  <div className="area-2">
    <div>
      <div style={{float:"left", width:"200px" , backgroundColor:"pink"}} className={styles.snb_snb}>
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
    </div>
    
    <div style={{height: "200vh", width:"700px" , backgroundColor:"blueviolet"}}>
      <Content
        className="site-layout-background"
        style={{
          padding: 24,
          margin: 0,
          minHeight: 280
        }}>
        Content
      </Content>

    </div>
  </div>
);

export default MyPage;
