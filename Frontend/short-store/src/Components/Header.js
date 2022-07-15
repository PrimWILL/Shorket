// import PropTypes from "prop-types";
import { Link } from "react-router-dom";
import styles from "./Header.module.css";

function Header() {
  return (
    <div className={styles.header}>
      <div className={styles.header_top}>
        <div className={styles.top_inner}>
          <ul className={styles.top_list}>
            <li className={styles.top_item}>
              <Link to="/notice">고객센터</Link>
            </li>
            <li className={styles.top_item}>
              <Link to="/my/wish">관심마켓</Link>
            </li>
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
          <h1>
            <Link to="/">Short Store</Link>
          </h1>
          <div className={styles.gnb_area}>
            <nav className={styles.gnb}>
              <ul className={styles.gnb_list}>
                <li className={styles.gnb_item}>
                  <Link to="/market/enroll">마켓 신청하기</Link>
                </li>
                <li className={styles.gnb_item}>
                  <Link to="/market/upcomming">예정인 마켓</Link>
                </li>
                <li className={styles.gnb_item}>
                  <Link to="/market/inProgress">진행중인 마켓</Link>
                </li>
                <li className={styles.gnb_item}>
                  <Link to="/market/manage">마켓 관리하기</Link>
                </li>
              </ul>
            </nav>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Header;
