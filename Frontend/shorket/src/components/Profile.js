import React, { useEffect } from "react";

import {
    List,
    ListItem,
    ListItemAvatar,
    ListItemText,
    Avatar,
    BeachAccessIcon,
    Divider,
    Typography,
} from "@mui/material";
import { deepOrange, deepPurple } from "@mui/material/colors";

import PersonIcon from "@mui/icons-material/Person";
import { Button } from "antd";

const ROLE = {
    I: "개인",
    A: "관리자",
    S: "판매자",
};

function Profile({ userData }) {
    useEffect(() => {
        console.log(userData);
    }, []);
    const btnStyle = {
        padding: "2rem 2rem",
        // border: "1px solid",
        borderRadius: ".25rem",
    };

    const btnStyle2 = {
        // fontSize: "25px",
        // padding: "2rem 2rem",
        margin: "1.2rem",
        textAlign: "left",
    };

    const btnStyle3 = {
        // fontSize: "25px",
        // padding: "2rem 2rem",
        margin: "1.2rem",
        textAlign: "center",
        color: "red",
    };
    return (
        <section className="profile">
            <h1>프로필 정보</h1>
            {/* <Divider /> */}
            <div className="profile_card">
                <Avatar
                    sx={{
                        bgcolor: deepOrange[500],
                        width: 80,
                        height: 80,
                        fontSize: 30,
                    }}
                >
                    {userData?.nickName?.slice(0, 1).toUpperCase()}
                </Avatar>
                <div className="detail_box">
                    <div>{userData?.nickName}</div>
                    <div>{userData?.email}</div>
                </div>
                <h3>{ROLE[userData?.userRole]}</h3>
                <Button>프로필 수정</Button>
            </div>

            {/* <Avatar>
                        <PersonIcon />
                    </Avatar> */}
        </section>
    );
}

export default Profile;
