import React, { useEffect, useCallback } from "react";
import styled from "styled-components";
import xregister from "../images/xregister.svg";
import camera from "../images/camera.svg";
import { useDispatch, useSelector } from "react-redux";
import { actionCreators as productActions } from "../redux/modules/products";
import { history } from "../redux/configureStore";

const AddProduct = () => {
  const dispatch = useDispatch();
  const is_login = useSelector((state) => state.user.is_login);

  const [image, setImage] = React.useState("");
  const [title, setTitle] = React.useState("");
  const [location, setLocation] = React.useState("");
  const [status, setStatus] = React.useState("");
  const [tradable, setTradable] = React.useState("");
  const [price, setPrice] = React.useState("");
  const [deliver, setDeliver] = React.useState("");
  const [description, setDescription] = React.useState("");
  const [keyword, setKeyword] = React.useState("");
  const [num, setNum] = React.useState("");

  const onChangeimage = useCallback((e) => setImage(e.target.value), []);
  const onChangetitle = useCallback((e) => setTitle(e.target.value), []);
  const onChangelocation = useCallback((e) => setLocation(e.target.value), []);
  const onChangestatus = useCallback((e) => setStatus(e.target.checked), []);
  const onChangetradable = useCallback(
    (e) => setTradable(e.target.checked),
    []
  );
  const onChangeprice = useCallback((e) => setPrice(e.target.value), []);
  const onChangedeliver = useCallback((e) => setDeliver(e.target.checked), []);
  const onChangedescription = useCallback(
    (e) => setDescription(e.target.value),
    []
  );
  const onChangekeyword = useCallback((e) => setKeyword(e.target.value), []);
  const onChangenum = useCallback((e) => setNum(e.target.value), []);

  const siteAddProduct = () => {
    dispatch(
      productActions.addProductsAPI(
        title,
        location,
        status,
        tradable,
        price,
        deliver,
        description,
        keyword,
        num
      )
    );
  };

  return (
    <Container>
      <AddContainer>
        <BasicInfo>
          <h2>기본정보</h2>
          <span>*필수항목</span>
        </BasicInfo>
        <ProductImage>
          <ImageLeft>
            상품이미지 <span className="star">*</span>{" "}
            <span className="num">(0/12)</span>
          </ImageLeft>
          <ImageRight>
            <Imageupload>
              <div>
                <img
                  style={{ width: "25px", height: "25px" }}
                  src={camera}
                  alt="카메라티콘"
                />
                <span>이미지 등록</span>
              </div>
              <input type="file" onChange={onChangeimage} value={image} />
            </Imageupload>
            <ImageText>
              <p>
                <b>* 상품 이미지는 640x640에 최적화 되어 있습니다.</b>
              </p>
              <p>- 이미지는 상품등록 시 정사각형으로 짤려서 등록됩니다.</p>
              <p>- 이미지를 클릭 할 경우 원본이미지를 확인할 수 있습니다.</p>
              <p>- 이미지를 클릭 후 이동하여 등록순서를 변경할 수 있습니다.</p>
              <p>
                - 큰 이미지일경우 이미지가 깨지는 경우가 발생할 수 있습니다.
              </p>
              <p>
                최대 지원 사이즈인 640 X 640 으로 리사이즈 해서 올려주세요.(개당
                이미지 최대 10M)
              </p>
            </ImageText>
          </ImageRight>
        </ProductImage>
        <Subject>
          <SubjectRight>
            제목<span>*</span>
          </SubjectRight>
          <RightSide>
            <SubjectLeft>
              <Inputbox>
                <Input
                  placeholder="상품 제목을 입력해주세요."
                  value={title}
                  onChange={onChangetitle}
                />
                <a
                  target="_blank"
                  href="https://m.bunjang.co.kr/customer/faq/2?id=220"
                >
                  거래금지품목
                </a>
              </Inputbox>
              <div style={{ marginLeft: "1.5rem" }}>0/40</div>
            </SubjectLeft>
            <SubjectBottom>
              <img
                style={{ marginRight: "4px" }}
                src={xregister}
                alt="금지티콘"
              />
              상품명을 2자 이상 입력해주세요
            </SubjectBottom>
          </RightSide>
        </Subject>
        <SellingLocation>
          <LocationLeft>
            거래지역<span>*</span>
          </LocationLeft>
          <LocationRight>
            <input
              placeholder="선호 거래지역을 적어주세요."
              type="text"
              onChange={onChangelocation}
              value={location}
            />
          </LocationRight>
        </SellingLocation>
        <Status>
          <LeftList>
            상태<span>*</span>
          </LeftList>
          <StatusRight>
            <OnlyFlex>
              <input
                type="checkbox"
                id="used"
                name="status"
                value="중고상품"
                onChange={onChangestatus}
                value={status}
              />
              <label htmlFor="used">중고상품</label>
            </OnlyFlex>
          </StatusRight>
        </Status>
        <Status>
          <LeftList>
            교환<span>*</span>
          </LeftList>
          <StatusRight>
            <OnlyFlex>
              <input
                type="checkbox"
                id="tradapossible"
                name="trade"
                value="교환불가"
                onChange={onChangetradable}
                value={tradable}
              />
              <label htmlFor="used">교환불가</label>
            </OnlyFlex>
          </StatusRight>
        </Status>
        <Pricearea>
          <LeftList>
            가격<span>*</span>
          </LeftList>
          <PriceRight>
            <PriceLine>
              <input
                type="number"
                placeholder="숫자만 입력해주세요."
                onChange={onChangeprice}
                value={price}
              />
              원
            </PriceLine>
            <div style={{ display: "flex", marginTop: "0px" }}>
              <OnlyFlex>
                <CheckboxInput
                  type="checkbox"
                  id="deliverfee"
                  name="pricearea"
                  value="배송비 포함"
                  onChange={onChangedeliver}
                  value={deliver}
                />
                <label htmlFor="used">배송비 포함</label>
              </OnlyFlex>
            </div>
          </PriceRight>
        </Pricearea>
        <Desc>
          <LeftList>설명</LeftList>
          <DescRight>
            <textarea
              placeholder="상품 설명을 입력해주세요."
              rows="6"
              onChange={onChangedescription}
              value={description}
            />
            <DescBottom>
              <span>
                혹시{" "}
                <a
                  target="_blank"
                  href="https://m.bunjang.co.kr/customer/notice?id=607"
                >
                  카카오톡 ID
                </a>
                를 적으셨나요?
              </span>
              <div>0/2000</div>
            </DescBottom>
          </DescRight>
        </Desc>
        <RelatedTag>
          <LeftList>연관태그</LeftList>
          <TagRight>
            <div>
              <input
                type="text"
                placeholder="연관태그를 입력해주세요.(최대 5개)"
                onChange={onChangekeyword}
                value={keyword}
              />
            </div>
            <div style={{ marginTop: "20px" }}>
              <p>
                - 태그는 띄어쓰기로 구분되며 최대 9자까지 입력할 수 있습니다.
              </p>
              <p>
                - 태그는 검색의 부가정보로 사용 되지만, 검색 결과 노출을
                보장하지는 않습니다.
              </p>
              <p>- 검색 광고는 태그정보를 기준으로 노출됩니다.</p>
              <p>
                - 상품과 직접 관련이 없는 다른 상품명, 브랜드, 스팸성 키워드
                등을 입력하면 노출이 중단되거나 상품이 삭제될 수 있습니다.
              </p>
            </div>
          </TagRight>
        </RelatedTag>
        <Count>
          <LeftList>수량</LeftList>
          <CountRight>
            <input
              type="number"
              defaultValue="1"
              onChange={onChangenum}
              value={num}
            />
            개
          </CountRight>
        </Count>
      </AddContainer>
      <Btnarea>
        <button onClick={siteAddProduct}>등록하기</button>
      </Btnarea>
    </Container>
  );
};

const Container = styled.div`
  width: 100%;
`;

const AddContainer = styled.div`
  width: 1024px;
  margin: 0 auto;
`;

const BasicInfo = styled.div`
  display: flex;
  align-items: center;
  height: 100px;
  border-bottom: 1px solid rgb(33, 33, 33);
  span {
    color: red;
    margin-left: 20px;
  }
`;
const ProductImage = styled.div`
  display: flex;
  height: 411px;
  padding: 32px 0px 0px;
  border-bottom: 1px solid #dcdbe4;
`;

const ImageLeft = styled.div`
  width: 10.5rem;
  font-size: 18px;
  .star {
    color: red;
  }
  .num {
    color: rgb(155, 153, 169);
    margin-left: 2px;
    font-size: 12px;
  }
`;

const ImageRight = styled.div`
  width: 856px;
  height: 346px;
  align-items: center;
  vertical-align: middle;
  padding: 20px 0px;
  box-sizing: border-box;
`;

const Imageupload = styled.div`
  border: 1px solid rgb(230, 229, 239);
  background: rgb(250, 250, 253);
  width: 202px;
  height: 202px;
  input {
    text-align: center;
    width: 202px;
    height: 202px;
    position: absolute;
    top: 340px;
    left: 250px;
    opacity: 0;
    cursor: pointer;
    font-size: 0px;
  }
  div {
    display: flex;
    align-items: center;
    justify-content: center;
    position: relative;
    top: 85px;
    left: -5px;
  }
  span {
    margin-left: 4px;
    color: #c3c2cc;
  }
`;

const ImageText = styled.div`
  color: rgb(74, 164, 255);
  margin-top: 30px;
  font-size: 12px;
  p {
    line-height: 0.4;
  }
`;

const Subject = styled.div`
  display: flex;
  width: 100%;
  border-bottom: 1px solid rgb(220, 219, 228);
  padding: 32px 0px;
  vertical-align: baseline;
`;

const SubjectLeft = styled.div`
  display: flex;
  align-items: center;
  width: 144px;
`;
const SubjectBottom = styled.div`
  display: flex;
  color: rgb(245, 126, 0);
  font-size: 14px;
  margin-top: 5px;
`;

const Inputbox = styled.div`
  position: relative;
  display: flex;
  align-items: center;
  width: 802px;
  a {
    position: absolute;
    right: 1rem;
    font-size: 1rem;
    color: rgb(155, 153, 169);
    text-decoration: underline;
  }
`;

const Input = styled.input`
  width: 801px;
  height: 3rem;
  padding: 0px 1rem;
  border: 1px solid rgb(195, 194, 204);
  box-sizing: border-box;
  align-items: center;
`;

const SubjectRight = styled.div`
  width: 144px;
  font-size: 18px;
  span {
    color: red;
    font-size: 14px;
    margin-left: 1px;
  }
`;

const RightSide = styled.div`
  width: 856px;
`;

const SellingLocation = styled.div`
  display: flex;
  width: 100%;
  border-bottom: 1px solid rgb(220, 219, 228);
  padding: 30px 0px 30px;
  vertical-align: baseline;
`;

const LocationLeft = styled.div`
  width: 10.5rem;
  align-items: center;
  font-size: 18px;
  box-sizing: border-box;
  span {
    color: red;
    font-size: 14px;
    margin-left: 3px;
  }
`;

const LocationRight = styled.div`
  width: 100%;
  input {
    width: 856px;
    background: rgb(244, 244, 250);
    border: 1px solid rgb(195, 194, 204);
    height: 3rem;
    padding: 0px 1rem;
    box-sizing: border-box;
  }
`;
const Status = styled.div`
  display: flex;
  align-items: center;
  width: 100%;
  border-bottom: 1px solid rgb(220, 219, 228);
  padding: 30px 0px 30px;
  vertical-align: baseline;
`;

const LeftList = styled.div`
  width: 10.5rem;
  align-items: center;
  font-size: 18px;
  box-sizing: border-box;
  span {
    color: red;
    font-size: 14px;
    margin-left: 3px;
  }
`;

const StatusRight = styled.div`
  display: flex;
  input {
    padding: 0.5rem;
    height: 1rem;
    width: 1rem;
  }
  label {
    display: flex;
    font-size: 16px;
    align-items: center;
    position: relative;
    margin-right: 8px;
    margin-left: 4px;
  }
`;
const OnlyFlex = styled.div`
  display: flex;
  align-items: center;
`;

const Pricearea = styled.div`
  display: flex;
  align-items: center;
  width: 100%;
  border-bottom: 1px solid rgb(220, 219, 228);
  padding: 30px 0px 30px;
  vertical-align: baseline;
`;

const PriceRight = styled.div`
  width: 856px;
  display: inline-block;
  align-items: center;
  vertical-align: middle;
`;

const CheckboxInput = styled.input`
  width: 16px;
  height: 16px;
  height: 3rem;
  padding: 0px 1rem;
  border: 1px solid rgb(195, 194, 204);
  margin-right: 4px;
`;

const PriceLine = styled.div`
  margin-bottom: 20px;
  input {
    height: 3rem;
    padding: 0px 1rem;
    border: 1px solid rgb(195, 194, 204);
    margin-right: 1rem;
    width: 240px;
  }
  input::-webkit-inner-spin-button {
    -webkit-appearance: none;
    margin: 0;
  }
`;

const Desc = styled.div`
  display: flex;
  align-items: center;
  width: 100%;
  border-bottom: 1px solid rgb(220, 219, 228);
  padding: 30px 0px 30px;
  vertical-align: baseline;
`;

const DescRight = styled.div`
  width: 856px;
  textarea {
    width: 96%;
    border: 1px solid rgb(195, 194, 204);
    padding: 1rem;
    resize: none;
    line-height: 1.35;
    margin-bottom: 4px;
  }
`;

const DescBottom = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  span {
    a {
      color: rgb(155, 153, 169);
      text-decoration: underline;
    }
    font-size: 13px;
    color: rgb(155, 153, 169);
  }
`;

const RelatedTag = styled.div`
  display: flex;
  align-items: center;
  width: 100%;
  border-bottom: 1px solid rgb(220, 219, 228);
  padding: 30px 0px 30px;
  vertical-align: baseline;
`;

const TagRight = styled.div`
  width: 856px;
  display: inline-block;
  vertical-align: middle;
  input {
    width: 96%;
    border: 1px solid rgb(195, 194, 204);
    padding: 1rem;
    line-height: 1.35;
    margin-bottom: 4px;
  }
  p {
    margin-top: 1px;
    line-height: 16px;
    color: rgb(136, 136, 136);
    font-size: 16px;
  }
`;

const Count = styled.div`
  display: flex;
  align-items: center;
  width: 100%;
  border-bottom: 1px solid rgb(220, 219, 228);
  padding: 30px 0px 30px;
  vertical-align: baseline;
`;

const CountRight = styled.div`
    width: 856px;
    input {
        height: 3rem;
        padding: 0px 1rem;
        border: 1px solid rgb(195, 194, 204);
        width: 240px;
        margin-right: 1rem;
    }
    input::-webkit-inner-spin-button {
        -webkit-appearance: none;
        margin: 0;
`;

const Btnarea = styled.div`
  width: 100%;
  height: 150px;
  background: rgb(250, 250, 253);
  box-shadow: rgb(234 233 241) 0px -1px 0px 0px;
  padding: 50px 0px 50px;
  box-sizing: border-box;

  button {
    display: absolute;
    left: 45%;
    border: none;
    height: 60px;
    width: 10rem;
    color: rgb(255, 255, 255);
    font-size: 20px;
    font-weight: 700;
    border-radius: 2px;
    background: rgb(255, 80, 88);
    position: relative;
  }
`;

export default AddProduct;
