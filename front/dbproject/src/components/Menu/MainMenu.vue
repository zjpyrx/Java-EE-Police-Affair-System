<template>
  <el-container
    class="home-container"
    :style="{
      height: containerHeight + 'px',
      backgroundSize: '100% ' + containerHeight + 'px',
    }"
    @wheel="handleScroll"
  >
    <!-- 头部 -->
    <el-header
      style="height: 70px; padding: 0; text-align: justify; hyphens: auto"
    >
      <div class="headpic">
        <div class="header1">
          <img class="logo" src="../../assets/image-2.png" />
          <div class="title">&nbsp;&nbsp;警务处理系统</div>
          <div class="button-container">
            <el-button type="primary" @click="handleSelfInfoClick">
              个人信息
            </el-button>
            <el-button type="primary" @click="Exit">
              退出账号
            </el-button>
          </div>
        </div>
      </div>
    </el-header>

    <!-- 页面主体 -->
    <el-container>
      <!-- 导航栏 -->
      <el-aside width="200px">
        <el-menu
          router
          class="el-menu-vertical-demo"
          default-active="/mainMenu/MainPage"
          background-color="transparent"
          text-color="#ffffff"
          active-text-color="#ffd04b"
        >
          <el-menu-item
            v-if="myAuthority >= 0"
            index="/mainMenu/MainPage"
            @click="scrollToTop"
          >
            <img
              class="nav-icon-for-menu-item"
              src="../../assets/logos/MainPage.png"
              alt="Icon"
            />
            &nbsp;主&nbsp;页
          </el-menu-item>

          <el-sub-menu index="1-1">
            <!-- 警员管理 -->
            <template #title>
              <span>
                <img
                  class="nav-icon-for-sub-menu"
                  src="../../assets/logos/PoliceManagement.png"
                  alt="Icon"
                />
                警员管理</span
              >
            </template>
            <el-menu-item
              v-if="myAuthority >= 1"
              index="/mainMenu/Register"
              @click="scrollToTop"
            >
              <img
                class="nav-icon-for-menu-item"
                src="../../assets/logos/Register.png"
                alt="Icon"
              />
              注&nbsp;册
            </el-menu-item>
            <el-menu-item
              v-if="myAuthority >= 0"
              index="/mainMenu/PoliceOfficerInfoManagement"
              @click="scrollToTop"
            >
              <img
                class="nav-icon-for-menu-item"
                src="../../assets/logos/PoliceOfficerInfoManagement.png"
                alt="Icon"
              />
              警员信息管理
            </el-menu-item>
            <el-menu-item
              v-if="myAuthority >= 1"
              index="/mainMenu/VideoManagement"
              @click="scrollToTop"
            >
              <img
                class="nav-icon-for-menu-item"
                src="../../assets/logos/VideoManagement.png"
                alt="Icon"
              />
              执法录像管理
            </el-menu-item>
          </el-sub-menu>

          <!-- 案件管理 -->
          <el-sub-menu v-if="myAuthority >= 1" index="1-2">
            <template #title>
              <span>
                <img
                  class="nav-icon-for-sub-menu"
                  src="../../assets/logos/CaseManagement.png"
                  alt="Icon"
                />
                案件管理</span
              >
            </template>
            <el-menu-item
              index="/mainMenu/CaseClassificationManagement"
              @click="scrollToTop"
            >
              <img
                class="nav-icon-for-menu-item"
                src="../../assets/logos/CaseClassificationManagement.png"
                alt="Icon"
              />
              案件分类与管理
            </el-menu-item>
          </el-sub-menu>

          <!-- 城区和居民管理 -->

          <el-sub-menu v-if="myAuthority >= 1" index="1-4">
            <template #title>
              <img
                class="nav-icon-for-menu-item"
                src="../../assets/logos/UrbanResidentmanagement.png"
                alt="Icon"
              />
              <span>城区和居民管理</span>
            </template>

            <el-menu-item
              index="/mainMenu/CitizenInfoManagement"
              @click="scrollToTop"
            >
              <img
                class="nav-icon-for-menu-item"
                src="../../assets/logos/CitizenInfoManagement.png"
                alt="Icon"
              />
              公民户籍管理
            </el-menu-item>
            <el-menu-item
              index="/mainMenu/UrbanZoningManagement"
              @click="scrollToTop"
            >
              <img
                class="nav-icon-for-menu-item"
                src="../../assets/logos/UrbanZoningManagement.png"
                alt="Icon"
              />
              城市地区数据统计
            </el-menu-item>
            <el-menu-item index="/mainMenu/FamilybgCheck" @click="scrollToTop">
              <img
                class="nav-icon-for-menu-item"
                src="../../assets/logos/FamilybgCheck.png"
                alt="Icon"
              />
              家族背景调查
            </el-menu-item>
          </el-sub-menu>

          
          <el-menu-item
            v-if="myAuthority >= 1"
            index="/mainMenu/CaseInvestigation"
            @click="scrollToTop"
          >
            <img
              class="nav-icon-for-menu-item"
              src="../../assets/logos/CaseInvestigation.png"
              alt="Icon"
            />
            案件办理
          </el-menu-item>
          <el-menu-item
            v-if="myAuthority >= 1"
            index="/mainMenu/SecretChat"
            @click="scrollToTop"
          >
            <img
              class="nav-icon-for-menu-item"
              src="../../assets/logos/SecretChat.jpg"
              alt="Icon"
            />
            行动交流
          </el-menu-item>
      
        </el-menu>
      </el-aside>

      <!-- 页面主体 -->
      <el-main class="background">
        <!-- 无登录权限提示框 -->
        <el-dialog v-model="loginErr" @close="handleClose" title="提示">没有登录权限，请重新登录！
          <el-button style="display:block;margin-top:20px;" @click="handleClose">确认</el-button>
        </el-dialog>
        
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>

  <!-- 账户信息展示 -->
  <el-drawer v-model="drawer2" :direction="direction">
    <template #header> </template>
    <template #default>
      <div>
        <img :src="myPic !== 'noPic' ? myPicDataURL : mynoPic" alt="My Image" style="width: 150px; height: 200px;">
      </div>
   
      <div class="myInfo">
        <span>警号：{{ myPoliceNumber }}</span>
      </div>
   
      <div class="myInfo">
        <span>姓名：{{ myName }}</span>
      </div>
    
      <div class="myInfo">
        <span>性别：{{ mySex }}</span>
      </div>
      
      <div class="myInfo">
        <span>出生日期：{{ myBirthday }}</span>
      </div>
     
      <div class="myInfo">
        <span>民族：{{ myPeoples }}</span>
      </div>
      
      <div class="myInfo">
        <span>联系电话：{{ myPhone }}</span>
      </div>
     
      <div class="myInfo">
        <span>状态：{{ myStatus }}</span>
      </div>
      
      <div class="myInfo">
        <span>职务：{{ myPosition }}</span>
      </div>
      
      <div class="myInfo">
        <span>权限等级：{{ myAuthority }}</span>
      </div>
    </template>
    <template #footer> </template>
  </el-drawer>
</template>

<script lang="js" setup>
import { ref, computed } from "vue";
import axios from "axios";

const containerHeight= ref(1500) // 初始化容器高度
const minHeight= ref(1500) // 最小高度
const maxScrollHeight= ref(3000) // 最大滚动高度，控制界面的延伸
// const mytitle= "注册"

const drawer2 = ref(false);
const direction = ref("rtl");
const username = ref("1234567");
const password = ref("44554");

const myPoliceNumber = localStorage.getItem('policeNumber');
const myAuthority = localStorage.getItem('authority');
var loginErr = localStorage.getItem('loginErr') === 'true';
const err= ref("警员不存在！");
const policemenInfo=ref([]);

const myName=ref("");
const mySex=ref("");
const myBirthday=ref("");
const myPeoples =ref("");
const myPhone=ref("");
const myStatus=ref("");
const myPosition=ref("");
const myPic=ref("noPic");
const myPicDataURL = computed(() => {
  return `data:image/png;base64,${myPic.value}`;
});
const mynoPic='data:image/png;base64,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';

function handleSelfInfoClick(event)
{
      drawer2.value = true;
      axios.post(`http://localhost:7078/api/policemenInfo?`, {
          policeNumber: myPoliceNumber
        },{
  headers: {
    'Content-Type': 'application/x-www-form-urlencoded'
  }
})
        .then((res) => {
          const policemenInfo = res.data;

    if (policemenInfo.gender === "F") {
      policemenInfo.gender = "女";
    } else if (policemenInfo.gender === "M") {
      policemenInfo.gender = "男";
    }

    myName.value = policemenInfo.name;
    mySex.value = policemenInfo.gender;
    myBirthday.value = policemenInfo.birthday;
    myPeoples.value = policemenInfo.nation;
    myPhone.value = policemenInfo.phone;
    myStatus.value = policemenInfo.status;
    myPosition.value = policemenInfo.position;
    
    //myPic.value=policemenInfo.pic;
    if (policemenInfo.pic === null) {
      myPic.value = "noPic";
    } else {
      myPic.value = policemenInfo.pic;
    }
    console.log(myPic.value)
        })
        .catch((err) => {
          console.log(err);
        });
}
function handleScroll(event) {
      if (event.deltaY > 0) {
        // 向下滚动
        if (containerHeight.value < maxScrollHeight.value) {
          containerHeight.value += 50;
        }
      } else {
        // 向上滚动
        if (containerHeight.value > minHeight.value) {
          containerHeight.value -= 50;
        }
      }
}
function scrollToTop() {
  containerHeight.value = 1500;
  window.scrollTo({
  top: 0,
  behavior: 'smooth'
  })
}
function Exit() {
  localStorage.setItem('loginErr','true');
  localStorage.setItem('policeNumber', '');
  localStorage.setItem('authority', 0);
  window.location.href="http://localhost:8080/";
}
function handleClose() {
  window.location.href="http://localhost:8080/";
  loginErr = false;
}
</script>

<style scoped>
.myInfo
{
  margin-left:15%;
  margin-top:10%;
}
.nav-icon-for-sub-menu {
  max-width: 30%;
  max-height: 30%;
  vertical-align: middle;
  margin-right: 5px; /* Optional: Add some spacing between icon and text */
}
.nav-icon-for-menu-item {
  max-width: 60%;
  max-height: 60%;
  vertical-align: middle;
  margin-right: 5px; /* Optional: Add some spacing between icon and text */
}

.home-container {
  width: 100%;
  overflow: hidden;
  background-color: #f0f0f0;
  transition: height 0.3s ease; /* 添加过渡效果 */
}

.el-menu-item.is-active {
  background-color: #1c80de !important;
  /* color: red; 设置选中时的字体颜色 */
}

.menu-link {
  text-decoration: none;
  color: #ffffff;
}

.background {
  background-image: url("../../assets/bg.jpg"); /* 替换为你的背景图路径 */
  background-size: cover; /* 根据需要调整背景图的尺寸适应方式 */
  background-attachment: fixed;
  background-position: center;
  background-repeat: no-repeat;
  /* 其他背景相关样式，例如背景颜色、背景重复等，可根据需要添加 */
}

.el-aside {
  background-color: #1620a9;
  background-image: url("../../assets/sdtest.jpg"); /* 替换为你的背景图路径 */

  background-attachment: fixed;
  background-repeat: no-repeat;
}
.headpic {
  background: #ffffff;
  width: 100%;
  position: relative;
  overflow: hidden;
}

.header1 {
  background: #1f2cdf;
  background-image: url("../../assets/hdtest.jpg"); /* 替换为你的背景图路径 */
  background-size: contain;
  background-position: right top; /* 背景图靠左上角 */
  background-repeat: no-repeat;
  width: 100%;
  height: 70px;
  position: relative;

  left: 0;
  display: flex;
  align-items: center;
  padding-left: 20px;
}

.button-container {
  margin-left: auto;
  margin-right: 20px;
}

.title {
  color: #ffffff;
  text-align: left;
  font: 400 36px "Inter", sans-serif;
  display: inline-block;
}
</style>
