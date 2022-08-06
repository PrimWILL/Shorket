import * as React from "react";
import { Link as RouterLink } from "react-router-dom";

import {
  Container,
  Box,
  Avatar,
  Button,
  TextField,
  FormControlLabel,
  Checkbox,
  Link,
  Grid,
  CssBaseline
} from "@mui/material";

import { createTheme, ThemeProvider } from "@mui/material/styles";
import LockOutlinedIcon from "@mui/icons-material/LockOutlined";
import "antd/dist/antd.css";
import { Typography, Menu, Divider } from "antd";
const { Title } = Typography;

const theme = createTheme();

function Login() {
  const handleSubmit = (event) => {
    event.preventDefault();
    const data = new FormData(event.currentTarget);
    console.log({
      email: data.get("email"),
      password: data.get("password")
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
            alignItems: "center"
          }}>
          <Title level={2}>로그인</Title>
          <Avatar sx={{ m: 1, bgcolor: "secondary.main" }}>
            <LockOutlinedIcon />
          </Avatar>
          <Box
            component="form"
            onSubmit={handleSubmit}
            noValidate
            sx={{ mt: 1 }}>
            <TextField
              margin="normal"
              required
              fullWidth
              id="email"
              label="이메일 주소"
              name="email"
              autoComplete="email"
              autoFocus
            />
            <TextField
              margin="normal"
              required
              fullWidth
              id="password"
              label="비밀번호"
              name="password"
              type="password"
              autoComplete="current-password"
            />

            <Button
              type="submit"
              fullWidth
              variant="contained"
              sx={{ mt: 3, mb: 2 }}>
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
              sx={{ mt: 3, mb: 2 }}>
              소셜 로그인
            </Button>
          </Box>
        </Box>
      </Container>
    </ThemeProvider>
  );
}

export default Login;
