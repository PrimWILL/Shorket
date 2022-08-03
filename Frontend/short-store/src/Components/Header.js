// import PropTypes from "prop-types";
import { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import NavToggle from "./NavToggle";
import MenuIcon from "@mui/icons-material/Menu";
import {
  AppstoreOutlined,
  MailOutlined,
  SettingOutlined
} from "@ant-design/icons";

import { Typography, Menu } from "antd";

import styles from "./Header.module.css";
const { Title } = Typography;

const items = [
  {
    label: <Link to="/search">마켓 둘러보기</Link>,
    key: "mail",
    icon: <MailOutlined />
  },
  {
    label: "관심마켓&부스",
    key: "app",
    icon: <AppstoreOutlined />,
    disabled: true
  },
  {
    label: "마켓&부스 신청하기",
    key: "SubMenu",
    icon: <SettingOutlined />,
    children: [
      {
        type: "group",
        label: "Item 1",
        children: [
          {
            label: "Option 1",
            key: "setting:1"
          },
          {
            label: "Option 2",
            key: "setting:2"
          }
        ]
      },
      {
        type: "group",
        label: "Item 2",
        children: [
          {
            label: "Option 3",
            key: "setting:3"
          },
          {
            label: "Option 4",
            key: "setting:4"
          }
        ]
      }
    ]
  },
  {
    label: (
      <a href="https://ant.design" target="_blank" rel="noopener noreferrer">
        마켓 관리하기
      </a>
    ),
    key: "alipay"
  }
];

function Header() {
  const [isToggled, setIsToggled] = useState(false);
  const [userToggled, setUserToggled] = useState(false);

  const [current, setCurrent] = useState("mail");

  const onClick = (e) => {
    console.log("click ", e);
    setCurrent(e.key);
  };

  return (
    <div className={styles.header}>
      <div className={styles.header_top}>
        <div className={styles.top_inner}>
          <ul className={styles.top_list}>
            <li className={styles.top_item}>
              <Link to="/my">마이페이지</Link>
            </li>
            <li className={styles.top_item}>
              <Link to="/login">로그인</Link>
            </li>
          </ul>
        </div>
      </div>
      <div className={styles.header_main}>
        <div className={styles.main_inner}>
          <Link to="/">
            <Title level={2}>SHORKET</Title>
          </Link>
          <Menu
            onClick={onClick}
            selectedKeys={[current]}
            mode="horizontal"
            items={items}
          />
        </div>
        <div className={styles.gnb_area}>
          <nav className={styles.gnb}>
            <ul className={styles.gnb_list}>
              <li className={styles.gnb_item}>
                <Link to="/search">마켓 둘러보기</Link>
              </li>
              <li className={styles.gnb_item}>
                <Link to="/my/wish">관심마켓&부스</Link>
              </li>
              <li className={styles.gnb_item}>
                <Link to="/enroll">마켓&부스 신청하기</Link>
              </li>
              <li className={styles.gnb_item}>
                <Link to="my/markets">마켓 관리하기</Link>
              </li>
            </ul>
          </nav>
        </div>
      </div>
    </div>
  );
}

export default Header;
