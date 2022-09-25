import React from "react";
import { Link } from "react-router-dom";

export const Sidebar = () => {
    return (
        <div className="snb_area">
            <Link to="/my">
                <h2 className="snb_main_title">마이페이지</h2>
            </Link>
            <nav className="snb">
                <div className="snb_list">
                    <strong className="snb_title">기본 사용자</strong>
                    <ul className="snb_menu">
                        <li className="snb_item">
                            <Link to="profile" className="menu_link">
                                프로필 정보
                            </Link>
                        </li>
                        <li className="snb_item">
                            <Link to="wishMarket" className="menu_link">
                                관심 마켓
                            </Link>
                        </li>
                        <li className="snb_item">
                            <Link to="wishBooth" className="menu_link">
                                관심 부스
                            </Link>
                        </li>
                    </ul>
                </div>
                <div className="snb_list">
                    <strong className="snb_title">마켓 관계자</strong>
                    <ul className="snb_menu">
                        <li className="snb_item">
                            <li className="snb_item">
                                <Link
                                    to="/market/:id/manage"
                                    className="menu_link"
                                >
                                    마켓 관리하기
                                </Link>
                            </li>
                        </li>
                        <li className="snb_item">
                            <li className="snb_item">
                                <Link
                                    to="/my/enroll/booth"
                                    className="menu_link"
                                >
                                    신청한 부스
                                </Link>
                            </li>
                        </li>
                    </ul>
                </div>
            </nav>
        </div>
    );
};

export default Sidebar;
