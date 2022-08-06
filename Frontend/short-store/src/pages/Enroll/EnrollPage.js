import styles from "./EnrollPage.module.css";
import BoothEnroll from "../../components/BoothEnroll";

function EnrollPage() {
  return (
    <div className={styles.area}>
      <h1>등록페이지 입니다</h1>
      <BoothEnroll />
    </div>
  );
}

export default EnrollPage;
