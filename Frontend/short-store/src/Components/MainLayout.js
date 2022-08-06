// import React, { useEffect } from "react";
// import Toast from "antd-mobile/lib/toast";

// import { ToastHandler } from "antd-mobile/es/components/toast";
import Header from "./Header";
import Footer from "./Footer";

// 넘겨주는 컴포넌트에 header, footer 값이 있으면 header, footer를 렌더합니다.
const MainLayout = ({ header, footer, children, loading }) => {
  // useEffect(() => (loading ? Toast.loading("Loading", 50000) : Toast.hide()), [
  //   loading
  // ]);

  return (
    <div>
      {header && (
        <Header
          title={header.title}
          backFunc={header.backFunc}
          params={header.params}
          noBackBtn={header.noBackBtn}
        />
      )}
      {/** children 이라는 값은 react에서 넘겨주는 값으로 vue의 slot과 비슷한 기능을 합니다. */}
      {children}
      {footer && <Footer />}
    </div>
  );
};

export default MainLayout;
