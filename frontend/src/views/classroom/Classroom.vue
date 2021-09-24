<template>
  <v-container fluid>
    <v-btn class="blue white" elevation="2" outlined x-large @click="get_all">
      반 전체조회
    </v-btn>
    <v-dialog v-model="dialog">
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          class="blue white"
          elevation="2"
          outlined
          x-large
          v-bind="attrs"
          v-on="on"
          >반 추가</v-btn
        >
      </template>

      <v-card>
        <v-card-title>
          <span class="text-h5">반 추가 양식</span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-text-field
                v-model="school_name"
                label="학교 이름"
                hint="학교 이름"
              ></v-text-field>
            </v-row>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" text @click="create"> 생성 </v-btn>
          <v-btn color="blue darken-1" text @click="dialog = false">
            창 닫기
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>

    <v-simple-table>
      <template v-slot:default>
        <thead>
          <tr>
            <th class="text-left">학교 이름</th>
            <th class="text-left">반 이름</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="item in classrooms" :key="item.classroom_name">
            <td>{{ item.school_name }}</td>
            <td>{{ item.classroom_name }}</td>
          </tr>
        </tbody>
      </template>
    </v-simple-table>
  </v-container>
</template>

<script>
import * as classroom_api from "@/api/classroom";
// import axios from "axios";

export default {
  name: "Classroom",

  data: () => ({
    dialog: false,
    school_name: "",
    classrooms: [],
  }),

  methods: {
    create() {
      classroom_api
        .create_classroom(this.school_name)
        .then(() => {
          this.get_all();
        })
        .catch((error) => {
          console.log(error);
        })
        .finally(() => {
          this.dialog = false;
        });
    },
    get_all() {
      classroom_api
        .get_all()
        .then((response) => {
          const classroom_datas = response.data.classrooms;
          for (let classroom_data of classroom_datas){
            if (classroom_data.school_name === null)
              classroom_data.school_name = "없음";
          }
          this.classrooms = classroom_datas;
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
</script>
