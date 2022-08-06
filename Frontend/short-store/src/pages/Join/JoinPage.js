import React, { Fragment } from "react";
import styles from "./Join.module.css";
import TextField from "@mui/material/TextField";
import { Typography, Menu } from "antd";
const { Title } = Typography;
function Join(props) {
  return (
    <div className={styles.joun_area}>
      <Title className={styles.header_title}  level={2}>회원가입</Title>

      <TextField
        margin="normal"
        required
        id="email"
        label="Email Address"
        name="email"
        autoComplete="email"
        autoFocus
        className={styles.TextField}
      />
      <TextField
        margin="normal"
        required
        fullWidth
        name="password"
        label="Password"
        type="password"
        id="password"
        autoComplete="current-password"
        className={styles.TextField}
      />
    </div>
    // <>
    //
    //   <Grid padding="16px">
    //
    //     <Text size="32px" bold>
    //       회원가입
    //     </Text>
    //     <Grid padding="16px 0px">
    //       <Input
    //         label="이름"
    //         placeholder="이름을 입력해주세요!"
    //         _OnChange={() => {
    //           console.log("이름 입력완료!");
    //         }}
    //       />
    //     </Grid>
    //     <Grid padding="16px 0px">
    //       <Input
    //         label="소속"
    //         placeholder="소속을 입력해주세요!"
    //         _OnChange={() => {
    //           console.log("소속 입력완료!");
    //         }}
    //       />
    //     </Grid>
    //     <Grid padding="16px 0px">
    //       <Input
    //         label="비밀번호"
    //         placeholder="비밀번호를 입력해주세요!"
    //         _OnChange={() => {
    //           console.log("비밀번호 확인완료!");
    //         }}
    //       />
    //     </Grid>
    //     <Grid padding="16px 0px">
    //       <Input
    //         label="email"
    //         placeholder="email을 입력해주세요!"
    //         _OnChange={() => {
    //           console.log("email 확인완료!");
    //         }}
    //       />
    //     </Grid>
    //     <Grid padding="16px 0px">
    //       <Input
    //         label="전화번호"
    //         placeholder="전화번호를 입력해주세요!"
    //         _OnChange={() => {
    //           console.log("비밀번호 확인완료!");
    //         }}
    //       />
    //     </Grid>
    //     <Button
    //       text="회원가입하기"
    //       _OnClick={() => {
    //         console.log("회원가입 완료");
    //       }}
    //     ></Button>
    //   </Grid>
    // </>
  );
}
export default Join;
