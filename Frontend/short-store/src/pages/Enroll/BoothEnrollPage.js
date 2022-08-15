import React, { useState, useEffect } from "react";
import { Link, Routes, Route } from "react-router-dom";
import { Input } from "antd";
import { TimePicker, DatePicker, Layout } from "antd";
import { Button } from "antd";
import { Form, AppLayout } from "antd";
import { UserOutlined, ShopOutlined } from "@ant-design/icons";

import axios from "axios";
import styles from "./BoothEnrollPage.module.css";

import {
  AutoComplete,
  Cascader,
  Checkbox,
  Col,
  InputNumber,
  Row,
  Select
} from "antd";
const { Option } = Select;
const { TextArea } = Input;
const residences = [
  {
    value: "패션의류/잡화/뷰티",
    label: "패션의류/잡화/뷰티",
    children: [
      {
        value: "패션의류",
        label: "패션의류",
        children: [
          {
            value: "여성의류",
            label: "여성의류"
          }
        ]
      }
    ]
  },
  {
    value: "컴퓨터/디지털/가전",
    label: "컴퓨터/디지털/가전",
    children: [
      {
        value: "디지털",
        label: "디지털",
        children: [
          {
            value: "모바일/태블릿",
            label: "모바일/태블릿"
          }
        ]
      }
    ]
  }
];
const formItemLayout = {
  labelCol: {
    xs: {
      span: 24
    },
    sm: {
      span: 8
    }
  },
  wrapperCol: {
    xs: {
      span: 24
    },
    sm: {
      span: 16
    }
  }
};
const tailFormItemLayout = {
  wrapperCol: {
    xs: {
      span: 24,
      offset: 0
    },
    sm: {
      span: 16,
      offset: 8
    }
  }
};
const { RangePicker } = DatePicker;

function BoothEnrollPage() {
  const [inputBoothName, setInputBoothName] = useState("");
  const [inputDate, setInputDate] = useState("");
  const [inputDate2, setInputDate2] = useState("");
  const [inputAbout, setInputAbout] = useState("");
  const [inputSeller, setInputSeller] = useState("");
  const [inputProduct, setInputProduct] = useState("");
  const [inputOpen, setInputOpen] = useState("");
  const [inputClose, setInputClose] = useState("");
  const [form] = Form.useForm();

  const onFinish = (values) => {
    console.log("Received values of form: ", values);
  };

  const prefixSelector = (
    <Form.Item name="prefix" noStyle>
      <Select
        style={{
          width: 70
        }}
      >
        <Option value="86">+86</Option>
        <Option value="87">+87</Option>
      </Select>
    </Form.Item>
  );
  const suffixSelector = (
    <Form.Item name="suffix" noStyle>
      <Select
        style={{
          width: 70
        }}
      >
        <Option value="USD">낱개</Option>
        <Option value="CNY">세트</Option>
      </Select>
    </Form.Item>
  );
  const [autoCompleteResult, setAutoCompleteResult] = useState([]);

  const onWebsiteChange = (value) => {
    if (!value) {
      setAutoCompleteResult([]);
    } else {
      setAutoCompleteResult(
        [".com", ".org", ".net"].map((domain) => `${value}${domain}`)
      );
    }
  };

  const websiteOptions = autoCompleteResult.map((website) => ({
    label: website,
    value: website
  }));

  return (
    <div className='area'>
    <Form>
      <div>
        <h2>부스 신청</h2>
        <div>
          <label htmlFor="input_name"></label>
          <Input placeholder="부스 이름" prefix={<ShopOutlined />} />
        </div>
        <div>
          <label htmlFor="input_date">
            부스 기간 : <DatePicker /> ~ <DatePicker />
          </label>
        </div>
        <div>
          <label htmlFor="input_about"></label>
          <Input
            placeholder="판매자 정보(ex: 이름/전화번호)"
            prefix={<UserOutlined />}
            name="input_seller"
            value={inputSeller}
            onChange={handleInputSeller}
          />
        </div>
        <div>
          <label htmlFor="input_about"></label>
          <TextArea
            rows={3}
            placeholder="판매상품목록"
            maxLength={6}
            name="input_seller"
            value={inputProduct}
            onChange={handleInputProduct}
          />
        </div>
        <div>
          <label htmlFor="input_date">운영 시간 : </label>
          <TimePicker.RangePicker />
        </div>
        <div>
          <label htmlFor="input_about"></label>
          <TextArea
            rows={4}
            placeholder="부스 설명"
            maxLength={6}
            onChange={onChange}
          />
        </div>
        <div>
          <Button type="primary onClick={onClickBoothEnroll}">
            부스 목록 보기
          </Button>
          <Button onClick={onClickBoothEnroll}>부스 등록하기</Button>
        </div>
        {/* <Link to="/register">회원가입</Link> */}
      </div>

    </Form>
    </div>
  );
}

export default BoothEnrollPage;
