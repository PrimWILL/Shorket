import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

import { Typography, Menu } from "antd";
import {
  Avatar,
  Button,
  CssBaseline,
  TextField,
  FormControl,
  FormControlLabel,
  Checkbox,
  Grid,
  Box,
  Container,
  useIsFocusVisible,
} from "@mui/material/";
import { createTheme, ThemeProvider } from "@mui/material/styles";

function Join(props) {
  const theme = createTheme();

  const [state, setState] = useState({
    userName: "",
    userId: "",
    email: "",
    password: "",
  });

  const [checked, setChecked] = useState({
    check1: false,
    check2: false,
  });

  const [isValidEmail, setIsvalidEmail] = useState(false);
  const [isValidPassword, setIsvalidPassword] = useState(false);
  const [isValidChecked, setIsvalidChecked] = useState(false);
  const [isValidAll, setIsvalidAll] = useState(false);

  useEffect(() => {
    // state 바뀌고 유효성 체크하는게 순서가 맞다 (handleChange 에서 유효성체크하면 적용이 바로 안됨)
    checkValidEmail();
    checkValidPassword();
    checkValidChecked();
    checkValidTotal();
  }, [state, checked]);

  useEffect(() => {
    checkValidTotal(); // 이게 제일 힘들었다... 여기를 한번 더 체크해야함. (변화 감지 부분이 어려움)
  }, [isValidEmail, isValidPassword, isValidChecked]);
  // 전체 유효성 검사
  const checkValidTotal = () => {
    if (
      state.email === "" ||
      state.password === "" ||
      state.userId === "" ||
      state.userName === ""
    ) {
      console.log("모든 칸을 작성해야함");
      setIsvalidAll(false);
      return;
    }
    if (isValidEmail && isValidPassword && isValidChecked) {
      console.log("!!");

      setIsvalidAll(true);
      return;
    }
    setIsvalidAll(false);
  };

  const checkValidChecked = () => {
    if (checked.check1 === true && checked.check2 === true) {
      console.log("킹아");
      setIsvalidChecked(true);
    } else {
      console.log("모시깽이");
      setIsvalidChecked(false);
    }
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

  //동의 체크
  const handleAgree = (e) => {
    setChecked({
      ...checked,
      [e.target.name]: e.target.checked,
    });
  };

  // form 전송
  const handleSubmit = (e) => {
    e.preventDefault();
  };

  const PostClick = () => {
    if (!isValidAll) {
      console.log("!!!");
      return;
    }
    axios
      .post("http://52.79.146.185:8080/api/users/post", {
        email: state.email,
        loginType: "A",
        name: state.userName,
        nickName: state.userId,
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
            marginTop: 13,
            display: "flex",
            flexDirection: "column",
            alignItems: "center",
          }}
        >
          <Avatar sx={{ m: 1, bgcolor: "secondary.main" }} />
          <Typography component="h1" variant="h5">
            회원가입
          </Typography>
          <Box
            component="form"
            noValidate
            onSubmit={handleSubmit}
            sx={{ mt: 3 }}
          >
            <FormControl component="fieldset" variant="standard">
              <Grid container spacing={2}>
                <Grid item xs={12}>
                  <TextField
                    required
                    autoFocus
                    fullWidth
                    type="email"
                    id="email"
                    name="email"
                    label="이메일 주소"
                    error={!isValidEmail && state.email != ""}
                    helperText={
                      isValidEmail || state.email == ""
                        ? ""
                        : "이메일 주소를 정확히 입력해주세요."
                    }
                    value={state.email}
                    onChange={handleChangeState}
                  />
                </Grid>
                <Grid item xs={12}>
                  <TextField
                    required
                    fullWidth
                    type="password"
                    id="password"
                    name="password"
                    label="비밀번호"
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
                </Grid>
                <Grid item xs={12}>
                  <TextField
                    required
                    fullWidth
                    id="userName"
                    name="userName"
                    label="이름"
                    value={state.userName}
                    onChange={handleChangeState}
                  />
                </Grid>
                <Grid item xs={12}>
                  <TextField
                    required
                    fullWidth
                    id="userId"
                    name="userId"
                    label="아이디"
                    value={state.userId}
                    onChange={handleChangeState}
                  />
                </Grid>
                <Grid
                  item
                  xs={12}
                  className="join_terms"
                  style={{
                    display: "flex",
                    flexDirection: "column",
                  }}
                >
                  <FormControlLabel
                    control={
                      <Checkbox
                        name="check1"
                        color="primary"
                        checked={checked.check1}
                        onChange={handleAgree}
                      />
                    }
                    label="[필수] 만 14세 이상이며 모두 동의합니다."
                  />
                  <FormControlLabel
                    control={
                      <Checkbox
                        name="check2"
                        color="primary"
                        checked={checked.check2}
                        onChange={handleAgree}
                      />
                    }
                    label="[선택] 광고성 정보 수신에 모두 동의합니다."
                  />
                </Grid>
              </Grid>
              <Button
                type="submit"
                fullWidth
                variant="contained"
                sx={{ mt: 3, mb: 2 }}
                size="large"
                onClick={PostClick}
                disabled={!isValidAll}
              >
                회원가입
              </Button>
            </FormControl>
          </Box>
        </Box>
      </Container>
    </ThemeProvider>
  );
}

export default Join;
