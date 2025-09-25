<template>
  <div class="login-container">
    <el-card class="login-card">
      <h2 class="login-title">学生成绩管理系统</h2>
      <el-form
          ref="loginFormRef"
          :model="loginForm"
          :rules="loginRules"
          class="login-form"
          @keyup.enter="handleLogin"
      >
        <el-form-item prop="username">
          <el-input
              v-model="loginForm.username"
              placeholder="请输入用户名"
              prefix-icon="User"
          ></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input
              v-model="loginForm.password"
              type="password"
              placeholder="请输入密码"
              show-password
              prefix-icon="Lock"
          ></el-input>
        </el-form-item>
        <el-form-item>
          <el-button
              type="primary"
              style="width: 100%;"
              :loading="loading"
              @click.prevent="handleLogin"
          >
            登 录
          </el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { login } from '@/api/user'; // 引入我们刚刚创建的 API 函数
import { ElMessage } from 'element-plus';

const router = useRouter();

const loginFormRef = ref(null);
const loading = ref(false);

const loginForm = ref({
  username: '',
  password: ''
});

const loginRules = {
  username: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
  password: [{ required: true, message: '请输入密码', trigger: 'blur' }]
};

const handleLogin = () => {
  loginFormRef.value.validate(async (valid) => {
    if (valid) {
      loading.value = true;
      try {
        const res = await login(loginForm.value);
        if (res.token) {
          // 登录成功
          localStorage.setItem('token', res.token); // 存储 token
          // 你也可以在这里将用户信息存入 Vuex
          // store.commit('setUser', userInfo);
          ElMessage.success('登录成功！');
          await router.push('/'); // 跳转到主页
        } else {
          ElMessage.error(res.message || '登录失败');
        }
      } catch (error) {
        console.error('登录请求失败', error);
        ElMessage.error('登录失败，请检查网络或联系管理员');
      } finally {
        loading.value = false;
      }
    } else {
      console.log('表单验证失败');
      return false;
    }
  });
};
</script>

<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #f0f2f5;
}

.login-card {
  width: 400px;
}

.login-title {
  text-align: center;
  margin-bottom: 20px;
}
</style>