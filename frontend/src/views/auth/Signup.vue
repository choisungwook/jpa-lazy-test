<template>
  <v-container fill-height>
    <v-row justify="center" align="center">
      <v-form ref="form" v-model="valid" lazy-validation>
        <v-text-field v-model="nickname" label="닉네임" required></v-text-field>

        <v-text-field
          v-model="password"
          label="비밀번호"
          required
        ></v-text-field>

        <v-text-field
          v-model="password_confirm"
          label="비밀번호확인"
          required
        ></v-text-field>

        <v-btn color="success" class="mr-4" @click="validate"> 회원가입 </v-btn>

        <v-btn
          color="error"
          class="mr-4"
          @click="$router.push({ name: 'home' })"
        >
          홈페이지로 돌아가기
        </v-btn>
      </v-form>
    </v-row>
  </v-container>
</template>

<script>
export default {
  data: () => ({
    nickname: "",
    password: "",
    password_confirm: "",
  }),
};
</script>
