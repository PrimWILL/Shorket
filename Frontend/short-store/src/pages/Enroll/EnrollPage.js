import styles from "./EnrollPage.module.css";
import BoothEnroll from "pages/Enroll/BoothEnroll";
import { PageHeader } from "antd";
import React from "react";

function EnrollPage() {
  return (
    <div className={styles.layout}>
      <div className={styles.area}>
        {/* <PageHeader
          className="site-page-header"
          onBack={() => null}
          title="Title"
          subTitle="This is a subtitle"
        /> */}
        <h1>부스 등록</h1>
      </div>
      <div className={styles.form}>
        <BoothEnroll />
      </div>
    </div>
  );
}

export default EnrollPage;
