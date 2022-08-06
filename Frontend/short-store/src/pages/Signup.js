// import { Button, Form, Input } from "antd";
// import { LockOutlined, MailOutlined, UserOutlined } from "@ant-design/icons";
// import { Link, Route } from "react-router-dom";
// // import { addUser } from "../../api/User";
// import { useEffect, useState } from "react";
// import { Redirect } from "react-router";

// const Signup = () => {
//   const [form] = Form.useForm();
//   const [isValid, setIsValid] = useState(false);
//   const [error, setError] = useState("");
//   const [isSuccess, setIsSuccess] = useState(false);

//   useEffect(() => {
//     if (isValid) {
//       form.setFields([
//         {
//           name: "email",
//           errors: [error]
//         }
//       ]);
//     }
//   }, [isValid, error]);

//   function onFinish(values) {
//     setIsValid(false);
//     setError("");
//     addUser(values).then((response) => {
//       if (response.status === 400) {
//         setIsValid(true);
//         setError(response.message);
//       }
//       if (response.status === 200) {
//         alert("등록성공");
//         console.log("등록성공.");
//         setIsSuccess(true);
//       }
//     });
//   }
//   return (
//     <>
//       <Form
//         form={form}
//         name={"normal_login"}
//         className={"login-form"}
//         initialValues={{ remember: true }}
//         onFinish={onFinish}
//         xs={{ span: 5, offset: 1 }}
//         lg={{ span: 6, offset: 2 }}
//       >
//         <Form.Item
//           name={"name"}
//           rules={[
//             {
//               required: true,
//               message: "Please Input your Username"
//             }
//           ]}
//         >
//           <Input
//             prefix={
//               <UserOutlined
//                 className={"site-form-item-icon"}
//                 placeholder={"Username"}
//               />
//             }
//           />
//         </Form.Item>
//         <Form.Item
//           name={"email"}
//           rules={[
//             {
//               type: "email",
//               required: true,
//               message: "Please Input your Email"
//             }
//           ]}
//         >
//           <Input
//             prefix={
//               <MailOutlined
//                 className={"site-form-item-icon"}
//                 placeholder={"Email"}
//               />
//             }
//           />
//         </Form.Item>
//         <Form.Item
//           name={"password"}
//           rules={[
//             {
//               required: true,
//               messaeg: "please input your Password"
//             }
//           ]}
//         >
//           <Input
//             prefix={<LockOutlined className={"site-form-item-icon"} />}
//             type={"password"}
//             placeholder={"Password"}
//           />
//         </Form.Item>
//         <Form.Item>
//           <Button
//             type={"primary"}
//             htmlType={"submit"}
//             className={"login-form-button"}
//           >
//             register now!
//           </Button>
//           Or <Link to={"/signin"}>Log in</Link>
//         </Form.Item>
//       </Form>
//       <Route>{isSuccess ? <Redirect to="/signin" /> : ""}</Route>
//     </>
//   );
// };

// export default Signup;
