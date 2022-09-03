import React, { useState, useEffect, useContext } from "react";
import AuthContext from '../../context/AuthProvider';
import axios from "../../api/axios";
import { Link as RouterLink } from "react-router-dom";
import { Cookies } from 'react-cookie';

import {
  Container,
  Box,
  Avatar,
  Button,
  TextField,
  Link,
  CssBaseline,
  StepContext,
} from "@mui/material";

import { createTheme, ThemeProvider } from "@mui/material/styles";
import LockOutlinedIcon from "@mui/icons-material/LockOutlined";
import "antd/dist/antd.css";
import { Typography, Divider } from "antd";

const LOGIN_URL = '/users/login';
const cookies = new Cookies();

const { Title } = Typography;

const theme = createTheme();

function Login() {

  const {auth, setAuth} = useContext(AuthContext);

  const [user, setUser] = useState('');
  const [pwd, setPwd] = useState('');
  const [errMsg, setErrMsg] = useState('');
  const [success, setSuccess] = useState(false);

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
      console.log("모든 칸을 작성하세요");
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

  // 서버 요청 
  const handleSubmit = (e) => {
    e.preventDefault();

    if (!isValidAll) {
      console.log("넌 아직 준비가 안됬다!");
      return;
    }

    console.log("login 시도");

    axios
      .post(LOGIN_URL, {
        email: state.email,
        password: state.password,
      })
      .then(function (res) {
        console.log(res);
        setCookie('id', res.data.token);// 쿠키에 토큰 저장
      })
      .catch(function (error) {
        console.log(error);
      });
    
    // const TryLogin = async () => {

    // }
    // try {
    //   const response = axios.post(LOGIN_URL, 
    //     JSON.stringify(state), 
    //     {
    //       headers: {'Content-Type' : 'application/json'},
    //       withCredentials: true
    //     }
    //   )
    //   console.log(response);
    //   console.log(JSON.stringify(response?.data));
    //   const accessToken = response?.data?.accessToken;
    //   // const roles = response?.data?.roles;
    //   // setAuth({state, accessToken, roles});
    //   console.log(accessToken);
    //   setAuth({state, accessToken});
    //   console.log(auth);
    // }
    // catch(err) {
    //   // console.log(err);
    //   if (!err?.response) {
    //     setErrMsg('No Server Response');
    //   } else if (err.response?.status === 400) {
    //       setErrMsg('Missing Username or Password');
    //   } else if (err.response?.status === 401) {
    //       setErrMsg('Unauthorized');
    //   } else if (err.response?.status === 500) {
    //     setErrMsg('아이디 또는 비밀번호가 일치하지 않습니다');
    //   } else {
    //       setErrMsg('Login Failed');
    //   }

    // }

    // axios
    //   .post(LOGIN_URL, {
    //     email: state.email,
    //     password: state.password,
    //   })
    //   .then(function (res) {
    //     console.log(res);
    //     setCookie('id', res.data.token);// 쿠키에 토큰 저장
    //   })
    //   .catch(function (error) {
    //     console.log(error);
    //   });
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
              <RouterLink to="/join">이메일 가입</RouterLink>
              <Divider type="vertical" />
              <RouterLink to="/join">이메일 찾기</RouterLink>
              <Divider type="vertical" />
              <RouterLink to="/findPw">비밀번호 찾기</RouterLink>
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
