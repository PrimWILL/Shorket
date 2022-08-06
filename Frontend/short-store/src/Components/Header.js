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

import styles from "./Header.module.css";

import { Typography, Menu } from "antd";
const { Title } = Typography;

const items = [
  {
    label: <Link to="/search">마켓 둘러보기</Link>,
    key: "search"
  },
  {
    label: <Link to="/my/wish">관심마켓&부스</Link>,
    key: "wish",
    children: [
      {
        label: "관심마켓",
        key: "market:1"
      },
      {
        label: "관심부스",
        key: "booth:1"
      }
    ]
  },
  {
    label: <Link to="/enroll">마켓&부스 신청하기</Link>,
    key: "enroll",
    children: [
      {
        label: "마켓 신청하기",
        key: "market:2"
      },
      {
        label: "부스 신청하기",
        key: "booth:2"
      }
    ]
  },
  {
    label: <Link to="my/markets">마켓 관리하기</Link>,
    key: "manage",
    icon: <SettingOutlined />
  }
];

function Header() {
  const [isToggled, setIsToggled] = useState(false);
  const [userToggled, setUserToggled] = useState(false);
  const [current, setCurrent] = useState("");

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
            <Title className={styles.header_title} level={2}>
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
