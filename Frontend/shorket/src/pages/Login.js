import React, { useState, useEffect } from "react";
import { Link as RouterLink, useNavigate, useLocation } from "react-router-dom";

import axios from "../api/axios";
import useAuth from "../hooks/useAuth";

// import { Cookies } from "react-cookie";

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

const LOGIN_URL = "/users/login";
// const cookies = new Cookies();

const { Title } = Typography;
const theme = createTheme();

function Login() {
    const { setAuth } = useAuth();

    const navigate = useNavigate();
    const location = useLocation();
    const from = location.state?.from?.passname || "/";

    const [userEmail, setUserEmail] = useState("");
    const [isValidEmail, setIsvalidEmail] = useState(false);
    const [userPassword, setUserPassword] = useState("");
    const [isValidPassword, setIsvalidPassword] = useState(false);

    const [isValidAll, setIsvalidAll] = useState(false);
    const [loading, setLoading] = useState(false);

    // 유효성 검사하기
    useEffect(() => {
        checkValidEmail(userEmail);
    }, [userEmail]);

    useEffect(() => {
        checkValidPassword(userPassword);
    }, [userPassword]);

    useEffect(() => {
        checkValidTotal();
    }, [isValidEmail, isValidPassword]);

    // 전체 유효성 검사
    const checkValidTotal = () => {
        console.log("check total");

        if (userEmail === "" || userPassword === "") {
            console.log("모든 칸을 작성하세요");
            setIsvalidAll(false);
            return;
        }
        if (isValidEmail && isValidPassword) {
            console.log("모든 조건 달성");
            setIsvalidAll(true);
            return;
        }
        setIsvalidAll(false);
    };

    //비밀번호 유효성 검사
    const checkValidPassword = (pwd) => {
        //  8 ~ 16자 영문, 숫자 조합
        var regExp = /^(?=.*[a-zA-Z])((?=.*\d)(?=.*\W)).{8,16}$/;
        // 형식에 맞는 경우 true 리턴
        // console.log("비밀번호 유효성 검사 :: ", regExp.test(pwd));
        setIsvalidPassword(regExp.test(pwd));
    };

    // 이메일 유효성 검사
    const checkValidEmail = (email) => {
        var regExp =
            /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
        // 형식에 맞는 경우 true 리턴
        // console.log("이메일 유효성 검사 :: ", regExp.test(email));
        setIsvalidEmail(regExp.test(email));
    };

    //state 체크
    const handleChangeEmail = (e) => {
        setUserEmail(e.target.value);
    };
    const handleChangePassword = (e) => {
        setUserPassword(e.target.value);
    };

    // 서버 로그인 요청
    const handleSubmit = async (e) => {
        e.preventDefault();

        if (!isValidAll) {
            console.log("넌 아직 준비가 안됬다!");
            return;
        }

        console.log("login 요청중");

        try {
            const response = await axios.post(
                LOGIN_URL,
                {
                    email: userEmail,
                    password: userPassword,
                },
                {
                    headers: { "Content-Type": "application/json" },
                    withCredentials: true,
                }
            );
            console.log(response?.data);
            // console.log(response);
            const accessToken = response?.data?.userToken.accessToken;
            const refreshToken = response?.data?.userToken.refreshToken;
            const role = response?.data?.roleType;
            setAuth({
                userEmail,
                userPassword,
                accessToken,
                refreshToken,
                role,
            });
            setUserEmail("");
            setUserPassword("");
            navigate(from, { replace: true });
        } catch (err) {
            console.log(err?.response);
            if (!err?.response) {
                alert("No Server Response");
            } else if (err.response?.status === 400) {
                alert("Missing Username or Password");
            } else if (err.response?.status === 401) {
                alert("Unauthorized");
            } else if (err.response?.status === 500) {
                alert(err.response?.data?.message);
            } else {
                alert("Login Failed");
            }
        }

        // requestPost();
        // console.log(auth);

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
                            error={!isValidEmail && userEmail != ""}
                            helperText={
                                isValidEmail || userEmail == ""
                                    ? ""
                                    : "이메일 주소를 정확히 입력해주세요."
                            }
                            value={userEmail}
                            onChange={handleChangeEmail}
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
                            error={!isValidPassword && userPassword != ""}
                            helperText={
                                isValidPassword || userPassword == ""
                                    ? ""
                                    : "영문, 숫자, 특수문자를 조합하여 입력해주세요. (8-16자)"
                            }
                            value={userPassword}
                            onChange={handleChangePassword}
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
                            disabled={!isValidAll || loading}
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
