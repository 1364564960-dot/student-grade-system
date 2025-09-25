<template>
  <el-container class="layout-container">
    <el-aside width="200px" class="aside">
      <div class="sidebar-header">
        <h3>学生成绩系统</h3>
      </div>
      <el-menu
          :default-active="$route.path"
          class="el-menu-vertical-demo"
          router
      >
        <el-menu-item index="/">
          <el-icon><house /></el-icon>
          <span>首页</span>
        </el-menu-item>
        <el-menu-item index="/about">
          <el-icon><info-filled /></el-icon>
          <span>关于</span>
        </el-menu-item>
      </el-menu>
    </el-aside>
    <el-container>
      <el-header class="header">
        <div>欢迎您！</div>
        <el-button type="danger" @click="handleLogout">退出登录</el-button>
      </el-header>
      <el-main class="main-content">
        <router-view />
      </el-main>
    </el-container>
  </el-container>
</template>

<script setup>
import { House, InfoFilled } from '@element-plus/icons-vue';
import { useRouter } from 'vue-router';
import { ElMessage } from 'element-plus';

const router = useRouter();

const handleLogout = () => {
  // 1. 清除本地存储的 token
  localStorage.removeItem('token');

  // 2. 提示用户退出成功
  ElMessage.success('您已成功退出登录');

  // 3. 跳转到登录页面
  router.push('/login');
};
</script>

<style scoped>
.layout-container {
  height: 100vh;
}
.aside {
  background-color: #545c64;
  color: white;
}
.sidebar-header {
  padding: 20px;
  text-align: center;
  background-color: #434a50;
}
.el-menu-vertical-demo {
  border-right: none;
  background-color: #545c64;
}
.el-menu-item {
  color: #fff;
}
.el-menu-item:hover, .el-menu-item.is-active {
  background-color: #434a50;
}
.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: #fff;
  border-bottom: 1px solid #dcdfe6;
}
.main-content {
  background-color: #f4f4f5;
  padding: 20px;
}
</style>