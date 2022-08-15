<<<<<<< HEAD

// import BoothEnroll from "pages/BoothEnroll";
// import AddProduct from "pages/AddProduct";
// import Signup from "pages/Signup";
=======
import styles from "./EnrollPage.module.css";
import BoothEnroll from "pages/Enroll/BoothEnroll";
import { PageHeader } from "antd";
import React from "react";
>>>>>>> 4da6f23e51d1a79ee596d4d8e086ea7695ee49bb


function EnrollPage() {
  return (
<<<<<<< HEAD
    <div className="area">
      <h1>등록페이지 입니다</h1>
      {/* <BoothEnroll /> */}
=======
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
>>>>>>> 4da6f23e51d1a79ee596d4d8e086ea7695ee49bb
    </div>
  );
}

export default EnrollPage;
