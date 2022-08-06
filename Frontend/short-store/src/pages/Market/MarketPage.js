import React from "react";
import styles from "./MarketPage.module.css";

import 'antd/dist/antd.css';
import {
  AppstoreOutlined,
  BarChartOutlined,
  CloudOutlined,
  ShopOutlined,
  TeamOutlined,
  UploadOutlined,
  UserOutlined,
  VideoCameraOutlined,
} from '@ant-design/icons';
import { Layout, Menu } from 'antd';

const { Header, Content, Footer, Sider } = Layout;
const items = [
  UserOutlined,
  VideoCameraOutlined,
  UploadOutlined,
  BarChartOutlined,
  CloudOutlined,
  AppstoreOutlined,
  TeamOutlined,
  ShopOutlined,
].map((icon, index) => ({
  key: String(index + 1),
  icon: React.createElement(icon),
  label: `nav ${index + 1}`,
}));

function MarketPage() {
  return (
    <div className={styles.area}>
      <h1>마켓상세페이지</h1>
      <div className={styles.content}>
        <div className={styles.column_bind} >
          <div className={styles.column_is_fixed}>
            1
          </div>
          <div className={styles.column}>2</div>
        </div>
      </div>
      <div className={styles.leaflet_area}>3</div>
      <div className={styles.relate_area}>4</div>
      
      <Content
        style={{
          margin: '24px 16px 0',
          overflow: 'initial',
        }}
      >
        <div
          className="site-layout-background"
          style={{
            padding: 24,
            textAlign: 'center',
          }}
        >
          <p>long content</p>
          {
            // indicates very long content
            Array.from(
              {
                length: 100,
              },
              (_, index) => (
                <React.Fragment key={index}>
                  {index % 20 === 0 && index ? 'more' : '...'}
                  <br />
                </React.Fragment>
              ),
            )
          }
        </div>
      </Content>

    </div>
  );
}

export default MarketPage;
