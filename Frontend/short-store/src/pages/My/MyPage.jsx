import React from "react";
import { Link } from "react-router-dom";

import styles from "./MyPage.module.css";
import Interest from "../../components/Interest.js";
import 'antd/dist/antd.css';
import { Layout, Menu } from 'antd';

const { Content, Sider } = Layout;
function MyPage() {
  return (
    <Layout
      className="site-layout-background"
      style={{
        padding: '24px 0',
      }}
    >
      <Sider className="site-layout-background" width={200}>
        <Menu
          mode="inline"
          defaultSelectedKeys={['1']}
          defaultOpenKeys={['sub1']}
          style={{
            height: '100%',
          }}
          items={items2}
        />
      </Sider>
      <Content
        style={{
          padding: '0 24px',
          minHeight: 280,
        }}
      >
        Content
      </Content>
    </Layout>
    // <div className={styles.area}>
    //   <div className={styles.snb_snb}>
    //     <Link to="/my">
    //       <h2>마이페이지</h2>
    //     </Link>
    //     <Sider
    //   breakpoint="lg"
    //   collapsedWidth="0"
    //   onBreakpoint={(broken) => {
    //     console.log(broken);
    //   }}
    //   onCollapse={(collapsed, type) => {
    //     console.log(collapsed, type);
    //   }}
    // >
    //   <div className="logo" />
    //   <Menu
    //     theme="dark"
    //     mode="inline"
    //     defaultSelectedKeys={['4']}
    //     items={[UserOutlined, VideoCameraOutlined, UploadOutlined, UserOutlined].map(
    //       (icon, index) => ({
    //         key: String(index + 1),
    //         icon: React.createElement(icon),
    //         label: `nav ${index + 1}`,
    //       }),
    //     )}
    //   />
    // </Sider>
    //     <nav className={styles.snb}>
    //       <div className={styles.snb_list}>
    //         <strong className={styles.snb_title}>기본 사용자</strong>
    //         <ul className={styles.snb_menu}>
    //           <li className={styles.snb_item}>
    //             <Link to="/my/profile">프로필 정보</Link>
    //           </li>
    //           <li className={styles.snb_item}>
    //             <Link to="/my/wish/market">관심 마켓</Link>
    //           </li>
    //           <li className={styles.snb_item}>
    //             <Link to="/my/wish/booth">관심 부스</Link>
    //           </li>
    //         </ul>
    //       </div>
    //       <div className={styles.snb_list}>
    //         <strong className={styles.snb_title}>마켓 관리자</strong>
    //         <ul className={styles.snb_menu}>
    //           <li className={styles.snb_item}>
    //             <a href="">마켓 관리하기</a>
    //           </li>
    //           <li className={styles.snb_item}>
    //             <a href="">신청한 부스</a>
    //           </li>
    //         </ul>
    //       </div>
    //     </nav>
    //   </div>
    //   <div></div>
    //   <Interest />
    // </div>
  );
}

export default MyPage;
