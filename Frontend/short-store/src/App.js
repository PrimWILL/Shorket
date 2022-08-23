import React from 'react';
import { Routes, Route } from "react-router-dom";
import "./styles.css";

import MainLayout from "./components/MainLayout";

import HomePage from "./pages/Home/HomePage";

import LoginPage from "./pages/Login/LoginPage";
import JoinPage from "./pages/Join/JoinPage";
import RegisterPage from "./pages/Join/RegisterPage";
import Mypage from "./pages/My/MyPage";
import SearchPage from "./pages/Search/SearchPage";
import SearchListPage from "./pages/Search/SearchListPage";
import BoothEnrollPage from "./pages/Enroll/BoothEnrollPage";
import MarketEnrollPage from "./pages/Enroll/MarketEnrollPage";
import MarketPage from "./pages/Market/MarketPage";
import ManagePage from "./pages/Manage/ManagePage";

export default function App() {
  return (
    <div className="App">
      <MainLayout>
        <Routes>
          <Route path="/" element={<HomePage />} exact={true}></Route>
          <Route path="/login" element={<LoginPage />}></Route>
          <Route path="/join" element={<JoinPage />}></Route>
          <Route path="/market/:id" element={<MarketPage />}></Route>

          <Route path="/my/*" element={<Mypage />}>

          </Route>

          <Route path="/search" element={<SearchPage />}></Route>
          <Route path="/searchList" element={<SearchListPage />}></Route>

          <Route path="/market/:id/manage" element={<ManagePage />}></Route>

          <Route
            path="/enroll/market"
            element={<MarketEnrollPage />}></Route>
          <Route
            path="/enroll/booth"
            element={<BoothEnrollPage />}></Route>
          <Route
            path="/market/:id/booth/:id"
            element={<div>부스상세 페이지</div>}></Route>
          <Route path="*" element={<div>없는페이지요</div>} />
        </Routes>
      </MainLayout>
    </div>
  );
}
