import React, { useState, useEffect } from "react";
import axios from "axios";

import { Link as RouterLink } from "react-router-dom";

import {
  Container,
  Box,
  Avatar,
  Button,
  TextField,
  Link,
  CssBaseline,
} from "@mui/material";

import { createTheme, ThemeProvider } from "@mui/material/styles";
import LockOutlinedIcon from "@mui/icons-material/LockOutlined";
import "antd/dist/antd.css";
import { Typography, Divider } from "antd";
const { Title } = Typography;

const theme = createTheme();

function Login() {
  const [state, setState] = useState({
    email: "",
    password: "",
  });

  const [isValidEmail, setIsvalidEmail] = useState(false);
  const [isValidPassword, setIsvalidPassword] = useState(false);
  const [isValidAll, setIsvalidAll] = useState(false);

  useEffect(() => {
    checkValidEmail();
    checkValidPassword();
    checkValidTotal();
  }, [state]);

  useEffect(() => {
    checkValidTotal();
  }, [isValidEmail, isValidPassword]);

  // 전체 유효성 검사
  const checkValidTotal = () => {
    if (state.email === "" || state.password === "") {
      console.log("모든 칸을 작성해야함");
      setIsvalidAll(false);
      return;
    }
    if (isValidEmail && isValidPassword) {
      console.log("!!");

      setIsvalidAll(true);
      return;
    }
    setIsvalidAll(false);
  };

  //비밀번호 유효성 검사
  const checkValidPassword = () => {
    //  8 ~ 16자 영문, 숫자 조합
    var regExp = /^(?=.*[a-zA-Z])((?=.*\d)(?=.*\W)).{8,16}$/;
    // 형식에 맞는 경우 true 리턴
    // console.log("비밀번호 유효성 검사 :: ", regExp.test(state.password));
    setIsvalidPassword(regExp.test(state.password));
  };

  // 이메일 유효성 검사
  const checkValidEmail = () => {
    var regExp =
      /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
    // 형식에 맞는 경우 true 리턴
    // console.log("이메일 유효성 검사 :: ", regExp.test(state.email));
    setIsvalidEmail(regExp.test(state.email));
  };

  //state 체크
  const handleChangeState = (e) => {
    setState({
      ...state,
      [e.target.name]: e.target.value,
    });
  };

  // const handleSubmit = (event) => {
  //   event.preventDefault();
  //   const data = new FormData(event.currentTarget);
  //   console.log({
  //     email: data.get("email"),
  //     password: data.get("password"),
  //   });
  // };

  const handleSubmit = (e) => {
    e.preventDefault();

    if (!isValidAll) {
      console.log("!!!");
      return;
    }
    console.log("login 시도");
    axios
      .post("http://52.79.146.185:8080/api/users/login", {
        email: state.email,
        password: state.password,
      })
      .then(function (res) {
        console.log(res);
      })
      .catch(function (error) {
        console.log(error);
      });
  };

  return (
    <ThemeProvider theme={theme}>
      <Container component="main" maxWidth="xs">
        <CssBaseline />
        <Box
          sx={{
            marginTop: 20,
            display: "flex",
            flexDirection: "column",
            alignItems: "center",
          }}
        >
          <Title level={2}>로그인</Title>
          <Avatar sx={{ m: 1, bgcolor: "secondary.main" }}>
            <LockOutlinedIcon />
          </Avatar>
          <Box
            component="form"
            onSubmit={handleSubmit}
            noValidate
            sx={{ mt: 1 }}
          >
            <TextField
              required
              autoFocus
              fullWidth
              type="email"
              id="email"
              name="email"
              label="이메일 주소"
              autoComplete="email"
              error={!isValidEmail && state.email != ""}
              helperText={
                isValidEmail || state.email == ""
                  ? ""
                  : "이메일 주소를 정확히 입력해주세요."
              }
              value={state.email}
              onChange={handleChangeState}
            />
            <TextField
              margin="normal"
              required
              fullWidth
              type="password"
              id="password"
              name="password"
              label="비밀번호"
              autoComplete="current-password"
              placeholder="영문, 숫자, 특수문자 조합 8-16자"
              error={!isValidPassword && state.password != ""}
              helperText={
                isValidPassword || state.password == ""
                  ? ""
                  : "영문, 숫자, 특수문자를 조합하여 입력해주세요. (8-16자)"
              }
              value={state.password}
              onChange={handleChangeState}
              inputProps={{
                maxLength: 16,
              }}
            />
            <Button
              type="submit"
              fullWidth
              variant="contained"
              sx={{ mt: 3, mb: 2 }}
              size="large"
              disabled={!isValidAll}
              onClick={handleSubmit}
            >
              로그인
            </Button>
            <div>
              <Link href="#" variant="body2">
                <RouterLink to="/join">이메일 가입</RouterLink>
              </Link>
              <Divider type="vertical" />
              <Link href="#" variant="body2">
                <RouterLink to="/join">이메일 찾기</RouterLink>
              </Link>
              <Divider type="vertical" />
              <Link href="#" variant="body2">
                <RouterLink to="/findPw">비밀번호 찾기</RouterLink>
              </Link>
            </div>
            <Button
              type="submit"
              fullWidth
              variant="outlined"
              size="large"
              sx={{ mt: 3, mb: 2 }}
              onClick={handleSubmit}
            >
              소셜 로그인
            </Button>
          </Box>
        </Box>
      </Container>
    </ThemeProvider>
  );
}

export default Login;
