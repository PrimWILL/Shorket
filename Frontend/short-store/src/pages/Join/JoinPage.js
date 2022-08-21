import React, { Fragment, useState } from "react";
import styles from "./Join.module.css";
import { Typography, Menu } from "antd";
import {
  Avatar,
  Button,
  CssBaseline,
  TextField,
  FormControl,
  FormControlLabel,
  Checkbox,
  FormHelperText,
  Grid,
  Box,
  // Typography,
  Container
} from "@mui/material/";
import { createTheme, ThemeProvider } from "@mui/material/styles";
const { Title } = Typography;

function Join(props) {
  const theme = createTheme();
  const [checked, setChecked] = useState(false);
  const [id, setId] = useState("");
  const [password, setPassword] = useState("");

  const handlePassword = (e) => {
    setPassword(e.target.password);
  };
  const handleId = (e) => {
    setId(e.target.id);
  };
  // 동의 체크
  const handleAgree = (e) => {
    setChecked(e.target.checked);
  };

  // form 전송
  const handleSubmit = (e) => {
    e.preventDefault();
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
            alignItems: "center"
          }}>
          <Avatar sx={{ m: 1, bgcolor: "secondary.main" }} />
          <Typography component="h1" variant="h5">
            회원가입
          </Typography>
          <Box
            component="form"
            noValidate
            onSubmit={handleSubmit}
            sx={{ mt: 3 }}>
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
                  />
                </Grid>
                <Grid item xs={12}>
                  <TextField
                    required
                    fullWidth
                    type="password"
                    id="password"
                    name="password"
                    label="비밀번호 (숫자+영문자+특수문자 8자리 이상)"
                  />
                </Grid>
                <Grid item xs={12}>
                  <FormControlLabel
                    control={
                      <Checkbox value="allowExtraEmails" color="primary" />
                    }
                    label="[필수] 만 14세 이상이며 모두 동의합니다."
                  />
                  <FormControlLabel
                    control={
                      <Checkbox value="allowExtraEmails" color="primary" />
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
                size="large">
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
