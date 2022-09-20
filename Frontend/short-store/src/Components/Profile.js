import React from "react";
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import ListItemAvatar from '@mui/material/ListItemAvatar';
import ListItemText from '@mui/material/ListItemText';
import Avatar from '@mui/material/Avatar';
import BeachAccessIcon from '@mui/icons-material/BeachAccess';
import Divider from '@mui/material/Divider';
import Typography from '@mui/material/Typography';
import PersonIcon from '@mui/icons-material/Person';
import { fontSize, padding } from "@mui/system";
import { Button } from 'antd';


function Profile() {

  const btnStyle = {
    padding: "2rem 2rem",
    // border: "1px solid",
    borderRadius: ".25rem",
  };

  const btnStyle2 = {
    // fontSize: "25px",
    // padding: "2rem 2rem",
    margin: "1.2rem",
    textAlign: 'left',
  }

  const btnStyle3 = {
    // fontSize: "25px",
    // padding: "2rem 2rem",
    margin: "1.2rem",
    textAlign: 'center',
    color: "red",
  }
  return (
    <div>
      <h1 style={btnStyle2}>프로필 정보</h1>
      <Divider />
      <ListItem style={btnStyle}>
        <ListItemAvatar>
          <Avatar>
            <PersonIcon />
          </Avatar>
        </ListItemAvatar>
        <ListItemText primary="닉네임" secondary="pobi555@naver.com" />
        <h3 style={btnStyle3}>관리자</h3>
        <Button>프로필 변경</Button>
      </ListItem>
      <Divider />
    </div >
  );
}

export default Profile;
