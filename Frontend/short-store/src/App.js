import { Routes, Route } from "react-router-dom";
import Header from "./Components/Header";
import Home from "./Pages/Home";
import Login from "./Pages/Login";
import Join from "./Pages/Join";

import "./styles.css";

export default function App() {
  return (
    <div className="App">
      <Header></Header>

      <>
        <Routes>
          <Route path="/" exact={true} element={<Home />}></Route>
          <Route path="/notice" element={<div>고객센터</div>}></Route>
          <Route path="/login" element={<Login />}></Route>
          <Route
            path="/register"
            element={
              <div>
                회원가입 페이지
                <Join />
              </div>
            }
          ></Route>
          <Route path="/my" element={<div>마이페이지</div>}></Route>
          <Route path="/my/wish" element={<div>관심마켓&부스</div>}></Route>
          <Route
            path="/my/markets"
            element={<div>내가 운영중인 마켓목록 페이지</div>}
          ></Route>
          <Route path="/markets" element={<div>마켓목록페이지</div>}></Route>
          <Route
            path="/markets/upcomming"
            element={<div>예정인마켓페이지</div>}
          ></Route>
          <Route
            path="/markets/inProgress"
            element={<div>진행중인마켓페이지</div>}
          ></Route>
          <Route
            path="/markets/end"
            element={<div>종료된마켓페이지</div>}
          ></Route>
          <Route path="/market/:id" element={<div>마켓상세페이지</div>}></Route>
          <Route
            path="/market/enroll"
            element={<div>마켓신청 페이지(폼)</div>}
          ></Route>
          <Route
            path="/market/:id/manage"
            element={<div>마켓관리 페이지</div>}
          ></Route>
          <Route
            path="/market/:id/booth/enroll"
            element={<div>부스신청 페이지</div>}
          ></Route>
          <Route
            path="/market/:id/booth/:id"
            element={<div>부스상세 페이지</div>}
          ></Route>
          <Route path="*" element={<div>없는페이지요</div>} />
        </Routes>
      </>
    </div>
  );
}
