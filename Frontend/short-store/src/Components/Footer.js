import * as React from "react";
import CssBaseline from "@mui/material/CssBaseline";
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import { createTheme, ThemeProvider } from "@mui/material/styles";

const theme = createTheme();
function Footer() {
  return (
    <ThemeProvider theme={theme}>
      <CssBaseline />

      {/* Footer */}
      <Box
        style={{ borderTop: "solid #eeeeee" }}
        sx={{ bgcolor: "background.paper", p: 6 }}
        component="footer"
      >
        <Typography variant="h6" align="center" gutterBottom>
          Footer
        </Typography>
        <Typography
          variant="subtitle1"
          align="center"
          color="text.secondary"
          component="p"
        >
          SHORKET ©2022 Created by SolMiSolx3
        </Typography>
      </Box>
      {/* End footer */}
    </ThemeProvider>
  );
}

export default Footer;
