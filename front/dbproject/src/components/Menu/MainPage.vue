<template>
  <div style="width:60%;display:inline-block;margin-left:20px">
      <div style="height:200px;font: 400 25px Inter, sans-serif;vertical-align: top;">
        <div><br>欢迎回来，{{ myPoliceNumber }}<br></div>
        <div style="font: 400 20px Inter, sans-serif;"><br>开始愉快的一天</div>
      </div>
     
    </div>
    <div style="width:48%;min-height: 100px;margin-left:1%;display:inline-block;background-color: rgba(255, 255, 255, 0.733);vertical-align: top;">
      <div class="syslogtitle">
        <div class="syslog">常用功能</div>
        <button style="height:80%;position: absolute;width:100px;right:10px;background-color:#c1b598;color:white;cursor: pointer;border: none;" @click="showMoreOptions">更多功能</button>
      </div>
      

      <div style="min-width:100%">
        <el-button class="ssqfuncbutton" v-for="option in selectedOptions" :key="option" :label="option" @click="goTo(option)">{{ option }}</el-button>
      </div>

     
    </div>
    <more-options-dialog @options-selected="handleOptionsSelected" ref="moreOptionsDialog"></more-options-dialog>
    <el-calendar class="calendar"></el-calendar> 
  </template>
  
  <script>
  import axios from "axios";
  import { ref } from "vue";
  import MoreOptionsDialog from '@/components/Menu/MoreOptionsDialog.vue';
  
  export default {
    setup() {
      const containerHeight = ref(1500); // 初始化容器高度
      const minHeight= ref(1500);// 最小高度
      const maxScrollHeight= ref(3000)// 最大滚动高度，控制界面的延伸

      const handleScroll = (event) => {
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
      };
  
      return {
        handleScroll,
        containerHeight,
        minHeight,
        maxScrollHeight,
      };
    },
    data() {
      return {
      selectedOptions: ['警员信息管理'],
      myAuthority: localStorage.getItem('authority'),
      myPoliceNumber: localStorage.getItem('policeNumber'),
      dialogVisible: false,

      auditInfo:[],
      };
    },
    components: {
      MoreOptionsDialog
    },
    methods: {
      goTo(funcName) {
        if (funcName == '注册') {
          this.$router.push('/mainMenu/Register');
        } else if (funcName == '警员信息管理') {
          this.$router.push('/mainMenu/PoliceOfficerInfoManagement');
        } else if (funcName == '执法录像管理') {
          this.$router.push('/mainMenu/VideoManagement');
        } else if (funcName == '案件分类与管理') {
          this.$router.push('/mainMenu/CaseClassificationManagement');
        } else if (funcName == '犯罪数据统计') {
          this.$router.push('/mainMenu/CrimeDataStatistics');
        } else if (funcName == '公民户籍管理') {
          this.$router.push('/mainMenu/CitizenInfoManagement');
        } else if (funcName == '城市地区管理') {
          this.$router.push('/mainMenu/UrbanZoningManagement');
        } else if (funcName == '家族背景调查') {
          this.$router.push('/mainMenu/FamilybgCheck');
        } else if (funcName == '案件办理') {
          this.$router.push('/mainMenu/CaseInvestigation');
        } 
      },
      showMoreOptions() {
        this.$refs.moreOptionsDialog.showMoreOptions(this.selectedOptions, this.myAuthority);
      },
      handleOptionsSelected(selectedOptions) {
        // 处理选项确认后的逻辑
        // 更新按钮的显示状态等
        this.selectedOptions = selectedOptions;
        localStorage.setItem('selectedOptions_' + this.myPoliceNumber, JSON.stringify(selectedOptions));
      },
  
      handleMouseMove(event) {
        const x = event.pageX - event.target.offsetLeft;
        const y = event.pageY - event.target.offsetTop; 
        event.target.style.setProperty('--x', `${x}px`);
        event.target.style.setProperty('--y', `${y}px`);
      },
    },
    created() {
      const myPoliceNumber = localStorage.getItem('policeNumber');
      const savedOptions = localStorage.getItem('selectedOptions_' + myPoliceNumber);
      if (savedOptions) {
      console.log(savedOptions)
      this.selectedOptions = JSON.parse(savedOptions);
      }
  
      
    
    },
  };
  </script>
  
  <style scoped>
  .syslogtitle {
        width: 100%;
        height: 30px;
        position: relative;
        overflow: hidden; 
        display: flex;
        align-items: center;
        padding-left: 20px;
        background: #d7caaa;
      }
      .syslog {
        color: #ffffff;
        text-align: left;
        display:relative;
        left:80px;
        width: 100%;
        font: 400 20px "Inter", sans-serif;
        padding-left:0px;
      }
      .ssqfuncbutton{
        margin-top:5px;
        margin-left:28px;
        width:100px;
      }
      .calendar 
      {
        display: float;
        position: absolute;
        top:15vh; /* 顶部距离为0，即页面上边缘 */
        right: 1vw; /* 右侧距离为0，即页面右边缘 */
        overflow: auto;
        width: 31%;
      }
  </style>
  