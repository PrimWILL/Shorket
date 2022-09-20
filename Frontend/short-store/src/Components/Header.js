import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";

import styles from "./Header.module.css";

import { Typography, Menu } from "antd";
import {
  TagOutlined,
  SearchOutlined,
  SettingOutlined,
  FormOutlined,
} from "@ant-design/icons";

// antd 컴포넌트 : Title, Menu 바
const { Title } = Typography;
const items = [
  {
    label: <Link to="/search">마켓 둘러보기</Link>,
    key: "search",
    icon: <SearchOutlined />,
  },
  {
    label: "관심마켓&부스",
    key: "wish",
    icon: <TagOutlined />,
    children: [
      {
        label: <Link to="/my/wishMarket">관심마켓</Link>,
        key: "market:1",
      },
      {
        label: <Link to="/my/wishBooth">관심부스</Link>,
        key: "booth:1",
      },
    ],
  },
  {
    label: "마켓&부스 신청하기",
    key: "enroll",
    icon: <FormOutlined />,
    children: [
      {
        // label: <Link to="/enroll/market">마켓 신청하기</Link>,
        label: (
          <a href="https://docs.google.com/forms/d/e/1FAIpQLSca4Vayhsr9s7FTZZi8vm_1MKwCDVZVrnXGyVHJXGxxDxmoug/viewform">
            마켓 신청하기
          </a>
        ),
        key: "market:2",
      },
      {
        label: <Link to="/enroll/booth">부스 신청하기</Link>,
        key: "booth:2",
      },
    ],
  },
  {
    label: <Link to="my/markets">마켓 관리하기</Link>,
    key: "manage",
    icon: <SettingOutlined />,
  },
];

function Header() {
  const [current, setCurrent] = useState(""); // 현재 선택된 메뉴 스타일 적용

  const onClick = (e) => {
    setCurrent(e.key);
  };

  return (
    <div className="header">
      <div className={styles.header_top}>
        <div className={styles.top_inner}>
          <ul className={styles.top_list}>
            <li className={styles.top_item}>
              <Link to="/my" style={{ color: "rgba(34, 34, 34, 0.8)" }}>
                마이페이지
              </Link>
            </li>
            <li className={styles.top_item}>
              <Link to="/login" style={{ color: "rgba(34, 34, 34, 0.8)" }}>
                로그인
              </Link>
            </li>
          </ul>
        </div>
      </div>
      <div className={styles.header_main}>
        <div className={styles.main_inner}>
          <Link onClick={onClick} to="/">
            <Title level={2} style={{ margin: 0 }}>
              SHORKET
            </Title>
          </Link>
          <Menu
            className={styles.gnb_area}
            onClick={onClick}
            selectedKeys={[current]}
            mode="horizontal"
            items={items}
          />
        </div>
      </div>
    </div>
  );
}

export default Header;
