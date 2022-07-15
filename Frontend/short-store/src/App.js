import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
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
          <Route path="/my" element={<div>마이페이지</div>}></Route>
          <Route path="/my/wish" element={<div>관심마켓</div>}></Route>
          <Route path="/market/:id" element={<div>마켓상세페이지</div>}></Route>
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
          <Route
            path="/market/enroll"
            element={<div>마켓 신청 페이지</div>}
          ></Route>
          <Route
            path="/market/upcomming"
            element={<div>예정인 마켓 페이지</div>}
          ></Route>
          <Route
            path="/market/inProgress"
            element={<div>진행중인 마켓 페이지</div>}
          ></Route>
          <Route
            path="/market/manage"
            element={<div>마켓 관리 페이지</div>}
          ></Route>
          <Route path="*" element={<div>없는페이지요</div>} />
        </Routes>
      </>
    </div>
  );
}
