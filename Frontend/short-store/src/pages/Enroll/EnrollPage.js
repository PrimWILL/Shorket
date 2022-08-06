import styles from "./EnrollPage.module.css";
import BoothEnroll from "pages/BoothEnroll";
import AddProduct from "pages/AddProduct";
import Signup from "pages/Signup";

function EnrollPage() {
  return (
    <div className={styles.area}>
      <h1>등록페이지 입니다</h1>
      <BoothEnroll />
    </div>
  );
}

export default EnrollPage;
